package com.az.Utilities;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.testng.ITestResult;

import com.az.CoreTestBase.TestSessionInitiator;
import com.az.Reporting.Authenticator;
import com.az.Utilities.YamlReader;



public class TestUtil extends TestSessionInitiator{
	public static String screenshotName;
	public static String screenShotPath;
	public static String methodName;
	String testdata;

	/** The today. */
	static Date today = new Date();

	/** The host. */
	public static String host = "smtp.gmail.com";

	/** The from. */
	public static String from = "az.imjrane@gmail.com";

	/** The password. */
	public static String password = "Selenium12&3";

	/** The port. */
	public static String port = "587";
	
	public static String textFile1;
	
	public TestUtil(String datafilelocator) {
		super(datafilelocator);
		// TODO Auto-generated constructor stub
	}

	public static void captureScreenShot(ITestResult result) throws IOException{
		
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		methodName = result.getName()+"_"+formater.format(calendar.getTime()) +".jpg";
		
		WebDriver augmentedDriver = new Augmenter().augment(driver);
		if (!result.isSuccess()) {
			try {
				File scrFile = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") +"/target/surefire-reports/html/" + methodName)); 

			} catch (Exception ex) {
				ex.printStackTrace();
			}			
		
		
	//	File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		//screenshotName = "error.jpg";
		//FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir") +"\\target\\surefire-reports\\html\\"+screenshotName));
		
	}

}
	public static Session getSession() {
		Authenticator authenticator = new Authenticator(from, password);
		Properties properties = new Properties();
		properties.setProperty("mail.transport.protocol", "smtps");
		properties.put("mail.smtps.auth", "true");
		properties.setProperty("mail.smtp.submitter", authenticator.getPasswordAuthentication().getUserName());
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.host", host);
		properties.setProperty("mail.smtp.port", port);
	//	properties.put("mail.smtp.starttls.enable", "true") ;
	//	properties.setProperty("mail.smtp.ssl.enable", "true");
		//properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		return Session.getInstance(properties, authenticator);
	}
	public static String setBodyText() throws IOException {
		String mailtext = "";
		mailtext = "Hi,<br>";
		mailtext = mailtext + "</br><br>The automated functional test were performed</br><br>";
		mailtext = mailtext + "<br><b><font style= Courier, color = green>Test Name: </font></b>" + getTestName();
		mailtext = mailtext + "<br><b><font color = green>Test Date: </font></b>" + today;
		mailtext = mailtext + "<br><b><font color = green>Test Browser: </font></b>" + System.getProperty("browser", YamlReader.getYamlValue("selenium.browser").toString());
		mailtext = mailtext + "<br><b><font color = green>Test Environment: </font></b>" + YamlReader.getYamlValue("testenv");
		mailtext = mailtext + "<br><b><font color = green>Test Server: </font></b>" + YamlReader.getYamlValue("selenium.server");
		if (YamlReader.getYamlValue("selenium.server").equalsIgnoreCase("remote")) {
			mailtext = mailtext + "<br><b><font color = green>Test Server host: </font></b>" + YamlReader.getYamlValue("selenium.remote.host");
		}
		mailtext = mailtext + "<br><b><font color = green>Test Case Executed By: </font></b>" + "Sylob Automation Team";
		//mailtext = mailtext + "<br><b><font color = green>Test Case Executed By: </font></b>" + System.getProperty("user.name");
		mailtext = mailtext + "<b>" + testSetResult() + "</b>";
		mailtext = mailtext + "<br><br>The detailed test results are given in the link below:  </br></br>";
		mailtext = mailtext + "<br><br>http://192.168.220.62:8080/job/SylobAutomationProject/Extent_20Reports/";
		mailtext = mailtext + "<br><br>If you can't open the link for limited access reasons, please download the attached file.";
		mailtext = mailtext + "<br><br>Best Regards" + "</br></br>";
		mailtext = mailtext + "<br>BANK APPLICATION Automation QA Team" + "</br>";
		mailtext = mailtext + "<br><br>Note: This is a system generated mail. Please do not reply." + "</br></br>";
		mailtext = mailtext + "<br>If you have any queries mail to <a href=mailto:ai_maroc@sylob.com?Subject=Reply of Automation Status>Test & AutomationQA</a></br>";
		return mailtext;
	}
	/**
	 * Sets the mail subject.
	 * 
	 * @return the string
	 */
	public static String setMailSubject() {
		return ("Automated Script Test Report - " + today);
	}
	
//	private String setMailSubject() {
//		if (getTestName().toLowerCase().contains("createnewcourse")) {
//			return (System.getProperty("environment", "").toUpperCase() + ": " + System.getProperty("product", "") + "_" + getTestName() + " - " + today);
//		} else {
//			if (getTestName().toLowerCase().contains("masteropencloseactivity")) {
//				return (System.getProperty("environment", "").toUpperCase() + ": " + System.getProperty("product", "") + "_" + getTestName() + " - " + today);
//			} else {
//				return (System.getProperty("environment", "").toUpperCase() + ": " + System.getProperty("product", "") + "_" + getTestName() + " - " + today);
//			}
//		}
//	}

