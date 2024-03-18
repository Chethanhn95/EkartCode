package rahulshettyacademy.Abstractcomponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.pageobjects.Cartpage;
import rahulshettyacademy.pageobjects.Orderpage;

public class Abstractcomponent {
	
	WebDriver driver;
	public Abstractcomponent(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	@FindBy(xpath="//button[@routerlink=\"/dashboard/cart\"]")
	WebElement Cart;
	@FindBy(css="[routerlink*='myorders']")
	WebElement Orders;
	
	
	public void Explicitwait(By findBy) {
	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
	wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));

}
	public void Explicitwaitforfindby(WebElement findBy) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	public Cartpage gotocartpage() {
		Cart.click();
		Cartpage cartpage=new Cartpage(driver);
		return cartpage;
		
	}
	public Orderpage gotoorderpage() {
		Orders.click();
		Orderpage orderpage=new Orderpage(driver);
		return orderpage;
		
	}
	public void invisiblilty(WebElement ele) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOf(ele));

	}
}
