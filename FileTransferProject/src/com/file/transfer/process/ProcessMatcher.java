package com.file.transfer.process;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import com.file.transfer.common.StringUtils;

public class ProcessMatcher {
	public static Predicate<Map<String, String>> getMacher(String term) {
		validateSyntax(term);
		return compileInternal(term);
	}
	
	private static Predicate<Map<String, String>> compileInternal(String term){
		if(StringUtils.isNullOrEmpty(term)) {
			return map -> true;
		}
		
		if(term.contains(" OR ")) {
			String[] orParts = splitPreservingGroups(term, " OR ");
			List<Predicate<Map<String, String>>> terms = new ArrayList<Predicate<Map<String,String>>>();
			for(String part : orParts) {
				terms.add(compileInternal(part));
			}
			return map -> terms.stream().anyMatch(c -> c.test(map));
		}
		
		String[] andParts = splitPreservingGroups(term, " AND ");
		List<Predicate<Map<String, String>>> terms = new ArrayList<Predicate<Map<String,String>>>();
		for(String part : andParts) {
			terms.add(compileSingleTerm(part.trim()));
		}
		return map -> terms.stream().allMatch(c -> c.test(map));
	}
	
	private static Predicate<Map<String, String>> compileSingleTerm(String term){
		boolean isNegative = term.contains("!=");
		String[] parts = isNegative ? term.split("!=", 2) : term.split("=", 2);
		if(parts.length != 2) {
			throw new IllegalArgumentException("잘못된 조건식입니다: " + term);
		}
		
		String key = parts[0].trim();
		String valuePattern = parts[1].trim();
		
		if("*".equals(valuePattern)) {
			return map -> {
				boolean exists = map.containsKey(key) && map.get(key) != null;
				return isNegative != exists;
			};
		}
		
		if(valuePattern.startsWith("(") && valuePattern.endsWith(")")) {
			String content = valuePattern.substring(1, valuePattern.length() - 1);
			String[] values = content.split(",");
			
			Set<String> includeSet = new HashSet<String>();
			Set<String> excludeSet = new HashSet<String>();
			
			for(String val : values) {
				val = val.trim();
				if(val.startsWith("!")) {
					excludeSet.add(val.substring(1));
				} else {
					includeSet.add(val);
				}
			}
			
			return map -> {
				String actual = map.get(key);
				if(actual == null) {
					return false;
				}
				
				boolean inInclude = includeSet.isEmpty() || includeSet.contains(actual);
				boolean inExclude = !excludeSet.contains(actual);
				boolean result = inInclude && inExclude;
				return isNegative != result;
			};
		}
		
		if(valuePattern.endsWith("*")) {
			String prefix = valuePattern.substring(0, valuePattern.length() - 1);
			return map -> {
				String actual = map.get(key);
				if(actual == null) {
					return false;
				}
				return isNegative != actual.startsWith(prefix);
			};
		} else if(valuePattern.startsWith("*")) {
			String suffix = valuePattern.substring(1);
			return map -> {
				String actual = map.get(key);
				if(actual == null) {
					return false;
				}
				return isNegative != actual.endsWith(suffix);
			};
		}
		
		return map -> {
			String actual = map.get(key);
			if(actual == null) {
				return false;
			}
			return isNegative != actual.equals(valuePattern);
		};
	}
	
	private static String[] splitPreservingGroups(String input, String delimiter) {
		List<String> parts = new ArrayList<String>();
		int start = 0;
		int depth = 0;
		
		for(int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if(c == '(') {
				depth++;
			} else if(c == ')') {
				depth--;
			}
			
			if(depth == 0 && i + delimiter.length() <= input.length()) {
				if(input.startsWith(delimiter, i)) {
					parts.add(input.substring(start, i));
					i += delimiter.length() - 1;
					start = i + 1;
				}
			}
		}
		parts.add(input.substring(start));
		return parts.toArray(new String[0]);
	}
	
	private static void validateSyntax(String term) {
		if(StringUtils.isNullOrEmpty(term)) {
			throw new IllegalArgumentException("조건이 빠져있습니다.");
		}
		
		int depth = 0;
		for(char c : term.toCharArray()) {
			if(c == '(') {
				depth++;
			} else if(c == ')') {
				depth--;
			}
			
			if(depth < 0) {
				break;
			}
		}
		if(depth != 0) {
			throw new IllegalArgumentException("괄호가 올바르게 사용되지 않았습니다: " + term);
		}
		
		if(term.startsWith("AND ") || term.startsWith("OR ")
				|| term.endsWith(" AND") || term.endsWith(" OR")) {
			throw new IllegalArgumentException("논리 연산자의 위지가 올바르지 않습니다: " + term);
		}
		
		if(!Pattern.matches("[\\w\\s!*(),=]+", term)) {
			throw new IllegalArgumentException("사용할 수 없는 문자가 포함되어 있습니다: " + term);
		}
		
		String[] parts = term.split("(?i)(?<= )AND | OR |AND(?= )|OR(?= )");
		for(String part : parts) {
			if(!part.trim().isEmpty()) {
				validateSingleTerm(part.trim());
			}
		}
	}
	
	private static void validateSingleTerm(String term) {
		if(!term.contains("=") && !term.contains("!=")) {
			throw new IllegalArgumentException("사용 가능한 논리 연산자가 없습니다: " + term);
		}
		
		String[] parts = term.split("!=", 2);
		boolean isNegative = parts.length == 2;
		if(!isNegative) {
			parts = term.split("=", 2);
			if(parts.length != 2) {
				throw new IllegalArgumentException("올바르지 못한 논리 연산 구문입니다: " + term);
			}
		}
		
		String key = parts[0].trim();
		String valuePattern = parts[1].trim();
		
		if(key.isEmpty()) {
			throw new IllegalArgumentException("조건이 되는 키 값이 비어있습니다: " + term);
		}
		
		validateValuePattern(valuePattern, term);
	}
	
	private static void validateValuePattern(String valuePattern, String fullTerm) {
		if(valuePattern.equals("*")) {
			return;
		}
		
		if(valuePattern.startsWith("(") && valuePattern.endsWith(")")) {
			String content = valuePattern.substring(1, valuePattern.length() - 1);
			if(content.trim().isEmpty()) {
				throw new IllegalArgumentException("빈 괄호 값입니다: " + fullTerm);
			}
		} else if(valuePattern.endsWith("*") || valuePattern.startsWith("*")) {
			if(valuePattern.length() == 1) {
				throw new IllegalArgumentException("사용하실 수 없는 '*' 위치 입니다: " + fullTerm);
			}
		}
	}
}
