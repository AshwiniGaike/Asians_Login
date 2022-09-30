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
import Pages.RegisterPage;

//@Listeners(ListenerClass.Listner1.class)
public class RegisterPageTest extends RegisterPage {
	public RegisterPageTest(WebDriver driver) {
		super(driver);
	}


	static WebDriver driver;
	RegisterPage lp;
	
	ExtentHtmlReporter ExtentReporter;
	ExtentReports report;
	ExtentTest test;

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
		lp = new RegisterPage(driver);
	}
	
	@Test (enabled=false)
	public void VerifyEmailFieldWithInvalidData() throws IOException, InterruptedException
	{
		lp.validateRegisterLink();
		Thread.sleep(2000);
		lp.enterInvalidEmail();
		lp.enterPassword();
		lp.ConfirmPassword();
		lp.clickRegisterBtn();
		
		String message=lp.validateErrorMesage();
		System.out.println("Error Message=" +message);
		Assert.assertEquals(message, "Invalid email address.");
	}
	@Test (enabled=true)
	public void VerifyEmailFieldWithBlankData() throws IOException, InterruptedException
	{
		lp.validateRegisterLink();
		Thread.sleep(2000);
		lp.enterPassword();
		lp.ConfirmPassword();
		lp.clickRegisterBtn();
		
		String message=lp.validateErrorMesage();
		System.out.println("Error Message=" +message);
		Assert.assertEquals(message, "Please specify username.");
	}
	
	@Test (enabled=true)
	public void VerifyPasswordFieldWithBlankData() throws IOException, InterruptedException
	{
		lp.validateRegisterLink();
		Thread.sleep(2000);
		lp.enterEmail();
		lp.ConfirmPassword();
		lp.clickRegisterBtn();
		
		String message=lp.validateErrorMesage();
		System.out.println("Error Message=" +message);
		Assert.assertEquals(message, "Please specify password.");
	}
	
	@Test (enabled=true)
	public void VerifyPasswordFieldWithInvalidData() throws IOException, InterruptedException
	{
		lp.validateRegisterLink();
		Thread.sleep(2000);
		lp.enterEmail();
		lp.enterInvalidPassword();
		lp.ConfirmPassword();
		lp.clickRegisterBtn();
		String message=lp.validateErrorMesage();
		System.out.println("Error Message=" +message);
		Assert.assertEquals(message, "Invalid password.");
	}
	@Test (enabled=true)
	public void VerifyRegistrationWithvalidData() throws IOException, InterruptedException
	{
		lp.validateRegisterLink();
		Thread.sleep(2000);
		lp.enterEmail();
		lp.enterPassword();
		lp.ConfirmPassword();
		lp.clickRegisterBtn();
		
		HomePage hp = new HomePage(driver);
		Assert.assertTrue(hp.getProfileName());
	}
	
	@Test (enabled=true)
	public void VerifyRegistrationWithInvalidData() throws IOException, InterruptedException
	{
		lp.validateRegisterLink();
		Thread.sleep(2000);
		lp.enterInvalidEmail();
		lp.enterInvalidPassword();
		lp.ConfirmPassword();
		lp.clickRegisterBtn();
		
		String message=lp.validateErrorMesage();
		System.out.println("Error Message=" +message);
		Assert.assertEquals(message, "Invalid email address.");
	}
	@Test (enabled=true)
	public void VerifyRegistrationWithBlankData() throws IOException, InterruptedException
	{
		lp.validateRegisterLink();
		Thread.sleep(2000);
		lp.ConfirmPassword();
		lp.clickRegisterBtn();
		
		String message=lp.validateErrorMesage();
		System.out.println("Error Message=" +message);
		Assert.assertEquals(message, "Please specify username.");
	}
	
	@Test (enabled=true)
	public void VerifyRegistrationWithoutPasswordConfirmation() throws IOException, InterruptedException
	{
		lp.validateRegisterLink();
		Thread.sleep(2000);
		lp.enterEmail();
		lp.enterPassword();
		lp.clickRegisterBtn();
		
		String message=lp.validateErrorMesage();
		System.out.println("Error Message=" +message);
		Assert.assertEquals(message, "Password confirmation doesn't match.");
	}
	
	@Test (enabled=true)
	public void VerifyRegistrationWithValidData() throws IOException, InterruptedException
	{
		lp.validateRegisterLink();
		Thread.sleep(2000);
		lp.enterEmail();
		lp.enterPassword();
		lp.ConfirmPassword();
		lp.clickRegisterBtn();
		
		HomePage hp = new HomePage(driver);
		Assert.assertTrue(hp.getProfileName());
	}
	
	@Test(enabled=true)
	public void validateBackToLoginTest() throws InterruptedException
	{
		lp.validateRegisterLink();
		String message=lp.validateBackToLoginLink();
		System.out.println("Page Label=" +message);
		Assert.assertEquals(message, "Sign in to your account");
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
