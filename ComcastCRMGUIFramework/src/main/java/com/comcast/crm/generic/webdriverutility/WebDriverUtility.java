package com.comcast.crm.generic.webdriverutility;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {

	
	public void waitUntilPageLoad(WebDriver driver) {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
	public void waitForElementPresent(WebDriver driver,WebElement element) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	public void switchToTabOnURL(WebDriver driver,String partialURL) {
		Set<String> cw = driver.getWindowHandles();
		Iterator<String> it = cw.iterator();
		
		while(it.hasNext()) {
			String windowID = it.next();
			driver.switchTo().window(windowID);
			
			String actualurl = driver.getCurrentUrl();
			if(actualurl.contains(partialURL)) {
				break;
			}
		}
	}
	public void switchToTabOnTitle(WebDriver driver,String partialTitle) {
		Set<String> cw = driver.getWindowHandles();
		Iterator<String> it = cw.iterator();
		
		while(it.hasNext()) {
			String windowID = it.next();
			driver.switchTo().window(windowID);
			
			String actualurl = driver.getTitle();
			if(actualurl.contains(partialTitle)) {
				break;
			}
		}
	}
	
	public void switchToFrame(WebDriver driver,int index) {
		driver.switchTo().frame(index);
	}
	public void switchToFrame(WebDriver driver,String nameID) {
		driver.switchTo().frame(nameID);
	}
	
	public void switchToFrame(WebDriver driver,WebElement element) {
		driver.switchTo().frame(element);
	}
	
	
	public void switchToAlertAccept(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	
	public void switchToAlertDismiss(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}
	
	public void select(WebElement element,	int index) {
		Select sel =new Select(element);
	
		sel.selectByIndex(index);
	}
	public void select(WebElement element, String orgName) {
		Select sel=new Select(element);
		sel.selectByVisibleText(orgName);
	}
	public void mousemoveOnElement(WebDriver driver, WebElement element) {
		Actions action= new Actions(driver);
		action.moveToElement(element).perform();
	}
	public void mousedoubleclick(WebDriver driver, WebElement element) {
		Actions action= new Actions(driver);
		action.doubleClick().perform();
	}
	
	
}
