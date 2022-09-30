package Pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Base.Base;
import Utility.util;

public class LoginPage extends util {

	@FindBy(xpath="//input[@id='username']") private WebElement email;
	@FindBy(xpath="//input[@id='password']") private WebElement password;
	@FindBy(xpath="//input[@id='rememberMe']") private WebElement rememberMeCheckBox;
	@FindBy(xpath="//input[@name='login']") private WebElement loginbtn;
	@FindBy(xpath="//a[text()='Forgot Password?']") private WebElement forgetPassLink;
	@FindBy(xpath="//a[text()='Register']") private WebElement registerLink;
	@FindBy(xpath="//span[@id='input-error']") private WebElement errorMesg;
	@FindBy(xpath="//div[@class='vue-avatar--wrapper']") private WebElement profileIcon;
	@FindBy(xpath="//button[text()='Logout']") private WebElement logout;
	@FindBy(xpath="//h1[@id='kc-page-title']") private WebElement forgotPassword;
	
	
	
	//constructor
		public LoginPage(WebDriver driver)
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
		
		
		public void clickLoginBtn()
		{
			loginbtn.click();
		}


		public String validateErrorMesage() {
			return errorMesg.getText();
		}
		public String validateForgotPass() {
			return forgotPassword.getText();
		}
		
		public void validateForgotPassLink()
		{
			forgetPassLink.click();
		}
		public void logOut()
		{
			profileIcon.click();
			logout.click();
		}
		public void validateRegisterLink()
		{
			registerLink.click();
		}
		public void validatRememberMeFunctionality()
		{
			rememberMeCheckBox.click();
		}

	}
