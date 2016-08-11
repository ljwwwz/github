package com.coolshow.jeesite.modules.cmag;

import java.lang.reflect.Method;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.IHookCallBack;
import org.testng.ITestResult;

@ContextConfiguration(locations = { "classpath*:/META-INF/spring-*.xml" })
public class ServiceTestBase extends AbstractTestNGSpringContextTests {

	@Override
	protected void springTestContextBeforeTestMethod(Method testMethod) throws Exception {
		super.springTestContextBeforeTestMethod(testMethod);
	}
	@Override
	public void run(IHookCallBack callBack, ITestResult testResult){
		super.run(callBack, testResult);
		
	}

}
