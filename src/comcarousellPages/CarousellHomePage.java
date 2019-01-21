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

/**
 * 
 * @author Smita
 * Verify Carousell home page
 *
 */
public class CarousellHomePage {
	AndroidDriver driver;
	ConfigFileReader obj = new ConfigFileReader();
	Logger logger = Logger.getLogger("mainClass");

	By seeAll = By.xpath("//android.widget.TextView[@text = \"See All\"]");
	By exploreCarosellText = By.id("com.thecarousell.Carousell:id/home_page_title_label");
	By searchTextFieldBox = By.id("com.thecarousell.Carousell:id/header_page_search_text_field");
	By searchUser = By.xpath("//android.widget.EditText[@text = \"Search Carousell\"]");
	//By searchfor101testUser = By.xpath("//android.widget.TextView[@text = \"Search user '101test' instead\"]");
	By searchfor101testUser = By.id("com.thecarousell.Carousell:id/text_search_user");
	By picUser = By.id("com.thecarousell.Carousell:id/pic_user");
	By toolbarSearch = By.id("com.thecarousell.Carousell:id/toolbar_search");
	
	//logout page
	By me = By.xpath("//android.widget.TextView[@text = \"ME\"]");
	By setting = By.id("com.thecarousell.Carousell:id/button_settings");
	// Calling constructor
	public CarousellHomePage(AndroidDriver driver)
	{
		this.driver = driver;

	}
	public SendMessageToUser verifyCarosellHomePage()
	{
		WebElement seeAllEle = (new WebDriverWait(driver, 10))
				.until(ExpectedConditions.presenceOfElementLocated(seeAll));
		logger.info("On Home page successfully landed "+seeAllEle);

		WebElement exploreCarosellText1 = driver.findElement(exploreCarosellText);
		logger.info(exploreCarosellText1);
		Assert.assertEquals(exploreCarosellText1.getText(),"Explore Carousell");
		logger.info("Assertion successful on Explore Carosell"+exploreCarosellText1);
		return new SendMessageToUser(driver);
	}

	public void scrollDown(String text) throws InterruptedException
	{
		if(text.equals("101test"))
		{
			MobileElement itemListingText = (MobileElement) driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"carousell.com/101test\"));");
			Assert.assertEquals(itemListingText.getText(),"carousell.com/101test");
			driver.findElement(toolbarSearch).click();
			Thread.sleep(2000);

		}else if(text.equals("logout"))
		{
			MobileElement logout = (MobileElement) driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Logout\"));");
			logger.info("Scrolldown successful on "+logout);
			logout.click();
		}

	}

	public void search(String searchValue)
	{
		driver.findElement(searchTextFieldBox).click();
		logger.info("Clicked on Search box");


		WebElement user = (new WebDriverWait(driver, 10))
				.until(ExpectedConditions.presenceOfElementLocated(searchUser));
		user.sendKeys(searchValue);

		//id = com.thecarousell.Carousell:id/text_search_user
		driver.findElement(searchfor101testUser).click();
		logger.info("Enter user -> '101test'");

		WebElement pic_user = (new WebDriverWait(driver, 10))
				.until(ExpectedConditions.presenceOfElementLocated(picUser));

		pic_user.click();
		logger.info("Clicked on 101test user");

	}

	public void enterSearchItem(String item)
	{
		//Enter 'White Top' in search box
		driver.findElementByAndroidUIAutomator("text(\"Search this seller's listings\")").sendKeys(item);
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		logger.info("Enter key event successful");

		// whitetopresult -> class= android.widget.TextView text= Your search "White Top"
		//If did not match any listings, try another search. gettext() and assert().
		MobileElement whiteTopText = (MobileElement) driver.findElementByAndroidUIAutomator("text(\"Your search \"White Top\" did not match any listings. Try another search?\")");
		logger.info("White top text is :"+whiteTopText);
		Assert.assertEquals(whiteTopText.getText(),"Your search \"White Top\" did not match any listings. Try another search?");
		logger.info("Aseertion successful : No result found in any category for 'Whie Top'");
	}

	public CarousellInbox tempFun()
	{
		return new CarousellInbox(driver);
	}


	public void logOut() throws InterruptedException
	{
		 //click on ME
	    driver.findElement(me).click();
	    logger.info("Landed on user profile");
	    //click on settings in Profile
	    driver.findElement(setting).click();
	    logger.info("I am on Profile setting page");
	    //scroll down till logout
	    scrollDown("logout");
	    /*MobileElement logout = (MobileElement) driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Logout\"));");
	    logout.click();
	    logger.info("Logout Successfull");*/
	}

}
