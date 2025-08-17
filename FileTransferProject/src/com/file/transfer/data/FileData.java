package com.file.transfer.data;

import java.util.HashMap;
import java.util.Map;

import com.file.transfer.common.StringUtils;
import com.file.transfer.constants.FILE_INFO;
import com.file.transfer.constants.FileConstants;
import com.file.transfer.constants.TABLE_01;
import com.file.transfer.constants.TABLE_02;
import com.file.transfer.constants.TABLE_03;
import com.file.transfer.constants.TABLE_NAMES;

public class FileData {
	public static final String SUCCESS = "SUCC";
	public static final String FAIL = "FAIL";
	private final Map<FileConstants, String> map;
	private boolean isDefFile;
	private boolean isDefDB;
	
	public FileData() {
		this.map = new HashMap<FileConstants, String>();
	}
	
	public FileData(Map<FileConstants, String> map) {
		this.map = new HashMap<FileConstants, String>(map);
	}
	
	public void put(FileConstants key, String value) {
		this.map.put(key, value);
	}
	
	public void putTableData(TABLE_NAMES table, Map<String, Object> map) {
		for(Map.Entry<String, Object> entry : map.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			switch (table) {
			case TABLE_01:
				if(StringUtils.isNotNullAndEmpty(value)) {
					this.map.put(TABLE_01.valueOf(key), String.valueOf(value));
				}
				break;
			case TABLE_02:
				if(StringUtils.isNotNullAndEmpty(value)) {
					this.map.put(TABLE_02.valueOf(key), String.valueOf(value));
				}
				break;
			case TABLE_03:
				if(StringUtils.isNotNullAndEmpty(value)) {
					this.map.put(TABLE_03.valueOf(key), String.valueOf(value));
				}
				break;
			case FILE_INFO:
				if(StringUtils.isNotNullAndEmpty(value)) {
					this.map.put(FILE_INFO.valueOf(key), String.valueOf(value));
				}
				break;
			default:
				break;
			}
		}
	}
	
	public boolean containsKey(FileConstants key) {
		return this.map.containsKey(key);
	}
	
	public boolean containsKeys(FileConstants... keys) {
		for(FileConstants key : keys) {
			if(!this.map.containsKey(key)) {
				return false;
			}
		}
		return true;
	}
	
	public String get(FileConstants key) {
		if(this.map.containsKey(key)) {
			return this.map.get(key);
		} else {
			return null;
		}
	}
	
	public String getDataIfNotNull(FileConstants... keys) {
		for(FileConstants key : keys) {
			if(this.map.containsKey(key)) {
				return this.map.get(key);
			}
		}
		return null;
	}
	
	public Map<FileConstants, String> getMap() {
		return new HashMap<FileConstants, String>(this.map);
	}
	
	public Map<String, String> getStringMap(){
		Map<String, String> stringMap = new HashMap<String, String>();
		for(Map.Entry<FileConstants, String> entry : this.map.entrySet()) {
			stringMap.put(entry.getKey().getName(), entry.getValue());
			stringMap.put(entry.getKey().getFullname(), entry.getValue());
		}
		return stringMap;
	}

	public boolean isDefFile() {
		return isDefFile;
	}

	public void setDefFile(boolean isDefFile) {
		this.isDefFile = isDefFile;
	}

	public boolean isDefDB() {
		return isDefDB;
	}

	public void setDefDB(boolean isDefDB) {
		this.isDefDB = isDefDB;
	}
}
