# Introduction
- Spring is a Java framework for building applications that talk to Database, Caches and to build REST APIs.
- SprintBoot is a wrapper over Spring to enable faster development and has several starter classes that makes it easier to get started with building applications.
  - SpringBoot = Spring + Auto Configuration + Embedded Servers.
  - Without SpringBoot, developers had to choose which version of components are going to be used after confirming that these versions work well together. 
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

- Another term that often comes up in Spring context is Inversion of Control Container. This means that instead of developers creating an instance of a class, we shift the responsbility of creating an instance from the class that needs a dependency onto to Spring framework. Because this is an Inversion, we refer to it as IoC.
- Spring provides a container referred to as Spring Application context that creates and manages application components. These components or beans are wired together inside the Spring application context to make a complete application. This act of wiring beans together is based on a pattern called Dependency Injection ( achieved using property accessor methods or constructor arguments ).
- Spring has several modules
  - Spring Core - Basic Management of Beans
  - Spring Security - Authentication and Authorization
  - Spring MVC - For web applications
  - Spring - Mainly for IOC / Dependency Injection. Testability. Achieves in loosely coupled application, which help in testing better.
  - Spring AOP - For Aspect Oriented Programming
  - Spring Data - For connections to databases
- Application Context and Bean Factory are two implementations of an IOC Container.
  - Spring recommends AC.
  - spring-core is what Bean Factory provides - basic wiring and management of Beans
  - Application Context includes AOP, Internationalization and WebApplicationContext etc.

# Annotations
- Earlier versions of Spring use XML for wiring the different bean classes together. But now Spring uses Annotations for wiring components together.
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

- Spring uses autowiring and component scanning to do automatic configuration - automatically wiring the dependencies.
- Few annotations:
  - @Component tells that the Bean class is to be managed by Spring.
  - @Autowired tells Spring that it has to instantiate a matching class and assign its reference to the variable.
  - @Qualifier("bubble") / @Primary are used to tell to Spring which one to prefer for autowiring.
  - @Scope("prototype") tells that Spring has to create a new instance of the target class each time and assign it. Default is a singleton.

### SpringApplication
```
package com.fireacademy.SpringBasic;

import com.fireacademy.SpringBasic.service.SearchData;
import com.fireacademy.SpringBasic.service.SortAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringBasicApplication
{
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringBasicApplication.class);

	public static void main(String[] args)
	{
		ApplicationContext ctx = SpringApplication.run(SpringBasicApplication.class, args);

		SearchData searchData = ctx.getBean(SearchData.class);
		LOGGER.info("------>" + searchData.describeSearch());
	}
}
```
### SortAlgorithm Interface
```
package com.fireacademy.SpringBasic.service;

import org.springframework.stereotype.Component;

@Component
public interface SortAlgorithm {
    public String describeSort();
}
```
### BubbleSortAlgorithm Concerete Class
```
package com.fireacademy.SpringBasic.service;

import org.springframework.stereotype.Component;

@Component
public class BubbleSortAlgorithm implements SortAlgorithm
{
    @Override
    public String describeSort() {
        return "BubbleSort";
    }
}
```
### QuickSortAlgorithm Concrete Class
```
package com.fireacademy.SpringBasic.service;

import org.springframework.stereotype.Component;

@Component
public class QuickSortAlgorithm implements SortAlgorithm
{
    @Override
    public String describeSort() {
        return "QuickSort";
    }
}
```
### SearchData Class
```
package com.fireacademy.SpringBasic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SearchData
{
    @Autowired
    private SortAlgorithm sortAlgorithm;

    public String describeSearch()
    {
        return "Using " + sortAlgorithm.describeSort();
    }
}
```
- SearchData class has a Dependency on SortAlgorithm interface & its concrete classes. So we mark it as Autowired.
- This however gives an error when the application starts up. This is because there are two implementations of SortAlgorithm and Spring does not know which one to use. 
```
***************************
APPLICATION FAILED TO START
***************************

Description:

Field sortAlgorithm in com.fireacademy.SpringBasic.service.SearchData required a single bean, but 2 were found:
	- bubbleSortAlgorithm: defined in file [D:\Development\springboot\SpringBasic\target\classes\com\fireacademy\SpringBasic\service\BubbleSortAlgorithm.class]
	- quickSortAlgorithm: defined in file [D:\Development\springboot\SpringBasic\target\classes\com\fireacademy\SpringBasic\service\QuickSortAlgorithm.class]


Action:

Consider marking one of the beans as @Primary, updating the consumer to accept multiple beans, or using @Qualifier to identify the bean that should be consumed
```
- To solve this problem, mark one of the classes as @Primary so that Spring understands which implementation to prefer.

