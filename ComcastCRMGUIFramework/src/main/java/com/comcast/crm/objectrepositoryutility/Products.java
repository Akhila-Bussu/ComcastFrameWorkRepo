package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Products {

	
	@FindBy(xpath="//input[@alt='Create Product...']")
	private WebElement productImgBtn;
	
	@FindBy(name="search")
	private WebElement ele1;
	
	@FindBy(name="user_name")
	private WebElement ele3;
	
}
