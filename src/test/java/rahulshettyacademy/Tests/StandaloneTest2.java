package rahulshettyacademy.Tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.Testcomponents.BaseTest;
import rahulshettyacademy.pageobjects.Cartpage;
import rahulshettyacademy.pageobjects.Checkoutpage;
import rahulshettyacademy.pageobjects.Orderpage;
import rahulshettyacademy.pageobjects.Productcatalogue;

public class StandaloneTest2 extends BaseTest {
	String productname = "ADIDAS ORIGINAL";

	@Test(dataProvider = "getData", groups = { "Purchaseorder" })
	public void submitorder(String Useremail, String userpassword, String productname) throws IOException {
		String Countryinput = "IND";
		String Countryname = "India";

		Productcatalogue productcatalogue = login.Loginpageactions(Useremail, userpassword);
		Assert.assertEquals(login.loginsucess(), "Login Successfully");

		productcatalogue.addtocart(productname);
		Cartpage cartpage = productcatalogue.gotocartpage();
		Boolean match = cartpage.verification(productname);
		Assert.assertTrue(match);
		Checkoutpage checkoutpage = cartpage.checkoutpage();

		checkoutpage.countryselection(Countryinput, Countryname);
		checkoutpage.submitpage();
		String orderplaced = checkoutpage.orderplacedconfirmation();
		Assert.assertEquals(orderplaced, "Order Placed Successfully");

		String ConfirmMessgae = checkoutpage.finalconfirmation();
		Assert.assertTrue(ConfirmMessgae.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}

	@Test(dependsOnMethods = { "submitorder" })
	public void orderconfirmation() {
		Productcatalogue productcatalogue = login.Loginpageactions("hnchethan95@gmail.com", "Chethan78.");
		Orderpage orderpage = productcatalogue.gotoorderpage();
		Assert.assertTrue(orderpage.verifyorderdisplay(productname));
	}

	@DataProvider
	public Object[][] getData() {
		return new Object[][] { { "hnchethan95@gmail.com", "Chethan78.", "ADIDAS ORIGINAL" },
				{ "anshika@gmail.com", "Iamking@000", "IPHONE 13 PRO" } };

	}

}
