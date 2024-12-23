package Chrome.Printemps2;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class registration1 {
	WebDriver driver;
	WebDriverWait wait;
	loginpom pom;
	
	@BeforeTest
	public void setup() throws IOException {
		driver = BaseTest1.getDriver(); 
			pom = new loginpom(driver);
			
			BaseTest1.hp();	
			pom.acceptcookies();

	}
	

	@Test
	public void registrationtest() throws SQLException {
		 // Retrieve data from the database
        ResultSet result = database();
        
        // Make sure to check if there's any result from the query before using it
        if (result.next()) {
            String email = result.getString("email");
            String password = result.getString("password");
            String username = result.getString("username");
            String userlastname = result.getString("userlastname");

		pom.email(email);
		pom.passwordr(password, username, userlastname);
		pom.loginverify();
        }
		}

	
	@AfterTest
	public void close() {
		driver.quit();
	}
	public ResultSet database() throws SQLException {
		//make connection to database
		String host  = "localhost";
		String port = "3306";
		Connection con = DriverManager.getConnection("jdbc:mysql://"+ host + ":" + port+ "/printemps", "root", "12345");
		
		Statement a = con.createStatement();
		ResultSet result = a.executeQuery("select * from registration where email = 'MIijic76MI@skrak1.com'");
		return result;
		
	}
}
