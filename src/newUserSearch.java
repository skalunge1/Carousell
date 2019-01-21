import java.net.MalformedURLException;

import org.testng.Assert;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class newUserSearch extends AppiumOne {

	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		
		AndroidDriver<MobileElement>driver = capabilities();
		driver.findElementByXPath("//android.widget.Button[@text = 'Log In']").click();

		driver.findElementByXPath("//android.widget.Button[@text = \"NONE OF THE ABOVE\"]").click();

		Thread.sleep(6000);
		MobileElement loginText = driver.findElementByXPath("//android.widget.TextView[@text = 'Log In']");
		Assert.assertEquals(loginText.getText(),"Log In");
		System.out.println("on login page");

		// Enter username and password
		driver.findElementByXPath("//android.widget.EditText[@text = 'email or username']").sendKeys("101test123");
		driver.findElementByXPath("//android.widget.EditText[@text = 'password']").sendKeys("Mau123");
		driver.findElementById("com.thecarousell.Carousell:id/login_page_login_button").click();
		Thread.sleep(5000);
		System.out.println("Login successful ");

		// click to search box: Enter 'White Top' , hit 'Enter'
		driver.findElementById("com.thecarousell.Carousell:id/header_page_search_text_field").click();
		Thread.sleep(1000);
		driver.findElementByXPath("//android.widget.EditText[@text = \"Search Carousell\"]").sendKeys("White Top");
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		System.out.println("Enter key event successful on Whte Top");

		// Click on 'Ok got it notification'
		driver.findElementByXPath("//android.widget.TextView[@text = \"Ok, got it!\"]").click();
		Thread.sleep(3000);

		//click on 'Autotester'
		driver.findElementByXPath("//android.widget.TextView[@text='autotester']").click();
		Thread.sleep(1000);

		//Verify its' autotester'
		MobileElement autotesterText = driver.findElementByAndroidUIAutomator("text(\"carousell.com/autotester\")");
		System.out.println("Its me, Autotester:"+autotesterText);
		Assert.assertEquals(autotesterText.getText(),"carousell.com/autotester");

		//click on white top image in autotester
		driver.findElementByXPath("//android.widget.TextView[@text = 'White Top']").click();
		Thread.sleep(1000);

		//handle ok got it notification
		driver.findElementById("com.thecarousell.Carousell:id/feature_button").click();
		
		
		// click on chat box
		driver.findElementById("com.thecarousell.Carousell:id/button_secondary").click();
		Thread.sleep(1000);

		//type message in text box   select/click on text= I'm interested! 
		driver.findElementByAndroidUIAutomator("text(\"Type your message here\")").sendKeys("I am interested!");
		System.out.println("Text entered successfully");
		// CLICK on send button
		driver.findElementById("com.thecarousell.Carousell:id/button_send").click();
		driver.quit();

	}

}