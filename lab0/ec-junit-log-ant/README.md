# Build Java Project by Apache ANT
Author: HBF  
Date: 2022-08-03  

## About this Apache Ant projct

This project is to demonstrate Apache Ant. [Apache Ant](https://ant.apache.org/) is a Java library and command-line tool to build, test and run Java applications. Ant can also be used effectively to build non Java applications, e.g., C or C++ applications similar to C build tool make. Ant can be used to do procedures of targets and tasks. Ant procedure is commonly described in the build.xml file.

Do the following:

1. Open and read the Ant project file build.xml
2. Right click build.xml -> Run As -> Ant Build (second) -> check the target to build, run or check apply and run. For example, check the jar target, and run, it will create the runnable jar in the jar folder (refresh the project to see).
3. Right click build.xml -> Run As -> Ant Build (first), run the applied/selected target. 
4. Run Ant by command in CLI, or by shell script. Open a cmd console, cd to the root directory of this project, where the build.xml file is stored. An Ant command has format:  ant target (or a list of targets).  For example, 

```
ant clean
ant clean compile
ant clean jar
ant clean run
ant main
```

As we see, the Ant is similar to that of the make tool for C programming. It brings the convenience to Java program development.

