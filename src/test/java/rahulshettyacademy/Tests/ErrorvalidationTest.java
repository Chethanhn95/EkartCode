package rahulshettyacademy.Tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import rahulshettyacademy.Testcomponents.BaseTest;
import rahulshettyacademy.pageobjects.Cartpage;
import rahulshettyacademy.pageobjects.Productcatalogue;

public class ErrorvalidationTest extends BaseTest {
	@Test
	public void Loginerrorvalidation() throws IOException {

		login.Loginpageactions("hnchethan95@gmail.com", "Chethan.");
		login.errormessagewrongcred();
		Assert.assertEquals(login.errormessagewrongcred(), "Incorrect email  password.");
	}

	@Test(groups = { "ErrorHandling" })
	public void producterrorvalidation() throws IOException {
		String productname = "ADIDAS ORIGINAL";

		Productcatalogue productcatalogue = login.Loginpageactions("anshika@gmail.com", "Iamking@000");
		productcatalogue.addtocart(productname);
		Cartpage cartpage = productcatalogue.gotocartpage();

		Boolean match = cartpage.verification("orginall");
		Assert.assertFalse(match);

	}

}
