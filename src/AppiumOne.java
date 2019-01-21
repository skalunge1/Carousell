	//import java.io.File;
//import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class AppiumOne {

	public static AndroidDriver driver;

	public static AndroidDriver<MobileElement> capabilities() throws MalformedURLException, InterruptedException {
		//File f =  new File("src");
		//File fs = new File(f, "Carousell-2.86.182.170-1236.apk");
		Logger logger = Logger.getLogger("AppiumOne");
		PropertyConfigurator.configure("/Users/mau/eclipse-workspace/Carousell/src/log4j.properties");
		
		try {
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Nexus6API");
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		cap.setCapability("skipUnlock", "true");
		cap.setCapability("appPackage","com.thecarousell.Carousell"); 
		cap.setCapability("appActivity","com.thecarousell.Carousell.activities.EntryActivity");  
		AndroidDriver<MobileElement> driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"),cap);
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		logger.info(driver);
		return driver;
		
		}catch(Exception e)
		{
			logger.error("Connection to Appium server not established");
		}
		return driver;


	}

	






}
