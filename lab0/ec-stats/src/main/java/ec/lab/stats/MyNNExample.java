package ec.lab.stats;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DecimalFormat;

/*
* Multi-layer Neuron Network model with training and predictions
* HBF
* Adapted from https://medium.com/coinmonks/implementing-an-artificial-neural-network-in-pure-java-no-external-dependencies-975749a38114
* https://gist.github.com/Jeraldy/7d4262db0536d27906b1e397662512bc
*/

import java.util.Arrays;

public class MyNNExample {
	final static DecimalFormat df = new DecimalFormat("0.00");

	public static void main(String[] args) {
		// OR function
		double[][] x = { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } };
		double[][] y = { { 0 }, { 1 }, { 1 }, { 1 } };
		// NN configuration
		int[] config = {2, 100, 1};
		
//// AND
//		double[][] x = { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } };
//		double[][] y = { { 0 }, { 0 }, { 0 }, { 1 } };
//		// NN configuration
//		int[] config = {2, 100, 1};

// // XOR       
//		double[][] x = { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } };
//		double[][] y = { { 1 }, { 0 }, { 0 }, { 1 } };
//		int[] config = {2, 100, 1};	
		
		
//		// OR and AND functions
//		double[][] x = { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } };
//		double[][] y = { { 0, 0 }, { 1, 0 }, { 1, 0 }, { 1, 1 } };		
//		// NN configuration
//		int[] config = {2, 100, 2};
		
//		// OR and AND and XOR functions
//		double[][] x = { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } };
//		double[][] y = { { 0, 0, 1 }, { 1, 0, 0 }, { 1, 0, 0 }, { 1, 1, 1 } };		
//		int[] config = {2, 20, 3};

		// create NN
		MNN mnn = new MNN(config);
		// set training parameters and train the NN on given training data
		int epochs = 4000;
		double tolerance = 0.01;
		double learnrate = 0.1;
		MNNTrain mnnt = new MNNTrain(mnn);

// train by whole train set as one batch		
		int epoch = mnnt.Train(x, y, learnrate, epochs, tolerance);

//		// train by by splitting training into batches of given batch size
//		int batch = 2;
//		int epoch = mnnt.Train(x, y, learnrate, epochs, tolerance, batch);

		// evaluate the trained NN model
		System.out.println("Epoch : " + epoch);
		System.out.println("Loss : " + mnnt.loss);

		// use the trained NN model for prediction
		System.out.println("Prediction");
		for (int i = 0; i < x.length; i++)
			System.out.println("input:" + Arrays.toString(x[i]) + ", output:" + Arrays.toString(mnn.predict(x[i])));

		// save the NN model to file
		mnn.save("mnntest.bin");

		// load the saved NN model
		MNN mnn1 = mnn.load("mnntest.bin");

