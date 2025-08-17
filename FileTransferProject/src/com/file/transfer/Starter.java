package com.file.transfer;

import java.util.ArrayList;
import java.util.List;

import com.file.transfer.data.FileInterfaceInfo;
import com.file.transfer.data.OnSignalResult;
import com.file.transfer.service.FileService;
import com.file.transfer.service.recv.Step01_TargetPolling;
import com.file.transfer.service.recv.Step02_TargetIdentify;
import com.file.transfer.service.recv.Step03_TargetFileProcess;
import com.file.transfer.service.recv.Step04_TargetDBProcess;

public class Starter {

	public static void main(String[] args) {
		List<FileService> serviceList = new ArrayList<FileService>();
		serviceList.add(new Step01_TargetPolling());
		serviceList.add(new Step02_TargetIdentify());
		serviceList.add(new Step03_TargetFileProcess());
		serviceList.add(new Step04_TargetDBProcess());
		
		FileInterfaceInfo info = new FileInterfaceInfo();
		info.setRecvDirPath("C:\\TEST\\RECV");
		info.setTempDirPath("C:\\TEST\\TEMP\\RECV");
		
		OnSignalResult firstObj = new OnSignalResult();
		firstObj.setInfo(info);
		
		for(FileService service : serviceList) {
			try {
				service.onSignal(firstObj);
				if(firstObj.getResultCount() == 0) {
					System.out.println("[" + service.getClass().toString() + "] Result count : 0, stop process");
					break;
				} else {
					System.out.println("[" + service.getClass().toString() + "] Result count : " + firstObj.getResultCount() + ", continue process");
				}
			} catch(Exception e) {
				System.out.println("오류가 발생하여 작업을 중지합니다.");
				e.printStackTrace();
			}
		}
	}
}