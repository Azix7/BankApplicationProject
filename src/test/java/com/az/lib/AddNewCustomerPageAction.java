package com.az.lib;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.az.PageObjects.BasePage;
import com.az.PageObjects.DepositPageUI;
import com.relevantcodes.extentreports.LogStatus;
import com.az.CoreTestBase.TestSessionInitiator;
import com.az.PageObjects.AddNewCustomerPageUI;
import com.az.PageObjects.BankHomePageUI;
import com.az.PageObjects.BankLoginPageUI;


public class AddNewCustomerPageAction {
	
	/** The driver. */
	 WebDriver driver;
	
	/** The home page. */
	 BankHomePageUI bankHomepg;
	/** The login page */
	 BankLoginPageUI bankLoginPg;
	 /** The articles page */
	 DepositPageUI depositPg;
	 /** The Donnee Technique page. */
	 AddNewCustomerPageUI addNewCustomerPg;

	 /**
		 * Instantiates a new sylob donnee technique page action.
		 * 
		 * @param driver
		 *            the driver
		 */
	 
	 public AddNewCustomerPageAction(WebDriver driver) {
			bankLoginPg = new BankLoginPageUI(driver);
			bankHomepg = new BankHomePageUI(driver);
			addNewCustomerPg = new AddNewCustomerPageUI(driver);	
			this.driver = driver;
	 }

	 
	 /**
		 * Navigate to add donnee technique form
		 * Switch to frame to get Add donnee technique button.
		 */
		
	
	 public void get_AddNewCustomer(String gender, String customerName, String dateOfBirth, String address, String city,
			 String state, String pin, String mobilePhone, String email, String password) {
		 
		 bankHomepg.waitForDomToLoad();
		 addNewCustomerPg.get_BtnAddCustomer().click();
		 addNewCustomerPg.txtCustomerNameTxtBox.sendKeys(customerName);	
		 if (gender == "male") {
		 addNewCustomerPg.get_radioBtnMale().click();
		 }else {
			 addNewCustomerPg.get_radioBtnFemale().click();
		 }
		 addNewCustomerPg.txtDateOfBirthTxtBox.sendKeys(dateOfBirth);
		 addNewCustomerPg.txtAdressTxtBox.sendKeys(address);
		 addNewCustomerPg.txtCityTxtBox.sendKeys(city);
		 addNewCustomerPg.txtStateTxtBox.sendKeys(state);
		 addNewCustomerPg.txtPinTxtBox.sendKeys(pin);
		 addNewCustomerPg.txtMobilePhoneTxtBox.sendKeys(mobilePhone);
		 addNewCustomerPg.txtEmailTxtBox.sendKeys(email);
		 addNewCustomerPg.txtPasswordTxtBox.sendKeys(password);
		 addNewCustomerPg.get_BtnSubmit().click();
//		 bankLoginPg.reportLog("Login to application with LOGIN " + userName + " and PASSWORD " + password);
			TestSessionInitiator.extentTestChild.log(LogStatus.INFO, "...Get to CUSTOMER PAGE successfully!");
	 }
	 
	/*	public void  get_Donnee_Technique_link( String donnerTechnique,String code, String CodeArticle) {
			bankHomepg.waitForDomToLoad();
			//sylobTechniquePg.txt_lancher.sendKeys(parametresociete);
			sylobTechniquePg.txt_lancher.sendKeys(donnerTechnique);
			sylobTechniquePg.li_donner_technique.click();
			driver.switchTo().frame(bankHomepg.get_FrameLevel4());
	 		sylobTechniquePg.get_Add_DonneeTechnique().click();
	 		sylobTechniquePg.get_Add_Article().click();
	 		sylobTechniquePg.get_CurrentWindowHandle();
	 		sylobTechniquePg.getWindowHandle();
	        //sylobTechniquePg.get_Create_Article().click();
	 		sylobTechniquePg.get_Search_Article().click();
	 		sylobTechniquePg.SelectElementFromDropdown(sylobTechniquePg.get_Search_Article(),"visibleText",code);
			sylobTechniquePg.txt_write.sendKeys(CodeArticle);
		
		}*/
	 
}
