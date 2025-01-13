# EC Regression

Author: HBF

Date: 2022-05-31 (update) 

Technologies: Maven, Apache Common.math3  
Summary: maven project to do multi-linear regression
Target Product: standalone Java program

Try this project by both Maven command line operation, and importing to Eclipse with Eclipse Maven operations. 

Note that the pom.xml is set create the executable jar in the root directory, rather than the default target directory. 


## Data

Store following in data file data.csv

<pre>
houseSize,lotSize,bedrooms,granite,bathroom,sellingPrice
3529,9191,6,0,0,205000
3247,10061,5,1,1,224900
4032,10150,5,0,1,197900
2397,14156,4,1,0,189900
2200,9600,4,0,1,195000
3536,19994,6,1,1,325000
2983,9365,5,0,1,230000
</pre>

Store the following in file test.csv as a testing case.
<pre>
4032,10150,5,0,1
</pre>

Store the following in file predict.csv as prediction case.
<pre>
3198,9669,5,1,1
</pre>


## Build

Command in cmd console:  

```
mvn clean package  
```

ec-regression.jar will be build and ec-regression project root directory.

## Run

Open command console, cd to the root director of the project. 

1. Run following command to computing the regression model by learning the the input data.  

```
java -jar ec-regression.jar -i data.csv -o regression.mod
```

This will generate regression.mod, with contents:
-21739.296665037586,-26.930783509146007,6.3345241045934095,44293.760584183656,7140.676293495514,43179.19988882629

2. Run the following command to test the model with given test data.
 
```
java -jar ec-regression.jar -m regression.mod -i test.csv -o result.txt
```

This will read in the regression.mod and test.csv,  output the prediction value to result.txt of contents:
198619.2066974534. The actual value is 197900. 

2. Run the following command to predict house price by the model and input prediction data. 

```
java -jar ec-regression.jar -m regression.mod -i predict.csv -o result.txt
```

This will read in the regression.mod and predict.csv output the prediction value to result.txt of contents:
225173.25034326722



