package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.Abstractcomponents.Abstractcomponent;

public class Cartpage extends Abstractcomponent{
	WebDriver driver;
	
	public Cartpage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
		
	}
	@FindBy(css=".cartSection h3")
	List<WebElement> CartProducts;
	@FindBy(css=".totalRow button")
	WebElement checkout;
	
	
	public Checkoutpage checkoutpage() {
		checkout.click();
		Checkoutpage checkoutpage=new Checkoutpage(driver);
		return checkoutpage;
	}
	public Boolean verification(String productname) {
		Boolean match = CartProducts.stream().anyMatch(cart -> cart.getText().equalsIgnoreCase(productname));
		return match;
	}

}
