package com.comcast.crm.listenerutility;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;


import com.crm.comcast.basetest.BaseClass;

public class ListernerImplemets implements ITestListener,ISuiteListener{
	
	
	public void onStart(ISuite suite) {
		System.out.println("Report Configuration");
	}
	
	public void onFinish(ISuite suite) {
		System.out.println("Report backup");
	}
	
	public void onTestStart(ITestResult result) {
		System.out.println("==========>"+result.getMethod().getMethodName());
	}
	
	public void onTestFailure(ITestResult result) {
		String testName=result.getMethod().getMethodName();
		
		TakesScreenshot ts=(TakesScreenshot)BaseClass.sdriver;
		File src =ts.getScreenshotAs(OutputType.FILE);
		String time= new Date().toString().replace(" ", "_").replace(":", "_");
		
		File dest=new File("./ScreenShot/"+testName+ "+"+time+".png");
		try {
			FileHandler.copy(src, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
