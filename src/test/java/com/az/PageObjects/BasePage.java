package com.az.PageObjects;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.az.Utilities.SeleniumWait;
import com.az.Utilities.YamlReader;
import com.az.Utilities.StringMatcher;


// TODO: Auto-generated Javadoc
/**
 * The Class BasePage.
 */
public class BasePage extends SeleniumWait {
	
	/** The driver. */
	protected WebDriver driver;
	/** The exp wait. */
	protected SeleniumWait expWait;

	/**
	 * Instantiates a new base page.
	 *
	 * @param driver
	 *            the driver
	 */
	public BasePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		this.driver = driver;
		expWait = new SeleniumWait(driver);
	}

	/**
	 * verify page title
	 * 
	 * @param pageTitle
	 */
	public boolean verifyPageTitle(String pageTitle) {
		System.out.println("Page Title : " + driver.getTitle());
		return StringMatcher.match(driver.getTitle(), pageTitle, false);
	}
	
	

	/**
	 * Gets the page title.
	 *
	 * @return the page title
	 */
	public String getPageTitle() {
		return driver.getTitle();
		 
	}

	/**
	 * Gets the element by index.
	 *
	 * @param elementlist
	 *            the elementlist
	 * @param index
	 *            the index
	 * @return the element by index
	 */
	protected WebElement getElementByIndex(List<WebElement> elementlist, int index) {
		return elementlist.get(index);
	}

	/**
	 * Gets the element by text.
	 *
	 * @param elementlist
	 *            the elementlist
	 * @param elementtext
	 *            the elementtext
	 * @return the element by text
	 */
	protected WebElement getElementByText(List<WebElement> elementlist, String elementtext) {
		WebElement element = null;
		for (WebElement elem : elementlist) {
			if (elem.getText().equalsIgnoreCase(elementtext)) {
				element = elem;
			}
		}
		if (element == null) {
		}
		return element;
	}

	/**
	 * Gets the window handle.
	 *
	 * @return the window handle
	 */
	public void getWindowHandle() {
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
		driver.manage().window().maximize();
		reportLog("Switch to new window. Title : " + driver.getTitle());
	}

	/**
	 * Gets the current window handle.
	 *
	 * @return the current window handle
	 */
	public String get_CurrentWindowHandle() {
		reportLog("Get current window. Title : " + driver.getTitle());
		return driver.getWindowHandle();
	}
	

	/**
	 * Gets the token name.
	 *
	 * @param token
	 *            the token
	 * @return the token name
	 */
	public void get_TokenName(String token) {
		waitLong(token.length());
	}

	/**
	 * Switch to frame.
	 *
	 * @param stf
	 *            the stf
	 */
	public void switchToFrame(String stf) {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(stf));
		} catch (Exception e) {
			reportLog("Try again for iFrame: " + stf);
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(stf));
		}
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(YamlReader.getYamlValue("selenium.timeout")),
				TimeUnit.SECONDS);
	}

	/**
	 * Switch to frame with render.
	 *
	 * @param stf the stf
	 */
	public void switchToFrameWithRender(String stf) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(stf));
	}

	/**
	 * Switch to default content.
	 */
	public void switchToDefaultContent() {
		driver.switchTo().defaultContent();
	}
	
	
	public void switchToPopUp() {
		
		driver.switchTo().alert();
	}

	/**
	 * Click on log out.
	 */
	public void clickOnLogOut() {
		driver.findElement(By.xpath(".//*[@class[contains(.,'user-menu-link')]]")).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById('logout_link').click();");
	}

	/**
	 * Wait for sync page.
	 */
	public void waitForSyncPage() {
		expWait.waitForDomToLoad();
	}

	/**
	 * Wait long.
	 *
	 * @param i
	 *            the i
	 */
	public void waitLong(int i) {
		try {
			Thread.sleep(i * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void MouseHouver(By locator, String elementName) throws Exception{
		
        try {
     // 	 InvokeBrowser.childTest.pass(" Able to Mouse Houver on :" + elementName );  
			Actions a = new Actions(driver);
			
			WebElement mo = driver.findElement(locator);
			a.moveToElement(mo).build().perform();
			
		} catch (Exception e) {
		//	InvokeBrowser.childTest.fail(" unable to Mouse Houver on :" + elementName + InvokeBrowser.childTest.addScreenCaptureFromPath(ScreenShot(driver)));  
			e.printStackTrace();
		}

	}
	/**
	 * Wait long min.
	 *
	 * @param i the i
	 */
	public void waitLongMin(int i) {
		try {
			Thread.sleep(i * 60 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Check week slider spinner to disappear.
	 */
	public void checkWeekSliderSpinnerToDisappear() {
		try {
			waitLong(5);
			// expWait.waitForElementToDisappear(By.id("weekslider"), 5);
		} catch (Exception e) {
			waitLong(5);
		}
	}

	/**
	 * Check week slider spinner to appear.
	 */
	public void checkWeekSliderSpinnerToAppear() {
		try {
			waitLong(3);
			// expWait.waitForElementToDisappear(By.id("weekslider"), 2);
		} catch (Exception e) {
			waitLong(2);
		}
	}

	/**
	 * Execute javascript.
	 *
	 * @param script
	 *            the script
	 */
	public void executeJs(String script) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(script, (Object) null);
	}

	/**
	 * Select element from dropdown.
	 *
	 * @param selectElement
	 *            the select element
	 * @param selectorType
	 *            the selector type
	 * @param sel
	 *            the sel
	 */
	public void SelectElementFromDropdown(WebElement selectElement, String selectorType, String sel) {
		Select select = new Select(selectElement);
		if (selectorType.equalsIgnoreCase("index")) {
			select.selectByIndex(Integer.parseInt(sel));
		}
		if (selectorType.equalsIgnoreCase("visibleText")) {
			select.selectByVisibleText(sel);
			
		}
		if (selectorType.equalsIgnoreCase("value")) {
			select.selectByValue(sel);
		}
	}
	
	public boolean getSelectedDropdownValue(String value) {
		WebElement drdn = driver.findElement(By.xpath("//*[@name='societe']" + value + "\")]"));
		// WebElement DrpDwn_selectedValue = driver.findElement(By
		// .xpath("//select/option[text()=\"" + value + "\"]"));
		System.out.println(drdn.getText());
		drdn.click();
		if (drdn.getText().equalsIgnoreCase(value))
			return true;
		return false;
	}
	
	public void selectByHit(WebElement element, String testdata)
	{	
		List<WebElement> options = element.findElements(By.className("option"));
			for(WebElement option : options) 
			{

				if((option.getText()).equals(testdata))
				{
								option.click();
								break;
							}
						}
					}
	


	/**
	 * Scroll into view.
	 *
	 * @param element the element
	 */
	public void scrollIntoView(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	/* (non-Javadoc)
	 * @see com.edu.Utilities.SeleniumWait#reportLog(java.lang.String)
	 */
	public void reportLog(String message) {
		System.out.println(message);
		Reporter.log(message);
	}

	/**
	 * Switch to active element.
	 */
	public void switchToActiveElement() {
		driver.switchTo().activeElement();
	}
	
	

	/**
	 * Check product plan spinner to disappear.
	 */
	public void checkProductPlanSpinnerToDisappear() {
		try {
			expWait.waitForElementToDisappear(By.xpath("//div[@id='loadingSpinner']"), 90);			
		} catch (Exception e) {
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	}
}
