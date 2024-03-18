package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import rahulshettyacademy.Abstractcomponents.Abstractcomponent;

public class Checkoutpage extends Abstractcomponent{
	WebDriver driver;
	public Checkoutpage(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}
	@FindBy(xpath="//input[@placeholder=\"Select Country\"]")
	WebElement Countryfield;
	@FindBy(css=".ta-results button span")
	List<WebElement> Countries;
	@FindBy(css="a.action__submit")
	WebElement submit;
	@FindBy(id="toast-container")
	WebElement messageconfirm;
	@FindBy(css=".hero-primary")
	WebElement finalconfirm;
	By toastmessage= By.id("toast-container");


	
	public void countryselection(String Countryinput,String countryname) {
		Countryfield.sendKeys(Countryinput);
		for (WebElement countrylist : Countries) {
			if (countrylist.getText().equalsIgnoreCase(countryname)) {
				countrylist.click();
				break;
			}
		}
		
	}
	public void submitpage() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", submit);		
	}
	public String orderplacedconfirmation() {
		Explicitwait(toastmessage);
		String orderplace=messageconfirm.getText();
		return orderplace;
	}
	public String finalconfirmation() {
		String ConfirmMessgae=finalconfirm.getText();
		return ConfirmMessgae;
	}
}