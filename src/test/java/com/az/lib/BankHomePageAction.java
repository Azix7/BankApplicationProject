package com.az.lib;

import static com.az.Utilities.YamlReader.getYamlValue;

import java.util.concurrent.TimeUnit;

import org.apache.xmlbeans.impl.tool.XSTCTester.TestCaseResult;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.az.CoreTestBase.TestSessionInitiator;
import com.az.PageObjects.DepositPageUI;
import com.az.Utilities.StringMatcher;
import com.relevantcodes.extentreports.LogStatus;
/** AMB Test import */
import com.az.PageObjects.AddNewCustomerPageUI;
import com.az.PageObjects.BankHomePageUI;
import com.az.PageObjects.BankLoginPageUI;


// TODO: Auto-generated Javadoc
/**
 * The Class SylobHomePageAction.
 */
public class BankHomePageAction {
	
	/** The sylob page UI. */
	BankLoginPageUI bankLoginPg;	
	/** The Sylob home pg. */
	BankHomePageUI bankHomePg;
	/** The Sylob home pg. */
	DepositPageUI sylobArticlesPg;
	/** The sylob DonneeTechnique pg. */
	AddNewCustomerPageUI sylobDonneeTechniquePg;
	/** The driver. */
	WebDriver driver;	
	/** The parent window. */
	String parentWindow;
	String txt;
	String actualResult;
	String expectedResult;
	/**
	 * Instantiates a new sylob login page action.
	 * 
	 * @param driver
	 *            the driver
	 */
	public BankHomePageAction(WebDriver driver) {
		
		bankLoginPg = new BankLoginPageUI(driver);
		bankHomePg = new BankHomePageUI(driver);
		//sylobArticlesPg = new SylobArticlesPageUI(driver);
		sylobDonneeTechniquePg = new AddNewCustomerPageUI(driver);
		this.driver = driver;
	}

	/**
	 * Login to sylob.
	 *
	 * @param userName
	 *            the user name
	 * @param password
	 *            the password
	 * @param databaseName
	 *            the databse name           
	 * @throws InterruptedException 
	 */
	public void loginToBankApplication(String userName, String password) {		
		
		bankLoginPg.txtLoginTxtBox.sendKeys(userName);
		bankLoginPg.txtPasswordTxtBox.sendKeys(password);
	//	bankLoginPg.get_Txt_Username().sendKeys(userName);
	//	bankLoginPg.get_Txt_Password().sendKeys(password);
	//	bankLoginPg.SelectElementFromDropdown(bankLoginPg.get_drpdwn_Database(), "index", "3");
	//	bankLoginPg.get_CheckBox_RememberMe().click();
		bankLoginPg.get_Btn_login().click();
		bankLoginPg.verifyPageTitle("Guru99 Bank Manager HomePage");
		bankLoginPg.reportLog("Login to application with LOGIN " + userName + " and PASSWORD " + password);
		TestSessionInitiator.extentTestChild.log(LogStatus.INFO, "...Get to start activity successfully!");
	}
	
public void loginToBankApplicationWithInvalidID(String userName, String password) {		
		
		bankLoginPg.txtLoginTxtBox.sendKeys(userName);
		bankLoginPg.txtPasswordTxtBox.sendKeys(password);
	//	bankLoginPg.get_Txt_Username().sendKeys(userName);
	//	bankLoginPg.get_Txt_Password().sendKeys(password);
	//	bankLoginPg.SelectElementFromDropdown(bankLoginPg.get_drpdwn_Database(), "index", "3");
	//	bankLoginPg.get_CheckBox_RememberMe().click();
		bankLoginPg.get_Btn_login().click();
		bankLoginPg.switchToPopUp();
		txt =driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();
		StringMatcher.match(txt, "User or Password is not valid", false);
		//StringMatcher.match(txt, "User or Password is not valid", false);
		//bankLoginPg.verifyPageTitle("Guru99 Bank Manager HomePage");
		bankLoginPg.reportLog("Login to application with invalid LOGIN " + userName + " and PASSWORD " + password);
		TestSessionInitiator.extentTestChild.log(LogStatus.INFO, "...Get to start activity successfully!");
	}
	
public void loginToBankApplicationWithInvalidPassword(String userName, String password) {		
	
	bankLoginPg.txtLoginTxtBox.sendKeys(userName);
	bankLoginPg.txtPasswordTxtBox.sendKeys(password);
//	bankLoginPg.get_Txt_Username().sendKeys(userName);
//	bankLoginPg.get_Txt_Password().sendKeys(password);
//	bankLoginPg.SelectElementFromDropdown(bankLoginPg.get_drpdwn_Database(), "index", "3");
//	bankLoginPg.get_CheckBox_RememberMe().click();
	bankLoginPg.get_Btn_login().click();
	bankLoginPg.switchToPopUp();
	txt = driver.switchTo().alert().getText();
	driver.switchTo().alert().accept();
	StringMatcher.match(txt, "User or Password is not valid", false);
	//bankLoginPg.verifyPageTitle("Guru99 Bank Manager HomePage");
	bankLoginPg.reportLog("Login to application with LOGIN " + userName + " and invalid PASSWORD " + password);
	TestSessionInitiator.extentTestChild.log(LogStatus.INFO, "...Get to start activity successfully!");
	
}

public void loginToBankApplicationWithInvalidIDAndPassword(String userName, String password) {		
	
	bankLoginPg.txtLoginTxtBox.sendKeys(userName);
	bankLoginPg.txtPasswordTxtBox.sendKeys(password);
//	bankLoginPg.get_Txt_Username().sendKeys(userName);
//	bankLoginPg.get_Txt_Password().sendKeys(password);
//	bankLoginPg.SelectElementFromDropdown(bankLoginPg.get_drpdwn_Database(), "index", "3");
//	bankLoginPg.get_CheckBox_RememberMe().click();
	bankLoginPg.get_Btn_login().click();
	bankLoginPg.switchToPopUp();
	String txt = driver.switchTo().alert().getText();
	driver.switchTo().alert().accept();
	StringMatcher.match(txt, "User or Password is not valid", false);
	bankLoginPg.reportLog("Login to application with invalid LOGIN " + userName + " and invalid PASSWORD " + password);
	TestSessionInitiator.extentTestChild.log(LogStatus.INFO, "...Get to start activity successfully!");
}

   public void verifyUserID(String userId) {
	 
	  Assert.assertEquals(bankHomePg.txt_UserId.getText(), "Manger Id : "+userId);
	  bankLoginPg.reportLog(bankHomePg.txt_UserId.getText()+ " EQUAL TO" + "Manger Id : "+userId);	  
	  TestSessionInitiator.extentTestChild.log(LogStatus.INFO, "...Get to start activity successfully!");
   }
	
	
	
	/**"
	 * Sync page.
	 */
	public void syncPage(){
		bankHomePg.waitLongMin(1);
	}

		
		
	/**
	 * Log out from application.
	 */
	public void Log_Out_From_Application(){
		
		bankHomePg.get_Btn_log_Out().click();
		driver.switchTo().alert().accept();
	}
}
