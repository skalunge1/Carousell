package comcarousellPages;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

/*
 * Store all the locators of Inbox and Archieve box
 * Write down methods to handle Inbox and Archieve
 */
public class CarousellInbox {
	AndroidDriver driver;
	ConfigFileReader obj = new ConfigFileReader();
	Logger logger = Logger.getLogger("mainClass");

	By chatButton = By.id("com.thecarousell.Carousell:id/header_page_chat_button");
	By viewInitialInbox = By.id("com.thecarousell.Carousell:id/view_inbox");
	By archieveIcon = By.id("com.thecarousell.Carousell:id/action_archive");
	By ALLOption = By.xpath("//android.widget.TextView[@text = 'ALL']");
	By viewInbox = By.id("com.thecarousell.Carousell:id/view_inbox");
	By archieveText = By.xpath("//android.widget.TextView[@text = 'ARCHIVED']");
	//By archievedIcon = By.id("com.thecarousell.Carousell:id/action_archived");
	By afterArchieveInbox = By.id("com.thecarousell.Carousell:id/view_inbox");
	By afterArchieveBtn = By.id("com.thecarousell.Carousell:id/action_archive");
	By archievedBckBtn = By.className("android.widget.ImageButton");
	By buyingButtonInbox = By.xpath("//android.widget.TextView[@text = 'BUYING']");
	By backButton = By.xpath("//android.widget.ImageButton[@content-desc = \"Navigate up\"]");


	public CarousellInbox(AndroidDriver driver)
	{
		this.driver = driver;
	}

	public int verifyInboxintialCount()
	{
		driver.findElement(chatButton).click();
		logger.info("Click on chat inbox");
		List<MobileElement> elements=	driver.findElements(viewInitialInbox);
		int initial_inboxLength = elements.size();
		logger.info("No. of message before in Inbox: "+initial_inboxLength);
		return initial_inboxLength;
	}

	public void longPress(String text)
	{
		if(text.equals("Inbox"))
		{
			TouchAction t = new TouchAction(driver);
			WebElement autotester = driver.findElementByAndroidUIAutomator("text(\"autotester\")");


			//This will longpress on email and archive option will enable on top
			t.longPress(longPressOptions().withElement(element(autotester)).withDuration(ofSeconds(2))).release().perform();
			//t.press(1440, 862).moveTo(0, 522).release().perform();
			logger.info("Long press successful");
		}else if(text.equals("Archieve"))
		{
			TouchAction t1 = new TouchAction(driver);
			WebElement autotester1 = driver.findElementByAndroidUIAutomator("text(\"autotester\")");


			//This will longpress on email and archive option will enable on top
			t1.longPress(longPressOptions().withElement(element(autotester1)).withDuration(ofSeconds(2))).release().perform();
			logger.info("Long press successful in archive");
		}
	}

	public void verifyArchieve(int intialLength) throws InterruptedException
	{

		//click on archieve button 
		driver.findElement(archieveIcon).click();
		logger.info("Clicked on move to archive icon successfully");


		// Clicked inside window to make it active
		Thread.sleep(5000);
		// After click inbox length
		driver.findElement(ALLOption).click();
		logger.info("Clicked inside Inbox to count number of messages");


		List<MobileElement> element2=	driver.findElements(viewInbox);
		int final_inboxLength = element2.size();
		logger.info("No. of message after archival in inbox: "+final_inboxLength);
		Assert.assertEquals(final_inboxLength+1, intialLength);


		WebElement archBtn = (new WebDriverWait(driver, 10))
				.until(ExpectedConditions.presenceOfElementLocated(archieveText));

		archBtn.click();
		// Click on Archived text go to Archive folder
		logger.info("Clicked on Archieve text");


		List<MobileElement> element3 = driver.findElements(afterArchieveInbox);
		int initial_ArchiveInboxLength = element3.size();
		logger.info("No. of message in Archived: "+initial_ArchiveInboxLength);
	}

	public void verifyInboxAfterArchieve()
	{
		//click on archieve button id=com.thecarousell.Carousell:id/action_archive
		WebElement archieveButton = (new WebDriverWait(driver, 10))
				.until(ExpectedConditions.presenceOfElementLocated(afterArchieveBtn));
		archieveButton.click();
		logger.info("Clicked on Archieve Button"+archieveButton);


		// click anywhere in archieve window for window activate
		//driver.findElementByXPath("//android.widget.TextView[@text = \"Archived Chats\"]").click();
		//logger.info("Clicked anywhere in Archieve Window to activate it");

		// click on back button
		
		WebElement archieveBckBtn = (new WebDriverWait(driver, 10))
				.until(ExpectedConditions.presenceOfElementLocated(archievedBckBtn));
		archieveBckBtn.click();
		logger.info("Clicked on Archieve Window back button");
		// Verify Inbox message

		//click on buying in inbox
		WebElement buyingButton = (new WebDriverWait(driver, 10))
				.until(ExpectedConditions.presenceOfElementLocated(buyingButtonInbox));
		logger.info("Clicked on buying button in Inbox");
		buyingButton.click();


		//Assert white top in inbox
		MobileElement whiteTopText = (MobileElement) driver.findElementByAndroidUIAutomator("text(\"White Top\")");
		String whiteTopTextValue = whiteTopText.getText();
		logger.info("WhiteTop by Autotester: "+whiteTopTextValue);
		Assert.assertEquals(whiteTopTextValue, "White Top");

		// click on back button
		driver.findElement(backButton).click();
		logger.info("Landed on Home Page");
	}

	
}
