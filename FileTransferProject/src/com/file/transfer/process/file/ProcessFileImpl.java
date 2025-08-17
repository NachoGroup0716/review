package com.file.transfer.process.file;

import java.util.List;

import com.file.transfer.data.FileData;
import com.file.transfer.data.OnSignalResult;
import com.file.transfer.process.ProcessImplement;
import com.file.transfer.process.file.recv.DefaultRecvFileProcess;

public abstract class ProcessFileImpl extends ProcessImplement {
	public abstract List<FileData> process(FileData orgFileData, OnSignalResult signalResult, DefaultRecvFileProcess defaultProcess) throws Exception;
}
