package ec.lab.log4j2;
/*
 * http://howtodoinjava.com/log4j2/log4j-2-xml-configuration-example/ 
 */


import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;

public class Log4j2Example0 {
    static Logger logger = LogManager.getLogger(Log4j2Example1.class);

    public static void main(String[] args) {
        logger.info("Hello World"); 
    }
}