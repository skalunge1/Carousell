package comcarousellTestcases;

import java.net.MalformedURLException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.Test;

import comcarousellPages.CarousellHomePage;
import comcarousellPages.ConfigFileReader;

public class VerifyCarousellHome {
	ConfigFileReader obj = new ConfigFileReader();

	@Test
	public void verifyHomePage() throws MalformedURLException, InterruptedException
	{
		Logger logger = Logger.getLogger("VerifyCarousellHome.java");
		PropertyConfigurator.configure("/Users/mau/eclipse-workspace/Carousell/src/log4j.properties");
		
		VerifyLogin v = new VerifyLogin();
		CarousellHomePage chp = v.verifyLogin();
		
		chp.verifyCarosellHomePage();
		logger.info("On Carousell search box page");
		
		chp.search(obj.getCarousellSearchUser());
		logger.info("Search value user passed successfully");
		
		chp.scrollDown("101test");
		logger.info("Scroll down successful");
		
		chp.enterSearchItem(obj.getCarousellitemname());
		logger.info("verifyHomePage Testcase Ended");
		
				
		
	}
}
