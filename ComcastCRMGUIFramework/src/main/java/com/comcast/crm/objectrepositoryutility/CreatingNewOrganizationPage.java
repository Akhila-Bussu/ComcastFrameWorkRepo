package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreatingNewOrganizationPage {

	WebDriver driver;
	public CreatingNewOrganizationPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name="accountname")
	private WebElement orgNameEdt;
	
	@FindBy(name="ship_street")
	private WebElement shipNameEdt;
	
	@FindBy(name="industry")
	private WebElement industryDD;
	
	@FindBy(name="accounttype")
	private WebElement typeDD;
	
	@FindBy(xpath="//input[@id='phone']")
	private WebElement phoneEdt;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement submitBtn;


	public WebElement getOrgNameEdt() {
		return orgNameEdt;
	}

	public WebElement getShipNameEdt() {
		return shipNameEdt;
	}
	
	public WebElement getPhoneEdt(String orgNames,String phone,String shipping) {
		orgNameEdt.sendKeys(orgNames);
		phoneEdt.sendKeys(phone);
		shipNameEdt.sendKeys(shipping);
		submitBtn.click();
		return phoneEdt;
	}

	public WebElement getIndustry() {
		return industryDD;
	}

	public WebElement getSubmitBtn() {
		return submitBtn;
	}
	
	public void createOrg(String orgName,String shipping) {
		orgNameEdt.sendKeys(orgName);
		shipNameEdt.sendKeys(shipping);
		submitBtn.click();
		
	}
	public void createOrg(String orgName,String shipping,String industry) {
		orgNameEdt.sendKeys(orgName);
		shipNameEdt.sendKeys(shipping);
		Select sel=new Select(industryDD);
		sel.selectByVisibleText(industry);
		submitBtn.click();	
	}

	public void createOrg(String orgName,String shipping,String industry,String type) {
		orgNameEdt.sendKeys(orgName);
		Select sel=new Select(typeDD);
		sel.selectByVisibleText(industry);
		sel.selectByVisibleText(type);
		submitBtn.click();	
	}
	public WebElement getTypeDD() {
		return typeDD;
	}
	
}
