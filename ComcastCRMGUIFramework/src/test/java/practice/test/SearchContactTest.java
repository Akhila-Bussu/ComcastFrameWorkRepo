package practice.test;

import org.testng.annotations.Test;

import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.crm.comcast.basetest.BaseClass;

/**
 * test class for Contact module
 * @author bussu
 **/

public class SearchContactTest extends BaseClass {
	/**
	 * 
	 * Scenario:login()==>navigateContact==>createContact()==>verify
	 * 
	 */
	
	@Test
	public void searchContactTest() {
		
		/*Step1:Login to App*/
		LoginPage lp=new LoginPage(driver);
		lp.loginToApp("url", "username", "password");
	}

}
