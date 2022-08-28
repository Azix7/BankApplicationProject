package com.az.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

// TODO: Auto-generated Javadoc
/**
 * The Class SylobArticlesPageUI.
 */
public class DepositPageUI extends BasePage {
	/** The driver. */
	WebDriver driver;

	/**
	 * Instantiates a new article page UI.
	 *
	 * @param driver the driver
	 */
	public DepositPageUI(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	

	/**
	 * Gets the option disconnect image options BOM.
	 *
	 * @param appID the app ID
	 * @return the option disconnect image options BOM
	 */
	public WebElement get_OptionDisconnectImage_OptionsBOM(String appID) {
		//return expWait.getWhenVisible(By.xpath("//div[contains(.,'" + appID + "') and @id='tbrc']//img"), 45);
		try{
		//return expWait.getWhenVisible(By.xpath("//tr[contains(.,'"+appID+"')]//td[contains(@oncontextmenu,'return cellctxm')]/*"), 15);
		return expWait.getWhenVisible(By.xpath("//tr[contains(.,'"+appID+"')]//td//input"), 15);
		//tr[contains(.,'10-201')]//td[@class='dt-head-left unknown optionGroup1 editable']/input
		}catch(Exception e){
			return driver.findElement(By.xpath("//tr[contains(.,'"+appID+"')]//td//input"));
		}

	}
	
	/**
	 * Gets the option disconnect options BOM.
	 *
	 * @param appID the app ID
	 * @return the option disconnect options BOM
	 */
	public WebElement get_OptionDisconnect_OptionsBOM(String appID) {
		return expWait.getWhenVisible(By.xpath("//tr[contains(.,'"+appID+"')]//td//input"), 45);
	}

	
	/**
	 * Gets the checkbox option copy.
	 *
	 * @return the checkbox option copy
	 */
	public WebElement get_CheckboxOptionCopy(){
		return expWait.getWhenClickable(By.xpath("//table//td//*[@type='checkbox']"), 45);
	}
	

}
