package ec.lab.stats;

import java.text.DecimalFormat;
import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;
import org.apache.commons.math3.stat.regression.GLSMultipleLinearRegression;

/*
 * Y=X*b+u
 * OLS regression
 * GLS regression
 */
public class MultiLinearRegressionExample {
	final static DecimalFormat df = new DecimalFormat("0.00");
	
	public static void main(String[] args) {
		int dataCount = 6;
		double[] y = new double[]{11.0, 12.0, 13.0, 14.0, 15.0, 16.0};
		double[][] x = new double[dataCount][];
		x[0] = new double[]{0, 0, 0, 0, 0};
		x[1] = new double[]{2.0, 0, 0, 0, 0};
		x[2] = new double[]{0, 3.0, 0, 0, 0};
		x[3] = new double[]{0, 0, 4.0, 0, 0};
		x[4] = new double[]{0, 0, 0, 5.0, 0};
		x[5] = new double[]{0, 0, 0, 0, 6.0};  
		
		OLSMultipleLinearRegression regression = new OLSMultipleLinearRegression();		
		regression.newSampleData(y, x);
		
		System.out.println("OLSMultipleLinearRegression analysis");
		double[] beta = regression.estimateRegressionParameters();  	
		int len = beta.length;
		System.out.println("parameter count:"+len);
		System.out.println("parameters: b0, b1, ..., bn");
		for (int i=0; i<len-1; i++) {
			System.out.print(df.format(beta[i]) + ", ");
		}
		System.out.println(df.format(beta[len-1]));
		System.out.println("Residuals:");
		double result=0; 
		for (int j=0; j<dataCount; j++) {			
			result = predict(beta, x[j]);
			System.out.println("y["+j+"]: "+ df.format(y[j]));
			System.out.println("y'["+j+"]=X["+j+"]b+b0: "+ df.format(result));
			System.out.println("y["+j+"]- y'["+j+"]: "+ df.format(y[j]-result));
		}
		
		System.out.println("Predict:");
		double[] xt = {1, 0, 0, 0, 0};
		result = predict(beta, xt);
		System.out.println("pretict: "+result);
		
		
	
		System.out.println("Provided analysis");
		System.out.println("residuals");
		double[] residuals = regression.estimateResiduals();
		len = residuals.length;
		for (int i=0; i<len-1; i++) {
			System.out.print(df.format(residuals[i]) + ", ");
		}
		System.out.println(df.format(residuals[len-1]));
		System.out.println("ParametersVariance");
		double[][] parametersVariance = regression.estimateRegressionParametersVariance();	
		for (int i=0; i<parametersVariance.length; i++) {
			for (int j=0; j<parametersVariance[i].length; j++) {
				System.out.print(df.format(parametersVariance[i][j]) + " ");
			}
			System.out.println("");
		}
		System.out.println("estimateRegressandVariance()="+ regression.estimateRegressandVariance());
		System.out.println("calculateRSquared()="+ regression.calculateRSquared());
		System.out.println("estimateRegressionStandardError()="+ regression.estimateRegressionStandardError());
		

		GLSMultipleLinearRegression regression1 = new GLSMultipleLinearRegression();
		double[][] omega = new double[dataCount][];
		omega[0] = new double[]{1.1, 0, 0, 0, 0, 0};
		omega[1] = new double[]{0, 2.2, 0, 0, 0, 0};
		omega[2] = new double[]{0, 0, 3.3, 0, 0, 0};
		omega[3] = new double[]{0, 0, 0, 4.4, 0, 0};
		omega[4] = new double[]{0, 0, 0, 0, 5.5, 0};
		omega[5] = new double[]{0, 0, 0, 0, 0, 6.6};
		regression1.newSampleData(y, x, omega); 
		
		
		System.out.println("GLSMultipleLinearRegression analysis");
		beta = regression.estimateRegressionParameters();  	
		len = beta.length;
		System.out.println("parameter count:"+len);
		System.out.println("parameters: b0, b1, ..., bn");
		for (int i=0; i<len-1; i++) {
			System.out.print(df.format(beta[i]) + ", ");
		}
		System.out.println(df.format(beta[len-1]));
		System.out.println("Residuals:");
		for (int j=0; j<dataCount; j++) {			
			result = predict(beta, x[j]);
			System.out.println("y["+j+"]: "+ df.format(y[j]));
			System.out.println("y'["+j+"]=X["+j+"]b+b0: "+ df.format(result));
			System.out.println("y["+j+"]- y'["+j+"]: "+ df.format(y[j]-result));
		}
		
		System.out.println("Predict:");
		result = predict(beta, xt);
		System.out.println("pretict: "+result);
		

		System.out.println("Provided analysis");
		System.out.println("residuals");
		residuals = regression1.estimateResiduals();
		len = residuals.length;
		for (int i=0; i<len-1; i++) {
			System.out.print(df.format(residuals[i]) + ", ");
		}
		System.out.println(df.format(residuals[len-1]));
		System.out.println("ParametersVariance");
		parametersVariance = regression1.estimateRegressionParametersVariance();	
		for (int i=0; i<parametersVariance.length; i++) {
			for (int j=0; j<parametersVariance[i].length; j++) {
				System.out.print(df.format(parametersVariance[i][j]) + " ");
			}
			System.out.println("");
		}
		System.out.println("estimateRegressandVariance()="+ regression1.estimateRegressandVariance());
		System.out.println("estimateRegressionStandardError()="+ regression1.estimateRegressionStandardError());
	}
	
	/**
	 * @param b
	 * @param x
	 * @return b[0] + b[1]*x[0] + ... + b[n]*x[n-1]
	 */
	public static double predict(double b[], double x[]) {
		double result = b[0];
		result = b[0];
		for (int i=1; i<b.length; i++) {
			result += b[i]*x[i-1];  
	    }
		return result;
	}
		
}
