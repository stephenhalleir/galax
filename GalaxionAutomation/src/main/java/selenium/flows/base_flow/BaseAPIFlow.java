package selenium.flows.base_flow;

import com.aventstack.extentreports.ExtentTest;
import org.apache.log4j.Logger;

public class BaseAPIFlow {

	public ExtentTest logger;
	public Logger logger4j;
	
	public BaseAPIFlow() {
		
	}

	public BaseAPIFlow(ExtentTest logger, Logger logger4j) {
		super();
		this.logger = logger;
		this.logger4j = logger4j;
	}

	public ExtentTest getLogger() {
		return logger;
	}

	public void setLogger(ExtentTest logger) {
		this.logger = logger;
	}

	public Logger getLogger4j() {
		return logger4j;
	}

	public void setLogger4j(Logger logger4j) {
		this.logger4j = logger4j;
	}

	// log the INFO message to the extent logger
	public void logInfo(String s) {
		log4j(s);
		if (logger != null) {
			logger.info(s);
		}
	}

	// log the PASS message to the extent logger
	public void logPass(String s) {
		log4j(s);
		if (logger != null) {
			logger.pass(s);
		}
	}

	// log the FAIL message to the extent logger
	public void logFail(String s) {
		log4j(s);
		if (logger != null) {
			logger.fail(s);
		}
	}

	// log a message to log4j
	public void log4j(String message) {
		if (logger4j != null) {
			logger4j.info(message);
		}
	}
}
