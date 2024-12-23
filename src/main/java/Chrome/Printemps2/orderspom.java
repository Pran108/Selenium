package Chrome.Printemps2;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Chrome.Printemps2.BaseTest1;

public class orderspom  extends BaseTest1 {
	WebDriver driver;
	WebDriverWait wait;
	Actions a ;
	JavascriptExecutor j;

	public orderspom(WebDriver driver) throws IOException {
	    this.driver = BaseTest1.getDriver();
	    wait = new WebDriverWait(driver, Duration.ofSeconds(40));
	    a = new Actions(driver);
		 j =  (JavascriptExecutor)driver;
	    PageFactory.initElements(driver, this); 
	  }
	
	//pagefactory
			//cookies 
			@FindBy(id="onetrust-accept-btn-handler")
			WebElement cookie;
			
			@FindBy(id="batchsdk-ui-alert__buttons_negative")
			WebElement notification;
			
			@FindBy(xpath="//a[contains(@href,'https://www.printemps.com/fr/fr/femme')]//span[contains(text(),'FEMME')]")
			WebElement femme;
			
			@FindBy(css=".menu__level__item__link[href='https://www.printemps.com/fr/fr/mini-sacs-femme?ap_source=anico&ap_medium=nav-femme&ap_campaign=mini_sacs']")
			WebElement navbar;
			
			@FindBy(css=".pcom-breadcrumb")
			WebElement sacs;
			
			@FindBy(css="article[data-position='3']")
			WebElement first;
			
			@FindBy(css="a[class='cta cta--block cta--primary-dark-1']")
			WebElement add;
			
			@FindBy(css="[data-action='getSizesDispo']")
			WebElement size;
			
			@FindBy(css="[data-target='size-select-menu']")
			WebElement options;

			@FindBy(css="a[data-action=\"finaliser-commande\"]")
			WebElement added;
			
			@FindBy(css="span[class='icon-count position-relative']")
			WebElement countt;
			
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
			  
			  public void plp(){
					a.moveToElement(femme).perform();
					click(navbar);
					waitforElementtoAppear(sacs);
					if(driver.getCurrentUrl().contains("/mini-sacs-femme")) {
						if(sacs.isDisplayed()) {
							System.out.println("Mini sacs page is displayed");
						}
					}
					else {
						System.out.println("Mini sacs page isn't displayed");
					}	
			  }
			  
			  public void productnames(){
				//product names dispalyed
					System.out.println("First 10 products displayed are");
					List<WebElement> brand = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("a[class='fw-bolder text-uppercase init-size w-100 d-block']")));
					List<WebElement> names = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("a[class='paragraph small-size-2 small-size-md-3 product-label__text--grey']")));
					List<WebElement> prices = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".mb-0.me-2.paragraph.init-size-md.small-size-3.fw-700.text-nowrap")));
					if (brand.size() >= 10) {
					for(int i=0;i<10;i++) {
						System.out.println(brand.get(i).getText() + " " + names.get(i).getText() + " " + prices.get(i).getText());
					}}
					else  {
						System.out.println("Less than 10 products are displayed");
						for (int i=0;i<brand.size();i++) {
								System.out.println(brand.get(i).getText() + " " +  names.get(i).getText() + " " + prices.get(i).getText());
							}			
					}
			  }
			  
			  public void pdp(){
					waitforElementtoAppear(first);
					j.executeScript("arguments[0].scrollIntoView", first);
					String productid = first.getAttribute("data-item-id");
					first.click();
				//Using Single Quotes for Attribute Values: or
					//Using Escaped Double Quotes:driver.findElement(By.cssSelector("article[data-position=\"2\"]")).click();
					waitforElementtoAppear(add);
			    wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
				if(add.isDisplayed()) {
					if(driver.getCurrentUrl().contains(productid)) {
						System.out.println("Product is displayed");
					}
				} else {
					System.out.println("Product isn't displayed");
				}
			  }
			
			  public void add2cart(){
				  try {
					    // Wait for the size element to be present
						waitforElementtoAppear(size);
					    // Check if the size element is displayed before clicking
					    if (size.isDisplayed()) {
					        size.click();

					        // Wait for the size selection options to be visible
							waitforElementtoAppear(options);
					        
					        // Continue with selecting sizes if options are visible
					        if (options.isDisplayed()) {
					            List<WebElement> sizes = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div.mb-3 li div.available")));
					            if (!sizes.isEmpty() && sizes.get(0).isDisplayed()) {
					                sizes.get(0).click();
					                System.out.println("Size selected");
					                add.click(); // Click the add button
					            } else {
					                System.out.println("No available sizes found.");
					            }
					        }
					    }
					} catch (org.openqa.selenium.TimeoutException e) {
					    System.out.println("Size element did not appear on this page within the timeout period.");
					    add.click(); // Handle absence
					} catch (NoSuchElementException e) {
					    System.out.println("Size element was not found: " + e.getMessage());
					    add.click(); // Handle absence
					} catch (Exception e) {
					    System.out.println("An unexpected error occurred: " + e.getMessage());
					    add.click(); // Handle absence
					}
			  }
			  
				//checking whether added or not
			  public void addedcheck(){
				  waitforElementtoAppear(added);
				  waitforElementtoAppear(countt);
					if (added.isDisplayed()) {
				String count = countt.getText();
				try {
					int productcount = Integer.parseInt(count.trim());
					if(productcount > 0) {
				System.out.println("product is added to cart");
						}}
					catch (NumberFormatException e) {
						System.out.println("Error parsing product count: \" + e.getMessage()");
					}
					}
					else {
						System.out.println("product is added to cart");
					}
			  }
			  
}
