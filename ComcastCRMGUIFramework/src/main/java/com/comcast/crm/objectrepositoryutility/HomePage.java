package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;
	public HomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
//	RULE 2:OBJECT CREATION
	@FindBy(linkText="Organizations")
	private WebElement orgLink;

//	@FindBy(linkText="Campaigns")
//	private WebElement campaignLink;
	
	@FindBy(linkText="Contacts")
	private WebElement contactlink;
	
	@FindBy(linkText="More")
	private WebElement moreLink;

	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminImg;
	
	@FindBy(linkText="Sign Out")
	private WebElement signOutLink;
	
//	RULE 3:OBJECT INITIALZATION
	
	public WebElement getOrgLink() {
		return orgLink;
	}
//	public WebElement getCampaignLink() {
//		return campaignLink;
//	}

	public WebElement getMoreLink() {
		return moreLink;
	}
	
	public WebElement getContactlink() {
		return contactlink;
	}
	

	//	public void navigateToMore() {
//		Actions action=new Actions(driver);
//		action.moveToElement(moreLink).perform();
//		campaignLink.click();
//	}
	public void logout() {
		Actions action=new Actions(driver);
		action.moveToElement(adminImg).perform();
		signOutLink.click();
	}
}
