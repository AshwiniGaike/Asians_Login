package Base;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import Utility.util;

//import UtilityClasses.Util1;

public class Base {

static WebDriver driver = null;
static ExtentHtmlReporter extentReporter = null;
static ExtentReports report =null;
static ExtentTest test = null;
	
	public static WebDriver getDriver(String browser) throws IOException
	{
		System.out.print(driver);
		
		if(driver == null)
		{
			if(browser.equals("chrome"))
			{
				System.setProperty("webdriver.chrome.driver", "C:\\Users\\User1\\eclipse-workspace\\Asiansgroup\\chromedriver.exe");
				driver = new ChromeDriver();
			}else if(browser.equals("firefox"))
			{
				System.setProperty("webdriver.gecko.driver", "src\\main\\resources\\Browsers\\geckodriver.exe");
				driver = new FirefoxDriver();
			}
			
			driver.get(util.getProperty("URL"));
			driver.manage().window().maximize();
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			
			return driver;
		}
		else
		{
			System.out.print("in else");
			return driver;
		}	
	}
	
	public static ExtentHtmlReporter getHtmlReporter()
	{
		if(extentReporter == null)
		{
			extentReporter = new ExtentHtmlReporter("ExtentReport.html");
		}
		
			return extentReporter;
		
	}
	
	public static ExtentTest getExtentTest(String testName)
	{
		
			test = report.createTest(testName);
			return test;
	}
	
	public static ExtentReports getReports()
	{
		if(report == null)
		{
			report = new ExtentReports();
			report.attachReporter(extentReporter);
		}
		
		return report;
	}
	
	
	
	

}
