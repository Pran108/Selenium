package Chrome.Printemps2;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Chrome.Printemps2.BaseTest1;

public class wishlistpom  extends BaseTest1  {
	WebDriver driver;
	WebDriverWait wait;
	Actions a ;

    // Constructor to initialize WebDriver
	public wishlistpom(WebDriver driver) throws IOException {
	    this.driver = BaseTest1.getDriver(); // Initialize WebDriver from BaseTest1
	    wait = new WebDriverWait(driver, Duration.ofSeconds(40)); // Initialize WebDriverWait
	    a = new Actions(driver); // Initialize Actions for performing mouse operations
	    PageFactory.initElements(driver, this);   // Initialize the elements defined by PageFactory
	  }
	
	//pagefactory
		//cookies 
		@FindBy(id="onetrust-accept-btn-handler")
		WebElement cookie;
		
		@FindBy(id="batchsdk-ui-alert__buttons_negative")
		WebElement notification;
		
		@FindBy(css=".d-none.d-lg-block[href='https://www.printemps.com/fr/fr/mes-informations']")
		WebElement account;
			
			//email entry page
			@FindBy(id="user_email_subscribe")
			WebElement useremail;
			
			@FindBy(id="validateMailBtn")
			WebElement mailbtn;
			
			@FindBy(css=".page-account.pb-5.container-fluid")
			WebElement login;
			//password page login
			@FindBy(id="connexionPassword")
			WebElement userpasswordl;
			
			@FindBy(id="cta_connexion")
			WebElement connection;
			
			@FindBy(xpath="//h2[normalize-space()='Saisissez votre mot de passe']")
			WebElement informations;
			
		
		@FindBy(xpath="//body/div[@class='main-layout']/nav[@class='menu']/ul[@class='menu__level menu__level__1']/li[1]")
		WebElement CADEAUX;
		
		@FindBy(xpath="//span[contains(@class,'menu__level__item__link--noTransform')][normalize-space()='Bougies']")
		WebElement bougies;
		
		@FindBy(id="lottie-wishlist-animation")
		 List<WebElement> wishicons;
		
		@FindBy(css="a[class='fw-bolder text-uppercase init-size w-100 d-block']")
		 List<WebElement> brandnames;
		
		@FindBy(css="a[class='paragraph small-size-2 small-size-md-3 product-label__text--grey']")
		 List<WebElement> productnames;
		
		@FindBy(className="has_product")
		WebElement wish;
		
		@FindBy(css=".mb-0.paragraph.fw-bold.text-uppercase.small-size-3.init-size-md")
		List<WebElement> wishbrandnamess;
		
		@FindBy(css=".dressing-product--name p[id]")
		List<WebElement> wishproductnamess;
		
	    // Method to accept cookies and reject notifications
		 public void acceptcookies(){
			 waitforElementtoAppear(cookie);
				 if (cookie.isDisplayed()) {
					 click(cookie);
		            System.out.println("User landed on site and cookie is accepted");
		        }else {
		            System.out.println("User landed on site but cookie isn't accepted");
					driver.quit();
		        }
				 
				waitforElementtoAppear(notification);
				 if (notification.isDisplayed()) {
					 click(notification);
		            System.out.println("Notification rejected");
		        }else {
		            System.out.println("Notification didnt appear");
		        }
			}
		 
		 //login to wishlist
		 public void email(String email) {
		    	waitforElementtoAppear(account);
				account.click();;
				if(driver.getCurrentUrl().contains("/connexion-inscription?url=mes-informations")) {
					System.out.println("User landed on accounts page");
					
					useremail.sendKeys(email);
					mailbtn.click();
				}else {
					System.out.println("User didnt land on accounts page");
					driver.quit();
				}
			}

		 public void enterpassword(String password) {
				waitforElementtoAppear(informations);
			if(informations.getText().contains("Saisissez votre mot de passe")) {
			if(driver.getCurrentUrl().contains("connexion-inscription?url=mes-informations")) {
				System.out.println("User landed on password page during login attempt");
				userpasswordl.sendKeys(password);
				connection.click();
			}}else{
			System.out.println("User didnt land on password page during login attempt");
			driver.quit();
		}	}
			
