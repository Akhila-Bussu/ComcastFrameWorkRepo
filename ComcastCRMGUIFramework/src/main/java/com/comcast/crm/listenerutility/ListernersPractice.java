package com.comcast.crm.listenerutility;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.crm.comcast.basetest.BaseClass;

public class ListernersPractice implements ITestListener,ISuiteListener {

	ExtentReports reports;
	ExtentTest test;
	@Override
	public void onStart(ISuite suite) {
		ExtentSparkReporter spark= new ExtentSparkReporter("./LowLevelReport/VtigerReport.html");
		spark.config().setDocumentTitle("AdvanceReports");
		spark.config().setReportName("VtigerReport");
		spark.config().setTheme(Theme.STANDARD);
		reports=new ExtentReports();
		reports.attachReporter(spark);
		reports.setSystemInfo("Laptop", "Dell");
		reports.setSystemInfo("OS", "Windows");
		reports.setSystemInfo("Browser", "chrome");
		
	}

	@Override
	public void onFinish(ISuite suite) {
		reports.flush();
		
	}

	@Override
	public void onTestStart(ITestResult result) {
		String testCase = result.getMethod().getMethodName();
//		Reporter.log(testCase + " Execution Started ");
		test=reports.createTest(testCase);
		test.log(Status.INFO, testCase + " Execution Started ");
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testCase = result.getMethod().getMethodName();
//		Reporter.log(testCase + " Execution Passed");
		test.log(Status.PASS,testCase + " Execution Passed");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testCase = result.getMethod().getMethodName();
//		Reporter.log(testCase + " Execution Failed ");
		test.log(Status.FAIL, testCase + " Execution Failed ");
		TakesScreenshot ts=(TakesScreenshot)BaseClass.sdriver;
		String time= new Date().toString().replace(" ", "_").replace(":", "_");
		File src=ts.getScreenshotAs(OutputType.FILE);
		File dest=new File("./ScreenShot/"+testCase+ "+"+time+"failureshots.png");
		try {
			FileHandler.copy(src, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String testCase = result.getMethod().getMethodName();
//		Reporter.log(testCase + " Execution Skipped ");
		test.log(Status.SKIP, testCase + " Execution Skipped ");
		
	}

		
	
}
