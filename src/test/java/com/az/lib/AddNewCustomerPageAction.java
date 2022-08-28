package com.az.lib;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.az.PageObjects.BasePage;
import com.az.PageObjects.DepositPageUI;
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