			public void loginverify() {
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("animConnexionOn")));
				WebElement login1 = waitforElementtoAppear(login);
				if(login1.isDisplayed()) {
					if(driver.getCurrentUrl().contains("user-creation"));{
						System.out.println("User login succesfull");
					}
				}else {
					System.out.println("User login fail");
					driver.quit();
				}
			}


		    // Method to navigate to the landing page for the "Bougies" category
		public void landingpage() {
			driver.navigate().refresh();
			 a.moveToElement(CADEAUX).perform();
			 waitforElementtoAppear(bougies);
			 bougies.click();
			 
			WebElement a = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1[class='heading-1 fw-black text-uppercase mb-0']")));
			
			if(driver.getCurrentUrl().contains("/bougies-")) {
		            System.out.println("User landed on plp named " + a.getText());
			 }else {
		            System.out.println("User didnt landed on plp");
			 }
		 }
		 
	    // Method to add items to the wishlist and validate them
		 public void wishlisting() {
			 
			 //wishlisting and storing it
		        List<String> addedbrandNames = new ArrayList<>();
		        List<String> addedproductNames = new ArrayList<>();
		        
		        waitForElementsToAppear(wishicons);
		        waitForElementsToAppear(productnames);
		        waitForElementsToAppear(brandnames);

                System.out.println("Products wishlisted are : ");
				for(int i=0; i<2;i++) {
					if (i >= wishicons.size() || i >= productnames.size() || i >= brandnames.size()) {
			            System.out.println("Less than 3 items available for wishlisting.");
			            break; // If there are fewer than 3 items, stop the loop
			        }
					WebElement wishicon = wishicons.get(i);
					WebElement productname = productnames.get(i);
					WebElement brandname = brandnames.get(i);
					
					click(wishicon);
					String product = productname.getText();
					String brand = brandname.getText();
					addedbrandNames.add(brand);
					addedproductNames.add(product);
					
					System.out.println(i + " brand name is " + brand + "& product name is " + product);
				}
				
				driver.navigate().refresh();
				waitforElementtoAppear(wish);
				click(wish);
				
				//landing on wish list page
				WebElement wishlist = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='dressing-list '] h2:nth-child(1)")));
				if(wishlist.isDisplayed() && driver.getCurrentUrl().contains("/wishlist")) {
					System.out.println("User landed on wishlist page");
				 }else {
			            System.out.println("User didnt landed on wishlist page");
				 }
				
				//storing items in wishlist page
				List<WebElement> products = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".dressing-product.product-info.d-flex.flex-column.justify-content-between.mb-5.col-6.col-xl-3.col-lg-4.col-md-6 ")));
				
				System.out.println("Number of products present in wish list page " +products.size());
				
				List<String> wishlistPagebrandNames = new ArrayList<>();
				List<String> wishlistPageproductNames = new ArrayList<>();
				
                System.out.println("Products present in wish page are : ");
				for(int i=0; i<2;i++) {
					if ( i >= wishbrandnamess.size() || i >= wishproductnamess.size()) {
			            System.out.println("Less than " + i + " items in the wishlist page.");
			            break; // If there are fewer than 3 items, stop the loop
			        }else {
					String wishbrandnames = wishbrandnamess.get(i).getText();
					String wishproductnames = wishproductnamess.get(i).getText();
					wishlistPagebrandNames.add(wishbrandnames);
					wishlistPageproductNames.add(wishproductnames);
					System.out.println(i + " brand names is " + wishbrandnames + " product names is " + wishproductnames);
				} }
				
		        // Reverse the lists for comparison
				Collections.reverse(wishlistPagebrandNames);
				Collections.reverse(wishlistPageproductNames);
				
				Assert.assertEquals(wishlistPagebrandNames, addedbrandNames, "Brand names isn't matching");
				Assert.assertEquals(wishlistPageproductNames, addedproductNames, "Product names isn't matching");
		 }
}
