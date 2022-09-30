package TestCase;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.relevantcodes.extentreports.LogStatus;

import Base.Base;
import Pages.HomePage;
import Pages.LoginPage;

//@Listeners(ListenerClass.Listner1.class)
public class LoginPageTest extends LoginPage {
	static WebDriver driver;
	LoginPage lp;
	
	ExtentHtmlReporter ExtentReporter;
	ExtentReports report;
	ExtentTest test;
	

	public LoginPageTest() {
		super(driver);
	}

	@BeforeClass
	public void beforeClass() throws IOException
	{
		ExtentReporter = Base.getHtmlReporter();
		report = Base.getReports();
		test = Base.getExtentTest("VerifyUserCanLogIn");
		
		
		driver = Base.getDriver("chrome");
		
	}
	
	@BeforeMethod
	public void beforeMethod() throws IOException
	{
		getDriver("Chrome");
		lp = new LoginPage(driver);
	}
	
	@Test (enabled=false)
	public void verifyUserCanLoginWithValidCredentials() throws IOException, InterruptedException
	{
		lp.enterEmail();
		lp.enterPassword();
		lp.clickLoginBtn();
		
		HomePage hp = new HomePage(driver);
		Assert.assertTrue(hp.getProfileName());
		lp.logOut();
		Thread.sleep(2000);
	}
	@Test (enabled=true)
	public void verifyUserCanLoginWithInValidCredentials() throws IOException, InterruptedException
	{
		lp.enterInvalidEmail();
		lp.enterInvalidPassword();
		lp.clickLoginBtn();
		
		String message=lp.validateErrorMesage();
		System.out.println("Error Message=" +message);
		Assert.assertEquals(message, "Invalid username or password.");
		
	}
	
	@Test (enabled=true)
	public void verifyUserCanLoginWithInValidEmail() throws IOException, InterruptedException
	{
		lp.enterInvalidEmail();
		lp.enterPassword();
		lp.clickLoginBtn();
		
		String message=lp.validateErrorMesage();
		System.out.println("Error Message=" +message);
		Assert.assertEquals(message, "Invalid username or password.");
	}
	
	@Test (enabled=true)
	public void verifyUserCanLoginWithInValidPassword() throws IOException, InterruptedException
	{
		lp.enterEmail();
		lp.enterInvalidPassword();
		lp.clickLoginBtn();
		
		String message=lp.validateErrorMesage();
		System.out.println("Error Message=" +message);
		Assert.assertEquals(message, "Invalid username or password.");
	}
	
	@Test(enabled=true)
	public void validateForgetPassLinkTest() throws InterruptedException
	{
		lp.validateForgotPassLink();
		String message=lp.validateForgotPass();
		System.out.println("Error Message=" +message);
		Assert.assertEquals(message, "Forgot Your Password?");
		Thread.sleep(3000);
		driver.close();
		
	}
	
	@Test(enabled=true)
	public void ClikOnRememberMe() throws InterruptedException, IOException
	{
		lp.enterEmail();
		lp.enterPassword();
		lp.validatRememberMeFunctionality();
		lp.clickLoginBtn();
		Thread.sleep(2000);
		driver.close();
		Thread.sleep(2000);
		lp.enterEmail();
		lp.enterPassword();
		lp.clickLoginBtn();
		
		HomePage hp = new HomePage(driver);
		Assert.assertTrue(hp.getProfileName());
	}
	
	@Test(enabled=true)
	public void validateRegisterLinkTest() throws InterruptedException
	{
		lp.validateRegisterLink();
		String message=lp.validateForgotPass();
		System.out.println("Message=" +message);
		Assert.assertEquals(message, "Register");
		Thread.sleep(3000);
		driver.close();
	}
	
	
	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException
	{
		if(result.getStatus() == ITestResult.SUCCESS)
		{
			test.log(Status.PASS, result.getName() + " test passed");
		}
		else {
			
			String path = lp.getScreenshot(driver, result.getName());
			test.log(Status.FAIL, result.getName() + " test failed", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		}
		
	}
	
	
	@AfterClass
	public void afterClass()
	{
		report.flush();
		driver.close();
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
