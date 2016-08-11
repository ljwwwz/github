package com.coolshow.jeesite.modules.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestLog {

	static Logger log = LoggerFactory.getLogger(TestLog.class);

    public void log(){

       log.debug("Debug info.");

       log.info("Info info");

       log.warn("Warn info");

       log.error("Error info");
    }
    
    public static void main(String[] args) {
    	TestLog testLog = new TestLog();
    	testLog.log();
    }
}
