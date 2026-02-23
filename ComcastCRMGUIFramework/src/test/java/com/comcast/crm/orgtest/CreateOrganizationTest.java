package com.comcast.crm.orgtest;

import java.io.FileInputStream;
import java.io.FileReader;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

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

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInformationPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class CreateOrganizationTest {

	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub
		
		FileUtility fu=new FileUtility();
		ExcelUtility eu=new ExcelUtility();
		JavaUtility ju=new JavaUtility();
		WebDriverUtility wdu=new WebDriverUtility();
		
//		Reading data from property file
		
		String Browser=fu.getDataFromPropertyFile("Browser");
		String Url=fu.getDataFromPropertyFile("URL");
		String UserName=fu.getDataFromPropertyFile("UserName");
		String password=fu.getDataFromPropertyFile("Password");
		
//		Reading data from excel file
		
		String orgnames=eu.getDataFromExcelFile("org", 1, 2)+ju.getRandomNumber();
		
		WebDriver driver=null;
		if(Browser.equals("chrome")) {
			driver=new ChromeDriver();
		}else if(Browser.equals("firefox")) {
			driver=new FirefoxDriver();
		}else if(Browser.equals("edge")){
			driver=new EdgeDriver();
		}else {
			driver=new ChromeDriver();
		}
		
//		LOGIN TO APPLICATION
		wdu.waitUntilPageLoad(driver);
		driver.get(Url);
		LoginPage lp=new LoginPage(driver);
		lp.loginToApp(UserName, password, Url);
		
//		NAVIGATE TO ORGANIZATION MODULE
		HomePage hp=new HomePage(driver);
		hp.getOrgLink().click();
//		hp.navigateToMore();
		
//		CLICK ON CREATE ORGANIZATION BUTTON
		OrganizationsPage op=new OrganizationsPage(driver);
		op.getCreateNewOrg().click();

		
//		ENTER ALL ORGANIZATION DETAILS AND CREATE NEW ORGANIZATION
		CreatingNewOrganizationPage cp=new CreatingNewOrganizationPage(driver);
		cp.createOrg(orgnames, "pune");
		
//		VERIFY THAT HEADER MSG EXCEPTED RESULT
		OrganizationInformationPage oip=new OrganizationInformationPage(driver);
		String actualOrgName=oip.getHeaderMsg().getText();
		if(actualOrgName.contains(orgnames)) {
			System.out.println(orgnames+" name is verified == PASS");
		}else {
			System.out.println(orgnames+ " name is not verified == FAIL");
		}
//		VERIFY HEADER ORGNAME INFO EXCEPTED RESULT

		
//		LOGOUT
		hp.logout();
		
		driver.quit();
		
	}

}
