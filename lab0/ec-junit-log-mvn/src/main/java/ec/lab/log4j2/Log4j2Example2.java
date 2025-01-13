/*
 * http://howtodoinjava.com/log4j2/log4j-2-xml-configuration-example/ 
 */
package ec.lab.log4j2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4j2Example2
{
    public static void main( String[] args ) {
    	System.setProperty("log4j.configurationFile","configuration.xml"); 
    	Logger logger = LogManager.getRootLogger();
    	logger.trace("Configuration File Defined To Be :: "+System.getProperty("log4j.configurationFile")); 
    	logger.debug("Debug Message Logged !!!");
        logger.info("Info Message Logged !!!");
        logger.error("Error Message Logged !!!", new NullPointerException("NullError"));
    	
    }
}
