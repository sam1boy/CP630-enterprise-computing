package ec.lab.stats;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class DescriptiveStatisticsExample {
	
	public static void main(String[] args) {
				
		DescriptiveStatistics stats = new DescriptiveStatistics();
				
		// Create a SynchronizedDescriptiveStatistics instance and
		// use as any other DescriptiveStatistics instance
		// DescriptiveStatistics stats = new SynchronizedDescriptiveStatistics();
		
		for( int i = 1; i <= 10; i++) {
		     stats.addValue(i);
		}
		
		System.out.println("\ntoString()="+stats.toString());
		System.out.println("************************************");
		System.out.println("Window size="+stats.getWindowSize());
		System.out.println("sum="+stats.getSum());
		System.out.println("count="+stats.getN());
		System.out.println("mininumu="+stats.getMin());
		System.out.println("maximum="+stats.getMax());
		System.out.println("mean="+stats.getMean());		
		System.out.println("std="+stats.getStandardDeviation());
		System.out.println("element at "+ 1 + "="+ stats.getElement(1));
		System.out.println("median="+stats.getPercentile(50));

		
		System.out.println("=========set window size 5===========");
		stats.setWindowSize(5);
		System.out.println("Window size="+stats.getWindowSize());
		
		System.out.println("sum="+stats.getSum());
		System.out.println("count="+stats.getN());
		System.out.println("mininumu="+stats.getMin());
		System.out.println("maximum="+stats.getMax());
		System.out.println("mean="+stats.getMean());		
		System.out.println("std="+stats.getStandardDeviation());

		System.out.println("element at "+ 1 + "="+ stats.getElement(1));
		System.out.println("median="+stats.getPercentile(50));
		
		System.out.println("=========set window size 10===========");
		stats.setWindowSize(10);
		System.out.println("Window size="+stats.getWindowSize());
		System.out.println("sum="+stats.getSum());
		System.out.println("count="+stats.getN());
		System.out.println("mininumu="+stats.getMin());
		System.out.println("maximum="+stats.getMax());
		System.out.println("mean="+stats.getMean());		
		System.out.println("std="+stats.getStandardDeviation());

		System.out.println("element at "+ 1 + "="+ stats.getElement(1));
		System.out.println("median="+stats.getPercentile(50));
		
		System.out.println("=========set window size -1===========");
		stats.setWindowSize(-1);
		System.out.println("Window size="+stats.getWindowSize());
		System.out.println("sum="+stats.getSum());
		System.out.println("count="+stats.getN());
		System.out.println("mininumu="+stats.getMin());
		System.out.println("maximum="+stats.getMax());
		System.out.println("mean="+stats.getMean());		
		System.out.println("std="+stats.getStandardDeviation());

		System.out.println("element at "+ 1 + "="+ stats.getElement(1));
		System.out.println("median="+stats.getPercentile(50));
		
	}
}
