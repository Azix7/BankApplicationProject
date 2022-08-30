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
	
	@FindBy(xpath = "//input[@name='name']") 
	public WebElement txtCustomerNameTxtBox;
	@FindBy(xpath = "//input[@id='dob']") 
	public WebElement txtDateOfBirthTxtBox;
	@FindBy(xpath = "//textarea[@name='addr']") 
	public WebElement txtAdressTxtBox;
	@FindBy(xpath = "//input[@name='city']") 
	public WebElement txtCityTxtBox;
	@FindBy(xpath = "//input[@name='state']") 
	public WebElement txtStateTxtBox;
	@FindBy(xpath = "//input[@name='pinno']") 
	public WebElement txtPinTxtBox;
	@FindBy(xpath = "//input[@name='telephoneno']") 
	public WebElement txtMobilePhoneTxtBox;
	@FindBy(xpath = "//input[@name='emailid']") 
	public WebElement txtEmailTxtBox;
	@FindBy(xpath = "//input[@name='password']") 
	public WebElement txtPasswordTxtBox;
	
	
	public WebElement get_BtnAddCustomer() {
		return expWait.getWhenClickable(By.xpath("//a[normalize-space()='New Customer']"), 10);
	}
	
	public WebElement get_radioBtnMale() {
		return expWait.getWhenClickable(By.xpath("//input[@value='m']"), 10);
	}
	public WebElement get_radioBtnFemale() {
		return expWait.getWhenClickable(By.xpath("//input[@value='f']"), 10);
	}
	public WebElement get_BtnSubmit() {
		return expWait.getWhenClickable(By.xpath("//input[@type='submit']"), 10);
	}
	public WebElement get_BtnReset() {
		return expWait.getWhenClickable(By.xpath("//input[@value='reset']"), 10);
	}
		
}
