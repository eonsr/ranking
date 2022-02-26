# **Project: ranking**

## **Technical features**

* **SpringBoot** project.
* **Maven** archetype.
* **Spring**
* **Junit5** unit testing.
* Java 11

## **Project Base Operation**

* The project exposes a command line functionality, showing the instructions and asking for a file path.

* The file name should be **rankingfile.txt**

* The extension of the file should be **.txt**

* The process validates de zise of the file in **long** data type (java) if it is grater than **1000** and error message will be sent.

* The file should have proper content for example:

~~~~cmd
    Lions 3, Snakes 3
    Tarantulas 1, FC Awesome 0
    Lions 1, FC Awesome 1
    Tarantulas 3, Snakes 1
    Lions 4, Grouches 0
~~~~

* The program generates the ranking table and shows the output, for example:

~~~~cmd
    1. Tarantulas, 6 pts
    2. Lions, 5 pts
    3. FC Awesome, 1 pt
    3. Snakes, 1 pt
    5. Grouches, 0 pts
~~~~

* To generate the output the program follows the next rules:
  - **Draw (tie)** is worth **1 point**.
  - **Win** is worth **3 points**.
  - **A loss** is worth **0 points**.

> Some file examples to test the program (used in the unit tests) are in **{projectpath}/src/test/resources/testFiles/** :

> * {projectpath}/src/test/resources/testFiles/no_extension
> * {projectpath}/src/test/resources/testFiles/somefile.txt
> * {projectpath}/src/test/resources/testFiles/rankingfile.txt
> * {projectpath}/src/test/resources/testFiles/ok_fill/rankingfile.txt
> * {projectpath}/src/test/resources/testFiles/error_fill/rankingfile.txt
> * {projectpath}/src/test/resources/testFiles/heavy_fill/rankingfile.txt

## **Compilation and Execution**

In the project folder:

**run**

~~~~cmd
    mvn clean spring-boot:run
~~~~

**clean install jar**
~~~~cmd
    mvn clean install -PselectedProfile
~~~~

**run jar**
~~~~cmd
    java -jar jarName.jar
~~~~

## **Installations**

The project needs Java 11 and maven 3.6.*+ to run.

**Install Java:**

1. Donwload the binary (could be zulu (Azul), openjdk or oracle version 11)

2. If Windows is the SO we can execute the file if it is Linux we should unzip the file.

3. Add enviroment variable JAVA_HOME and add %JAVA_HOME%\bin to the variable **path** (if linux we should modify the **.bash** file)

4. Opena a new Terminal or CMD and execute **java -version** (should be 11).

**Install Maven:**

1. Donwload the zip from apache site.

2. Unzip the files in the folder selected.

3. Add enviroment variable MAVEN_HOME and add %MAVEN_HOME%\bin to the variable **path** (if linux we should modify the **.bash** file)

4. Opena a new Terminal or CMD and execute **mvn -version** (should be 3.6.*+).