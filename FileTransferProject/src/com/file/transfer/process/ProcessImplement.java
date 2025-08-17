package com.file.transfer.process;

import java.util.Map;
import java.util.function.Predicate;

public abstract class ProcessImplement {
	private String pattern;
	private Predicate<Map<String, String>> matcher;
	
	public void getMatcher() {
		this.matcher = ProcessMatcher.getMacher(this.pattern);
	}
	
	public boolean isMatch(Map<String, String> map) {
		return this.matcher.test(map);
	}
	
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	
	public String getPattern() {
		return this.pattern;
	}
}