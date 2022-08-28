package com.az.Test;

import static com.az.Utilities.YamlReader.getYamlValue;

import java.io.FileNotFoundException;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.az.CoreTestBase.TestSessionInitiator;

@Test
public class VerifyLoginSection {
	
	/** The test. */
	TestSessionInitiator test;

	/** The course key. */
	String courseKey;
	/** The environment. */
	String environment;
	/** The product. */
	String product;
	/** The activity name. */
	String activityName;
	String productID;
	String dataFile = "EDU_TestDonneeTechnique.yml";
	
	@BeforeClass
	public void setUpClass() {
		System.out.println("\n##############################################################################");
		System.out.println("################ Sylob Product Devlopment Test #############");
		System.out.println("##############################################################################\n\n");
		System.out.println("Test Name: setUpClass");
	//	System.setProperty("browser", "firefox");
		test = new TestSessionInitiator(System.getProperty("datafile", dataFile));
		
	}
	@Test
	public void verifyloginSection() throws FileNotFoundException {

		test.launchUrl(getYamlValue("url"));
		test.bankHome.loginToBankApplication(getYamlValue("loginInfo.username"),
				getYamlValue("loginInfo.password"));
		test.bankHome.verifyUserID(getYamlValue("loginInfo.username"));
		test.bankHome.Log_Out_From_Application();
		
		test.bankHome.loginToBankApplicationWithInvalidID(getYamlValue("loginInfo.invalidUserName"),
				getYamlValue("loginInfo.password"));
		
		test.bankHome.loginToBankApplicationWithInvalidPassword(getYamlValue("loginInfo.username"),
				getYamlValue("loginInfo.invalidPassword"));
		
		test.bankHome.loginToBankApplicationWithInvalidIDAndPassword(getYamlValue("loginInfo.invalidUserName"),
				getYamlValue("loginInfo.invalidPassword"));
	}
	

	/**
	 * TC 002 add new donnees teschnique.
	 */
	//@Test(dependsOnMethods = "TC001_loginToSylob")
	//public void TC002_AddNewdonnerTechnique(){
		//test.sylobDonneeTechnique.get_Donnee_Technique_link(getYamlValue("AddDonneeTechnique.DonneeTechniqueLancher"),
			//	                                           getYamlValue("AddDonneeTechnique.Code"),
				//                                           getYamlValue("AddDonneeTechnique.CodeArticle"));
//	}
	

	@AfterMethod
	public void captureScreenShotOnFailure(ITestResult result) {
		System.out.println("******************************************************");
		System.out.println("Test Name: " + result.getName());
		if (!result.isSuccess()) {
			System.out.println("Test Result: FAIL");
			if (getYamlValue("selenium.server").equalsIgnoreCase("remote")) {
				test.takeScreenshotOfFailure(result);
			} else {
				test.takeScreenshotOfFailureFromLocalMachine(result);
			}
		} else {
			System.out.println("Test Result: PASS");
		}
		System.out.println("******************************************************");
		System.out.println("__________________________________________________________________________");
	}
	@AfterClass
	public void tearDownClass() throws Exception {
		test.closeBrowserSession();
		System.out.println("###############################################################################");
		System.out.println("############################################################################### \n\n");
	}

}
