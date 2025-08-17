package com.file.transfer.service.recv;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.file.transfer.constants.FILE_INFO;
import com.file.transfer.data.FileData;
import com.file.transfer.data.FileInterfaceInfo;
import com.file.transfer.data.OnSignalResult;
import com.file.transfer.service.FileService;

public class Step01_TargetPolling extends FileService {
	@Override
	public void onSignal(OnSignalResult result) throws Exception {
		FileInterfaceInfo info = result.getInfo();
		String[] recvDirPathArr = info.getRecvDirPath().split(",");
		List<FileData> polledDataList = new ArrayList<FileData>();
		
		for(String recvDirPath : recvDirPathArr) {
			Path recvDir = Paths.get(recvDirPath);
			try(DirectoryStream<Path> recvStreamList = Files.newDirectoryStream(recvDir)){
				recvStreamList.forEach(item -> {
					if(Files.isRegularFile(item)) {
						try {
							long size = Files.size(item);
							if(size > 0L) {
								FileData data = new FileData();
								data.put(FILE_INFO.INIT_FILE_PATH, item.getParent().toAbsolutePath().toString());
								data.put(FILE_INFO.INIT_FILE_NM, item.getFileName().toString());
								data.put(FILE_INFO.INIT_FILE_SIZE, String.valueOf(size));
								polledDataList.add(data);
							}
						} catch(IOException e) {
							//skip
						}
					}
				});
			}
		}
		
		if(polledDataList.size() > 0) {
			result.setResultCount(polledDataList.size());
			result.setResultObject(polledDataList);
		} else {
			result.setResultCount(0);
		}
	}
}