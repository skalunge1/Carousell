package comcarousellPages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.appium.java_client.android.AndroidDriver;

/**
 * 
 * @author mau
 * This class will store all locators of Login page
 *
 */
public class LoginPage {

	AndroidDriver driver;
	
	//WebDriver driver;

	By firstLoginBtn = By.xpath("//android.widget.Button[@text = 'Log In']");
	By cancelBtn = By.id("com.google.android.gms:id/cancel");
	By assertLogInText = By.xpath("//android.widget.TextView[@text = 'Log In']");
	By userName = By.xpath("//android.widget.EditText[@text = 'email or username']");
	By userPass = By.xpath("//android.widget.EditText[@text = 'password']");
	By FinalLoginBtn = By.id("com.thecarousell.Carousell:id/login_page_login_button");

	//calling constructor
	public LoginPage(AndroidDriver driver)
	{
		this.driver = driver;
	}

	public void clickOnLogIn()
	{
		driver.findElement(firstLoginBtn).click();
	}
	public void clickonCancelBtn()
	{
		WebElement CancelBtn = (new WebDriverWait(driver, 10))
				  .until(ExpectedConditions.presenceOfElementLocated(cancelBtn));
		CancelBtn.click();
	}
	public void assertLogInPage()
	{
		WebElement loginText = (new WebDriverWait(driver, 10))
				  .until(ExpectedConditions.presenceOfElementLocated(assertLogInText));
		Assert.assertEquals(loginText.getText(),"Log In");
	}
	
	public CarousellHomePage enterLoginDetails(String Uname, String Upass)
	{
		driver.findElement(userName).sendKeys(Uname);
		driver.findElement(userPass).sendKeys(Upass);
		driver.findElement(FinalLoginBtn).click();
		
		return new CarousellHomePage(driver);
		
	}
	


}
