package poi_Basics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.io.File;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DWS_RegisterTest {
	
	@DataProvider(name="RegisterTestData")
	public Object[][] getTestData() throws Exception {
		String path="./TestData/DWSTestData.xlsx";
		FileInputStream fis=new FileInputStream(new File(path));
		
		Workbook workbook = WorkbookFactory.create(fis);
		Sheet sheet = workbook.getSheet("Sheet1");
		int rowcount=sheet.getPhysicalNumberOfRows();
		int colcount=sheet.getRow(0).getPhysicalNumberOfCells();
		
		Object [][] data=new Object[rowcount-1][colcount];
		
		for(int i=1;i<=rowcount-1;i++) {
			for(int j=0;j<=colcount-1;j++) {
				data[i-1][j]=sheet.getRow(i).getCell(j).toString();	
			}
		}
		return data;
	}
	@Test(dataProvider="RegisterTestData")
	public void redisterTc(String FirstName,String LastName,String Email,String Password,String confirmpassword) {
//		System.out.println(FirstName);
//		System.out.println(LastName);
//		System.out.println(Email);
//		System.out.println(Password);
//		System.out.println(confirmpassword);
		
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get("https://demowebshop.tricentis.com/");
		
		driver.findElement(By.linkText("Register")).click();
		driver.findElement(By.id("gender-female")).click();
		driver.findElement(By.id("FirstName")).sendKeys(FirstName);
		driver.findElement(By.id("LastName")).sendKeys(LastName);
		driver.findElement(By.id("Email")).sendKeys(Email);
		driver.findElement(By.id("Password")).sendKeys(Password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(confirmpassword);
		
		driver.findElement(By.id("register-button")).click();
		driver.close();
	}

}
