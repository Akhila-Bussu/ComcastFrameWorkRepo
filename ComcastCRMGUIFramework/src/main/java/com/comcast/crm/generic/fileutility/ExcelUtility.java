package com.comcast.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {

	
	public String getDataFromExcelFile(String sheetName,int rowNum,int celNum) throws EncryptedDocumentException, IOException {
		
		FileInputStream fis=new FileInputStream("./testscriptData/testScriptdata.xlsx");
		
		Workbook wb = WorkbookFactory.create(fis);
		
		String data = wb.getSheet(sheetName).getRow(rowNum).getCell(celNum).getStringCellValue();
		
		return data;
	}
	
	public int getRowCount(String sheetName) throws EncryptedDocumentException, IOException {
		FileInputStream fis=new FileInputStream("./testscriptData/testScriptdata.xlsx");
		
		Workbook wb = WorkbookFactory.create(fis);
		int rowCount = wb.getSheet(sheetName).getLastRowNum();
		
		return rowCount;	
	}
	
	public void setDataIntoExcel(String sheetName,int rowNum,int celNum,String data) throws EncryptedDocumentException, IOException {
		FileInputStream fis=new FileInputStream("./testscriptData/testScriptdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		wb.getSheet(sheetName).getRow(rowNum).getCell(celNum);
		
		FileOutputStream fos=new FileOutputStream("./testscriptData/testScriptdata.xlsx");
		wb.write(fos);
		wb.close();	
	}
}
