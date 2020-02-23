package com.pe.hatv.service;

import com.pe.hatv.model.JobLogger;

public interface IJobLogger {

	public void LogMessage(String messageText, JobLogger jobLogger) throws Exception;
}
