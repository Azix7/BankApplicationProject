package com.az.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

// TODO: Auto-generated Javadoc
/**
 * The Class SylobHomePageUI.
 */
public class BankHomePageUI extends BasePage {
	/** The driver. */
	WebDriver driver;

	/**
	 * Instantiates a new sylob home page UI.
	 *
	 * @param driver
	 *            the driver
	 */
	public BankHomePageUI(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	
	/**  txt UserId. */
	@FindBy(xpath = "(//td[contains(.,'Manger Id :')])[2]")
	public WebElement txt_UserId;
	/** The txt box commercial folder. */
	


	/**
	 * Gets the btn LogOut.
	 *
	 * @return the btn LogOut
	 */
	public WebElement get_Btn_log_Out() {
		return expWait.getWhenClickable(By.xpath("//a[normalize-space()='Log out']"), 10);
	}

}
