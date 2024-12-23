package Chrome.Printemps2;


import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Chrome.Printemps2.BaseTest1;
import Chrome.Printemps2.loginpom;
@Listeners(Chrome.Printemps2.testlisteners.class) // Adding listeners for test execution reporting
public class login1 {
	 WebDriver driver;   // WebDriver instance
	    WebDriverWait wait; // WebDriverWait instance for waiting during tests
	    loginpom pom;      // Page Object Model instance for the login page
	
	    // Setup method runs before each test. Initializes driver and performs setup actions.
	@BeforeTest
	public void setup() throws IOException {
		
		driver = BaseTest1.getDriver(); // Initializing the driver from BaseTest1
		
		 pom = new loginpom(driver); // Create an instance of loginpom1 for interaction with login page
		 
		 BaseTest1.hp();	//  BaseTest1.hp() initializes the homepage 
			pom.acceptcookies(); // Accept cookies on the login page
	}
	
	// Test method that takes data from the DataProvider 'getLoginDataFromJson'
    @Test(dataProvider = "getLoginDataFromJson")
	public void logintest(HashMap<String, String> input) throws IOException {
		//pom.logout();
		 driver = BaseTest1.getDriver(); 
		pom.email(input.get("email"));
		pom.enterpassword(input.get("password"));
		pom.takeScreenshot(input.get("email"));
		pom.logout();
	}
	
 // Another test method with a different DataProvider ('getData') that passes login data directly
	@Test (dataProvider = "getData")
	public void logintest1(String email, String password) throws IOException {
		//pom.logout();
		 driver = BaseTest1.getDriver(); // Initialize driver
		pom.email(email);
		pom.enterpassword(password);
		pom.takeScreenshot(email);
		pom.logout();
	}
	
    // Test with dataProvider 'GetData' to get data from hashmap that is set to 'enabled = false' (won't run)
	@Test (dataProvider = "GetData" , enabled = false)
	public void logintest2(HashMap<String, String> input) throws IOException {
		//pom.logout();
		 driver = BaseTest1.getDriver(); 
		pom.email(input.get("email"));
		pom.enterpassword(input.get("password"));
		pom.takeScreenshot(input.get("email"));
		pom.logout();
	}
	
    // AfterTest method runs after all the tests, responsible for closing the WebDriver
	@AfterTest
	public void close() throws IOException {
		BaseTest1.getDriver().quit();
	}

    //  DataProvider that provides login credentials (email and password)
	@DataProvider 
	public Object[][] getData() {
		return new Object[][] {
		{"resibal213@marchub.com", "Resibal213?"},
		{"rugage11@cyclelove.cc", "rugagE@123"}
		};
		
	}
	
    // Parallel DataProvider for login credentials
	@DataProvider (parallel = true)
	public Object[][] GetData(){
		HashMap<String, String> map = new HashMap <String, String>();
		map.put("email", "kiposi9559@carspure.com");
		map.put("password", "Kiposi95591@");
		
		HashMap<String, String> map1 = new HashMap <String, String>();
		map1.put("email", "kiposi95591@carspure.com");
		map1.put("password", "Kiposi95591@");
		
		return new Object[][] {{map}, {map1}}; // Returning the login data as an array of HashMaps
		
		
	}
	

    // Helper method to read and parse JSON data file into a List of HashMaps
	public List<HashMap<String, String>> getJsonDataToMap(String filepath) throws IOException {

	    // Reading JSON file to string
	    String json = FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);

        // Use Jackson's ObjectMapper to parse JSON into a List of HashMaps
	    ObjectMapper objectMapper = new ObjectMapper();
	 
	        List<HashMap<String, String>> data = objectMapper.readValue(json, new TypeReference<List<HashMap<String, String>>>() {});
	      
	        System.out.println(data); // Print parsed data 

	    return data;
	}
	
    // DataProvider to read login data from a JSON file
	@DataProvider
	public Object[][] getLoginDataFromJson() throws IOException {
	    String filepath = "C:\\Users\\Pran\\eclipse-workspace\\Printemps2\\src\\test\\java\\Chrome\\Printemps2\\login2.json";
		List<HashMap<String, String>> data =  getJsonDataToMap(filepath);
	    Object[][] dataArray = new Object[data.size()][1];
	    
	    for (int i = 0; i < data.size(); i++) {
	        dataArray[i][0] = data.get(i); // Store each HashMap as an object
	    }
	    
	    return dataArray;		
	}
}
