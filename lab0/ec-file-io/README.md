# Java File I/O Example Project in Maven
Author: HBF  
Date: 2022-05-31 

## Basic Concepts of Java Stream I/O

Data I/O is about how to store data in files and transfer data to another computer/device through network. The format can be either text or binary (stream). The text format stores data as sequence of characters, it requires some conversions when doing I/O. The binary format stores data as sequence of bytes as they are in memory. The text data format is readable. The binary format is not readable, but data can be directly loaded from file to memory without conversion, so it is more efficient for object I/O. 

Java has class and method for text and binary file I/O. It uses the term stream instead of binary. In general, a serializable object is capable of streaming. Many commonly used data types are serializable, such as String, Integer, Double, array, map, arraylist. User can define serializable class by implementing the serializable interface. 

## About this project 

This Maven project give examples of file I/O:

1. text file I/O
2. stream data (byte array) I/O
3. String object stream I/O
4. Array object stream I/O
5. Array list object stream I/O
6. Map object stream I/O
7. User defined serializable object stream I/O
8. This project will be used as reference for stream I/O in follow-up labs and assignments. 


## What to do

1. Check FileIO.java for file I/O code examples.
2. Check Person.java for user defined serializable object class.
3. Open a cmd console, change the current directory to the project root directory

```
mvn package
java -jar target/ec-file-io.jar
```

4. Check the generated files in the project root directory.

