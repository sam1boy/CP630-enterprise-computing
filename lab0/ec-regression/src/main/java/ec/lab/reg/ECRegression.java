package ec.lab.reg;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * @brief - multi-linear regression model computing and prediction, sample solution, based on assignment submissions
 */
public class ECRegression {
	private static final Logger logger = LogManager.getLogger(ECRegression.class.getName());  
	
	public static void main(String[] args) throws IOException {
		/*
		 * get cli arguments
		 */
		int option = 0;
		String inFileName = "data.csv";
		String modelFileName = "regression.mod";
		String outFileName = "result.txt";
		
		for (int i=0; i<args.length; i++) {
			if (args[i].equals("-m")) {
				option = 1; 
				modelFileName =  args[++i]; 
			} 
			else if (args[i].equals("-i")) {
				inFileName =  args[++i]; 
			}
			else if (args[i].equals("-o") ) {
				outFileName =  args[++i];
			} 
			else {
				i++;
			}
		}
		
		if (option == 0) {
	
			try {
								
				/*
				 * get lines of data from file
				 */
				List<String> listString = FileListString(inFileName);
				String[] AttrNames = listString.get(0).split(",");
				
				/*
				 * retrieve and set X, Y data for multi-linear regression 
				 */
				int attrCount =  AttrNames.length;
				int dataCount =  listString.size()-1;
				double[] Y = new double[dataCount];
				double[][] X = new double[dataCount][attrCount-1];
				
				//double[][] X = new double[dataCount][attrCount-2];   // for attribute elimination
				int j=0;
				for (int i = 0; i < dataCount; i++) {
					String[] stringArray = listString.get(i+1).split(",");
					for (j = 0; j < attrCount-1; j++) {
						X[i][j] = Double.parseDouble(stringArray[j]);
					}
					Y[i] =  Double.parseDouble(stringArray[j]);				
				}
				

				/*
				 * get the model			
				 */
				double[] beta = regressionModel(X, Y);
				logger.info("Regression model computing is invoked"); 		
				System.out.println(Arrays.toString(beta));
				
				try {
					ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(outFileName));
					os.writeObject(beta);
					os.close();					
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				

			} catch (IOException e) {				
				e.printStackTrace();
			} 
			
		} 
		
		else if (option == 1) {	

			try {
				// read regression model object, double array
				ObjectInputStream is = new ObjectInputStream(new FileInputStream(modelFileName));
				double[] beta = (double[]) is.readObject();
				is.close();
				
				double[] x = readDoubleArrayFromFile(inFileName);				
				double result = predict(beta, x);
				
				System.out.println(result);
				logger.info("prediction is invoked"); 
				
				// save result to text file
				FileWriter writer = null;	
				writer = new FileWriter(new File(outFileName));
				writer.write(String.valueOf(result));
				writer.flush();
				writer.close();

			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

		}
		
		else {
			System.out.println("not defined");
		}
	}

	/**
	 *  read text file and return list of string
	 * 
	 * @param filename
	 * @return List<String>
	 * @throws IOException 
	 */
	private static List<String> FileListString(String filename) throws IOException {
		BufferedReader input = new BufferedReader(new FileReader(filename));
		String line = null;
		List<String> stringlist = new ArrayList<String>();
		while ((line = input.readLine()) != null) {
			stringlist.add(line);
		}	
		return stringlist;
	}
	
	/**
	 * Static Method reads csv file and provides a double array as an output.
	 * @author Jai
	 * @param fileName
	 * @return
	 */
	private static double[] readDoubleArrayFromFile(String fileName) {
		BufferedReader input = null;
		double[] fileContents = null;
		try {
			input = new BufferedReader(new FileReader(fileName));
			String line = null;

			while ((line = input.readLine()) != null) {
				String[] st = line.split(",");
				fileContents = new double[st.length];
				for (int j = 0; j < st.length; j++) {
					fileContents[j] = Double.parseDouble(st[j]);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileContents;
	}
	
	/**
	 *  evaluate data by model
	 * 
	 * @param model
	 * @param x
	 * @return double model.x
	 */
	private static double predict(double[] model, double[] x) {
		int length = model.length;
		double result = model[0];
		for (int i = 1; i < length; i++) {
			result += model[i] * x[i - 1];
		}
		return result;
	}
	
	/**
	 *  compute regression model
	 * 
	 * @param X
	 * @param Y
	 * @return double[] beta
	 */
	private static double[] regressionModel(double[][] X, double[] Y) {
		OLSMultipleLinearRegression regression = new OLSMultipleLinearRegression();
		regression.newSampleData(Y, X);
		return regression.estimateRegressionParameters();
	}
	
}
