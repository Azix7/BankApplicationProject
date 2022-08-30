package com.az.Utilities;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.MimeMessage;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;

import com.az.PageObjects.BasePage;
import com.az.Utilities.TestUtil;
import com.az.Utilities.YamlReader;
import com.relevantcodes.extentreports.LogStatus;
import com.az.CoreTestBase.TestSessionInitiator;

public class CustomListeners implements ITestListener,ISuiteListener {

	public 	String messageBody;
	
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailure(ITestResult arg0) {

		System.setProperty("org.uncommons.reportng.escape-output","false");
		try {
			TestUtil.captureScreenShot(arg0);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			try {
				TestUtil.captureScreenShot(arg0);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		//test.log(LogStatus.FAIL, arg0.getName().toUpperCase()+" Failed with exception : "+arg0.getThrowable());
		//test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));
		 TestSessionInitiator.extentTestChild.log(LogStatus.FAIL, "<font color='#FF0000'>"+"UNABLE TO PERFORM ACTION SEE ERROR BELOW !! : "+"</font>" +"<br>" + arg0.getName().toUpperCase()+ (" Failed with exception : " + arg0.getThrowable()));		 
		 TestSessionInitiator.extentTestChild.log(LogStatus.FAIL, TestSessionInitiator.extentTest.addScreenCapture(TestUtil.methodName));

		Reporter.log("Click to see Screenshot");
		Reporter.log("<a target=\"_blank\" href="+TestUtil.screenshotName+">Screenshot</a>");
		Reporter.log("<br>");
		Reporter.log("<br>");
		Reporter.log("<a target=\"_blank\" href="+TestUtil.screenshotName+"><img src="+TestUtil.screenshotName+" height=200 width=200></img></a>");
		TestSessionInitiator.rep.endTest(TestSessionInitiator.extentTest);
		TestSessionInitiator.rep.flush();
		//	rep.endTest(test);
		//rep.flush();
		
	}

	public void onTestSkipped(ITestResult arg0) {

		TestSessionInitiator.extentTest = TestSessionInitiator.rep.startTest(arg0.getInstanceName().substring(13)+"--"+arg0.getName().toUpperCase());
	     TestSessionInitiator.extentTest.log(LogStatus.SKIP, arg0.getName().toUpperCase()+ (" TEST SKIPPED :   " + arg0.getThrowable()));
	     TestSessionInitiator.rep.endTest(TestSessionInitiator.extentTest);
		 TestSessionInitiator.rep.flush();
		
	}


	public void onTestStart(ITestResult arg0) {

		TestSessionInitiator.extentTest = TestSessionInitiator.rep.startTest(arg0.getInstanceName().substring(13)+" -- "+arg0.getName().toUpperCase());
		TestSessionInitiator.extentTestChild = TestSessionInitiator.rep.startTest(arg0.getName().toUpperCase());
		TestSessionInitiator.extentTest.appendChild(TestSessionInitiator.extentTestChild);
	
	}

	public void onTestSuccess(ITestResult arg0) {

		TestSessionInitiator.extentTestChild.log(LogStatus.PASS, arg0.getName().toUpperCase() + " PASS");
		TestSessionInitiator.rep.endTest(TestSessionInitiator.extentTest);
		TestSessionInitiator.rep.flush();

		
	}

	public void onFinish(ISuite arg0) {
		
		Runtime.getRuntime().addShutdownHook(new Thread(){
			public void run(){
			YamlReader.setYamlFilePath(System.getProperty("datafile", "EDU_TestData_Properties.yml"));
			System.out.println("%%%%%%%%%%%%%%%%%%%%%");
			try {
				if (YamlReader.getYamlValue("results.sendEmail").equalsIgnoreCase("yes")) {
					Properties props = new Properties();
					props.put("mail.smtps.auth", "true");
					Session session = Session.getInstance(props, null);
					Message message = new MimeMessage(TestUtil.getSession());
					TestUtil.setMailRecipient(message);
					message.setSubject(TestUtil.setMailSubject());
					message.setContent(TestUtil.setAttachement());
					Transport transport = session.getTransport("smtps");
					transport.connect(TestUtil.host, TestUtil.from, TestUtil.password);
					transport.sendMessage(message, message.getAllRecipients());
					transport.close();
					
				} else {
					System.out.println("enteredElse");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			}
		});

		
	}

	public void onStart(ISuite arg0) {
		// TODO Auto-generated method stub
		
	}

}
