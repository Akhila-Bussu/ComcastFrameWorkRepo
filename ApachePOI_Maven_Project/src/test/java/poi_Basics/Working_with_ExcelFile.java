package poi_Basics;

import java.io.FileInputStream;

import java.io.File;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.Test;

public class Working_with_ExcelFile {

	@Test
	public void demo() throws Exception{
		String path="./TestData/DWS_TestData.xlsx";
		FileInputStream fis=new FileInputStream(new File(path));
		
		
//		create the workbook where the test data is stored
		Workbook workbook = WorkbookFactory.create(fis);
		
//		Access the sheet where the data is present for you to work on
		Sheet sheet = workbook.getSheet("Sheet1");
		
//		print the values of cells
//	String row_value=sheet.getRow(0).getCell(0).toString();
//	System.out.println(row_value);
		
//		To take the rows that have been used in excel file
		int rowcount=sheet.getPhysicalNumberOfRows();
		int colcount=sheet.getRow(0).getPhysicalNumberOfCells();
//		System.out.println(rowcount);
//		System.out.println(colcount);
		
//		to print all the rows
//		for(int i=0;i<=rowcount-1;i++) {
//		String cellvalue=sheet.getRow(i).getCell(0).toString();
//			System.out.println(cellvalue);
//		}
//		to print entry values
		for(int i=0;i<=rowcount-1;i++) {
			for(int j=0;j<=colcount-1;j++) {
				System.out.print(sheet.getRow(i).getCell(j).toString()+" ");
			}
			System.out.println();
		}
		
}
	
}
