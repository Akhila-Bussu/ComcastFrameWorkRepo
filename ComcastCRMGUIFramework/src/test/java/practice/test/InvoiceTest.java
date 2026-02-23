package practice.test;

import org.jspecify.annotations.Nullable;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.comcast.basetest.BaseClass;

//@Listeners(com.comcast.crm.listenerutility.ListernerImplemets.class)
@Listeners(com.comcast.crm.listenerutility.ListernersPractice.class)
public class InvoiceTest extends BaseClass{

	@Test
	public void createInvoiceTest() {
		System.out.println("executing InvoiceTest");
		String title = driver.getTitle();
		Assert.assertEquals("", "LoginIN");
		System.out.println("Step-1");
		System.out.println("step-2");
		System.out.println("step-3");
		
	}
}
