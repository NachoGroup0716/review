package com.file.transfer.data;

import lombok.Data;

@Data
public class OnSignalResult {
	private int resultCount;
	private Object resultObject;
	private FileInterfaceInfo info;
}
