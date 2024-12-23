package Chrome.Printemps2;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Chrome.Printemps2.BaseTest1;
import Chrome.Printemps2.orderspom;

/*Objective : 
 * The shopping cart is where customers store products they intend to purchase. 
 * Key features include:
Add/Remove Products
Seamless transition to checkout
 */

@Listeners(Chrome.Printemps2.testlisteners.class) 

public class orders {
 
	WebDriver driver;
	WebDriverWait wait;
	orderspom pom;
	
	@BeforeTest
	public void setup() throws IOException {
		/*driver = new FirefoxDriver();
		  wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		 driver.manage().window().maximize();*/
		
		driver = BaseTest1.getDriver(); // Initialize the driver
		
		 pom = new orderspom(driver);
		 
		 BaseTest1.hp();	
			pom.acceptcookies();
	}
	
	@Test
	public void search() throws IOException {
		driver = BaseTest1.getDriver(); 
		pom.plp();
		pom.productnames();
		pom.pdp();
		pom.add2cart();
		pom.addedcheck();
	}
	
	@AfterTest
	public void quit() throws IOException {
		driver = BaseTest1.getDriver(); 
		driver.quit();
	}
}