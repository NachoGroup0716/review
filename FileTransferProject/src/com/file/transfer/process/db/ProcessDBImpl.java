package com.file.transfer.process.db;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.file.transfer.data.FileData;
import com.file.transfer.data.OnSignalResult;
import com.file.transfer.process.ProcessImplement;
import com.file.transfer.process.db.recv.DefaultRecvDBProcess;

public abstract class ProcessDBImpl extends ProcessImplement {
	private SqlSession sqlSession;
	
	public abstract List<FileData> process(FileData orgFileData, OnSignalResult signalResult, DefaultRecvDBProcess defaultProcess) throws Exception;
}
