package com.pe.hatv.util;

import java.io.IOException;

import com.pe.hatv.model.JobLogger;

public interface IJobLoggerUtil {

	public int valueT(JobLogger jobLogger);
	
	public void logMessage(JobLogger jobLogger, String messageText);
	
	public void logToFileConsole(JobLogger jobLogger, String message) throws IOException;
	
	public void logDb(String message, int t);
	
	public boolean isValidationConsoleFileDb(JobLogger jobLogger);
	
	public boolean isValidationMessageErrorWarning(JobLogger jobLogger);
	
}
