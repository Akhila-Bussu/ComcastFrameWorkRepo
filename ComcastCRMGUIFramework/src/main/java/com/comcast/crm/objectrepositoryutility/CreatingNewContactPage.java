package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingNewContactPage {

	WebDriver driver;
	public CreatingNewContactPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@name='lastname']")
	private WebElement lastNameEdt;
	
	@FindBy(xpath="//input[@name='support_start_date']")
	private WebElement supportDate;
	
	@FindBy(xpath="//input[@name='support_end_date']")
	private WebElement endDate;
	
	@FindBy(xpath="(//input[@type='submit'])[1]")
	private WebElement saveBtn;
	
	public WebElement getLastNameEdt(String lastname) {
		lastNameEdt.sendKeys(lastname);
		return lastNameEdt;
	}

	public WebElement getSupportDate(String date) {
		supportDate.sendKeys();
		return supportDate;
	}
	
	public WebElement getSaveBtn() {
		return saveBtn;
	}

	public void createContact(String lastname,String startdate,String enddate) {
		CreatingNewContactPage ccp=new CreatingNewContactPage(driver);
		ccp.lastNameEdt.sendKeys(lastname);
		ccp.supportDate.sendKeys(startdate);
		ccp.endDate.sendKeys(enddate);
	}
	
	
}
