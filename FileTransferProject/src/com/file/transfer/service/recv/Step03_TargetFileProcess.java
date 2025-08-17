package com.file.transfer.service.recv;

import java.util.ArrayList;
import java.util.List;

import com.file.transfer.data.FileData;
import com.file.transfer.data.OnSignalResult;
import com.file.transfer.process.file.ProcessFileImpl;
import com.file.transfer.process.file.recv.DefaultRecvFileProcess;
import com.file.transfer.service.FileService;

public class Step03_TargetFileProcess extends FileService {
	private List<ProcessFileImpl> processList;
	private DefaultRecvFileProcess defaultProcess;
	
	@Override
	public void onSignal(OnSignalResult result) throws Exception {
		List<FileData> polledDataList = (List<FileData>) result.getResultObject();
		List<FileData> resultDataList = new ArrayList<FileData>();
		for(FileData data : polledDataList) {
			if(processList != null && processList.size() > 0) {
				for(ProcessFileImpl process : processList) {
					if(process.isMatch(data.getStringMap())) {
						resultDataList.addAll(process.process(data, result, defaultProcess));
						break;
					}
				}
			} else if(data.isDefFile()) {
				resultDataList.addAll(defaultProcess.process(data, result));
			}
		}
		if(resultDataList.size() > 0) {
			result.setResultCount(resultDataList.size());
			result.setResultObject(resultDataList);
		}
	}

	public List<ProcessFileImpl> getProcessList() {
		return processList;
	}

	public void setProcessList(List<ProcessFileImpl> processList) {
		this.processList = processList;
	}

	public DefaultRecvFileProcess getDefaultProcess() {
		return defaultProcess;
	}

	public void setDefaultProcess(DefaultRecvFileProcess defaultProcess) {
		this.defaultProcess = defaultProcess;
	}
}
