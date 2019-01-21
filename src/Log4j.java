import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log4j {

	// Initialize Log4j logs
	public static void main(String [] args) {
	 Logger logger = Logger.getLogger("mainClass.java");
	 PropertyConfigurator.configure("/Users/mau/eclipse-workspace/Carousell/src/log4j.properties");
	 logger.debug("Debug");
	 logger.info("info");
	 logger.warn("Warn");
	 logger.error("Error");
	 logger.fatal("Fatal");


	}

}