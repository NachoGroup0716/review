package com.file.transfer.service;

import com.file.transfer.data.OnSignalResult;

public abstract class FileService {
	public abstract void onSignal(OnSignalResult result) throws Exception;
}
