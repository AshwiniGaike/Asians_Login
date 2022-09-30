package Pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Base.Base;
import Utility.util;

public class RegisterPage extends util {

	@FindBy(xpath="//input[@id='email']") private WebElement email;
	@FindBy(xpath="//input[@id='password']") private WebElement password;
	@FindBy(xpath="//input[@id='password-confirm']") private WebElement confirmpassword;
	@FindBy(xpath="//input[@type='submit']") private WebElement registerBtn;
	@FindBy(xpath="//a[text()='« Back to Login']") private WebElement backToLoginLink;
	@FindBy(xpath="//a[text()='Register']") private WebElement registerLink;
	@FindBy(xpath="//span[@class='pf-c-alert__title kc-feedback-text']") private WebElement errorMesg;
	@FindBy(xpath="//a[text()='« Back to Login']") private WebElement backToLogin;
	@FindBy(xpath="//h1[@id='kc-page-title']") private WebElement loginPageTitle;
	
	//constructor
		public RegisterPage(WebDriver driver)
		{
			super();
			PageFactory.initElements(driver,this);
		}
		
		
		//public methods
		public void enterEmail() throws IOException
		{
			email.sendKeys(getProperty("username"));
		}
		
		public void enterPassword() throws IOException
		{
			password.sendKeys(getProperty("password"));
		}
		
		public void enterInvalidEmail() throws IOException
		{
			email.sendKeys(getProperty("invalidUsername"));
		}
		
		public void enterInvalidPassword() throws IOException
		{
			password.sendKeys(getProperty("invalidPassword"));
		}
		
		public void ConfirmPassword() throws IOException
		{
			password.sendKeys(getProperty("invalidPassword"));
		}
		
		public void clickRegisterBtn()
		{
			registerBtn.click();
		}
		
		public void validateRegisterLink()
		{
			registerLink.click();
		}

		public String validateErrorMesage() {
			return errorMesg.getText();
		}
		public String validateBackToLoginLink()
		{
			return loginPageTitle.getText();
		}
		
		

	}
