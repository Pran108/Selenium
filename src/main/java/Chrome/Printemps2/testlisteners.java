package Chrome.Printemps2;

import org.testng.IRetryAnalyzer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Chrome.Printemps2.BaseTest1;

public class testlisteners extends BaseTest1 implements ITestListener, IRetryAnalyzer {
	ExtentTest Test;
    ExtentReports extent = BaseTest1.config();
    int count =0;
    int maxTry=1;
    
    public void onTestStart(ITestResult result) {
        Test = extent.createTest(result.getMethod().getMethodName());
    }
    
    public void onTestSuccess(ITestResult result) {
        Test.log(Status.PASS, "Pass");
    }
    
    public void onTestFailure(ITestResult result) {
        Test.fail(result.getThrowable());
        String filepath = takeScreenshot(result.getMethod().getMethodName());
        Test.addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
    }
    
    public void onFinish(ITestContext context) {
        extent.flush(); // Ensure the report is flushed
    }
    
    //179. Iretry ANalyzer to return the flaxy failed Selenium Test in section 22
    public boolean retry(ITestResult reult) {
    	if(count<maxTry) {
    		count++;
    		return true;
    	}
    	return false;
    }
}
