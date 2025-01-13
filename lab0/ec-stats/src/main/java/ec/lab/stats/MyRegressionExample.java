/**
 *  LR algorithms
 *  HBF
 *  2024-07-26
 */
package ec.lab.stats;

import java.text.DecimalFormat;
import java.util.Random;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

/*
 * y=x*b + b0 = [1 x]*[b0 b]  = X*B, X = [1 x], B = [u b]
 * Matrix algorithm
 * Back propagation algorithm
 */
public class MyRegressionExample {
	final static DecimalFormat df = new DecimalFormat("0.00");

	public static void main(String[] args) {
		double[] y = new double[] { 11.0, 12.0, 13.0, 14.0, 15.0, 16.0 };
		double[][] x = new double[6][];
		x[0] = new double[] { 0, 0, 0, 0, 0 };
		x[1] = new double[] { 2.0, 0, 0, 0, 0 };
		x[2] = new double[] { 0, 3.0, 0, 0, 0 };
		x[3] = new double[] { 0, 0, 4.0, 0, 0 };
		x[4] = new double[] { 0, 0, 0, 5.0, 0 };
		x[5] = new double[] { 0, 0, 0, 0, 6.0 };

		double[] B = MyLinearRegressionMatrix(x, y);
		int row = x.length;
		System.out.println("MyLinearRegressionMatrix");
		int len = B.length;
		System.out.println("parameter count:" + len);
		System.out.print("parameters: ");
		for (int i = 0; i <= x[0].length; i++) {
			System.out.print("b"+i+" ");
		}
		System.out.println();
		
		
		for (int i = 0; i < len - 1; i++) {
			System.out.print(df.format(B[i]) + ", ");
		}
		System.out.println(df.format(B[len - 1]));
		System.out.println("Residuals:");
		double result = 0;
		for (int j = 0; j < row; j++) {
			result = predict(B, x[j]);
			System.out.println("y[" + j + "]: " + df.format(y[j]));
			System.out.println("y'[" + j + "]=X[" + j + "]b+b0: " + df.format(result));
			System.out.println("y[" + j + "]- y'[" + j + "]: " + df.format(y[j] - result));
		}
		
		System.out.println("Predict:");
		double[] xt = { 1, 0, 0, 0, 0 };
		result = predict(B, xt);
		System.out.println("pretict: " + df.format(result));

		/**
		 * test for LR aggregate
		 */
		System.out.println("MyLinearRegressionAggregate");
		double[][] a1 = xta(x);
		double[][] a2 = xta(x);
		double[] b1 = MyLinearRegressionMatrix(x, y);
		double[] b2 = MyLinearRegressionMatrix(x, y);
		double[] b = MyLinearRegressionAggregate(a1, b1, a2, b2);
		for (int i = 0; i < len - 1; i++) {
			System.out.print(df.format(b[i]) + ", ");
		}
		System.out.println(df.format(b[len - 1]));

		/**
		 * test for LR incremental algorithm
		 */

		a1 = xta(x);
		b1 = MyLinearRegressionMatrix(x, y);
		double[][] x1 = { { 0, 0, 0, 5.0, 0 }, { 0, 0, 0, 0, 6.0 } };
		double[] y1 = { 15, 16 };
		System.out.println("MyLinearRegressionIncremental");
		double[] bi = MyLinearRegressionIncremental(a1, b1, x1, y1);
		for (int i = 0; i < len - 1; i++) {
			System.out.print(df.format(bi[i]) + ", ");
		}
		System.out.println(df.format(bi[len - 1]));

		/**
		 * test for LR backward propagation algorithm
		 */
		
		System.out.println("MyLinearRegressionBackPropagationMatrix");
		double[] bp = MyLinearRegressionBackPropagationMatrix(x, y);
		for (int i = 0; i < len - 1; i++) {
			System.out.print(df.format(bp[i]) + ", ");
		}
		System.out.println(df.format(bp[len - 1]));
		
		
		System.out.println("MyLinearRegressionBackPropagation");
		bp = MyLinearRegressionBackPropagation(x, y);
		for (int i = 0; i < len - 1; i++) {
			System.out.print(df.format(bp[i]) + ", ");
		}
		System.out.println(df.format(bp[len - 1]));

	}

	/**
	 * @param x[][]
	 * @param y[]
	 * @return B = (X' * X)^-1 * X' * Y as double array, where X=[1 x], Y = y' are
	 *         matices
	 */
	public static double[] MyLinearRegressionMatrix(double x[][], double y[]) {
		RealMatrix X = MatrixUtils.createRealMatrix(augm(x));
		RealMatrix Y = MatrixUtils.createRealMatrix(atv(y));
		return MatrixUtils.inverse(X.transpose().multiply(X)).multiply(X.transpose()).multiply(Y).getColumnVector(0)
				.toArray();
	}

