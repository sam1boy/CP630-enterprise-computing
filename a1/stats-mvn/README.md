# stats-mvn

## Overview
`stats-mvn` is a simple Java-based Maven project for computing descriptive statistics such as count, minimum, maximum, mean, and standard deviation. It implements an incremental statistics computation and supports logging via Log4J.

## Features
- Computes basic descriptive statistics (count, min, max, mean, std deviation).
- Incremental computation for efficiency.
- Logging via Log4J.
- JUnit tests for validation.
- JavaDoc documentation generation.
- Executable JAR packaging via Maven.

## Project Structure
```
stats-mvn/
├── src/main/java/ec/stats/
│   ├── Statistics.java      # Interface for statistics operations
│   ├── ECStatistics.java    # Implementation of Statistics
│   ├── MyStatistics.java    # Main program with command-line arguments and logging
├── src/test/java/ec/stats/
│   ├── StatisticsTest.java  # JUnit tests for ECStatistics
├── pom.xml                  # Maven configuration file
└── README.md                # Project documentation
```

## How to Build, Test, and Run

### **1. Run Unit Tests**
```bash
mvn test
```

### **2. Run the Main Program Directly**
```bash
mvn exec:java -Dexec.mainClass="ec.stats.MyStatistics"
```

### **3. Create an Executable JAR**
```bash
mvn clean package
```
This will create `target/stats-mvn.jar`.

### **4. Generate JavaDoc Documentation**
```bash
mvn site
```
The documentation will be available in `target/site/index.html`.

### **5. Run the Executable JAR with Arguments**
```bash
java -jar target/stats-mvn.jar 1 10000 1
```
Example Output:
```
[INFO ] 2025-01-12 16:46:30.437 [main] MyStatistics - data from 1.0 to 10000.0 by step 1.0 are added
count: 10000
min: 1.0
max: 10000.0
mean: 5000.5
STD: 2886.7513315143733
[INFO ] 2025-01-12 16:46:30.460 [main] MyStatistics - stats() method is called to recompute stats summary
count: 10000
min: 1.0
max: 10000.0
mean: 5000.5
STD: 2886.751331514372
```