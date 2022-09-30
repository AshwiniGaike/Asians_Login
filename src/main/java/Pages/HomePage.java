package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utility.util;

public class HomePage extends util{
	
WebDriver driver;
	
	
	@FindBy(xpath="//span[text()='A']")
	private WebElement profileName;
	
	@FindBy(xpath="(//li[@class='_2NOVgj'])[1]")
	private WebElement myProfileText;
	
	
	
	

	public HomePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
	
	
	public boolean getProfileName() throws InterruptedException{
		Thread.sleep(2000);
		explicitWait(driver, profileName);
		String pName = profileName.getText();
		
		if(pName.equals("A"))
		{
			return true;
		}
		return false;	
	}

	public void moveToProfileName(){
		moveToElement(driver, profileName);
	}
	
	public void clickOnProfileText(){
		WebElement element = explicitWait(driver, myProfileText);
		element.click();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
