package com.az.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddNewCustomerPageUI extends BasePage{

	/** The driver. */
	WebDriver driver;
	
	/**
	 * Instantiates a new article page UI.
	 *
	 * @param driver the driver
	 */
	public AddNewCustomerPageUI(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	


		
}
