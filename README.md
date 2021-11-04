# Introduction
- Spring is a Java framework for building applications that talk to Database, Caches and to build REST APIs.
- SprintBoot is a wrapper over Spring to enable faster development and has several starter classes that makes it easier to get started with building applications.
- Spring is a Dependency Injection Framework.
  - Meaning if a class A needs to have an object of class B in it, then one way is to have Class A instantiate B, but this would put a hard dependency between the two. If a different type of Interface that B adheres to is to be plugged in, class A has to undergo a change. This is tight coupling. Another way of this is to build Class A so that it accepts an input ( either in Constructor or in its Setter ). This way A and B are isolated and have less dependency. Moreover if we want to use a different type of Interface that B adheres to, we can change it in the calling API.
### Tight coupling
```
class ComplexMathSolver
{
	SortAlgorithm instance;
	
	ComplexMathSolver()
	{
		// Hard dependency with the concrete implementation of SortAlgorithm
		instance = new BubbleSortAlgorithm();
	}
}

class BubbleSortAlgorithm implements SortAlgorithm
{

}

class InsertionSortAlgorithm implements SortAlgorithm
{

}
```

### Loose Coupling
```
class ComplexMathSolver
{
	SortAlgorithm instance;
	
	ComplexMathSolver(SortAlgorithm instance)
	{
		// No direct dependency with a specific implementation of SortAlgorithm
		this.instance = instance;
	}
}

class BubbleSortAlgorithm implements SortAlgorithm
{

}

class InsertionSortAlgorithm implements SortAlgorithm
{

}
```

- Another term that comes up is Inversion of Control Container. This means that instead of developers creating an instance of a class, we shift the responsbility of creating an instance from the class that needs a dependency onto to Spring framework. Because this is an Inversion, we refer to it as IoC.

# Annotations
- Spring uses several annotations to do special processing. Annotations give special meaning to functions and classes.
- For example, in the below code @SpringBootApplication and @Autowired give the meaning that the application is a SpringBootApplication and that the instance will be auto-writed ( instantiated by Spring and assigned ) automatically. This second part is IoC and Dependency Injection framework.

```
package com.fireacademy.dbconnector;

import com.fireacademy.dbconnector.jdbc.PersonJdbcDao;
import com.fireacademy.dbconnector.jdbc.entity.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class DbconnectorApplication implements CommandLineRunner
{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	PersonJdbcDao dao;
	public static void main(String[] args) 
	{
		SpringApplication.run(DbconnectorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception 
	{
		// Get all data
		logger.info("FindAll Users");
		logger.info( "All users -> {}", dao.findAll() );

		// Get select data
		logger.info("FindById Users");
		logger.info( "All users -> {}", dao.findById(10002) );

		// Delete by ID
		logger.info("deleteById Users");
		logger.info( "All users -> {}", dao.deleteById(10003) );

		// Post delete search
		logger.info("After Delete FindAll Users");
		logger.info( "All users -> {}", dao.findAll() );

		// Insert
		logger.info("Insert Pavan");
		logger.info( "Insert Pavan -> {}", dao.insert(new Person(10005, "Pavan", "Chicago", new Date())) );
		logger.info( "After Insert All users -> {}", dao.findAll() );

		// Update
		logger.info("Update Pavan Users");
		logger.info( "Update Pavan -> {}", dao.update(new Person(10005, "Pavan Dittakavi", "Chicago", new Date())) );
		logger.info( "After Update All users -> {}", dao.findAll() );
	}
}
```



- Earlier versions of Spring use XML for wiring the different bean classes together.

@Primary
@Component
@Scope("prototype")
- ConfigureableBeanFactory
@Autowired
@Qualifier("bubble")
Autowiring by name

@Proxy
@ComponentScan
- By default a SpringBootApplication annotation adds the package it is defined in to ComponentScan.
- So other packages would need to be added to this ComponentScan using this annotation.

