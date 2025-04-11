package ec.stats;  // or your chosen package

/**
 * Interface defining the API for computing and retrieving
 * basic descriptive statistics.
 */
public interface Statistics {

    /**
     * Add a data value to the underlying data structure.
     *
     * @param value the data value to be added
     */
    void addData(double value);

    /**
     * Compute the descriptive statistics (minimum, maximum, mean,
     * standard deviation, etc.) based on the current data set.
     *
     * Implementation detail: This method could perform 
     * heavy computations if necessary. For large data sets, 
     * consider using running sums or other approaches for efficiency.
     */
    void stats();

    /**
     * Return the number of data elements that have been added.
     *
     * @return the count of data elements
     */
    int getCount();

    /**
     * Return the minimum value in the data set.
     *
     * @return the minimum value
     */
    double getMin();

    /**
     * Return the maximum value in the data set.
     *
     * @return the maximum value
     */
    double getMax();

    /**
     * Return the mean (average) of the data set.
     *
     * @return the mean of the data
     */
    double getMean();

    /**
     * Return the standard deviation of the data values.
     * The standard deviation is typically calculated using
     * the population or sample formula; be sure to clarify
     * which version you are using in documentation.
     *
     * @return the standard deviation
     */
    double getSTD();
}
