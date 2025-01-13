/*
 * set the logging programmably
 * http://howtodoinjava.com/log4j2/log4j-2-xml-configuration-example/
 * http://www.journaldev.com/7128/log4j2-example-tutorial-configuration-levels-appenders#log4j2-configuration 
 */
package ec.lab.log4j2;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.ConfigurationFactory;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.config.xml.XmlConfigurationFactory;
import org.apache.logging.log4j.core.layout.PatternLayout;

public class Log4j2Example3
{
    public static void main( String[] args ) throws FileNotFoundException, IOException {
 
    	// Get context instance
    	LoggerContext context = new LoggerContext("LoggerContext");
    	
    	
    	// Get instance of configuration factory; your options are default ConfigurationFactory, XMLConfigurationFactory,
    	// 	YamlConfigurationFactory & JsonConfigurationFactory
    	ConfigurationFactory factory =  XmlConfigurationFactory.getInstance();
 
    	// Locate the source of this configuration, this located file is dummy file contains just an empty configuration Tag
    	ConfigurationSource configurationSource = new ConfigurationSource(new FileInputStream(new File("./src/main/resources/configuration.xml")));
 
    	ConfigurationSource configurationSource1 = new ConfigurationSource(new FileInputStream(new File("configuration.xml")));
    	 
    	
    	// Get a reference from configuration
    	Configuration configuration = factory.getConfiguration(context, configurationSource);
    	
    	
    	Configuration configuration1 = factory.getConfiguration(context, configurationSource1);
    	
    	
    	// Create default console appender
    	ConsoleAppender appender = ConsoleAppender.createDefaultAppenderForLayout(PatternLayout.createDefaultLayout());
 
    	// Add console appender into configuration
    	configuration.addAppender(appender);
 
    	// Create loggerConfig
    	LoggerConfig loggerConfig = new LoggerConfig("com",Level.FATAL,false);
 
    	// Add appender
    	loggerConfig.addAppender(appender,null,null);
 
    	// Add logger and associate it with loggerConfig instance
    	configuration.addLogger("com", loggerConfig);
     	
    	// Start logging system
    	context.start(configuration1);
   
    	// Get a reference for logger
    	Logger logger = context.getLogger("com");
 
    	// LogEvent of DEBUG message
    	logger.log(Level.FATAL, "Logger Name :: "+logger.getName()+" :: Passed Message ::");
 
    	// LogEvent of Error message for Logger configured as FATAL
    	logger.log(Level.ERROR, "Logger Name :: "+logger.getName()+" :: Not Passed Message ::");
 
    	// LogEvent of ERROR message that would be handled by Root
    	logger.getParent().log(Level.ERROR, "Root Logger :: Passed Message As Root Is Configured For ERROR Level messages");
    }
}
