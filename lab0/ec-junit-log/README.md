# Warm Up Java Project
Author: HBF  
Date: 2022-05-31

## This project is to demonstrate the following
1. Create a simple Java project using Eclipse.
2. Create an interface. 
3. Create a class to implement the interface.
4. Create a class for unit test of the implementation class.
5. Create an application class containing the main method and instantiation of the implementation class and method calls. 
6. Create runtime logging with log4j.
7. Test markdown for simple documentation, like this readme file.

## Prerequisites

1. Java
2. Eclipse IDE

## Do the following to know this project
 
1. Open and read Calculator.java in the src / ec.lab (i.e. src/ec/lab/junit directory)
2. Open and read the ECCalculator.java 
3. Open and read the CalTest.java  in test/ec.lab. 
4. Right click the CalTest.java -> Run As -> Junit Test.  It will run all unit tests. The testing info will be showing in Console tab (output of System.out.print()) and JUnit tab. Running Junit test can also be done by clicking the debug button.
5. Open and read MyCalculator.java.  Right click the file name or the opened file window -> Run As -> Java Application.
6. Check the lib folder, it contains the jar file libraries for junit, log4j
7. Check project Properties -> Java Build Path -> Libraries. The junit and log4j libraries in the lib folder are added. The results and logging info are shown in the console tab.  
8. Export the project to runnable jar package: ec-junit-log.jar to the root directory of ec-junit-log project:  
Right click the ec-junit-log project -> Export -> Java -> runnable jar file -> set launch configuration to MyCalculator,    
set export destination to the root directory of ec-junit-log project with file name mycalculator.jar,  check Extract required libraries into generated JAR under Library handling ->  click Finish. 
9. Open cmd console, cd to the above directory, run command: java -jar mycalculator.jar
   
   The output and log info will be on the console terminal. A logs folder will be created and the log file app-info.log will be stored in logs. Run the above command again, you can see the logging information in app-info.log file.  
   
10. Check the log4j configuration file resource/log4j2.xml, and check the log file in logs folder.


## Markdown 

1. Check the markdown files under doc folder. These files give a very brief introduction to the markdown.  
2. In the console of previous step, run command: 
   pandoc -i README.md -o readme.html  
   This will create readme.html file under the project root, may refresh the project to see the new file. It will use markdown to write project README.md file and use pandoc to convert markdown file to html or other file formats.
   
   
