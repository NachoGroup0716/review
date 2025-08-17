package com.file.transfer.constants;

public enum FILE_INFO implements FileConstants {
	INIT_FILE_PATH("INIT_FILE_PATH"),
	INIT_FILE_NM("INIT_FILE_NM"),
	INIT_FILE_SIZE("INIT_FILE_SIZE"),
	PRCS_FILE_PATH("PRCS_FILE_PATH"),
	PRCS_FILE_NM("PRCS_FILE_NM"),
	RSLT_FILE_PATH("RSLT_FILE_PATH"),
	RSLT_FILE_NM("RSLT_FILE_NM"),
	DATE_TIME("DATE_TIME"),
	DATE("DATE"),
	TIME("TIME")
	;
	
	private String name;
	private String fullname;
	
	private FILE_INFO(String name) {
		this.name = name;
		this.fullname = "FILE_INFO." + name;
	}
	
	@Override
	public String getName() {
		return this.name;
	}
	
	@Override
	public String getFullname() {
		return this.fullname;
	}
}
