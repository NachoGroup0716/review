package com.file.transfer.service.recv;

import java.util.ArrayList;
import java.util.List;

import com.file.transfer.data.FileData;
import com.file.transfer.data.OnSignalResult;
import com.file.transfer.process.db.ProcessDBImpl;
import com.file.transfer.process.db.recv.DefaultRecvDBProcess;
import com.file.transfer.service.FileService;

public class Step04_TargetDBProcess extends FileService {
	private List<ProcessDBImpl> processList;
	private DefaultRecvDBProcess defaultProcess;

	@Override
	public void onSignal(OnSignalResult result) throws Exception {
		List<FileData> polledDataList = (List<FileData>) result.getResultObject();
		List<FileData> resultDataList = new ArrayList<FileData>();
		for (FileData data : polledDataList) {
			if(processList != null && processList.size() > 0) {
				for(ProcessDBImpl process : processList) {
					if(process.isMatch(data.getStringMap())) {
						resultDataList.addAll(process.process(data, result, defaultProcess));
					}
				}
			} else if(data.isDefDB()) {
				resultDataList.addAll(defaultProcess.process(data, result));
			}
		}
	}

	public List<ProcessDBImpl> getProcessList() {
		return processList;
	}

	public void setProcessList(List<ProcessDBImpl> processList) {
		this.processList = processList;
	}

	public DefaultRecvDBProcess getDefaultProcess() {
		return defaultProcess;
	}

	public void setDefaultProcess(DefaultRecvDBProcess defaultProcess) {
		this.defaultProcess = defaultProcess;
	}
}
