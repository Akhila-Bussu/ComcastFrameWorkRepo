package poi_Basics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;
import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class DWS_LoginTest_PropertyFile {

	WebDriver driver;
	@Test
	public void TC1() throws Exception {

//		Property File
		FileInputStream fis=new FileInputStream(new File("./Test_Configuration/TestConfiguration.properties.txt"));
		
		Properties prop=new Properties();
		prop.load(fis);
		
		String browsername=prop.getProperty("Browser");
		String time=prop.getProperty("timeout");
		String url=prop.getProperty("URL");
		
		if(browsername.equals("chrome")) {
			driver=new ChromeDriver();
			driver.manage().window().maximize();
			
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get(url);
		
		driver.findElement(By.linkText("Log in")).click();
		driver.findElement(By.id("Email")).sendKeys(prop.getProperty("username"));
		driver.findElement(By.id("Password")).sendKeys(prop.getProperty("password"));
		driver.findElement(By.xpath("(//input[@type='submit'])[2]")).click();
		
		
	Thread.sleep(2000);
	driver.close();
		
	}
}
