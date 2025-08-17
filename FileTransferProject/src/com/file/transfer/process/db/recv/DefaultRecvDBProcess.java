package com.file.transfer.process.db.recv;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.file.transfer.data.FileData;
import com.file.transfer.data.OnSignalResult;

public class DefaultRecvDBProcess {
	private SqlSession sqlSession;
	
	public List<FileData> process(FileData orgFileData, OnSignalResult onSignalResult) {
		return new ArrayList<FileData>();
	}

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
}
