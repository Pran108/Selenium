package Chrome.Printemps2;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Chrome.Printemps2.BaseTest1;
import Chrome.Printemps2.wishlistpom;

public class wishlist1 extends BaseTest1 {
	WebDriver driver;
	WebDriverWait wait;
	wishlistpom pom;
	
	@BeforeTest
	public void setup() throws IOException {
		driver = BaseTest1.getDriver(); // Initialize the driver
		pom = new wishlistpom(driver);
		
		 BaseTest1.hp();	
		 pom.acceptcookies();
	}
	
	@Test (dataProvider = "getData")
	public void wish(String email, String password) throws IOException{
		driver = BaseTest1.getDriver(); 
		
		pom.email(email);
		pom.enterpassword(password);
		//pom.takeScreenshot(email);
		pom.loginverify();
		pom.landingpage();
		pom.wishlisting();
	}
	
	@AfterTest
	public void close() throws IOException {
		driver = BaseTest1.getDriver(); 
		driver.quit();

	}
	
//  DataProvider that provides login credentials (email and password)
	@DataProvider 
	public Object[][] getData() {
		return new Object[][] {
		{"resibal213@marchub.com", "Resibal213?"}		};
		
	}
	
}

