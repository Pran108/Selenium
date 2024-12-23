package Chrome.Printemps2;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Chrome.Printemps2.BaseTest1;

public class loginpom  extends BaseTest1 {
	WebDriver driver;
	WebDriverWait wait;
	Actions a ;

	public loginpom(WebDriver driver) throws IOException {
	    this.driver = BaseTest1.getDriver();
	    wait = new WebDriverWait(driver, Duration.ofSeconds(40));
	    a = new Actions(driver);
	 // otherwise it wont consider pagefactory elements
	    PageFactory.initElements(driver, this);  // Initialize page elements
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
		
		//password page registration
		@FindBy(id="input_password_component")
		WebElement fill;
		
		@FindBy(className="show")
		WebElement error;
		
		@FindBy(id="inscriptionPassword")
		WebElement userpasswordr;
		
		@FindBy(id="inscriptionFirstName")
		WebElement username;
		
		@FindBy(id="inscriptionName")
		WebElement userlastname;
		
		@FindBy(id="validerInscription")
		WebElement validbtn;
		
		@FindBy(css=".page-account.pb-5.container-fluid")
		WebElement login;
		
		//logout process
		@FindBy(id="form-mes-infos")
		WebElement loading;
		
		@FindBy(css=".d-none.d-lg-block.connected")
		WebElement logout1;
		
		@FindBy(css="div[class='header__main--inside-icon-hover']")
		WebElement block;
		
		@FindBy(linkText="Me déconnecter")
		WebElement lodisconnect;
		
		//password page login
		@FindBy(id="connexionPassword")
		WebElement userpasswordl;
		
		@FindBy(id="cta_connexion")
		WebElement connection;
		
		@FindBy(xpath="//h2[normalize-space()='Saisissez votre mot de passe']")
		WebElement informations;

	    
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

		public void passwordr(String password, String name, String lastname) {
			waitforElementtoAppear(fill);
			if(fill.isDisplayed()) {
			if(driver.getCurrentUrl().contains("/inscription?url=mes-informations&etape=mot-de-passe")) {
				System.out.println("User landed on password page during registration");
				userpasswordr.sendKeys(password);
				username.sendKeys(name);
				userlastname.sendKeys(lastname);			
				validbtn.click();
			}}else{
			System.out.println("User didnt land on password page during registration");
			driver.quit();
		}	
				
		}
		
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
		
		public void logout() {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("animConnexionOn")));
			waitforElementtoAppear(loading);
			a.moveToElement(logout1).build().perform();;
			waitforElementtoAppear(block);
			if(block.isDisplayed()) {
				lodisconnect.click();
				if(driver.getCurrentUrl().contains("/deconnexion")) {
					System.out.println("Logout attempt successful");
				}
			}else {
				System.out.println("Logout failed");
				driver.quit();
			}
			
			//driver.navigate().refresh();
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
	    
	    
}
