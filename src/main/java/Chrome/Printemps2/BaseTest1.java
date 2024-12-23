package Chrome.Printemps2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class BaseTest1 {
    // Declare static WebDriver, WebDriverWait, and JavascriptExecutor variables
	private static WebDriver driver;
	static WebDriverWait wait;
    static JavascriptExecutor js;

    // Method to initialize WebDriver instance,
	public static WebDriver getDriver() throws IOException {
	    if (driver == null) {
            // Initialize Firefox options and driver
	       FirefoxOptions options = new FirefoxOptions();
	        driver = new FirefoxDriver(options);  // Initialize the driver first
	        driver.manage().window().maximize();  // Maximize the browser window
	        wait = new WebDriverWait(driver, Duration.ofSeconds(40));  // Initialize WebDriverWait after driver
	        js =  (JavascriptExecutor) driver;  // Initialize JavascriptExecutor for JS operations
	    }
	    return driver;
	}

    // Method to navigate to the homepage of the site
	 public static void hp() {
	    	driver.get("https://www.printemps.com/fr/fr"); // Navigate to the Printemps homepage
	    	System.out.println(driver.getCurrentUrl()); // Print the current URL for verification
	    }
	 
	    // Method to take a screenshot and save it to the specified directory
	    public String takeScreenshot(String testcase) {
	    	// Capture screenshot as a file
			File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		    String screenshotPath = "D:\\Automation Practice\\" + testcase + ".jpeg"; // Define the screenshot path
			try {
				// Copy the screenshot to the specified location
		        FileUtils.copyFile(src, new File(screenshotPath));
	            System.out.println("ScreenShot taken succesfully!"); // Print success message
	        } catch (IOException e) {
	            e.printStackTrace(); // Handle potential IOExceptions
	        }
			return screenshotPath; // Return the screenshot file path
			
	    }
	    
	    // Method to wait for an element to appear on the page
	    public WebElement waitforElementtoAppear(WebElement locator) {
			return wait.until(ExpectedConditions.visibilityOf(locator)); // Wait for visibility of element
		}
	    
	    // Method to wait for a list of elements to appear on the page
	    public List<WebElement> waitForElementsToAppear(List<WebElement> wishicons) {
	        return wait.until(ExpectedConditions.visibilityOfAllElements(wishicons)); // Wait for all elements 
	    }

	    // Method to click on an element after waiting for it to appear
	    public  void click(WebElement locator) {
	    	waitforElementtoAppear(locator);  // Ensure element is visible before clicking
	    	 locator.click(); // Click on the element
	    }
	    
	    // Method to send keys to an element after clearing any existing text
	    public void sendKeys(WebElement locator, String keysToSend) {
	        waitforElementtoAppear(locator); // Ensure element is visible before interacting
	        locator.clear();  // Clear the field before sending keys
	        locator.sendKeys(keysToSend); // Send the provided keys to the element
	    }

	    // Method to scroll to an element on the page
	    public void scrolltoElement(WebElement locator) {
	        waitforElementtoAppear(locator); // Ensure element is visible before scrolling
            js.executeScript("arguments[0].scrollIntoView(true);", locator);
	    }
	    
	 // Method to configure ExtentReports for logging test results
		public static ExtentReports config() {
			String reportDirectory = "D:\\java\\Framework1\\Printemps\\reports";
	        File reportDir = new File(reportDirectory);
			
			// Create the directory if it doesn't exist
	        if (!reportDir.exists()) {
	            reportDir.mkdirs(); // Create the directory and any necessary parent directories
	        }

	        String file = reportDirectory + "\\index.html"; // Set the report file path
			ExtentSparkReporter report=new ExtentSparkReporter(file); // Create an ExtentSparkReporter instance
			report.config().setReportName("Results");
			report.config().setDocumentTitle("Results");
			
	        // Create an ExtentReports instance and attach the reporter
			ExtentReports extent = new ExtentReports();
			extent.attachReporter(report); // Attach the reporter to the ExtentReports instance
			extent.setSystemInfo("Tester", "Tester");
			return extent; // Return the ExtentReports instance
		}
}

