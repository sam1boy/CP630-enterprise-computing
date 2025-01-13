package ec.lab.stats;

import org.apache.commons.math3.stat.descriptive.SummaryStatistics;

public class SummaryStatisticsExample {

	public static void main(String[] args) {

		SummaryStatistics stats = new SummaryStatistics();
		
		for (int i = 1; i <= 10; i++) {
			stats.addValue(i);
		}

		System.out.println("sum=" + stats.getSum());
		System.out.println("count=" + stats.getN());
		System.out.println("mininumu=" + stats.getMin());
		System.out.println("maximum=" + stats.getMax());
		System.out.println("mean=" + stats.getMean());
		System.out.println("std=" + stats.getStandardDeviation());
		System.out.println("/n/ntoString()=" + stats.toString());
		
	}

}
