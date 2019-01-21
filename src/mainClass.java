import java.util.concurrent.TimeUnit;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

//import com.oracle.tools.packager.Log;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import java.net.MalformedURLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.TouchAction;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static java.time.Duration.ofSeconds;

public class mainClass extends AppiumOne{
	    ConfigFileReader obj = new ConfigFileReader();
	    Logger logger = Logger.getLogger("mainClass");
		
		@BeforeMethod
		public void UserId() 
		{
			System.out.println("Before test start method");
			logger.info("Testing log");
			
		}
		
		
		@Test ( priority = 1 )
		//@Test(enabled=false)
		public void check101Test_user() throws MalformedURLException, InterruptedException
		{
//		    Log.startTestCase("check101Test_user");
			logger.info("check101Test_user Testcase started");
			AndroidDriver<MobileElement>driver = capabilities();
			driver.findElementByXPath("//android.widget.Button[@text = 'Log In']").click();
			WebElement CancelBtn = (new WebDriverWait(driver, 10))
					  .until(ExpectedConditions.presenceOfElementLocated(By.id("com.google.android.gms:id/cancel")));
			
			CancelBtn.click();
			//driver.findElementById("com.google.android.gms:id/cancel").click();
			WebElement loginText = (new WebDriverWait(driver, 10))
					  .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text = 'Log In']")));
			//Thread.sleep(6000);
			//MobileElement loginText = driver.findElementByXPath("//android.widget.TextView[@text = 'Log In']");
			Assert.assertEquals(loginText.getText(),"Log In");
			
			// Enter username and password
			driver.findElementByXPath("//android.widget.EditText[@text = 'email or username']").sendKeys(obj.getCarouselluser());
			driver.findElementByXPath("//android.widget.EditText[@text = 'password']").sendKeys(obj.getCarousellPass());
			driver.findElementById("com.thecarousell.Carousell:id/login_page_login_button").click();
			//Thread.sleep(5000);
			
