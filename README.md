# <h1 align="center">UniversityGuide</h1>

# Introduction: 

After getting admints from various Universities, we have noticed that students struggle to decide the college based on their admits only and also the information which a student gathers from various sources could not be entirely valid. By developing this application, we plan to provide a platform to prospective students of Syracuse University where they can post their queries regarding the University and get answers from verified students who are already studying here. Other than that, for the current and alumni students we want to provide a platform where they can discuss various topics ranging from sports to jobs.

# Application Idea:

Our application would mainly consists of four modules such as :
1.	SU Forum, where the new aspirants can post their queries and will be able to get the information about the courses, professors etc.  
2.	SU Housing, where the new aspirants will get the valid information regarding off-campus housing options, locality, restaurants, grocery shops and also provide a platform for students to rent a reliable short-term accommodation.
3.	SU Sports, gives the latest articles on the Syracuse Sports such as football and Soccer, etc .
4.	SU Marketplace, which will help university student/faculty/staff to buy/sell/exchange products.

# Technology  Stack:
Spring Boot - v2.2.6
React - v16.13.1

# Project Setup
`cd UniversityGuide/frontend` to learn how to setup the frontend of the project <br>

# Back end Setup

UniversityGuide is a [Spring Boot](https://spring.io/projects/spring-boot) application built using [Maven](https://maven.apache.org/). 
You can build a jar file and run it from the command line:
```$xslt
 git clone https://github.com/AkshayDalavai/UniversityGuide.git
 ./mvnw package
 java -jar target/*.jar
```
You can then access UniversityGuide backend here: http://localhost:8080/

Or you can run it from Maven directly using the Spring Boot Maven plugin. If you do this it will pick up changes that 
you make in the project immediately (changes to Java source files require a 
compile as well - most people use an IDE for this):
```$xslt
./mvnw spring-boot:run
```

# Running UniversityGuide using IDE
**Prerequisites** <br>

The following items should be installed in your system:<br>

1. Java 8 or newer.
2. git command line tool (https://help.github.com/articles/set-up-git)
3. IntelliJ IDEA <br>
    Windows -> https://www.jetbrains.com/idea/download/#section=windows <br>
    Mac -> https://www.jetbrains.com/idea/download/#section=mac <br>
    Linux -> https://www.jetbrains.com/idea/download/#section=linux
    
**Steps** <br>
1. On Command Line
    ```$xslt
    git clone https://github.com/AkshayDalavai/UniversityGuide.git
    ```
2. Inside IntelliJ
    ```$xslt
    File -> Open and select `pom.xml`. Click on Open button -> Open as Project
    ```
3. A run configuration named `UniversityGuide` should have been created for you if you're using a recent 
Ultimate version. Otherwise, run the application by right clicking on the `UniversityGuideApplication` main class and 
choosing `Run 'UniversityGuideApplication'`.

4. Navigate to UniversityGuide <br>
    Visit http://localhost:8080 in your browser.
    
**DataBase Configuration** <br>
