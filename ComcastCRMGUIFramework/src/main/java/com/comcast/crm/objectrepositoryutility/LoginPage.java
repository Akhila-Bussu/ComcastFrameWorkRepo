package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;


/**
 * 
 * @author 
 * 
 * Contains Login page elements and business library like login
 **/
																				//Rule 1:POM CLASS CREATION

public class LoginPage extends WebDriverUtility{
	
	WebDriver driver;
//	Rule 2:OBJECT CREATION
	
	@FindBy(name="user_name")
	private WebElement usernameEdt;
	
	@FindBy(name="user_password")
	private WebElement passwordEdt;
	
	@FindBy(id="submitButton")
	private WebElement loginBtn;


//	Rule 3:OBJECT INITIALIZATION (PERFORM IN TEST SCRIPT USING PAGEFACTORYINITELEMENTS)
//	Rule 4:OBJECT ENCAPSULATION
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	
	public WebElement getUsenameEdt() {
		return usernameEdt;
	}

	public WebElement getPasswordEdt() {
		return passwordEdt;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}
	
//	RULE 5:OBJECT UTILIZATION(PERFORM ACTION)

	/**
	 * 
	 * @param url
	 * @param username
	 * @param password
	 */
	public void loginToApp(String url,String username,String password) {
		waitUntilPageLoad(driver);
		driver.get(url);
		driver.manage().window().maximize();
		
		usernameEdt.sendKeys(username);
		passwordEdt.sendKeys(password);
		loginBtn.click();
	}
	
}
