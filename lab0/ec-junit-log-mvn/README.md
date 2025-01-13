# Build Java Project by Apache MAVEN
Author: HBF  
Date: 2022-05-31 (update) 

## About this project

This project is to demonstrate project build by Maven. A Maven project is defined by a (Project Object Model) POM file. It is an XML file containing information about the project and configuration details used by Maven to build the project. 
 
1. Open and read the pom.xml
2. Open a cmd console, cd to the project root directory, i.e., the directory containing the pom.xml file. Use command format: mvn  (list of goals), for example:

```
mvn compile
mvn test
mvn package
mvn clean
mvn clean package
mvn install
mvn dependency:copy-dependencies
mvn dependency:sources
mvn dependency:resolve -Dclassifier=javadoc
```

- The compile goal tells Maven to compile the source code programs to create class files and save in the target directory.  
- The test goal does the compile (if not done yet)  and then runs the test programs. 
- The package goal does the compile and then runs build plugins. The default build plugin is maven-jar-plugin. We can provide additional plugins, e.g. maven-assembly-plugin in this pom.xml
- The clean goal removes the build directory, e.g., the target directory. 
- The install goal does compile, package, and save the package to local repository, so that it can be used as dependency by other other project. 
- The dependency:copy-dependencies goal downloads and saves all dependency jars to target/dependencies directory, so that we can see what they in one place. 
- The dependency:sources goal is to download the source (code) of dependencies to local repo. This allows us to read the source code conveniently within Eclipse. 
- The dependency:resolve -Dclassifier=javadoc is to download the javadoc of the dependencies to local repo. 



 
  
3. Command: `mvn package`, it will create ec-junit-log-mvn.jar in the target directory. Type the following command to run the Java jar program. 

```
java -jar target/ec-junit-log-mvn.jar
```


## Apache Log4J

Similar to the ec-junit-log project, it uses log4j for logging.  Apache [Log4J](https://logging.apache.org/log4j/2.x/) is a logging tool. 

1. Check and run Log4j2Example0.java for a simple example of Log4J.
2. Check and run Log4j2Example1.java, it uses different logging levels. There are six levels.   

```
FATAL	100
ERROR	200
WARN	300
INFO	400
DEBUG	500
TRACE	600
```

3. Check and run Log4j2Example2.java, it uses specific configuration file.
4. Check and run Log4j2Example3.java for more customized configurations by programming.


## JavaDoc with Maven

Javadoc is Java's standard tool to extract code documentation following javadoc comment format (https://www.oracle.com/technical-resources/articles/java/javadoc-tool.html)

This project presents an example of using javadoc. Check code comment in ICalculator.java. Run the following Maven command to build the project documentation in html. The document will be generated in `target/site` directory. 

```
mvn site
```

## Import to Eclipse

As we see, we can develop Maven project using any text editor editors, such as NotePad,, NodePad++, VS Code. However, Eclipse is a more effective tool to develop Java projects. We import an existing Maven project to Eclipse as follows. 

1. Eclipse, File -> Import -> Maven -> Existing Maven Projects, Next -> Root Directory, browse to select the ` C:\enterprise\workspace\lab0\ec-junit-log-mvn` directory, Finish.  
2. Right click pom.xml -> Run As -> Maven Build (the second), and fill in the box of goals for the Maven command, e.g. test, then click the run button.    
3. Try the goal `clean` for Run As -> Maven clean,  it will clean the target folder.
4. Try the goal `compile`,  it will build programs in the target folder, then you can run the MyCalculator.java by Run As -> Java application. 
5. Try the goal `test`, it will compile and run the unit tests.  
6. Try the goal `package`,  it will build programs in the target folder. 

