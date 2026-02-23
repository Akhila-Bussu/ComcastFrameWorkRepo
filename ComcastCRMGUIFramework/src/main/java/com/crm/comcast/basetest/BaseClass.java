package com.crm.comcast.basetest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

public class BaseClass {
	
	
	public WebDriver driver=null;
	public static WebDriver sdriver=null;
	public FileUtility fu=new FileUtility();
	public ExcelUtility eu=new ExcelUtility();
	public JavaUtility ju=new JavaUtility();
	public WebDriverUtility wdu=new WebDriverUtility();
	
	
	@BeforeSuite(groups={"smokeTest","RegressionTest"})
	public void confgiBS() {
		System.out.println("=====Connect to db,Report config====");
	}
	
//	@Parameters("Browser")
	@BeforeClass(groups={"smokeTest","RegressionTest"})
	public void confgiBC(/*String browser*/) throws Throwable {
		System.out.println("====launch browser===");
//		String Browser=browser;
		String Browser=fu.getDataFromPropertyFile("Browser");
		if(Browser.equals("chrome")) {
			driver=new ChromeDriver();
		}else if(Browser.equals("firefox")) {
			driver=new FirefoxDriver();
		}else if(Browser.equals("edge")){
			driver=new EdgeDriver();
		}else {
			driver=new ChromeDriver();
		}
		sdriver=driver;
	}
	@BeforeMethod(groups={"smokeTest","RegressionTest"})
	public void congiBM() throws Throwable {
		System.out.println("====login===");
		LoginPage lp=new LoginPage(driver);
		String URL=fu.getDataFromPropertyFile("URL");
		String UserName=fu.getDataFromPropertyFile("UserName");
		String Password=fu.getDataFromPropertyFile("Password");
		lp.loginToApp(URL, UserName,Password);
	}
	
	@AfterMethod(groups={"smokeTest","RegressionTest"})
	public void congiAM() {
		System.out.println("===logout===");
		HomePage hp=new HomePage(driver);
		hp.logout();
	}
	
	@AfterClass(groups={"smokeTest","RegressionTest"})
	public void confgiAC() {
		System.out.println("===close browser===");
		driver.quit();
	}
	
	@AfterSuite(groups={"smokeTest","RegressionTest"})
	public void confgiAS() {
		System.out.println("====close db,report backup====");
	}
	
	@BeforeTest
	public void configBT() {
		System.out.println("====execute pre-condition====");
	}
	
	@AfterTest
	public void configAT() {
		System.out.println("execute post condition====");
	}
}