	/**
	 * 
	 * @param a1 array of X1'X1 of the first data set
	 * @param b1 array of LR parameters of first data set
	 * @param x  array of data set
	 * @param y  array of values
	 * @return aggregated array of LR parameters of both data set =
	 *         (a1+axm^Taxm)^-1(a1b1+axm*(axm^T*y), axm = [1 x],
	 */
	public static double[] MyLinearRegressionIncremental(double a1[][], double b1[], double x[][], double y[]) {
		RealMatrix A1 = MatrixUtils.createRealMatrix(a1);
		RealVector B1 = MatrixUtils.createRealVector(b1);
		RealMatrix axm = MatrixUtils.createRealMatrix(augm(x));// create rowX(col+1) matrix axm from x, like [1 x]
		RealMatrix A2 = axm.transpose().multiply(axm); // A2 = axm^T * axm
		RealMatrix Y = MatrixUtils.createRealMatrix(atv(y));// create rowX1 matrix Y from array y, like [y_1, y_2, ...,
															// y_k]^T
		return MatrixUtils.inverse(A1.add(A2))
				.preMultiply(A1.preMultiply(B1).add(axm.transpose().multiply(Y).getColumnVector(0))).toArray();
	}

	/**
	 * 
	 * @param a1 array of X1'X1 of the first data set
	 * @param b1 array of LR parameters of first data set
	 * @param a2 array of X2'X2 of the first data set
	 * @param b2 array of LR parameters of first data set
	 * @return aggregated array of LR parameters of both data set =
	 *         (a1+a2)^-1(a1b1+a2b2)
	 */
	public static double[] MyLinearRegressionAggregate(double a1[][], double b1[], double a2[][], double b2[]) {
		RealMatrix A1 = MatrixUtils.createRealMatrix(a1);
		RealVector B1 = MatrixUtils.createRealVector(b1);
		RealMatrix A2 = MatrixUtils.createRealMatrix(a2);
		RealVector B2 = MatrixUtils.createRealVector(b2);
		RealVector B = MatrixUtils.inverse(A1.add(A2)).preMultiply(A1.preMultiply(B1).add(A2.preMultiply(B2)));
		return B.toArray();
	}

	/**
	 * @param x[][] training data
	 * @param y[] target value
	 * @return B = (X' * X)^-1 * X' * Y as double array, where X=[1 x], Y = y' are
	 *         matrices.
	 */
	public static double[] MyLinearRegressionBackPropagationMatrix(double x[][], double y[]) {
		int maxEpoch = 1000;
		double learnrate = 0.05;
		double tol = 0.00001;

		// create the parameter 1 column matrix B of random initial values
		Random r = new Random();
		int len = x[0].length;
		double[] b = new double[len + 1]; // plus 1 for the bias parameter as b[0].
		for (int i = 0; i < len + 1; i++)
			b[i] = r.nextDouble();

		return TrainByBackpropagationMatrix(b, x, y, learnrate, maxEpoch, tol);
	}

	/**
	 * This is the matrix version of back propagation training
	 * @param           b[] given initial model parameter for traning
	 * @param           x[][] training data
	 * @param           y[] target value
	 * @param learnrate - learning rate
	 * @param epoch     - maximum number of epocks
	 * @param tol       - tolerance for loss
	 * @return trained parameters as double array
	 * @return
	 */
	public static double[] TrainByBackpropagationMatrix(double b[], double x[][], double[] y, double learnrate, int epoch,
			double tol) {
		double[][] ax = augm(x);
		RealMatrix B = MatrixUtils.createRealMatrix(atv(b));
		RealMatrix X = MatrixUtils.createRealMatrix(ax);
		RealMatrix Y = MatrixUtils.createRealMatrix(atv(y));

		for (int i = 1; i <= epoch; i++) {
			RealMatrix Output = X.multiply(B);
			RealMatrix Error = Y.subtract(Output);
			RealMatrix derivative = X.transpose().multiply(Error);  
			RealMatrix delta = derivative.scalarMultiply(learnrate);
			B = B.add(delta);
			// Loss(B, X, Y) is the square sum of X^tB - Y			
			double norm  = Error.getColumnVector(0).getNorm();
			double totalLoss = norm*norm;		
			//System.out.printf("Epoch %d, loss %1.3f\n", i, totalLoss);
			if (totalLoss < tol) break;			
		}
		return B.getColumnVector(0).toArray();
	}

	
	/**
	 * @param x[][] training data
	 * @param y[] target value
	 * @return B = (X' * X)^-1 * X' * Y as double array, where X=[1 x], Y = y' are
	 *         matrices.
	 */
	public static double[] MyLinearRegressionBackPropagation(double x[][], double y[]) {
		int maxEpoch = 1000;
		double learnrate = 0.05;
		double tol = 0.00001;

		// create the parameter 1 column matrix B of random initial values
		Random r = new Random();
		int len = x[0].length;
		double[] b = new double[len + 1]; // plus 1 for the bias parameter as b[0].
		for (int i = 0; i < len + 1; i++)
			b[i] = r.nextDouble();

		return TrainByBackpropagation(b, x, y, learnrate, maxEpoch, tol);
	}
	
