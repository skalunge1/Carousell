package comcarousellPages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

/*
 * Store all the locators when user founds white top from another existing user
 * Write down methods related 1) Enter White Top generic search
 *                            2) Verify existing search on Whote Top user
 *                            3) Send message to user to show interest in 'White Top'
 */
public class SendMessageToUser {
	AndroidDriver driver;
	ConfigFileReader obj = new ConfigFileReader();
	Logger logger = Logger.getLogger("SendMessageToUser");

	By searchTextFieldBox = By.id("com.thecarousell.Carousell:id/header_page_search_text_field");
	By searchUser = By.xpath("//android.widget.EditText[@text = \"Search Carousell\"]");
	By OKNotification = By.xpath("//android.widget.TextView[@text = \"Ok, got it!\"]");
	By autotesterUser = By.xpath("//android.widget.TextView[@text='autotester']");
	By whiteTopImage = By.xpath("//android.widget.TextView[@text = 'White Top']");
	By alertNotification = By.id("com.thecarousell.Carousell:id/feature_button");
	By chatboxmsg = By.id("com.thecarousell.Carousell:id/button_secondary");
	//By chatboxmsg = By.id("com.thecarousell.Carousell:id/tvInfo");
	//Create constructor
	public SendMessageToUser(AndroidDriver driver)
	{
		this.driver = driver;
	}

	public void enterSearchValue(String itemName)
	{
		driver.findElement(searchTextFieldBox).click();

		WebElement enterSearchBoxValue = (new WebDriverWait(driver, 10))
				.until(ExpectedConditions.presenceOfElementLocated(searchUser));
		enterSearchBoxValue.sendKeys(itemName);
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		logger.info("Enter key event successful on Whte Top");

		// Click on 'Ok got it notification'
		driver.findElement(OKNotification).click();
	}

	public void clickOnUser()
	{
		WebElement enterSearchBoxAutotester = (new WebDriverWait(driver, 10))
				.until(ExpectedConditions.presenceOfElementLocated(autotesterUser));

		//click on 'Autotester'
		enterSearchBoxAutotester.click();
	}

	public void assertUser()
	{
		WebElement autotesterVerified = (new WebDriverWait(driver, 10))
				.until(ExpectedConditions.presenceOfElementLocated(By.id("com.thecarousell.Carousell:id/view_verified")));
		logger.info(autotesterVerified);

		//Verify its' autotester'
		MobileElement autotesterText = (MobileElement) driver.findElementByAndroidUIAutomator("text(\"carousell.com/autotester\")");
		logger.info("Its me, Autotester:"+autotesterText);
		Assert.assertEquals(autotesterText.getText(),"carousell.com/autotester");
	}

	public void clickOnTop() throws InterruptedException
	{
		//click on white top image in autotester
		driver.findElement(whiteTopImage).click();


		//handle ok got it notification
		WebElement alertboxViewChat = (new WebDriverWait(driver, 10))
				.until(ExpectedConditions.presenceOfElementLocated(alertNotification));
		alertboxViewChat.click();
		logger.info("Alert on View Chat box handled"+alertboxViewChat);

		try {
			// click on chat box
			Thread.sleep(5000);
			WebElement ViewChatBox = (new WebDriverWait(driver, 10))
					.until(ExpectedConditions.presenceOfElementLocated(chatboxmsg));
			ViewChatBox.click();
			logger.info("Inside View Chat box"+ViewChatBox);
		} catch (Exception e) {
			logger.error("View chat box is not clickable");
			driver.quit();
		}


	}

}
