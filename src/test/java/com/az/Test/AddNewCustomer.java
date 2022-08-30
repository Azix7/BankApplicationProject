package com.az.Test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import static com.az.Utilities.YamlReader.getYamlValue;


import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.az.CoreTestBase.TestSessionInitiator;
import com.az.PageObjects.BasePage;
import com.az.Utilities.YamlReader;



import com.az.Test.BaseTest;
/**
 * The Class SylobAddNewArticle.
 */
@Test
public class AddNewCustomer {

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
	
	/**
	 * Sets the up class.
	 */
	@BeforeClass
	public void setUpClass() {
		System.out.println("\n##############################################################################");
		System.out.println("################ Sylob Product Devlopment Test #############");
		System.out.println("##############################################################################\n\n");
		System.out.println("Test Name: setUpClass");
		System.setProperty("browser", "chrome");
		test = new TestSessionInitiator(System.getProperty("datafile", dataFile));
		
	}

	/**
	 * TC 001 login to Sylob.
	 *
	 * @throws FileNotFoundException
	 *             the file not found exception
	 */
	public void verifyloginSection() throws FileNotFoundException {
		test.launchUrl(getYamlValue("url"));
		test.bankHome.loginToBankApplication(getYamlValue("loginInfo.username"),
				getYamlValue("loginInfo.password"));
		test.bankHome.verifyUserID(getYamlValue("loginInfo.username"));
		
	}

	/**
	 * TC 002 add new article.
	 */
	@Test(dependsOnMethods = "verifyloginSection")
	public void addNewCustomer(){
        
		test.addNewCustomer.get_AddNewCustomer(getYamlValue("addCustomer.gender"),getYamlValue("addCustomer.customerName"),
				getYamlValue("addCustomer.dob"),getYamlValue("addCustomer.address"),
				getYamlValue("addCustomer.city"),getYamlValue("addCustomer.state"),
				getYamlValue("addCustomer.pin"),getYamlValue("addCustomer.phone"),
				getYamlValue("addCustomer.email"),getYamlValue("addCustomer.password"));
		
		/*test.sylobArticles.Get_To_Add_Articles();
		test.sylobArticles.Select_Unite_Gestion_From_DrpDown((getYamlValue("AddArticleFlow2.UniteDeGestion3")));
		test.sylobArticles.Fill_Txt_Boxes_Add_Article_Form(getYamlValue("AddArticleFlow2.Code"),
				getYamlValue("AddArticleFlow2.Designation"),
				getYamlValue("AddArticleFlow2.DernierPrixAchat"));
		
		test.sylobArticles.Select_Strategy_From_DrpDown(getYamlValue("AddArticleFlow2.Strategie1"));
		test.sylobArticles.Select_Type_Of_Manage_From_DrpDown();
		test.sylobArticles.Select_Politique_Lotissement_From_DrpDown(getYamlValue("AddArticleFlow2.PolitiqueDeLotissement1"));
		test.sylobArticles.Select_Unite_Achat_From_DrpDown(getYamlValue("AddArticleFlow2.UniteAchat"));
		test.sylobArticles.Select_Type_Tva_From_DrpDown(getYamlValue("AddArticleFlow2.TypeTVA"));
		test.sylobArticles.Select_Classification_Account_From_DrpDown(getYamlValue("AddArticleFlow2.ClassificationComptable"));
		test.sylobArticles.Select_Code_Valorisation_From_DrpDown(getYamlValue("AddArticleFlow2.ValoriseAu"));
		
		test.sylobArticles.get_Validate_button();*/
		
		
	}
	
	/**
	 * TC 003 add Create Operation.
	 */
	//@Test(dependsOnMethods = "addNewCustomer")
	//public void TC003_VerifyCustomerCreated(){
	
	//	test.sylobArticles.get_txt_msg();		
	//	test.sylobArticles.get_Verify_Article_Existance(getYamlValue("AddArticleFlow2.Code"));	      	  
	    
	//}
	
	/**
	 * Inits the.
	 */
	@BeforeMethod
	public void init() {
		System.out.println("\n\n__________________________________________________________________________");
	}

	/**
	 * Capture screen shot on failure.
	 * 
	 * @param result
	 *            the result
	 */
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

	
	/**
	 * Tear down class.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	@AfterClass
	public void tearDownClass() throws Exception {
		test.closeBrowserSession();
		System.out.println("###############################################################################");
		System.out.println("############################################################################### \n\n");
	}
	
}