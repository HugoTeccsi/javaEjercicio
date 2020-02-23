package com.pe.hatv.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pe.hatv.model.JobLogger;
import com.pe.hatv.service.IJobLogger;
import com.pe.hatv.util.IJobLoggerUtil;

@Service
public class JobLoggerService implements IJobLogger {
	
	@Autowired
	private IJobLoggerUtil iJobLoggerUtil;
	
	@Override
	public void LogMessage(String messageText, JobLogger jobLogger) throws Exception {
		
		if (messageText == null || messageText.length() == 0) {
			return;
		}
		if(iJobLoggerUtil.isValidationConsoleFileDb(jobLogger))	{
			throw new Exception("Invalid configuration");
		}
		if (iJobLoggerUtil.isValidationMessageErrorWarning(jobLogger)) {
			throw new Exception("Error or Warning or Message must be specified");
		}
		
		messageText.trim();
		
		int t = iJobLoggerUtil.valueT(jobLogger);
		
		if(jobLogger.isLogError() || jobLogger.isLogMessage() || jobLogger.isLogWarning()) {
			iJobLoggerUtil.logMessage(jobLogger, messageText);
		}
		if(jobLogger.isLogToFile() || jobLogger.isLogToConsole()) {
			iJobLoggerUtil.logToFileConsole(jobLogger, messageText);
		}
		if(jobLogger.isLogToDatabase()) {
			iJobLoggerUtil.logDb(messageText, t);
		}
		
	}

}
