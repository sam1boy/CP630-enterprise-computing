package ec.stats;

import java.util.logging.Logger;
import java.util.logging.Level;

public class MyStatistics {
	private static final Logger logger = Logger.getLogger(MyStatistics.class.getName());
	public static void main(String[] args) {
	    if (args.length != 3) {
	        System.err.println("Usage: java ec.stats.MyStatistics <start> <end> <step>");
	        return;
	    }

	    try {
	        double start = Double.parseDouble(args[0]);
	        double end = Double.parseDouble(args[1]);
	        double step = Double.parseDouble(args[2]);

	        if (step <= 0) {
	            System.err.println("Step must be a positive number.");
	            return;
	        }

	        ECStatistics stats = new ECStatistics();

	        for (double value = start; value <= end; value += step) {
	            stats.addData(value);
	        }

	        logger.log(Level.INFO, "data from {0} to {1} by step {2} are added", new Object[]{start, end, step});
	        System.out.println("count:" + stats.getCount());
	        System.out.println("min:" + stats.getMin());
	        System.out.println("max:" + stats.getMax());
	        System.out.println("mean:" + stats.getMean());
	        System.out.println("STD:" + stats.getSTD());

	        logger.log(Level.INFO, "stats() method is called to recompute stats summary");
	        stats.stats();

	        System.out.println("count:" + stats.getCount());
	        System.out.println("min:" + stats.getMin());
	        System.out.println("max:" + stats.getMax());
	        System.out.println("mean:" + stats.getMean());
	        System.out.println("STD:" + stats.getSTD());
	        
	        logger.log(Level.INFO, "stats main end");
	    } catch (NumberFormatException e) {
	        System.err.println("Invalid number format. Please provide valid numeric arguments.");
	    }
	}
}