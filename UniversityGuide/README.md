# Backend Setup

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

**DataBase Configuration** <br>

1. Install MySQL - Follow [this](https://www.onlinetutorialspoint.com/mysql/install-mysql-on-windows-10-step-by-step.html) tutorial to know how.
2. Create new connection
3. Open our DB script located at `db-config/` and execute the file
4. Step 3 should have created all the tables required.
5. Execute `Use university_guide` followed by `show tables;` to verify if all the tables were successfully created
6. Insert Few fields to `cateogry` table as it is mandatory requirement to run our application
