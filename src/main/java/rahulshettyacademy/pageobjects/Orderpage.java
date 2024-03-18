package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.Abstractcomponents.Abstractcomponent;

public class Orderpage extends Abstractcomponent{
	WebDriver driver;
	
	public Orderpage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
		
	}
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> orderedProducts;
	@FindBy(css=".totalRow button")
	WebElement checkout;
	
	
	public Checkoutpage checkoutpage() {
		checkout.click();
		Checkoutpage checkoutpage=new Checkoutpage(driver);
		return checkoutpage;
	}
	public Boolean verifyorderdisplay(String productname) {
		Boolean match = orderedProducts.stream().anyMatch(cart -> cart.getText().equalsIgnoreCase(productname));
		return match;
	}

}
