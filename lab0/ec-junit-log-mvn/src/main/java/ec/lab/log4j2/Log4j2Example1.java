package ec.lab.log4j2;
/*
 * http://howtodoinjava.com/log4j2/log4j-2-xml-configuration-example/ 
 */

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
 
public class Log4j2Example1 
{
    private static final Logger LOGGER = LogManager.getLogger(Log4j2Example1.class.getName());
     
    public static void main(String[] args) 
    {
    	LOGGER.trace("Trace Message Logged !!!");
    	LOGGER.debug("Debug Message Logged !!!");
        LOGGER.info("Info Message Logged !!!");
        LOGGER.error("Error Message Logged !!!", new NullPointerException("NullError"));
    }
}
