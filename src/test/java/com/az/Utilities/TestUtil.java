package com.az.Utilities;


import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.az.CoreTestBase.TestSessionInitiator;



public class TestUtil extends TestSessionInitiator{
	public static String screenshotName;
	public static String screenShotPath;
	public TestUtil(String datafilelocator) {
		super(datafilelocator);
		// TODO Auto-generated constructor stub
	}

	public static void captureScreenShot() throws IOException{
		
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		screenshotName = "error.jpg";
		FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir") +"\\target\\surefire-reports\\html\\"+screenshotName));
		
	}

}
