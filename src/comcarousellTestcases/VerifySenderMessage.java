package comcarousellTestcases;

import java.net.MalformedURLException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.Test;

import comcarousellPages.CarousellHomePage;
import comcarousellPages.ConfigFileReader;
import comcarousellPages.SendMessageToUser;

/*
 * Verify message send to user
 */
public class VerifySenderMessage {

	ConfigFileReader obj = new ConfigFileReader();

	@Test
	public void verifyMessage() throws MalformedURLException, InterruptedException
	{
		Logger logger = Logger.getLogger("VerifyCarousellHome.java");
		PropertyConfigurator.configure("/Users/mau/eclipse-workspace/Carousell/src/log4j.properties");

		VerifyLogin v = new VerifyLogin();
		CarousellHomePage chp = v.verifyLogin();

		SendMessageToUser sendmsgUser = chp.verifyCarosellHomePage();
		logger.info("On Home page successfully after verification");


		sendmsgUser.enterSearchValue(obj.getCarousellitemname());
		logger.info("Enter search item successfully in serach box");

		sendmsgUser.clickOnUser();
		logger.info("Click on Autotester successfully");

		sendmsgUser.assertUser();
		logger.info("Assertion on Autotester is successful");

		sendmsgUser.clickOnTop();
		logger.info("Click on White Top is successful");
		logger.info("VerifySenderMessagee Testcase Ended");

		//chp.logOut();

	}

}