Bean - Singleton Bean / prototype / Request and Session
 - Bean can be put on class methods as in case of 3rd part libraries where you cant annotate the method as @Component.
 - In this case, the custom class would be marked as @Configuration.
 - https://stackoverflow.com/a/53975920/815961
 - https://stackoverflow.com/a/40861225/815961
Business Service
Dependency Injection
Inverstion of control
Container / ApplicationContext

# Reading from the properties files
@PropertySource("classpath:app.properties")

@Value("${external.service.url}")

@PostConstruct

@PreDestroy

# Application.properties
logging.level.org.springframework = debug

# Context and Dependency Injection
- JPA is interface, Hibernate is implentation
- CDI is interface, Spring is implementation for Dependency Injection
  - Context and Dependency Injection https://stackoverflow.com/a/5974145/815961

- Spring Core - Basic Management of Beans
- Spring Security - Authentication and Authorization
- Spring MVC - For web applications
- Spring Boot - 
- Spring - Mainly for IOC / Dependency Injection. Testability. Achieves in loosely coupled application, which help in testing better.


- To remove @SpringBootApplication, add @Configuration and @ComponentScan.
- What all does @SpringBootApplication bring?.

AnnotationConfigApplicationContext ( Spring ) vs ApplicationContext ( SpringBoot )

# To do
- Add dependencies with logback / log4j and see how things work in basic Spring application.
- 

# Inversion of Control
- Shifting the responsbility of creating an instance from the class that needs a dependency onto to Spring framework.
- This is achived by @autowired and @component.
- IOC Container is the part of Spring that does it.
- This is a generic concept.
- There are two implementations
  - Bean Factory
  - Application Contet
    - Bean Factory++
    - Spring AOP
    - Internationalization
    - WebApplicationContext 

@Component
 - Generic Component
@Controller
 - Determines a Controller in web application
@Service
 - Business logic
 - BinarySearchImpl
@Repository
 - Database retrieval

- Used for classifying the software classes.
- Spring AOP gives more options per classification type.


- Mockito vs JUnit


# SprintBoot
- A wrapper over Spring to make development easier by including autoconfiguration, embedeed servers etc.
- View Resolvers?. Dispatcher Servlets?.
- Starters (Web, JPA etc) help in ................
- Externalization.
- Actuators to understand analytics - how many times the MS is called, how many times it failed etc.

- @RESTController
- @GetMapping
- Autoconfiguration
  - Is implemented by an autoconiguration jar file. Ex: If SpringMVC Jar is on classpath, it configures the DispatcherServlet etc.

# DevTools
# Actuator

# Database connection
- JPA, JDBC and H2 database, Web ( to view H2 content ).
## Working with H2 database
- schema.sql shall contain all the SQL statement.
- application.properties shall contain the following settings specific to H2 connection
  - spring.datasource.url=jdbc:h2:mem:testdb
  - spring.data.jpa.repositories.bootstrap-mode=default
  - spring.h2.console.enabled=true
- BeanPropertyRowMapper
  - Map Bean Property to Table Column name.
  - RowMapper<Person> interface can be extended to create custom mappers. These are useful when our Bean attributes does not match Table columns.
- CommandLineRunner

 - SpringJDBC is advantageous over JDBC as it provides some default handling which JDBC does not provide. Read more on this.
	- SpringJDBC maps SQL queries to Beans using Row Mappers. JPA maps an object to a row in the table.
   - Question, where does Hibernate fit it?. Should be in JPA side.
   - With JPA, we do the mapping between tables and classes and by using proper method conventions, we rely on the framework to generate the query for us.

@Entity
@Id
@GeneratedValue
@PersistenceContext
Schema Update Hibernate

Methods on Entity Manager
find
merge
remove
	
HQL
JPQL
NamedQuery
SpringDataJPA
JpaRepository

## Spring Database Connectivity
- JDBC
- Hibernate
- JdbcTemplate
- SpringJDBC
- JPA
- SpringDataJPA
	
- Read on Maven build lifecycle.
	
	