	/**
	 * Sets the mail recipient.
	 * 
	 * @param message
	 *            the new mail recipient
	 */
	public static void setMailRecipient(Message message) {
		// System.out.println("EnteredsetMailRecipient");
		try {
			for (Object recipient : YamlReader.getYamlNodesArray("results.recipients").values()) {
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient.toString()));
				message.addRecipient(Message.RecipientType.BCC, new InternetAddress(recipient.toString()));
			}
			message.addRecipient(Message.RecipientType.TO, new InternetAddress("az.imjrane@gmail.com"));
			message.addRecipient(Message.RecipientType.BCC, new InternetAddress("aziz.m.imjrane@gmail.com"));
		//	message.addRecipient(Message.RecipientType.TO, new InternetAddress("hem-maroc@sylob.com"));
		} catch (MessagingException me) {
			System.out.println("Exception in Results mail sending:-" + me.getStackTrace());
		}
	}

	/**
	 * Sets the attachement.
	 * 
	 * @return the multipart
	 * @throws Exception
	 *             the exception
	 */
	public static Multipart setAttachement() throws Exception {
		// Create the message part
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(setBodyText(), "text/html");
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
		// Part two is attachment
		messageBodyPart = new MimeBodyPart();
		System.out.println("getTestName() :::::::::::: " + getTestName());
		
			messageBodyPart.attachFile("./target/surefire-reports/html/extent.html");
			multipart.addBodyPart(messageBodyPart);
		
		return multipart;
	}

	/**
	 * Gets the test name.
	 * 
	 * @return the test name
	 */
	private static String getTestName() {
		String test = System.getProperty("test", "null");
		String testsuite = System.getProperty("testsuite", "null");
		String testName;
		if (test != "null") {
			testName = test + " was executed";
			return testName;
		} else if (testsuite != "null") {
			testName = testsuite + "were executed";
			return testName;
		} else {
			testName = "complete automation test suite or TestNg xml was executed";
			return testName;
		}
	}

	/**
	 * Gets the file path.
	 * 
	 * @return the file path
	 */
	public static void getFilePath() {
		File folder = new File("./target/surefire-reports");
		String[] fileNames = folder.list();
		int total = 0;
		for (int i = 0; i < fileNames.length; i++) {
			if (fileNames[i].contains("reportemail")) {
				total++;
				// System.out.println("total is" + total);
				assert total == 1;
				textFile1 = fileNames[i];
				 System.out.println("The filename is:" + textFile1);
			}
		}
	}

	/**
	 * Test set result.
	 * 
	 * @return the string
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static String testSetResult() throws IOException {
		String messageToBeSent = ("");
		getFilePath();// calling method getFilepath()
		String textFilePath = "./target/surefire-reports/" + textFile1;
		FileInputStream fstream = new FileInputStream(textFilePath);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		int num = 4;
		// Read File Line By Line
		String[] strLine = new String[num];
		for (int i = 0; i < num; i++) {
			strLine[i] = br.readLine();
			messageToBeSent = messageToBeSent + "<br>" + strLine[i] + "</br>";
		}
		br.close();
		return messageToBeSent;
	}
}