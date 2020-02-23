package com.pe.hatv.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobLogger {

	private boolean logToFile;
	private boolean logToConsole;
	private boolean logMessage;
	private boolean logWarning;
	private boolean logError;
	private boolean logToDatabase;
		
		
}
