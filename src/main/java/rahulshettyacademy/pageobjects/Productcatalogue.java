package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.Abstractcomponents.Abstractcomponent;


public class Productcatalogue extends Abstractcomponent{
	WebDriver driver;
	public Productcatalogue(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);	
	}
	@FindBy(css=".mb-3")	
	List<WebElement> products;
	@FindBy(css="ng-animating")
	WebElement Spinner;
	By products1=By.cssSelector(".mb-3");
	By addtocart=By.cssSelector(".card-body button:last-of-type");
	By toastmessage=By.id("toast-container");
	
	 public List<WebElement> getlistofproducts() {
		 
		 Explicitwait(products1);
		 return products;	 
	 }
	 public WebElement getproductsbyname(String productname) {

			WebElement prod = getlistofproducts().stream()
					.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst()
					.orElse(null);
			return prod;
	 }
	 public void addtocart(String productname) {
		 WebElement prod=getproductsbyname(productname);
		 prod.findElement(addtocart).click();
		 Explicitwait(toastmessage);
		 invisiblilty(Spinner);
	 }
	
}