	/**
	 * 
	 * @param b[] given initial model parameter for traning
	 * @param x[][] training data
	 * @param y[] target value
	 * @param learnrate - learning rate
	 * @param epoch     - maximum number of epocks
	 * @param tol       - tolerance for loss
	 * @return trained parameters as double array
	 * @return
	 */
	public static double[] TrainByBackpropagation(double b[], double x[][], double[] y, double learnrate, int epoch,
			double tol) {
		for (int i = 1; i <= epoch; i++) {
			double totalLoss = 0.0;
			for (int j = 0; j < y.length; j++) {
				double output = predict(b, x[j]);
				double error = y[j] - output;
				totalLoss += error*error;	
				b[0] += learnrate * error;    // update the weight of bias
				for (int k = 1; k < b.length; k++) {
					double delta = learnrate * x[j][k-1] * error; // negative derivative * learnrate
					b[k] += delta;  
				}
			}
			
			//System.out.printf("Epoch %d, loss %1.3f\n", i, totalLoss);
			if (totalLoss< tol)
				break;
		}
		return b;
	}

	
	/**
	 * @param           x[][] training data
	 * @param           y[] target value
	 * @param learnrate - learning rate
	 * @param epoch     - maximum number of epocks
	 * @param tol       - tolerance for loss
	 * @return trained parameters as double array
	 */
	public static double[] MyLinearRegressionBackPropagation(double x[][], double y[], double learnrate, int epoch,
			double tol) {
		int len = x[0].length;
		double[][] ax = augm(x);
		RealMatrix X = MatrixUtils.createRealMatrix(ax);
		RealMatrix Y = MatrixUtils.createRealMatrix(atv(y));

		// create the parameter 1 column matrix B of random initial values
		Random r = new Random();
		double[] b = new double[len + 1]; // plus 1 for the bias parameter as b[0].
		for (int i = 0; i < len + 1; i++)
			b[i] = r.nextDouble();
		RealMatrix B = MatrixUtils.createRealMatrix(atv(b));

		double ls = 0.0;
		for (int i = 1; i <= epoch; i++) {
			ls = X.multiply(B).subtract(Y).getColumnVector(0).getNorm(); // square root of Loss(X, B, Y) = norm of X^TB
																			// - Y
			System.out.printf("Epoch %d, loss %1.3f\n", i, ls);
			if (ls < tol)
				break;
			// B =
			// B.subtract(X.transpose().multiply(X.multiply(B).subtract(Y)).scalarMultiply(learnrate));
			// // breakdown
			RealMatrix derivative = X.transpose().multiply(X.multiply(B).subtract(Y)); // the derivative of loss
																						// function Loss(X, B, Y) as
																						// gradient
			RealMatrix delta = derivative.scalarMultiply(learnrate);
			B = B.subtract(delta);
		}
		return B.getColumnVector(0).toArray();
	}

	/**
	 * covert 1D array to 2D array of one column
	 * 
	 * @param x - 1D array
	 * @return -
	 */
	public static double[][] atv(double x[]) {
		int len = x.length;
		double[][] y = new double[len][1];
		for (int i = 0; i < len; i++) {
			y[i][0] = x[i];
		}
		return y;
	}

	/**
	 * 
	 * @param x - double train data array
	 * @return - [1 x]^T[1 x]
	 */
	public static double[][] xta(double x[][]) {
		int row = x.length;
		int col = x[0].length;
		// create augmented matrix [1 x] = X
		double[][] ax = new double[row][];// '2D array'
		for (int i = 0; i < row; i++) {
			ax[i] = new double[col + 1];
			ax[i][0] = 1;
			for (int j = 0; j < col; j++)
				ax[i][j + 1] = x[i][j];
		}

		double[][] ata = new double[col + 1][];// '2D array'
		for (int i = 0; i < col + 1; i++) {
			ata[i] = new double[col + 1];
			for (int j = 0; j < col + 1; j++) {
				ata[i][j] = 0;
				for (int k = 0; k < row; k++)
					ata[i][j] += ax[k][i] * ax[k][j];
			}
		}
		return ata;
	}

	/**
	 * Creates augmented matrix
	 * 
	 * @param x - 2D array
	 * @return - [1 x]
	 */
	public static double[][] augm(double x[][]) {
		int row = x.length;
		int col = x[0].length;
		// create augmented matrix [1 x] = X
		double[][] ax = new double[row][];
		for (int i = 0; i < row; i++) {
			ax[i] = new double[col + 1];
			ax[i][0] = 1;
			for (int j = 0; j < col; j++)
				ax[i][j + 1] = x[i][j];
		}
		return ax;
	}

	public static double loss(double y[], double y1[]) {
		int len = y.length;
		double squareSum = 0;
		for (int i = 0; i < len; i++) {
			squareSum += (y[i] - y1[i]) * (y[i] - y1[i]);
		}
		return squareSum;
	}

	public static double predict(double b[], double x[]) {
		double result = b[0];
		result = b[0];
		for (int i = 1; i < b.length; i++) {
			result += b[i] * x[i - 1];
		}
		return result;
	}

}
