package poi_Basics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.io.File;
import org.testng.annotations.Test;

public class Working_with_PropertyFile {

	@Test
	public void demo() throws Exception {
		String path="./Test_Configuration/TestConfiguration.properties.txt";
		FileInputStream fis=new FileInputStream(new File(path));
		
//		create an object of properties class
		
		Properties prop=new Properties();
		prop.load(fis);
		
//		to retrieve the values from the property file
//		String fname=prop.getProperty("FirstName");
//		System.out.println(fname);
//		
//		String lname=prop.getProperty("LastName");
//		System.out.println(lname);
//		
//		String company=prop.getProperty("Company");
//		System.out.println(company);
		
		
		
		
		
	}
}
