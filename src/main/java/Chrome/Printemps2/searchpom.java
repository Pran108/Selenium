package Chrome.Printemps2;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

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

public class searchpom  extends BaseTest1 {
	WebDriver driver;
	WebDriverWait wait;
	Actions a ;

	public searchpom(WebDriver driver) throws IOException {
	    this.driver = BaseTest1.getDriver();
	    wait = new WebDriverWait(driver, Duration.ofSeconds(40));
	    a = new Actions(driver);
	 // otherwise it wont consider pagefactory elements
	    PageFactory.initElements(driver, this); 
	  }
	
	//pagefactory
		//cookies 
		@FindBy(id="onetrust-accept-btn-handler")
		WebElement cookie;
		
		@FindBy(id="batchsdk-ui-alert__buttons_negative")
		WebElement notification;
	
		@FindBy(className="input-search-radius__input")
		WebElement search;
		
		@FindBy(id="search-input-handler")
		WebElement searchinput;
		
		@FindBy(css=".col-md-11.p-0.header__search.d-flex.justify-content-center.row.mt-3.mt-lg-4")
		WebElement suggestions;
		
		@FindBy(css="button[type='submit']")
		WebElement submit;
	
		@FindBy(className="search-result-title")
		WebElement title;
		
		@FindBy(id="products-wrapper")
		WebElement wrapper;
		
		@FindBy(id="dropdown-sort")
		WebElement sort;
		
		@FindBy(css="ul[id='viewallPrefsTriSelect'] li[data-label='Prix croissant'] span[class='labelSort']")
		WebElement sorting_suggestion;
		
		@FindBy(id="dropdown-filter-uni")
		WebElement femme;
		
		@FindBy(css="label[for='uni_0'] span[class='checkmark-dark']")
		WebElement femme_suggestion;
		
		@FindBy(css="button[class=' clearFilters align-items-center justify-content-center gap-2 py-1 d-flex mx-0 mx-md-2 mx-lg-0']")
		WebElement clear;
		
		@FindBy(id="dropdown-filter-mrk")
		WebElement mrkdropdown;
		
		@FindBy(css="label[for='mrk_11'] span[class='checkmark-dark']")
		WebElement mrk_11;
		
		@FindBy(css="label[for='mrk_12'] span[class='checkmark-dark']")
		WebElement mrk_22;
		
		@FindBy(id="dropdown-filter-tai")
		WebElement size;
		
		@FindBy(id="label[for='tpp_3']")
		WebElement size_3;
		
		@FindBy(id="dropdown-filter-prx")
		WebElement prx;
		
		@FindBy(css=".noUi-handle-lower")
		WebElement lower;
		
		@FindBy(css=".main-container-lg.section-filter__action.d-flex.flex-nowrap.flex-md-wrap.gap-2.py-3.justify-content-evenly.justify-content-md-start.position-sticky")
		WebElement chips;
		
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
    
	//search typing without clicking on search suggestions
    public void searching(String text){
    	click(search);
    	sendKeys(searchinput,text);
    		waitforElementtoAppear(suggestions);
    		System.out.println(suggestions.isDisplayed());
    		 click(submit);
    		 
    		 waitforElementtoAppear(title);
    		 waitforElementtoAppear(wrapper);
    			try {
    					 if(driver.getCurrentUrl().contains("query=shirt"));
    			if(title.isDisplayed());{
    				if(wrapper.isDisplayed()) {
    				System.out.println("searching succesful");}
    			}
    			}
    		catch(Exception e) {
    			System.out.println("searching failed");
    			driver.quit();
    		}
    }
    
    public void sorting() {
    	click(sort);    
    	click(sorting_suggestion);
    	click(sort);    
    			
    			//added so that site till then rearranges the pdp
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    	
    		    //check is sorting applied is correct
    			try {
    				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span[class='mb-0 me-2 paragraph init-size-md small-size-3 fw-700 text-nowrap']")));
    			    
    			    List<WebElement> prices = driver.findElements(By.cssSelector("span[class='mb-0 me-2 paragraph init-size-md small-size-3 fw-700 text-nowrap']"));
    			    List<Double> prices2 = new ArrayList<>();
    			boolean count = false;
    			for(int i =0; i< Math.min(prices.size(), 4); i++) {
    				String price = prices.get(i).getText().replaceAll("€", "");
    				double price1 = Double.parseDouble(price.replace(",", "."));
    				prices2.add(price1);
    			}
    			for(int i =0; i<prices2.size()-1;i++) {
    				if(prices2.get(i)< prices2.get(i+1)) {
    					count = true;
    					break;
    				}
    			}
    			if (count==true) {
    	            System.out.println("Prices are sorted in ascending order.");
    	        } else {
    	            System.out.println("Prices are not sorted in ascending order.");
    	        }}
    				
    			catch(Exception e) {
    			    e.printStackTrace();
    			}
    }
    
    //single filter
    public void femmefilter() {
    	
    	click(femme);
    	click(femme_suggestion);
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    	
		List<WebElement> chips = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("ul[class='selected-filters pb-2 overflow-auto d-none d-lg-flex'] span[class='text-nowrap small-size-3 fw-700 me-1']")));
		for( WebElement chip : chips) {
		if(driver.getCurrentUrl().contains("Femme")) {
			if(chip.getText().contains("Femme")) {
				System.out.println("Femme filter is applied");
			}
		}}
    }
    
    //multiplefilter
    public void brandfilter() {
    	
    	//handling checkbox
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    		click(mrkdropdown);
    			 waitforElementtoAppear(mrk_11);
    			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", mrk_11);
    			mrk_11.click();
    			
    	    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    			 waitforElementtoAppear(mrk_22);
     			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", mrk_22);
     			mrk_22.click();
    			
    			if(driver.getCurrentUrl().contains("mrk")) {
    				System.out.println("Brands  filter is applied");
    	    		click(mrkdropdown);
    			}
    			
    			else {			System.out.println("Brands  filter isn't applied");}}
    
    			
    			
    		    public void pricefilter() {

    					click(prx);
    					//WebElement higher = driver.findElement(By.cssSelector(".noUi-handle-upper"));
    					// Click and hold the slider
    			        a.clickAndHold(lower)
    			         .moveByOffset(50, 0) // Move right by specified pixels
    			         .release() // Release the mouse at the new position
    			         .build() // Build the action chain
    			         .perform(); // Perform the action chain
    			
    			if(driver.getCurrentUrl().contains("prx")) {
    				System.out.println("Price filter is applied");
    			}
    			
    			else {			System.out.println("Price filter isn't applied");}}
    		    
    		    public void clear() {
    		    	//clearing filter
    		    	waitforElementtoAppear(clear);
    		    	click(clear);
    		    	wait.until(ExpectedConditions.invisibilityOf(chips));
    		    	if(!chips.isDisplayed()) {
    		    		System.out.println("cleared filters");
    		    	}
    		    }

}
