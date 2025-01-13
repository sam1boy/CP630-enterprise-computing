package ec.lab.stats;

import org.apache.commons.math3.stat.descriptive.AggregateSummaryStatistics;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;

public class MultipleSampleDescriptiveStatisticsExample {
	
	public static void main(String[] args) {
		
		// Create a AggregateSummaryStatistics instance to accumulate the overall statistics 
		// and AggregatingSummaryStatistics for the subsamples
		AggregateSummaryStatistics aggregate = new AggregateSummaryStatistics();
		SummaryStatistics setOneStats = aggregate.createContributingStatistics();
		SummaryStatistics setTwoStats = aggregate.createContributingStatistics();

		   
		for( int i = 1; i <= 10; i++) {
			setOneStats.addValue(i);
			setTwoStats.addValue(i);
		}
		
		// Full sample data is reported by the aggregate
		double totalSampleSum = aggregate.getSum();
		System.out.println("totalSampleSum="+totalSampleSum);
		System.out.println("Count="+aggregate.getN());
		System.out.println("mean="+aggregate.getMean());
		System.out.println("std="+aggregate.getStandardDeviation());
		
	}

}
