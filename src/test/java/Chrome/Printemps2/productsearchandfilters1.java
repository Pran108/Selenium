package Chrome.Printemps2;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Chrome.Printemps2.BaseTest1;
import Chrome.Printemps2.searchpom;

/*Objective: 
 * Validate the functionality of the product search and filtering options on the e-commerce website.
 * Applying filters on search page

Steps:
Navigate to the e-commerce website’s search bar.
Enter a specific product name or category and initiate the search.
Verify that the search results match the entered criteria.
Apply various filters (price range, brand, etc.) and confirm that the displayed products are filtered accordingly.
Ensure that sorting options (e.g., by price or popularity) are functional.*/

@Listeners(Chrome.Printemps2.testlisteners.class) 
public class productsearchandfilters1 {
	WebDriver driver;
	WebDriverWait wait;
	searchpom pom;
	
	@BeforeTest
	public void setup() throws IOException {
		
		driver = BaseTest1.getDriver(); // Initialize the driver
		
		 pom = new searchpom(driver);
		 
		 BaseTest1.hp();	
			pom.acceptcookies();
	}
	
	@Test
	public void search() throws IOException {
		driver = BaseTest1.getDriver(); 
		
		pom.searching("shirt");
		
		pom.sorting();
		
		pom.femmefilter();;
		
		pom.brandfilter();
				
	pom.pricefilter();
	
	pom.clear();
	}
	
	@AfterTest
	public void quit() throws IOException {
		driver = BaseTest1.getDriver(); 
		driver.quit();
	}
}

