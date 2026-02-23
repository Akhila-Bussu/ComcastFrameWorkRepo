package com.comcast.crm.contacttest.TestNg;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.CreatingNewContact;
import com.comcast.crm.objectrepositoryutility.CreatingNewContactPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.crm.comcast.basetest.BaseClass;

public class CreateContactTest extends BaseClass {	
	@Test(groups="smoke")
	public void createContactTest() throws EncryptedDocumentException, IOException {	
		
//		Reading data from excel file
		String lastname = eu.getDataFromExcelFile("contact", 1, 2) +ju.getRandomNumber();
		
//		NAVIGATE TO ORGANIZATION MODULE
		HomePage hp=new HomePage(driver);
		hp.getContactlink().click();	
		
//		CLICK ON CREATEContact Module
		CreatingNewContact cc=new CreatingNewContact(driver);
		cc.getCreateContactLink().click();
		
//		ENTER ALL ORGANIZATION DETAILS AND CREATE NEW ORGANIZATION
		CreatingNewContactPage ccp=new CreatingNewContactPage(driver);
		ccp.getLastNameEdt(lastname);
		ccp.getSaveBtn().click();
		
//		VERIFY HEADER ORGNAME INFO EXCEPTED RESULT
		String actHeader=cc.getHeaderMsg().getText();
		boolean status=actHeader.contains(lastname);
		Assert.assertTrue(status);
		String actlastname=cc.getLastMsg().getText();
		boolean status1=actlastname.contains(actlastname);
		Assert.assertTrue(status1);
	}
	
	@Test(groups="Regression")
	public void createContactWithSupportDate() throws EncryptedDocumentException, IOException {
//		Reading data from excel file
		String lastname=eu.getDataFromExcelFile("contact", 4, 2)+ju.getRandomNumber();

//		NAVIGATE TO ORGANIZATION MODULE
		HomePage hp=new HomePage(driver);
		hp.getContactlink().click();	
		
//		CLICK ON CREATEContact Module
		CreatingNewContact cc=new CreatingNewContact(driver);
		cc.getCreateContactLink().click();
		
//		ENTER ALL DETAILS AND CREATE NEW CONTACT
		CreatingNewContactPage ccp=new CreatingNewContactPage(driver);
		ccp.getLastNameEdt(lastname);
		
		String startDate = ju.getSystemData();
		String endDate = ju.getRequriedDate(30);
		ccp.createContact(lastname, startDate, endDate);
		ccp.getSaveBtn().click();
		
//		VERIFY AND VALIDATE
		String actStartDate=driver.findElement(By.id("dtlview_Support Start Date")).getText();
		if(actStartDate.contains(startDate)) {
			System.out.println(startDate +" information is verified === PASS");
		}else {
			System.out.println(startDate + "information is not verified === FAIL");
		}
		
//		Verify Date
		String actEndDate=driver.findElement(By.id("dtlview_Support End Date")).getText();
		if(actEndDate.contains(endDate)) {
			System.out.println(endDate +" information is verified === PASS");
		}else {
			System.out.println(endDate + "information is not verified === FAIL");
		}

	}
//	
}


