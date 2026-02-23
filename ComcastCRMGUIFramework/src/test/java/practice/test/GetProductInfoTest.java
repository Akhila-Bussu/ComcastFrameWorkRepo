package practice.test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class GetProductInfoTest {

	@Test(dataProvider = "getData")
	public void getProductTest(String productname) throws InterruptedException {
		
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://demowebshop.tricentis.com/");
	
		
//		Search the product
		Thread.sleep(2000);
		String x="//div[@data-productid='"+productname+"']/../div[@class='product-item']/div[2]/div[3]/div[1]";
		String price=driver.findElement(By.xpath(x)).getText();
		System.out.println(price);
		driver.quit();
	}
	
	@DataProvider
	public Object[][] getData(){
		Object[][] obj=new Object[3][1];
		
		obj[0][0]="2";
		 
		obj[1][0]="31";
		
		obj[2][0]="72";
		
		return obj;
		
		
	}
}
