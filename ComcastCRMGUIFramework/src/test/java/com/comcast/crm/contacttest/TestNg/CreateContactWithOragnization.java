package com.comcast.crm.contacttest.TestNg;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.jspecify.annotations.Nullable;
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
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;
import com.crm.comcast.basetest.BaseClass;

public class CreateContactWithOragnization extends BaseClass {

	@Test
	public void createContactWithOrgTest() throws EncryptedDocumentException, IOException{
		// TODO Auto-generated method stub
		
		String orgnames=eu.getDataFromExcelFile("contact", 7, 2) + ju.getRandomNumber();
		String contactLastName=eu.getDataFromExcelFile("contact", 7, 3);
		
//		NAVIGATE TO ORGANIZATION MODULE
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

//		CLICK ON CREATE ORGANIZATION BUTTON
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateNewOrg().click();

//		ENTER ALL ORGANIZATION DETAILS AND CREATE NEW ORGANIZATION
		CreatingNewOrganizationPage cp = new CreatingNewOrganizationPage(driver);
		cp.createOrg(orgnames, "pune");
		
//		VERIFY THAT HEADER MSG EXCEPTED RESULT
		String headerinfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(headerinfo.contains(orgnames)) {
			System.out.println(orgnames +" is created === PASS");
		}else {
			System.out.println(orgnames + "is not created === FAIL");
		}
		
//		VERIFY HEADER ORGNAME INFO EXCEPTED RESULT
		String actOrgname=driver.findElement(By.id("dtlview_Organization Name")).getText();
		if(actOrgname.contains(orgnames)) {
			System.out.println(orgnames +" is created === PASS");
		}else {
			System.out.println(orgnames + "is not created === FAIL");
		}
		
//		NAVIGATE TO ORGANIZATION MODULE
		hp.getContactlink().click();	
		
//		CLICK ON CREATEContact Module
		CreatingNewContact cc=new CreatingNewContact(driver);
		cc.getCreateContactLink().click();
		
//		ENTER ALL ORGANIZATION DETAILS AND CREATE NEW ORGANIZATION
		CreatingNewContactPage ccp=new CreatingNewContactPage(driver);
		ccp.getLastNameEdt(contactLastName);
		
//		ENTER ALL ORGANIZATION DETAILS AND CREATE NEW ORGANIZATION
		String startDate = ju.getSystemData();
		String endDate = ju.getRequriedDate(30);
		ccp.createContact(contactLastName, startDate, endDate);
		ccp.getSaveBtn().click();
		
//		SWITCH TO CHILD WINDOW
		
		wdu.switchToTabOnURL(driver,"module=Accounts");

		
		driver.findElement(By.xpath("//input[@id='search_txt']")).sendKeys(orgnames);
		driver.findElement(By.xpath("//input[@name='search']")).click();
		driver.findElement(By.xpath("//a[text()='"+orgnames+"']")).click();
		
//		SWITCH TO PARENT WINDOW
		wdu.switchToTabOnTitle(driver,"Contacts&action");
		
		
		driver.findElement(By.xpath("(//input[@class='crmbutton small save'])[1]")).click();
	
		
//		VERIFY HEADER PHONENUMBER INFO EXCEPTED RESULT
//		String actphone=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
//		if(actphone.trim().equals(orgnames)) {
//			System.out.println(orgnames +" is created === PASS");
//		}else {
//			System.out.println(orgnames + "is not created === FAIL");
//		}
		
//		VERIFY HEADER ORGANIZATION INFO EXCEPTED RESULT
//		String actlastname=driver.findElement(By.id("mouseArea_Organization Name")).getText();
//		String actlastname=driver.findElement(By.xpath("//input[@name='lastname']")).getText();
		String actlastname=driver.findElement(By.xpath("//span[@id='dtlview_Last Name']")).getText();
		System.out.println(actlastname);
//		System.out.println(contactLastName);
		if(actlastname.contains(contactLastName)) {
			System.out.println(contactLastName +" is created === PASS");
		}else {
			System.out.println(contactLastName + "is not created === FAIL");
		}


	}

}
