package com.az.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

// TODO: Auto-generated Javadoc
/**
 * The Class SylobLoginPageUI.
 */
public class BankLoginPageUI extends BasePage {

	/** The driver. */
	WebDriver driver;

	
	/**
	 * Instantiates a new sylob login page UI.
	 *
	 * @param driver the driver
	 */
	public BankLoginPageUI(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	
	@FindBy(xpath = "//input[contains(@name,'uid')]") 
	public WebElement txtLoginTxtBox;
	@FindBy(xpath = "//input[contains(@type,'password')]")
	public WebElement txtPasswordTxtBox;
	
	
	
	public WebElement get_Btn_login() {
		return expWait.getWhenClickable(By.xpath("//input[@name='btnLogin']"), 10);
	}
	
	
	
		
	/**
	 * Gets the txt username.
	 *
	 * @return the txt username
	 */
	public WebElement get_Txt_Username(){
		return expWait.getWhenClickable(By.xpath("//*[@name='login']"), 120);
	}
	
}	