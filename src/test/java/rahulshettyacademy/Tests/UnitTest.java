package rahulshettyacademy.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class UnitTest {
	public static void main (String[]args) {
		String productname="ADIDAS ORIGINAL";
//	https://rahulshettyacademy.com/client/auth/login
	WebDriverManager.chromedriver().setup();
	WebDriver driver=new ChromeDriver();
	driver.manage().deleteAllCookies();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	driver.manage().window().maximize();
	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
	driver.get("https://rahulshettyacademy.com/client");
	driver.findElement(By.id("userEmail")).sendKeys("hnchethan95@gmail.com");
	driver.findElement(By.id("userPassword")).sendKeys("Chethan78.");
	driver.findElement(By.id("login")).click();
//	ng-tns-c4-5 toast-title ng-star-inserted
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
	String toaster=driver.findElement(By.id("toast-container")).getText();
	Assert.assertEquals(toaster, "Login Successfully");
	
	List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
	WebElement prod = products.stream()
			.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst()
			.orElse(null);
	prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
	wait.until(ExpectedConditions.visibilityOfElementLocated((By.id("toast-container"))));
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("ng-animating")));
	driver.findElement(By.xpath("//button[@routerlink=\"/dashboard/cart\"]")).click();
	List<WebElement> cartproducts = driver.findElements(By.cssSelector(".cartSection h3"));
	Boolean match = cartproducts.stream().anyMatch(cart -> cart.getText().equalsIgnoreCase(productname));
	Assert.assertTrue(match);
	driver.findElement(By.cssSelector(".totalRow button")).click();
	driver.findElement(By.xpath("//input[@placeholder=\"Select Country\"]")).sendKeys("IND");
	List<WebElement> counrty_list = driver.findElements(By.cssSelector(".ta-results button span"));
	for (WebElement countrylist : counrty_list) {
		if (countrylist.getText().equalsIgnoreCase("India")) {
			countrylist.click();
			break;
		}
	}
	WebElement Submit = driver.findElement(By.cssSelector("a.action__submit"));

	JavascriptExecutor js = (JavascriptExecutor) driver;

	js.executeScript("arguments[0].click();", Submit);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
	String orderplaced=driver.findElement(By.id("toast-container")).getText();
	Assert.assertEquals(orderplaced,"Order Placed Successfully" );
	
	String ConfirmMessgae= driver.findElement(By.cssSelector(".hero-primary")).getText();
	Assert.assertTrue(ConfirmMessgae.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	driver.close();
	
}


}

