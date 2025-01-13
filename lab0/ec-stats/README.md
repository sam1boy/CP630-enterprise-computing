# Example Java Projects for Statistics
Author: HBF  
Date: 2024-08-01

Import this Maven poject to Eclipse as existing Maven project, run Maven test, then run each Java program as Java application, i.e. right click a Java program containing the main method, -> Run As -> Java Application. 

 
## Open source math library

We will the open source Apache apache.commons.math3.stat library for this statistic projects. The example programs in this project come from the original [Apatche site](http://commons.apache.org/proper/commons-math/userguide/stat.html).  
Some examples are modified and simplified. 

## Summary statistics

In SummaryStatisticsExample.java, the SummaryStatistics class implement the fundamental interface of StatisticalSummary. 

## Descriptive statistics

In DescriptiveStatistics.java,  the DescriptiveStatistics class also implements the fundamental interface StatisticalSummary plus additional methods. 

## Statistics Utilites

In StatUtilityExample.java, the StatUtils class contain some commonly used static functions.

## Frequency 

In FrequencyExample.java, the Frequency class contains methods for frequency statistics.  

## Simple regression

In SimpleRegressionExample.java, the SimpleRegression class contains methods to calculate simple linear regression model, i.e. y = intercept + slope * x for given set of (x, y) values. 

## Multi-linear regression

In MultiLinearRegressionExample.java, it computes the multi-dimensional linear regression model Y=X*b+u for given training data (X, Y). The OLSMultipleLinearRegression class uses ordinary least squares (OLS) or linear least squares is a method for estimating the unknown parameters in the linear regression model.  The GLSMultipleLinearRegression class uses a generalized least squares method. 


## My linear regression  (added in 2024-07)

MyLinearRegressionExample.java contains implementations of four linear regression (LR) algorithms including the matrix based algorithm, incremental algorithm, aggregation algorithm, and the iterative backward propagation algorithm. To make it simple, the implementations use the linear algebra library org.apache.commons.math3.linear. 

1. The matrix based algorithm computes the LG parameters B from linear equation system (X^TX)B-X^TY = 0, namely compute B = (X^TX)^{-1}X^TY.  This algorithm is good for batch processing of small training data set. 
2. The incremental algorithm is an online algorithm, which computes the updated LG model B and A = X^TX, when new a set of data is added.
3. The aggregation algorithm computes a new LG model from two existing LG models as a result of merging two their data set. Such algorithm is used in distributed computing, where models of partial data sets are computed on different computers, then aggregates these models together to form the LG model of the whole data set. 
4. The backward propagation algorithm is an iteration algorithm, which does not compute matrix inverse, rather it uses the derivatives to update B in each iteration (or epoch) until loss function value is small enough or done maximum epochs. Such algorithm is the foundation of modern neuron network training algorithm. 

This example shows that LR model can be trained using incremental/online algorithm, and as well as distributed aggregation algorithm.


## My neuron network  (added in 2024-08)

MyNNExample.java contains implementations multi-layer neuron network model. It uses custom matrix operation methods (adopted) nnm.java. It uses sigma function as activation function, and cross_entropy function for loss function. It uses back propagation algorithm for training. The main function contain testing of using NN to do OR function, and save model and load model.  

1. Run the program MyNN.java, to test OR. Check console output to see the results.
2. Train MNN for AND function.
3. Train MNN for XOR function
4. Configure and train MNN for OR and AND functions
5. Try different configurations, e.g. different number of neurons on middle layers, different number of layers. 



