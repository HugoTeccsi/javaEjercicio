package com.pe.hatv;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pe.hatv.model.JobLogger;
import com.pe.hatv.model.JobLoggerDB;
import com.pe.hatv.repository.IJobLoggerDB;
import com.pe.hatv.serviceImpl.JobLoggerService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EjercicioJavaApplicationTests {

	@Autowired
	private JobLoggerService loggerService;
	
	@Autowired
	private IJobLoggerDB iJobLoggerDB;
	
	@Test
	public void saveDbTest() {
		JobLoggerDB db=new JobLoggerDB();
		db.setMessage("saveDbTest");
		db.setTypeMessage(1);
		iJobLoggerDB.save(db);
		
		String messageDb=iJobLoggerDB.findAll().stream()
				.filter(p->p.getMessage().equals("saveDbTest"))
				.map(JobLoggerDB::getMessage).findAny().orElse(null);
		assertEquals("saveDbTest",messageDb);
	}
	
	@Test
	public void inicioBbToLogTest() throws Exception {		
		JobLogger logger=new JobLogger(false,false,false, false,true,true);
		String message="inicioBbToLogTest";
		loggerService.LogMessage(message, logger);
		String messageDb=iJobLoggerDB.findAll().stream()
				.filter(p->p.getMessage().equals("inicioBbToLogTest"))
				.map(JobLoggerDB::getMessage).findAny().orElse(null);
		assertEquals("inicioBbToLogTest",messageDb);
	}
	
	@Test
	public void inicioConsoleToErrorTest() throws Exception  {
		JobLogger logger=new JobLogger(false, true, false, false, true, false);
		String message="inicioConsoleToErrorTest";
		loggerService.LogMessage(message, logger);
		assertNotNull(true);
	}
	
	@Test
	public void inicioFileToErrorTests() throws Exception {
		JobLogger logger=new JobLogger(true, false, false, false, true, false);
		String message="inicioFileToErrorTests";
		loggerService.LogMessage(message, logger);
		assertNotNull(true);
	}
	
	@Test
	public void inicioFileToErrorWarningTests() throws Exception {
		JobLogger logger=new JobLogger(true,false,false,true,true,false);
		String message="inicioFileToErrorWarningTests";
		loggerService.LogMessage(message, logger);
		assertNotNull(true);
	}
}
