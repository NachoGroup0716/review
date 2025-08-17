package com.file.transfer.common;

public class StringUtils {
	public static boolean isNotNullAndEmpty(Object item) {
		if(item == null) {
			return false;
		} else {
			return !String.valueOf(item).isEmpty();
		}
	}
	
	public static boolean isNullOrEmpty(Object item) {
		return !isNotNullAndEmpty(item);
	}
}
