package com.pe.hatv.util;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pe.hatv.model.JobLogger;
import com.pe.hatv.model.JobLoggerDB;
import com.pe.hatv.repository.IJobLoggerDB;

@Component
public class JobLoggerUtil implements IJobLoggerUtil {

	@Autowired
	private IJobLoggerDB iJobLoggerDB;
	
	private static final String logPathFile="./logFile.txt";
	
	private static final Logger logger = Logger.getLogger("MyLog");
	
	@Override
	public int valueT(JobLogger jobLogger) {
		int value=0;
		if (jobLogger.isLogMessage()) {
			value = 1;
		}

		if (jobLogger.isLogError()) {
			value = 2;
		}

		if (jobLogger.isLogWarning()) {
			value = 3;
		}
		return value;
	} 
	
	@Override
	public void logMessage(JobLogger jobLogger, String messageText) {
		String messageLog="";
		if (jobLogger.isLogError()) {
			messageLog = messageLog + "error " + DateFormat.getDateInstance(DateFormat.LONG).format(new Date()) + messageText;
		}

		if (jobLogger.isLogWarning()) {
			messageLog = messageLog + "warning " +DateFormat.getDateInstance(DateFormat.LONG).format(new Date()) + messageText;
		}

		if (jobLogger.isLogMessage()) {
			messageLog = messageLog + "message " +DateFormat.getDateInstance(DateFormat.LONG).format(new Date()) + messageText;
		}
	}

	@Override
	public void logToFileConsole(JobLogger jobLogger,String message) throws IOException {
		File logFile=new File(logPathFile);
		if (!logFile.exists()) {
			logFile.createNewFile();
		}
		
		FileHandler fh = new FileHandler(logPathFile);
		ConsoleHandler ch = new ConsoleHandler();
		
		if(jobLogger.isLogToFile()) {
			logger.addHandler(fh);
			logger.log(Level.INFO, message);
		}
		
		if(jobLogger.isLogToConsole()) {
			logger.addHandler(ch);
			logger.log(Level.INFO, message);
		}		
		
	}

	@Override
	public void logDb(String message, int t) {
		JobLoggerDB jobLoggerDB=new JobLoggerDB();
		jobLoggerDB.setMessage(message);
		jobLoggerDB.setTypeMessage(t);
		iJobLoggerDB.save(jobLoggerDB);
	}

	@Override
	public boolean isValidationConsoleFileDb(JobLogger jobLogger) {
		List<Boolean> values=new ArrayList<Boolean>(
				Arrays.asList(jobLogger.isLogToConsole(),
						jobLogger.isLogToFile(),
						jobLogger.isLogToDatabase()));
		return values.stream().allMatch(p->p.equals(false));
	}

	@Override
	public boolean isValidationMessageErrorWarning(JobLogger jobLogger) {
		List<Boolean> values=new ArrayList<Boolean>(
				Arrays.asList(jobLogger.isLogMessage(),
						jobLogger.isLogError(),
						jobLogger.isLogWarning()));
		return values.stream().allMatch(p->p.equals(false));
	}
}
