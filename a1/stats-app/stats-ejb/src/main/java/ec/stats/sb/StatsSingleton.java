package ec.stats.sb;
import java.applet.*;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;

import ec.stats.Statistics;
import ec.stats.StatsSummary;

@Singleton
@LocalBean
public class StatsSingleton implements StatsSingletonLocal, StatsSingletonRemote
{
	public static final String MODEL_SAVE_PATH = "C:/enterprise/tmp/model/stats.bin";
	public static final Logger LOGGER = Logger.getLogger(StatsSingleton.class.getName());
	
	private ArrayList<Double> ds;  // Data structure to store values
    private int count;             // Number of data elements
    private double min;            // Minimum value
    private double max;            // Maximum value
    private double mean;           // Average value
    private double variance;	   // Variance
    private double std;            // Standard deviation (population)
    
    private double sum;
    private double sumOfSquares;
	
    public StatsSingleton() {
        ds = new ArrayList<>();
        count = 0;
        min = Double.POSITIVE_INFINITY;
        max = Double.NEGATIVE_INFINITY;
        mean = 0.0;
        std = 0.0;
        sum = 0.0;
        sumOfSquares = 0.0;
    }

    @Override
    public int getCount() {
        return this.count;
    }

	@Override
	public Statistics stats() {
        count = ds.size();
        if (count == 0) {
            min = 0;
            max = 0;
            mean = 0;
            std = 0;
        }
        else {
            sum = 0;
            sumOfSquares = 0;
            double minValue = Double.POSITIVE_INFINITY;
            double maxValue = Double.NEGATIVE_INFINITY;
            
        	// use one for loop to compute the stats values. 
        	for (Double x : ds) {
        		sum += x;
        		sumOfSquares += x * x;
        	
        		minValue = Math.min(minValue, x);
                maxValue = Math.max(maxValue, x);
        	}
        	
            double variance = (sumOfSquares / count) - (mean * mean);
            // In case of floating-point precision or negative rounding:
            if (variance < 0) {
                variance = 0; 
            }
            std = Math.sqrt(variance);

            // Update min and max
            min = minValue;
            max = maxValue;
       }
       Statistics DescriptiveStats = new Statistics(count, min, max, mean, variance, std);
       
       return DescriptiveStats;

    }

	@Override
    public void addData(Double x) {
        ds.add(x);
        // Update sums
        sum += x;
        sumOfSquares += x * x;

        count += 1;
        if (count == 1) {
            min = x;
            max = x;
            mean = x;
            std = 0;
        } else {
            if (x < min) min = x; // this uses the existing min to update the new min. 
            if (x > max) max = x;       
            
            mean = sum / count;
            
            double variance = (sumOfSquares / count) - (mean * mean);
            // In case of floating-point precision or negative rounding:
            if (variance < 0) {
                variance = 0; 
            }
            std = Math.sqrt(variance);
        }
    }

	@Override
	public void saveModel()
	{
		StatsSummary statsSummary = new StatsSummary();
		statsSummary.setCount(ds.size());
		statsSummary.setMin(min);
		statsSummary.setMax(max);
		statsSummary.setMean(mean);
		statsSummary.setSTD(std);
		
	    File file = new File(MODEL_SAVE_PATH);
	    File parentDir = file.getParentFile();
		
	    try {
	        if (!parentDir.exists()) {
	            boolean dirsCreated = parentDir.mkdirs();
	            if (!dirsCreated) {
	                LOGGER.severe("Failed to create directories: " + parentDir.getAbsolutePath());
	                return;
	            }
	        }

	        if (!parentDir.canWrite()) {
	            LOGGER.severe("No write permission for directory: " + parentDir.getAbsolutePath());
	            return;
	        }

	        try (FileOutputStream fileOut = new FileOutputStream(file);
	             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
	            out.writeObject(statsSummary);
	            LOGGER.info("Model saved successfully: " + file.getAbsolutePath());
	        }
	    } catch (IOException e) {
	        LOGGER.severe("Failed to save model: " + e.getMessage());
	        e.printStackTrace();
	    }
	}
}