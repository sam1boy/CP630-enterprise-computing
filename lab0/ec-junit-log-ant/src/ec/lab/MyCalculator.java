package ec.lab;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyCalculator {
	private static final Logger logger = LogManager.getLogger(MyCalculator.class.getName());  
    public static void main(String[] args) {
    	Calculator calculator = new ECCalculator();
    	int result = calculator.sum(3, 4);
    	System.out.println(result);
        logger.info("The sum of 3 and 4 is " + result); 
    }
}
