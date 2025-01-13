package ec.lab.stats;

import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.util.FastMath;

public class StatUtilityExample {
	
	public static void main(String[] args) {
		double[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		System.out.println("mean="+StatUtils.mean(values));
		System.out.println("std="+FastMath.sqrt(StatUtils.variance(values)));
		System.out.println("median="+StatUtils.percentile(values, 50));				
		System.out.println("mena of the first three values="+StatUtils.mean(values, 0, 3));
	}
}