			WebElement seeAllEle = (new WebDriverWait(driver, 10))
					  .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text = \"See All\"]")));
			
			logger.info(seeAllEle);
			// homepage
			MobileElement exploreCarosellText = driver.findElementById("com.thecarousell.Carousell:id/home_page_title_label");
			logger.info(exploreCarosellText);
			Assert.assertEquals(exploreCarosellText.getText(),"Explore Carousell");
			
			driver.findElementById("com.thecarousell.Carousell:id/header_page_search_text_field").click();
			//Thread.sleep(1000);
			WebElement user = (new WebDriverWait(driver, 10))
					  .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.EditText[@text = \"Search Carousell\"]")));
			
			user.sendKeys(obj.getCarousellSearchUser());
			//driver.findElementByXPath("//android.widget.EditText[@text = \"Search Carousell\"]").sendKeys(obj.getCarousellSearchUser());
			driver.findElementByXPath("//android.widget.TextView[@text = \"Search user '101test' instead\"]").click();
			//Thread.sleep(1000);
			WebElement pic_user = (new WebDriverWait(driver, 10))
					  .until(ExpectedConditions.presenceOfElementLocated(By.id("com.thecarousell.Carousell:id/pic_user")));

			pic_user.click();
			//driver.findElementById("com.thecarousell.Carousell:id/pic_user").click();
					
			//scroll should stop at text = 119 Listings
			MobileElement itemListingText = driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"carousell.com/101test\"));");
			Assert.assertEquals(itemListingText.getText(),"carousell.com/101test");
			
			// search box id = com.thecarousell.Carousell:id/toolbar_search click
			driver.findElementById("com.thecarousell.Carousell:id/toolbar_search").click();
			Thread.sleep(2000);
			
			
			//clear serach box 
			driver.findElementByAndroidUIAutomator("text(\"Search this seller's listings\")").sendKeys(obj.getCarousellitemname());
			((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
			logger.info("Enter key event successful");
			
			// whitetopresult -> class= android.widget.TextView text= Your search "White Top"
			//did not match any listings. Try another search?, gettext and assert
			MobileElement whiteTopText = driver.findElementByAndroidUIAutomator("text(\"Your search \"White Top\" did not match any listings. Try another search?\")");
			logger.info("White top text is :"+whiteTopText);
			Assert.assertEquals(whiteTopText.getText(),"Your search \"White Top\" did not match any listings. Try another search?");
			logger.info("Aseertion successful : No result found in any category for 'Whie Top'");
			logger.info("check101Test_user Testcase Ended");
		}
		
		@Test ( priority = 2)
		//@Test(enabled=false)
		public void sendMessage() throws MalformedURLException, InterruptedException
		{
			logger.info("Inside 'sendMessage' Testcase Started");
			
			AndroidDriver<MobileElement>driver = capabilities();
			driver.findElementByXPath("//android.widget.Button[@text = 'Log In']").click();
			WebElement CancelBtn = (new WebDriverWait(driver, 10))
					  .until(ExpectedConditions.presenceOfElementLocated(By.id("com.google.android.gms:id/cancel")));
			
			CancelBtn.click();
		
			WebElement loginText = (new WebDriverWait(driver, 10))
					  .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text = 'Log In']")));
			
			Assert.assertEquals(loginText.getText(),"Log In");
			
			// Enter username and password
			driver.findElementByXPath("//android.widget.EditText[@text = 'email or username']").sendKeys(obj.getCarouselluser());
			driver.findElementByXPath("//android.widget.EditText[@text = 'password']").sendKeys(obj.getCarousellPass());
			driver.findElementById("com.thecarousell.Carousell:id/login_page_login_button").click();
			
			logger.info("Login successful!");
			WebElement seeAllEle = (new WebDriverWait(driver, 10))
					  .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text = \"See All\"]")));
			
			logger.info(seeAllEle);
			

			// click to search box: Enter 'White Top' , hit 'Enter'
			driver.findElementById("com.thecarousell.Carousell:id/header_page_search_text_field").click();
			
			WebElement enterSearchBoxValue = (new WebDriverWait(driver, 10))
					  .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.EditText[@text = \"Search Carousell\"]")));
			enterSearchBoxValue.sendKeys(obj.getCarousellitemname());
			
			
			((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
			logger.info("Enter key event successful on Whte Top");

			// Click on 'Ok got it notification'
			driver.findElementByXPath("//android.widget.TextView[@text = \"Ok, got it!\"]").click();
			
			WebElement enterSearchBoxAutotester = (new WebDriverWait(driver, 10))
					  .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='autotester']")));

			//click on 'Autotester'
			enterSearchBoxAutotester.click();
			
		
			WebElement autotesterVerified = (new WebDriverWait(driver, 10))
					  .until(ExpectedConditions.presenceOfElementLocated(By.id("com.thecarousell.Carousell:id/view_verified")));
			logger.info(autotesterVerified);
			
			//Verify its' autotester'
			MobileElement autotesterText = driver.findElementByAndroidUIAutomator("text(\"carousell.com/autotester\")");
			logger.info("Its me, Autotester:"+autotesterText);
			Assert.assertEquals(autotesterText.getText(),"carousell.com/autotester");

			//click on white top image in autotester
			driver.findElementByXPath("//android.widget.TextView[@text = 'White Top']").click();
			

			//handle ok got it notification
			WebElement alertboxViewChat = (new WebDriverWait(driver, 10))
					  .until(ExpectedConditions.presenceOfElementLocated(By.id("com.thecarousell.Carousell:id/feature_button")));
			alertboxViewChat.click();
			logger.info("Alert on View Chat box handled"+alertboxViewChat);
			
			
			// click on chat box
			WebElement ViewChatBox = (new WebDriverWait(driver, 10))
					  .until(ExpectedConditions.presenceOfElementLocated(By.xpath("com.thecarousell.Carousell:id/button_secondary")));
			ViewChatBox.click();
			logger.info("Inside View Chat box"+ViewChatBox);
			Thread.sleep(1000);
			
			
//			driver.findElementByXPath("//android.widget.Button[@text='VIEW CHAT']").click();
//			
//			logger.info("Inside View Chat box");
//			
//			// Verify chat box
//			WebElement verifyChatBox = (new WebDriverWait(driver, 10))
//					  .until(ExpectedConditions.presenceOfElementLocated(By.id("com.thecarousell.Carousell:id/text_chat")));
//			
//			verifyChatBox.click();
//			logger.info("Inside View Chat box"+verifyChatBox);
//
//			//type message in text box   select/click on text= I'm interested! 
//			driver.findElementByAndroidUIAutomator("text(\"Type your message here\")").sendKeys("I am interested!");
			//logger.info("Text entered successfully");

			
			// CLICK on send button
			//((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
			
			//driver.findElementById("com.thecarousell.Carousell:id/button_send").click();
			logger.info("Inside 'sendMessage' Testcase Ended");
		}
		
    	@Test ( priority = 3)
		//@Test(enabled=false)
		public void checkInboxFunction_withAutoTester() throws MalformedURLException, InterruptedException
		{
			logger.info("Inside 'checkInboxFunction_withAutoTester' Testcase");
			
			AndroidDriver<MobileElement>driver = capabilities();
			driver.findElementByXPath("//android.widget.Button[@text = 'Log In']").click();
			WebElement CancelBtn = (new WebDriverWait(driver, 10))
					  .until(ExpectedConditions.presenceOfElementLocated(By.id("com.google.android.gms:id/cancel")));
			
			CancelBtn.click();
		
			WebElement loginText = (new WebDriverWait(driver, 10))
					  .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text = 'Log In']")));
			
			Assert.assertEquals(loginText.getText(),"Log In");
			
			// Enter username and password
			driver.findElementByXPath("//android.widget.EditText[@text = 'email or username']").sendKeys(obj.getCarouselluser());
			driver.findElementByXPath("//android.widget.EditText[@text = 'password']").sendKeys(obj.getCarousellPass());
			driver.findElementById("com.thecarousell.Carousell:id/login_page_login_button").click();
			
			logger.info("Login successful!");
			
			WebElement seeAllEle = (new WebDriverWait(driver, 10))
					  .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text = \"See All\"]")));
			
			logger.info(seeAllEle);
			
			driver.findElementById("com.thecarousell.Carousell:id/header_page_chat_button").click();
			logger.info("click on chat inbox");
				
			
			List<MobileElement> elements=	driver.findElementsById("com.thecarousell.Carousell:id/view_inbox");
			int initial_inboxLength = elements.size();
			logger.info("No. of message before in Inbox: "+initial_inboxLength);
			
			// swipe left
			TouchAction t = new TouchAction(driver);
			WebElement autotester = driver.findElementByAndroidUIAutomator("text(\"autotester\")");
			//this will tap on emIL RECIVED BY AUTOTESTER
			//t.tap(tapOptions().withElement(element(autotester))).perform();
			
			//This will longpress on email and archive option will enable on top
			t.longPress(longPressOptions().withElement(element(autotester)).withDuration(ofSeconds(2))).release().perform();
		    //t.press(1440, 862).moveTo(0, 522).release().perform();
		    logger.info("Long press successful");
		    
		    //click on archieve button 
		    driver.findElementById("com.thecarousell.Carousell:id/action_archive").click();
		    logger.info("Clicked on move to archive icon successfully");
		    //Thread.sleep(500);
		    // Clicked inside window to make it active
		    driver.findElementByAndroidUIAutomator("text(\"BUYING\")").click();
		    //driver.findElementByXPath("//android.widget.TextView[@text = 'BUYING']").click();
		    logger.info("Clicked inside Inbox ->Buying to make window active");
		  
		    // After click inbox length
		    driver.findElementByXPath("//android.widget.TextView[@text = 'ALL']").click();
		    logger.info("Clicked inside Inbox to count number of messages");
		    
		    List<MobileElement> element2=	driver.findElementsById("com.thecarousell.Carousell:id/view_inbox");
			int final_inboxLength = element2.size();
			logger.info("No. of message after archival in inbox: "+final_inboxLength);
			Assert.assertEquals(final_inboxLength+1, initial_inboxLength);
			
			// Click on Archived text go to Archive folder
			driver.findElementByXPath("//android.widget.TextView[@text = 'ARCHIVED']");
			logger.info("Clicked on Archieve text");

			
		    driver.findElementById("com.thecarousell.Carousell:id/action_archived").click();
			List<MobileElement> element3 = driver.findElementsById("com.thecarousell.Carousell:id/view_inbox");
			int initial_ArchiveInboxLength = element3.size();
			logger.info("No. of message in Archived: "+initial_ArchiveInboxLength);
			
			TouchAction t1 = new TouchAction(driver);
			WebElement autotester1 = driver.findElementByAndroidUIAutomator("text(\"autotester\")");
			
			
			//This will longpress on email and archive option will enable on top
			t1.longPress(longPressOptions().withElement(element(autotester1)).withDuration(ofSeconds(2))).release().perform();
		    logger.info("Long press successful in archive");
		    
		    
		    //click on archieve button id=com.thecarousell.Carousell:id/action_archive
		    WebElement archieveButton = (new WebDriverWait(driver, 10))
					  .until(ExpectedConditions.presenceOfElementLocated(By.id("com.thecarousell.Carousell:id/action_archive")));
		    archieveButton.click();
		    logger.info("Clicked on Archieve Button"+archieveButton);
		    
		    
		    // click anywhere in archieve window for window activate
		    //driver.findElementByXPath("//android.widget.TextView[@text = \"Archived Chats\"]").click();
		    //logger.info("Clicked anywhere in Archieve Window to activate it");
		  
		    // click on back button
		    //driver.findElementByClassName("android.widget.ImageButton").click();
		   // driver.findElementByXPath("//com.thecarousell.Carousell[@package = \"com.thecarousell.Carousell\"]").click();
		    WebElement archieveBckBtn = (new WebDriverWait(driver, 10))
					  .until(ExpectedConditions.presenceOfElementLocated(By.className("android.widget.ImageButton")));
		    archieveBckBtn.click();
		    logger.info("Clicked on Archieve Window back button");
		    // Verify Inbox message
		    
		    //click on buying in inbox
		   //driver.findElementByAndroidUIAutomator("text(\"BUYING\")").click();
		    WebElement buyingButton = (new WebDriverWait(driver, 10))
					 .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text = 'BUYING']")));
		    logger.info("Clicked on buying button in Inbox");
		    buyingButton.click();
		    
		    
		    //Assert white top in inbox
		    MobileElement whiteTopText = (MobileElement) driver.findElementByAndroidUIAutomator("text(\"White Top\")");
		    String whiteTopTextValue = whiteTopText.getText();
		    logger.info("WhiteTop by Autotester: "+whiteTopTextValue);
			Assert.assertEquals(whiteTopTextValue, "White Top");
			
			// click on back button
			driver.findElementByXPath("//android.widget.ImageButton[@content-desc = \"Navigate up\"]").click();
		   logger.info("Landed on Home Page");
			
		    //click on ME
		    driver.findElementByXPath("//android.widget.TextView[@text = \"ME\"]").click();
		    logger.info("Landed on user profile");
		    //click on settings in Profile
		    driver.findElementById("com.thecarousell.Carousell:id/button_settings").click();
		    logger.info("I am on Profile setting page");
		    //scroll down till logout
		    MobileElement logout = (MobileElement) driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Logout\"));");
		    logout.click();
		    logger.info("Logout Successfull");
			

		}

		
	@AfterTest
	public void endTest()
		{
		   System.out.println("Quitting Android Driver after test");
		   driver.quit();
			
	}

	}