		// use reloaded model for prediction
		System.out.println("Prediction by loaded model");
		for (int i = 0; i < x.length; i++)
			System.out.println("input:" + Arrays.toString(x[i]) + ", output:" + Arrays.toString(mnn1.predict(x[i])));
	}

	/**
	 * A NN layer is defined by the number of output neurons numOutNeurons, number of input neurons
	 * numInNeurons Weight parameter array W[numOutNeurons][numInNeuronss], bias parameter
	 * b[numOutNeurons][1]
	 */
	public static class Layer implements Serializable {
		private static final long serialVersionUID = 1L;
		public int numOutNeurons = 0;
		public int numInNeurons = 0;
		public double[][] W = null;
		public double[][] b = null;

		public Layer(int numoutneurons, int numinneurons) {
			this.numOutNeurons = numoutneurons;
			this.numInNeurons = numinneurons;
			W = nnm.random(numoutneurons, numinneurons);
			b = new double[numoutneurons][1];
		}

		public Layer(double[][] W, double[][] b) {
			this.numOutNeurons = W.length;
			this.numInNeurons = W[0].length;
			this.W = W;
			this.b = b;
		}
	}

	/**
	 * Multi-layer Neuron Network (MNN) defined by int[] config, 
	 * which define the number of neurons of each layer.
	 * Layer[] L - array layer object, L[0] is the first layer. 
	 */
	public static class MNN implements Serializable {
		private static final long serialVersionUID = 1L;
		public int[] config = null;
		public Layer[] L = null;

		public MNN(int[] config) {
			this.config = config;
			L = new Layer[config.length - 1];
			for (int i = 0; i < config.length - 1; i++) {
				L[i] = new Layer(config[i + 1], config[i]);
			}
		}

		/**
		 * Computes the output NN for given input
		 * 
		 * @param x - input
		 * @return - output of output layer
		 */
		public double[] predict(double[] x) {
			double[][] X = new double[x.length][1];
			for (int i = 0; i < x.length; i++)
				X[i][0] = x[i];
			// computes the output of the first layer
			double[][] Z = nnm.rowadd(nnm.dot(L[0].W, X), L[0].b);
			double[][] A = nnm.sigmoid(Z);

			// computes output of layers forward
			for (int i = 1; i < config.length - 1; i++) {
				Z = nnm.rowadd(nnm.dot(L[i].W, A), L[i].b);
				A = nnm.sigmoid(Z);
			}
			// converts output of last layer to 1D array
			double[] out = new double[A.length];
			for (int i = 0; i < A.length; i++)
				out[i] = A[i][0];

			return out;
		}

		/**
		 * Save MNN model to file
		 */
		public void save(String filename) {
			try {
				ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(filename));
				os.writeObject(this);
				os.close();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		/**
		 * Load MNN model from file
		 */
		public MNN load(String filename) {
			MNN mnn = null;
			try {
				ObjectInputStream is = new ObjectInputStream(new FileInputStream(filename));
				mnn = (MNN) is.readObject();
				is.close();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return mnn;
		}

	}

	/**
	 * 
	 * @author hfan
	 *
	 */
	public static class LayerTrain {
		public Layer L = null;
		public double[][] Z = null; // WX + b
		public double[][] A = null; // sigma(Z)
		public double[][] Ap = null; // output for previous layer
		public double[][] dZ = null; //
		public double[][] dW = null;
		public double[][] db = null;

		public LayerTrain(Layer L) {
			this.L = L;
		}

		public void Forward(double[][] X) {
			Z = nnm.rowadd(nnm.dot(L.W, X), L.b);
			A = nnm.sigmoid(Z);
		}

		public void Forward(LayerTrain PL) {
			Z = nnm.rowadd(nnm.dot(L.W, PL.A), L.b);
			A = nnm.sigmoid(Z);
		}

		public void Backward(double[][] Y) {
			dZ = nnm.subtract(A, Y);
			dW = nnm.dot(dZ, nnm.T(Ap));
			db = nnm.rowsum(dZ);
		}

		public void Backward(LayerTrain NL) {
			dZ = nnm.multiply(nnm.dot(nnm.T(NL.L.W), NL.dZ), nnm.subtract(1.0, nnm.power(A, 2)));
			dW = nnm.dot(dZ, nnm.T(Ap));
			db = nnm.rowsum(dZ);
		}

		public void BackwardBatch(double[][] Y) {
			dZ = nnm.subtract(A, Y);
			dW = nnm.add(dW, nnm.dot(dZ, nnm.T(Ap)));
			db = nnm.add(db, nnm.rowsum(dZ));
		}

		public void BackwardBatch(LayerTrain NL) {
			dZ = nnm.multiply(nnm.dot(nnm.T(NL.L.W), NL.dZ), nnm.subtract(1.0, nnm.power(A, 2)));
			dW = nnm.add(dW, nnm.dot(dZ, nnm.T(Ap)));
			db = nnm.add(db, nnm.rowsum(dZ));
		}

		public void update(double learnrate) {
			L.W = nnm.subtract(L.W, nnm.multiply(learnrate, dW));
			L.b = nnm.subtract(L.b, nnm.multiply(learnrate, db));
		}
	}

	public static class MNNTrain {
		public int totalsize = 1;
		public MNN mnn = null;
		LayerTrain[] LT = null;
		public int layercount = 0;
		public double learnrate = 0.01;
		public double loss = 0;

		public MNNTrain(MNN mnn, int m) {
			this.layercount = mnn.config.length - 1;
			this.totalsize = m;
			LT = new LayerTrain[layercount];
			for (int i = 0; i < layercount; i++) {
				LT[i] = new LayerTrain(mnn.L[i]);
			}
		}

		public MNNTrain(MNN mnn) {
			this.layercount = mnn.config.length - 1;
			LT = new LayerTrain[layercount];
			for (int i = 0; i < layercount; i++) {
				LT[i] = new LayerTrain(mnn.L[i]);
			}
		}

		/**
		 * Train NN get the W and b of each layer
		 * @param x - matrix of training set, one input per row
		 * @param y - matrix of target values corresponding to rows of x
		 * @param learnrate - learning rate 
		 * @param epochs   - maximum number of epochs
		 * @param tolerance - tolerance of loss, i.e. stopping training when loss < tolerance
		 * @return - the number of epochs in training.
		 */
		public int Train(double[][] x, double[][] y, double learnrate, int epochs, double tolerance) {
			totalsize = x.length;
			double[][] X = nnm.T(x);
			double[][] Y = nnm.T(y);

			int t = 0;
			for (t = 0; t < epochs; t++) {
				LT[0].Forward(X);
				LT[0].Ap = X;
				for (int i = 1; i < layercount; i++) {
					LT[i].Ap = LT[i - 1].A;
					LT[i].Forward(LT[i - 1]);
				}

				// backward
				LT[layercount - 1].Backward(Y);
				for (int i = layercount - 2; i >= 0; i--) {
					LT[i].Backward(LT[i + 1]);
				}

				// update W and b
				for (int i = 0; i < layercount; i++) {
					LT[i].update(learnrate);
				}

//				if (t % 100 == 0) {
//					System.out.println("epoch : " + t);
//					System.out.println("Cost : " + loss);
//					System.out.println("Predictions : " + Arrays.deepToString(LT[layercount-1].A));
//				}

				loss = nnm.cross_entropy(totalsize, Y, LT[layercount - 1].A);
				if (loss < tolerance)
					break;

			}
			return t;
		}

		/**
		 * Train NN get the W and b of each layer
		 * @param x - matrix of training set, one input per row
		 * @param y - matrix of target values corresponding to rows of x
		 * @param learnrate - learning rate 
		 * @param epochs   - maximum number of epochs
		 * @param tolerance - tolerance of loss, i.e. stopping training when loss < tolerance
		 * @param batch - batch size for training, for memory efficiency.
		 * @return - the number of epochs in training.
		 */
		public int Train(double[][] x, double[][] y, double learnrate, int epochs, double tolerance, int batch) {
			totalsize = x.length;
			int t = 0;
			for (t = 0; t < epochs; t++) {
				loss = 0;
				for (int k = 0; k < totalsize / batch; k++) {
					double[][] X1 = nnm.T(nnm.getBatch(x, batch, k));
					double[][] y1 = nnm.T(nnm.getBatch(y, batch, k));
					LT[0].Forward(X1);
					LT[0].Ap = X1;
					for (int i = 1; i < layercount; i++) {
						LT[i].Ap = LT[i - 1].A;
						LT[i].Forward(LT[i - 1]);
					}
					loss = loss + nnm.cross_entropy(1, y1, LT[layercount - 1].A);

					// Backward
					if (k == 0) {
						LT[layercount - 1].Backward(y1);
						for (int i = layercount - 2; i >= 0; i--) {
							LT[i].Backward(LT[i + 1]);
						}
					} else {
						LT[layercount - 1].BackwardBatch(y1);
						for (int i = layercount - 2; i >= 0; i--) {
							LT[i].BackwardBatch(LT[i + 1]);
						}
					}

//				if (t % 100 == 0) {
//					System.out.println("epoch : " + t);
//					System.out.println("Cost : " + loss);
//				}						

				}

				// update W and b
				for (int i = 0; i < layercount; i++) {
					LT[i].update(learnrate);
				}

				loss /= totalsize;
				if (loss < tolerance)
					break;

			}
			return t;
		}
	}
}
