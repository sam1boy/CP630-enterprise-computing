package ec.stats;

import java.util.ArrayList;

public class ECStatistics implements Statistics {

    private ArrayList<Double> ds;  // Data structure to store values
    private int count;             // Number of data elements
    private double min;            // Minimum value
    private double max;            // Maximum value
    private double mean;           // Average value
    private double std;            // Standard deviation (population)
    
    private double sum;
    private double sumOfSquares;

    /**
     * Default constructor initializes fields and data structure.
     */
    public ECStatistics() {
        ds = new ArrayList<>();
        count = 0;
        min = Double.POSITIVE_INFINITY;
        max = Double.NEGATIVE_INFINITY;
        mean = 0.0;
        std = 0.0;
        sum = 0.0;
        sumOfSquares = 0.0;
    }

    /**
     * Add a data value to the array list ds, and update
     * count, min, max, mean, and std incrementally.
     *
     * @param x the data value to be added
     */
    @Override
    public void addData(double x) {
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

    /**
     * Compute the statistics (min, max, mean, std) by traversing
     * the entire data set once (one loop). Then update the
     * corresponding property values in this object.
     */
    @Override
    public void stats() {
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
    }

    /**
     * Return the number of data elements in the data structure.
     */
    @Override
    public int getCount() {
        return this.count;
    }

    /**
     * Return the minimum value in the data structure.
     */
    @Override
    public double getMin() {
        return this.min;
    }

    /**
     * Return the maximum value in the data structure.
     */
    @Override
    public double getMax() {
        return this.max;
    }

    /**
     * Return the average (mean) of the data values.
     */
    @Override
    public double getMean() {
        return this.mean;
    }

    /**
     * Return the standard deviation (population) of the data values.
     */
    @Override
    public double getSTD() {
        return this.std;
    }
}
