package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.Abstractcomponents.Abstractcomponent;


public class Loginpage extends Abstractcomponent {
	WebDriver driver;
	public Loginpage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);	
	}
	@FindBy(id="userEmail")
	WebElement userEmail;
	@FindBy(id="userPassword")
	WebElement userpassword;
	@FindBy(id="login")
	WebElement submit;
	@FindBy(id="toast-container")
	WebElement toast;
	By toastcontainer=By.id("toast-container");
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	public Productcatalogue Loginpageactions(String UserEmail, String userPassword) {
	userEmail.sendKeys(UserEmail);
	userpassword.sendKeys(userPassword);
	submit.click();
	Productcatalogue productcatalogue=new Productcatalogue(driver);
	return productcatalogue;
	}
	public String errormessagewrongcred() {
		Explicitwaitforfindby(errorMessage);
	return errorMessage.getText();	
	}
	
	public void gotourl() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	public String loginsucess() {
		Explicitwait(toastcontainer);
		return toast.getText();
		
		
	}
	
	
}