- @ComponentScan tells Spring which all packages to scan for Bean classes.
  - When you try to access SearchData and if it happens to be in a different directory than the class with @SpringBootApplication, then we encounter these problems below.
``` 
Exception in thread "main" org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type 'com.fireacademy.service.SearchData' available
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.getBean(DefaultListableBeanFactory.java:351)
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.getBean(DefaultListableBeanFactory.java:342)
	at org.springframework.context.support.AbstractApplicationContext.getBean(AbstractApplicationContext.java:1172)
	at com.fireacademy.SpringBasic.SpringBasicApplication.main(SpringBasicApplication.java:19)
```
 - Solution to this would be to update the component scan to include this additional packages as well.
```
@SpringBootApplication
@ComponentScan(basePackages = "com.fireacademy.service")
public class SpringBasicApplication
{
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringBasicApplication.class);

	public static void main(String[] args)
	{
		ApplicationContext ctx = SpringApplication.run(SpringBasicApplication.class, args);

		SearchData searchData = ctx.getBean(SearchData.class);
		LOGGER.info("------>" + searchData.describeSearch());
	}
}
```
- Any subclass of Component annotation will be considered for this scan.
  - Ex: @Component, @Service, @Controller etc. 
  - https://stackoverflow.com/a/15925869/815961
  - By default a SpringBootApplication annotation adds the package it is defined in to ComponentScan.
  - So other packages would need to be added to this ComponentScan using this annotation.

- @Service holds the business logic / service layer.
  - BubbleSortAlgorithm and QuickSortAlgorithm classes in the above instances are examples of business logic. So they can have the @Service annotation on them.

- @Controller is the controller in Spring MVC.
- More on annotations here https://springframework.guru/spring-framework-annotations/

- @Bean is very generic. You can configure it to only use a single instance in an application or multiple instances in an application.
  - This is done via @Scope("prototype") annotation or @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE).
	- singleton - one per entire application lifetime.
	- prototype - new instance each time.
	- request - one per request.
	- session - one per session.
- @Bean vs @Component
  - https://stackoverflow.com/questions/10604298/spring-component-versus-bean
- @Component vs @Service
  - https://stackoverflow.com/a/15925869/815961

???
@Proxy
@Configuration
@Beans
???

- Application Configuration in Spring is done using @Configuration
- ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringBootLearning.class);
- By default SpringBoot defines a ComponentScan on the class where the Configuration is present.

## In Action
- Any Spring project with its set of dependencies can be generated from https://start.spring.io/
- The final build artifact can be a JAR file or a WAR file. In case of that being a JAR file, it would contain main method to help with the case of Microservices.
- 

## REST API

## Reading from databases







Bean - Singleton Bean / prototype / Request and Session
 - Bean can be put on class methods as in case of 3rd part libraries where you cant annotate the method as @Component.
 - In this case, the custom class would be marked as @Configuration.
 - https://stackoverflow.com/a/53975920/815961
 - https://stackoverflow.com/a/40861225/815961

# Reading from the properties files
@PropertySource("classpath:app.properties")

@Value("${external.service.url}")

@PostConstruct

@PreDestroy

# Application.properties
- Every spring application has an application.properties file from where it reads configuration settings. This is usually in the resources folder.
- These settings can be database connections, cache server urls or even log level monitoring etc.
- For example, the variable ```logging.level.org.springframework = debug``` set the logging to DEBUG.
- 

# Context and Dependency Injection
- JPA is interface, Hibernate is implentation
- CDI is interface, Spring is implementation for Dependency Injection
  - Context and Dependency Injection https://stackoverflow.com/a/5974145/815961




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
	
	
