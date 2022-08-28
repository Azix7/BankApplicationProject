package com.az.Utilities;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

// TODO: Auto-generated Javadoc
/**
 * The Class SeleniumWait.
 */
public class SeleniumWait {
	/** The driver. */
	WebDriver driver;

	/**
	 * Instantiates a new selenium wait.
	 * 
	 * @param driver
	 *            the driver
	 */
	public SeleniumWait(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Gets the when visible.
	 * 
	 * @param locator
	 *            the locator
	 * @param timeout
	 *            the timeout
	 * @return the when visible
	 */
	public WebElement getWhenVisible(By locator, int timeout) {
		WebElement element;
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return element;
	}

	/**
	 * Gets the when visible list.
	 *
	 * @param locator the locator
	 * @param timeout the timeout
	 * @return the when visible list
	 */
	public List<WebElement> getWhenVisibleList(By locator, int timeout) {
		List<WebElement> element;
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		element = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		return element;
	}

	/**
	 * Gets the when clickable.
	 * 
	 * @param locator
	 *            the locator
	 * @param timeout
	 *            the timeout
	 * @return the when clickable
	 */
	public WebElement getWhenClickable(By locator, int timeout) {
		WebElement element;
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		return element;
	}

	/**
	 * Click when ready.
	 * 
	 * @param locator
	 *            the locator
	 * @param timeout
	 *            the timeout
	 */
	public void clickWhenReady(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		element.click();
	}

	/**
	 * Wait for dom to load.
	 */
	public void waitForDomToLoad() {
		WebDriverWait wait = new WebDriverWait(driver, 80);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*")));
	}

	/**
	 * Wait for element to disappear.
	 *
	 * @param locator the locator
	 * @param timeOut the time out
	 */
	@SuppressWarnings("unchecked")
	public void waitForElementToDisappear(By locator, int timeOut) {
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(YamlReader.getYamlValue("selenium.timeout")),
				TimeUnit.SECONDS);
	}

	/**
	 * Gets the when visible with out implicit.
	 *
	 * @param locator the locator
	 * @param timeout the timeout
	 * @return the when visible with out implicit
	 */
	public WebElement getWhenVisibleWithOutImplicit(By locator, int timeout) {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebElement element;
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(YamlReader.getYamlValue("selenium.timeout")),
				TimeUnit.SECONDS);
		return element;
	}

	/**
	 * Wait for page title.
	 *
	 * @param title the title
	 * @param timeout the timeout
	 */
	public void waitForPageTitle(String title, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.titleContains(title));
	}

	/**
	 * The Enum ActionType.
	 */
	public enum ActionType {
		
		/** The up. */
		UP, 
 /** The down. */
 DOWN, 
 /** The pagedown. */
 PAGEDOWN, 
 /** The enter. */
 ENTER, 
 /** The tab. */
 TAB,
 DOWNENTER
	}

	/**
	 * Key board actions.
	 *
	 * @param Action the action
	 */
	public void keyBoardActions(String Action) {
		Actions actions = new Actions(driver);
		switch (ActionType.valueOf(Action.toUpperCase())) {
		case UP:
			actions.keyDown(Keys.CONTROL).sendKeys(Keys.UP).perform();
			break;
		case DOWN:
			actions.keyDown(Keys.CONTROL).sendKeys(Keys.DOWN).perform();
			break;
		case PAGEDOWN:
			actions.keyDown(Keys.CONTROL).sendKeys(Keys.PAGE_DOWN).perform();
			break;
		case ENTER:
			actions.keyDown(Keys.CONTROL).sendKeys(Keys.ENTER).perform();
			break;
		case TAB:
			actions.keyDown(Keys.CONTROL).sendKeys(Keys.TAB).perform();
			break;
		case DOWNENTER:
			actions.keyDown(Keys.CONTROL).sendKeys(Keys.DOWN).perform();
			actions.keyDown(Keys.CONTROL).sendKeys(Keys.ENTER).perform();
			break;
		default:
			reportLog("No keyboard action selected");
			break;
		}
	}

	/**
	 * Right click option select.
	 *
	 * @param selectElement the select element
	 * @param contextMenuOption the context menu option
	 */
	public void rightClickOptionSelect(WebElement selectElement, String contextMenuOption) {
		Actions oAction = new Actions(driver);
		oAction.moveToElement(selectElement);
		oAction.contextClick(selectElement).build()
				.perform(); /* this will perform right click */
		WebElement elementOpen = driver.findElement(By.linkText(
				contextMenuOption)); /*
										 * This will select menu after right click
										 */
		elementOpen.click();
	}

	/**
	 * Double click.
	 *
	 * @param element the element
	 */
	public void doubleClick(WebElement element) {
		if (element.isDisplayed() == false) {
			WebDriverWait wait = new WebDriverWait(driver, 30000);
			element = wait.until(ExpectedConditions.elementToBeClickable(element));
		}
		Actions oAction = new Actions(driver);
		oAction.doubleClick(element).build().perform();
	}

	/**
	 * Type.
	 *
	 * @param element the element
	 * @param textToType the text to type
	 */
	public void type(WebElement element, String textToType) {
		Actions act = new Actions(driver);
		act.sendKeys(element, textToType).build().perform();
	}

	/**
	 * Report log.
	 *
	 * @param message the message
	 */
	public void reportLog(String message) {
		System.out.println(message);
		Reporter.log(message);
	}
}
