package com.comcast.crm.orgtest.testng;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInformationPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;
import com.crm.comcast.basetest.BaseClass;
import com.crm.comcast.basetest.BaseClass1;

public class CreateOrganizationTest extends BaseClass1 {

	@Test(groups="smokeTest")
	public void createOrganizationTest() throws EncryptedDocumentException, IOException {
		// TODO Auto-generated method stub
System.out.println("organization.....");
//		Reading data from excel file
		String orgnames = eu.getDataFromExcelFile("org", 1, 2) + ju.getRandomNumber();

//		NAVIGATE TO ORGANIZATION MODULE
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
//		hp.navigateToMore();

//		CLICK ON CREATE ORGANIZATION BUTTON
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateNewOrg().click();

//		ENTER ALL ORGANIZATION DETAILS AND CREATE NEW ORGANIZATION
		CreatingNewOrganizationPage cp = new CreatingNewOrganizationPage(driver);
		cp.createOrg(orgnames, "pune");

//		VERIFY THAT HEADER MSG EXCEPTED RESULT
		OrganizationInformationPage oip = new OrganizationInformationPage(driver);
		String actualOrgName = oip.getHeaderMsg().getText();
		if (actualOrgName.contains(orgnames)) {
			System.out.println(orgnames + " name is verified == PASS");
		} else {
			System.out.println(orgnames + " name is not verified == FAIL");
		}
	}
	
	@Test(groups="RegressionTest")
	public void createOrgWithContact() throws EncryptedDocumentException, IOException {
//		Reading data from excel file 
		String orgnames=eu.getDataFromExcelFile("org", 6, 2) + ju.getRandomNumber();
		String phonenumber=eu.getDataFromExcelFile("org", 6, 3); 
		
//		NAVIGATE TO ORGANIZATION MODULE
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
		
//		CLICK ON CREATE ORGANIZATION BUTTON
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateNewOrg().click();
		
//		ENTER ALL ORGANIZATION DETAILS AND CREATE NEW ORGANIZATION
		CreatingNewOrganizationPage cp = new CreatingNewOrganizationPage(driver);
		cp.getPhoneEdt(orgnames, phonenumber, "Pune");
		
//		VERIFY HEADER ORGNAME INFO EXCEPTED RESULT
		String actphone=driver.findElement(By.id("dtlview_Phone")).getText();
		if(actphone.contains(phonenumber)) {
			System.out.println(phonenumber +" is created === PASS");
		}else {
			System.out.println(phonenumber + "is not created === FAIL");
		}
	}
	
	@Test(groups="RegressionTest")
	public void createOrgWithIndustry() throws EncryptedDocumentException, IOException {
//		Reading data from excel file
		String orgnames= eu.getDataFromExcelFile("org", 4, 2)+ ju.getRandomNumber();
		String industry=eu.getDataFromExcelFile("org", 4, 3).toString();
		String type=eu.getDataFromExcelFile("org", 4, 4).toString();
		
//		NAVIGATE TO ORGANIZATION MODULE
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
		
//		CLICK ON CREATE ORGANIZATION BUTTON
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateNewOrg().click();
		
//		ENTER ALL ORGANIZATION DETAILS AND CREATE NEW ORGANIZATION
		CreatingNewOrganizationPage cp = new CreatingNewOrganizationPage(driver);
		cp.createOrg(orgnames, "pune", industry);
		
//		VERIFY THE INDUSTRY AND TYPE INFO
		String actIndustry=driver.findElement(By.id("dtlview_Industry")).getText();
		if(actIndustry.contains(industry)) {
			System.out.println(industry +" is created === PASS");
		}else {
			System.out.println(industry + "is not created === FAIL");
		}
		String actType=driver.findElement(By.id("dtlview_Type")).getText();
		if(actType.contains(type)) {
			System.out.println(type +" is created === PASS");
		}else {
			System.out.println(type + "is not created === FAIL");
		}

	}

}
