package com.file.transfer.data;

import lombok.Data;

@Data
public class FileInterfaceInfo {
	private String recvDirPath;
	private String sendDirPath;
	private String tempDirPath;
}
