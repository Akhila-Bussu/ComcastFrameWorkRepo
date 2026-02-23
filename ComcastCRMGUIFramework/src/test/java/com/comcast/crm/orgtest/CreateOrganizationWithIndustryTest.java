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
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateOrganizationWithIndustryTest {

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
		String orgnames= eu.getDataFromExcelFile("org", 4, 2)+ ju.getRandomNumber();
		String industry=eu.getDataFromExcelFile("org", 4, 3).toString();
		String type=eu.getDataFromExcelFile("org", 4, 4).toString();

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
		driver.findElement(By.name("user_name")).sendKeys(UserName);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		
//		NAVIGATE TO ORGANIZATION MODULE
		driver.findElement(By.linkText("Organizations")).click();
		
//		CLICK ON CREATE ORGANIZATION BUTTON
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
//		ENTER ALL ORGANIZATION DETAILS AND CREATE NEW ORGANIZATION
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(orgnames);
		driver.findElement(By.xpath("//textarea[@name='ship_street']")).sendKeys("hyderabad");
		WebElement ind = driver.findElement(By.name("industry"));
		Select sel=new Select(ind);
		sel.selectByVisibleText(industry);
		
		WebElement ty = driver.findElement(By.name("accounttype"));
		Select sel1=new Select(ty);
		sel1.selectByVisibleText(type);		
		driver.findElement(By.xpath("(//input[@class='crmbutton small save'])[1]")).click();
		
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
		
//		LOGOUT
		driver.quit();
		
	}

}
