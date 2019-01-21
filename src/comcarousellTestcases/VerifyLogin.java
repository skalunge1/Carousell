/**
 * 
 */
/**
 * @author mau
 *
 */
package comcarousellTestcases;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import comcarousellPages.CarousellHomePage;
import comcarousellPages.ConfigFileReader;
import comcarousellPages.LoginPage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

class VerifyLogin{
	//public static AndroidDriver driver;
	ConfigFileReader obj = new ConfigFileReader();
	
	@Test
	public CarousellHomePage verifyLogin() throws MalformedURLException
	{
		Logger logger = Logger.getLogger("VerifyLogin.java");
		PropertyConfigurator.configure("/Users/mau/eclipse-workspace/Carousell/src/log4j.properties");
		
		
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Nexus6API");
			cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
			cap.setCapability("skipUnlock", "true");
			cap.setCapability("appPackage","com.thecarousell.Carousell"); 
			cap.setCapability("appActivity","com.thecarousell.Carousell.activities.EntryActivity");  
			AndroidDriver<MobileElement> driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"),cap);
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			logger.info(driver);
			
			LoginPage login = new LoginPage(driver);
			login.clickOnLogIn();
			login.clickonCancelBtn();
			login.assertLogInPage();
			CarousellHomePage chp = login.enterLoginDetails(obj.getCarouselluser(), obj.getCarousellPass());	
			return chp;
			
	}
		

}