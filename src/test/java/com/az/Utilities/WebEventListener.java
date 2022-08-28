package com.az.Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.testng.Reporter;

// TODO: Auto-generated Javadoc
/**
 * The listener interface for receiving webEvent events.
 * The class that is interested in processing a webEvent
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addWebEventListener<code> method. When
 * the webEvent event occurs, that object's appropriate
 * method is invoked.
 *
 * @see WebEventEvent
 */
public class WebEventListener extends AbstractWebDriverEventListener {
	
	/* (non-Javadoc)
	 * @see org.openqa.selenium.support.events.AbstractWebDriverEventListener#beforeNavigateTo(java.lang.String, org.openqa.selenium.WebDriver)
	 */
	public void beforeNavigateTo(String url, WebDriver driver) {
		System.out.println("Before navigating to: '" + url + "'");
	}

	/* (non-Javadoc)
	 * @see org.openqa.selenium.support.events.AbstractWebDriverEventListener#afterNavigateTo(java.lang.String, org.openqa.selenium.WebDriver)
	 */
	public void afterNavigateTo(String url, WebDriver driver) {
		//System.out.println("Navigated to:'" + url + "'");
	}

	/* (non-Javadoc)
	 * @see org.openqa.selenium.support.events.AbstractWebDriverEventListener#beforeClickOn(org.openqa.selenium.WebElement, org.openqa.selenium.WebDriver)
	 */
	public void beforeClickOn(WebElement element, WebDriver driver) {
		String elementText = element.getText();
		try {
			if (!elementText.isEmpty()) {
				if (elementText.length() < 100) {
					System.out.println("Clicked on: " + element.getText());
					Reporter.log("Clicked on: " + element.getText());
				}
			}
		} catch (Exception e) {
			elementText = element.getAttribute("textcontent");
			try {
				if (!elementText.isEmpty()) {
					if (elementText.length() < 100) {
						System.out.println("Clicked on: " + element.getAttribute("textcontent"));
						Reporter.log("Clicked on: " + element.getAttribute("textcontent"));
					}
				}
			} catch (Exception ex) {
				System.out.println("Clicked on: " + element.toString());
				Reporter.log("Clicked on: " + element.toString());
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.openqa.selenium.support.events.AbstractWebDriverEventListener#afterClickOn(org.openqa.selenium.WebElement, org.openqa.selenium.WebDriver)
	 */
	public void afterClickOn(WebElement element, WebDriver driver) {
	}

	/* (non-Javadoc)
	 * @see org.openqa.selenium.support.events.AbstractWebDriverEventListener#onException(java.lang.Throwable, org.openqa.selenium.WebDriver)
	 */
	public void onException(Throwable error, WebDriver driver) {
	}
}

