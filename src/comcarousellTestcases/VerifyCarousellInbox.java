package comcarousellTestcases;

import java.net.MalformedURLException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.Test;

import comcarousellPages.CarousellHomePage;
import comcarousellPages.CarousellInbox;
import comcarousellPages.ConfigFileReader;
import comcarousellPages.SendMessageToUser;

/*
 * 1) Verify Inbox existing message
 * 2) Longpress Inbox message
 * 3) Verify total count in Inbox
 * 4) Longpress same inbox message in Archieve box
 * 5) Verify total count in Inbox
 * 6) Logout from profile
 */
public class VerifyCarousellInbox {
	ConfigFileReader obj = new ConfigFileReader();

	@Test
	public void verifyInboxMsg() throws MalformedURLException, InterruptedException
	{
		Logger logger = Logger.getLogger("VerifyCarousellHome.java");
		PropertyConfigurator.configure("/Users/mau/eclipse-workspace/Carousell/src/log4j.properties");

		VerifyLogin v = new VerifyLogin();
		CarousellHomePage chp = v.verifyLogin();

		chp.verifyCarosellHomePage();
		logger.info("On Home page successfully after verification");

		CarousellInbox cInbox = chp.tempFun();
		int count = cInbox.verifyInboxintialCount();
		logger.info("Initial Inbox count verified successfully");

		cInbox.longPress("Inbox");
		logger.info("Initial longpress on Inbox successful");

		cInbox.verifyArchieve(count);
		logger.info("verifyArchieve successful");

		cInbox.longPress("Archieve");
		logger.info("Archieve longpress successful");

		cInbox.verifyInboxAfterArchieve();
		logger.info("Verified Inbox after Archieve successful");

		chp.logOut();
		logger.info("Logout successful");
		logger.info("VerifyCarousellInbox testcase ended successfully");
	}

}
