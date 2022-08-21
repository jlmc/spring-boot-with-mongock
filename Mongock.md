# Mongock migration tool

ChangeLogs and ChangeSets
Mongock uses ChangeLogs which are the Java Classes responsible for Migrations and ChangeSets, which are methods which are responsible to apply the migrations to the database schema.

To be able to run migrations,

We have to annotate the class responsible for migration with `@ChangeLog`
And annotate the method responsible to apply migrations with `@Changeset` annotation.
At the time of startup, Mongock scans the package provided for the `mongock.change-logs-scan-package` for the ChangeLog and Changesets, and will start executing it.

Let’s create the java class responsible for the migration – DatabaseChangeLog.java

## How to install in our app

1. add dependencies
```
    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>com.github.cloudyrock.mongock</groupId>
                <artifactId>mongock-bom</artifactId>
                <version>4.3.8</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>
    
    
    <dependency>
        <groupId>com.github.cloudyrock.mongock</groupId>
        <artifactId>mongock-spring-v5</artifactId>
    </dependency>
    <dependency>
        <groupId>com.github.cloudyrock.mongock</groupId>
        <artifactId>mongodb-springdata-v3-driver</artifactId>
    </dependency>

```

2. Configure in our application.yml file

  - Add your changeLog package path to your property file (application.yml). it's an array, so you can add more than one.
```
mongock:
  enabled: true
  change-logs-scan-package: io.github.jlmc.mongodbschemamsg.infrastructure.dbmigrations
  index-creation: true
  runner-type: ApplicationRunner
  #transaction-enabled: true
```

  - In order to use MongoDB transactions, we need to enable transactions in Mongock(this won't be required in next versions as transactions will be enabled by default).
    - ```
      driver.enableTransaction();
      ```
    - or, using properties
    - ```
      mongock.transaction-enabled=true
      ```
    - Keep in mind that your MongoDB database must allow multi-document ACID transactions



3. Enable Mongock

Once you have successfully imported the necessary dependencies, there are two ways to activate Mongock in our project. 
In most cases, when using the Spring framework, the easiest and most convenient way is the annotation approach. However, sometimes you don't use Spring or you need more control over your Mongock bean. In this case, you should go for the traditional builder approach.

We opt for the annotation approach in our project.

Add the @EnableMongock annotation to the main class.

```java
import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableMongock

@SpringBootApplication
public class MongodbSchemaMsgApplication {
```

4. create ChangeLogs migrations classes

ChangeLogs and ChangeSets
ChangeLogs are migration classes. They contain ChangeSets, which are the methods that actually perform the migration.

To tell Mongock to run the migration, you need:

Annotate the changeLog classes by @ChangeLog.
Annotate the changeSet methods by @ChangeSet.
Let's create our first ChangeLog class to initialize the basic data.


```
ChangeLog(order = "001")
public class DatabaseInitChangeLog {


    @ChangeSet(order = "001", id = "init_departments", author = "aek")
    public void initDepartments(DepartmentRepository departmentRepository) {
        departmentRepository.save(Department.builder()
                .name("Human Resource Management")
                .code("HR")
                .build());
        departmentRepository.save(Department.builder()
                .name("R&D")
                .code("Research and Development (often abbreviated to R&D)")
                .build());
        departmentRepository.save(Department.builder()
                .name("Prod")
                .code("Production")
                .build());
    }

    @ChangeSet(order = "00
```
