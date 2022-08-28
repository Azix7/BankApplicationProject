package com.az.lib;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import java.io.File;

import org.openqa.selenium.WebElement;
import com.az.PageObjects.DepositPageUI;
import com.az.PageObjects.BankHomePageUI;
import com.az.PageObjects.BankLoginPageUI;


public class DepositPageAction {

	/** The driver. */
	 WebDriver driver;
	/** The Article page. */
	 DepositPageUI depositPg;
	/** The home page. */
	 BankHomePageUI bankHomePg;
	 BankLoginPageUI bankLoginPg;

	 /**
		 * Instantiates a new sylob article page action.
		 * 
		 * @param driver
		 *            the driver
		 */
	
	public DepositPageAction(WebDriver driver) {
		bankLoginPg = new BankLoginPageUI(driver);
		bankHomePg = new BankHomePageUI(driver);
		depositPg = new DepositPageUI(driver);	
		this.driver = driver;
	
	}

     	//public void Select_Code_Valorisation_From_DrpDown(String section) {		
     //		sylobArticlesPg.SelectElementFromDropdown(sylobArticlesPg.DrpBox_Article_Code_Valorisation, "visibleText", section);	
 	//			sylobArticlesPg.reportLog("Section: '" + section + "' selected Successfully");
   //  	}
 	
 	/**
 	 * Select classification comptable.
 	 *
 	 * @param section
 	 *           classification comptable.
 	 */
/*     	public void Select_Classification_Account_From_DrpDown(String section) {		
 				sylobArticlesPg.SelectElementFromDropdown(sylobArticlesPg.DrpBox_Article_Classification_account, "visibleText", section);	
     		sylobArticlesPg.reportLog("Section: '" + section + "' selected Successfully");
     	}*/
 	

	
}
	

