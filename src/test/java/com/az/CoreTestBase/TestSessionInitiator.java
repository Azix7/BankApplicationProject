package com.az.CoreTestBase;

import static com.az.Utilities.YamlReader.getYamlValue;
import static com.az.Utilities.YamlReader.getYamlValues;
import static com.az.Utilities.YamlReader.setYamlFilePath;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
//import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.az.Utilities.WebEventListener;
import com.az.lib.DepositPageAction;
import com.az.Utilities.ExcelReader;
import com.az.Utilities.ExtentManager;
import com.az.lib.AddNewCustomerPageAction;
import com.az.lib.BankHomePageAction;

import org.openqa.selenium.remote.DesiredCapabilities;
import static com.az.Utilities.YamlReader.*;

//TODO: Auto-generated Javadoc
/**
* The Class TestSessionInitiator.
* 
* @author tempshtiw
*
*/

public class TestSessionInitiator {

	/** The sylob home. */
	public BankHomePageAction bankHome;
	
	/** The sylob donnee technique */
    public AddNewCustomerPageAction addNewCustomer;
    
	/** The e driver. */
	private EventFiringWebDriver e_driver;
	/** The event listener. */
	private WebEventListener eventListener;
	/** The driver. */
	public static WebDriver driver;
	/** The browser. */
	String browser;
	/** The seleniumserver. */
	String seleniumserver;
	/** The seleniumserverhost. */
	String seleniumserverhost;
	/** The appbaseurl. */
	String appbaseurl;
	/** The applicationpath. */
	String applicationpath;
	
	/** The chromedriverpath. */
	String chromedriverpath;
	/** The timeout. */
	long timeout;
	/** The chrome options. */
	Map<String, Object> chromeOptions = null;
	/** The capabilities. */
	DesiredCapabilities capabilities;
	public static ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest extentTest;
	public static ExtentTest extentTestChild;
    public static ExcelReader excel;
	/**
	 * Instantiates a new test session initiator.
	 * 
	 * @param datafilelocator
	 *            the datafilelocator
	 */
	
	public TestSessionInitiator(String datafilelocator)
	{
     setYamlFilePath(datafilelocator);
		_getSessionConfig();
		_configureBrowser();
		_initPage();
		extentTestChild = new ExtentTest("Tests", "test");
		excel = new ExcelReader(System.getProperty("user.dir")+"/resources/testData/Book.xlsx");
	}
	
	/**
	 * _get session config.
	 */
	private void _getSessionConfig() {
		browser = getYamlValue("selenium.browser");
		seleniumserver = getYamlValue("selenium.server");
		seleniumserverhost = getYamlValue("selenium.remote.host");
		appbaseurl = getYamlValue("url");
	//	applicationpath = appbaseurl;
		timeout = Integer.parseInt(getYamlValue("selenium.timeout"));
		extentTestChild = new ExtentTest("Tests", "test");
	}
	

	/**
	 * _configure browser.
	 */
	private void _configureBrowser() {
		Map<String, Object> driverConfig = getYamlValues("selenium");
		driver = WebDriverFactory.getDriver(driverConfig);
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		//***********************
		
		e_driver = new EventFiringWebDriver(driver);
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		System.getProperties().put("org.apache.commons.logging.simplelog.defaultlog","fatal");
	}

	/**
	 * Launch url.
	 * 
	 * @param URL
	 *            the url
	 */
	public void launchUrl(String URL) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.get(URL);
	}
	
	/**
	 * _init page.
	 */
	private void _initPage() {
		bankHome = new BankHomePageAction(driver);
		//sylobArticles = new SylobArticlesPageAction(driver);
		addNewCustomer = new AddNewCustomerPageAction(driver);
	}
	
	/**
	 * Launch application.
	 */
	public void launchApplication() {
		System.out.println("The application url is :- " + applicationpath);
		driver.get(applicationpath);
	}
	
	/**
	 * Launch Sylob application url.
	 * 
	 * @param URL
	 *            the url
	 */
	public void launchApplicationUrl(String URL) {
		launchUrl(URL);
	}
	
	/**
	 * Close browser session.
	 */
	public void closeBrowserSession() {
		
		driver.quit();
	}
	
	/**
	 * Switch to default content.
	 */
	public void switchToDefaultContent() {
		driver.switchTo().defaultContent(); 
	}
	
	/**
	 * Take screenshot of failure.
	 * 
	 * @param result
	 *            the result
	 */
	public void takeScreenshotOfFailure(ITestResult result) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		String methodName = result.getName();
		WebDriver augmentedDriver = new Augmenter().augment(driver);
		if (!result.isSuccess()) {
			try {
				File scrFile = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrFile, new File("target/failsafe-reports/screenshots/" + methodName + "_"
						+ formater.format(calendar.getTime()) + ".jpg"));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	/**
	 * Take screenshot of failure from local machine.
	 *
	 * @param result the result
	 */
	public void takeScreenshotOfFailureFromLocalMachine(ITestResult result) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		String methodName = result.getName();
		if (!result.isSuccess()) {
			try {
				File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrFile, new File("target/failsafe-reports/screenshots/" + methodName + "_"
						+ formater.format(calendar.getTime()) + ".jpg"));
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}
	
	}

}