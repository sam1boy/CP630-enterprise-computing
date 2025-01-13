package ec.lab.stats;

import org.apache.commons.math3.stat.regression.SimpleRegression;

/*
 * y = intercept + slope * x
 *  or y = slope * x
 */
public class SimpleRegressionExample {
	
	public static void main(String[] args) {
				
		SimpleRegression regression = new SimpleRegression();
		
		regression.addData(1d, 2d);
		// At this point, with only one observation,
		// all regression statistics will return NaN

		regression.addData(3d, 3d);
		// With only two observations,
		// slope and intercept can be computed
		// but inference statistics will return NaN

		regression.addData(3d, 3d);
		// Now all statistics are defined.
		
		
		System.out.println("interception="+regression.getIntercept());
		// displays intercept of regression line

		System.out.println("slope="+regression.getSlope());
		// displays slope of regression line

		System.out.println("stderr="+regression.getSlopeStdErr());
		// displays slope standard error
		         
		System.out.println("prediction at 1.5 is "+regression.predict(1.5d));
				// displays predicted y value for x = 1.5
		
		System.out.println("Estimate a model from a double[][] array of data points");
		double[][] data = { { 1, 3 }, {2, 5 }, {3, 7 }, {4, 14 }, {5, 11 }};
		SimpleRegression regression1 = new SimpleRegression();
		//SimpleRegression regression1 = new SimpleRegression(false); // false means excluding the intercept
		regression1.addData(data);
		System.out.println("interception="+regression1.getIntercept());
		System.out.println("slope="+regression1.getSlope());
		System.out.println("stderr="+regression1.getSlopeStdErr());       
	}
		

}
