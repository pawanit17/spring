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

## REST API
---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
- Action needed is manifested by the HTTP Verb. GET, POST, DELETE etc.
- Earlier web application are built in traditional modes like one servlet that handles a request.
- Now, web applications are built using REST architectural style.

| Syntax      | Description |
| ----------- | ----------- |
| Header      | GET /users/1       |
| Paragraph   | GET /users        |
|              | POST /user |
|    | PUT /users/1 |
|    | DELETE /users/1 |
|    | DELETE /users |
|    | /user/1/messages |
|    | /user/1/messages/5/comments |

### Header Entries
- Content-Type ( How the server should interpret the data in the HTTP body )
  - Ex: application/json
- Accept ( What all types can the sender/user accept )
  - Ex: application/json
  - Ex: application/xml 

### Bare minimum REST API in SpringBoot
- The following is a minimal implementation for a REST API 'http://localhost:8080/users'
- Each of the HTTP verbs are mapped to a certain method.
```
package com.fireacademy.SpringREST.com.fireacademy.SpringRest.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users") // http://localhost:8080/users
public class UserController
{
    @GetMapping
    public String getUser()
    {
        return "get user was called";
    }

    @PostMapping
    public String createUser()
    {
        return "create user was called";
    }

    @PutMapping
    public String updateUser()
    {
        return "update user was called";
    }

    @DeleteMapping
    public String deleteUser()
    {
        return "delete user was called";
    }
}
```

### Path Variables
- localhost:8080/users/17
```
@GetMapping(path="/{userId}")
public String getUser(@PathVariable String userId)
{
    return "get user was called with userId " + userId;
}
```

### Query String
- localhost:8080/users?page=3&limit=50
```
@GetMapping
public String getUsers(@RequestParam(value="page") int page,
		    @RequestParam(value="limit") int limit)
{
    return "get user was called for page " + page + " and limit " + limit;
}
```
- get user was called for page 3 and limit 50

### Making the query parameters optional
- localhost:8080/users?limit=10
- localhost:8080/users?page=2
```
@GetMapping
public String getUsers(@RequestParam(value="page", defaultValue="1") int page,
		    @RequestParam(value="limit", defaultValue="50") int limit)
{
    return "get user was called for page " + page + " and limit " + limit;
}
```

### Returning a Java Object as Reponse
- Create a POJO class
- Return an instance of that class during the get API call.
```
@GetMapping(path="/{userId}")
public UserRest getUser(@PathVariable String userId)
{
	UserRest user = new UserRest();
	user.setFirstName("Light");
	user.setLastName("Yagami");
	user.setEmailID("lighty@note.com");
	return user;
}
```

### Serving XML and JSON serialization responses
- Add produces to convey what type it can operate on.
- Based on the Accept HTTP Header in the Request, the return format of the server is determined.
```
@GetMapping(path="/{userId}", produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
public UserRest getUser(@PathVariable String userId)
{
	UserRest user = new UserRest();
	user.setFirstName("Light");
	user.setLastName("Yagami");
	user.setEmailID("lighty@note.com");
	return user;
}
```
- JSON
```
{
    "firstName": "Light",
    "lastName": "Yagami",
    "emailID": "lighty@note.com"
}
```
-XML
```
<UserRest>
    <firstName>Light</firstName>
    <lastName>Yagami</lastName>
    <emailID>lighty@note.com</emailID>
</UserRest>
```

### HTTP POST Request
- Add consumes and produces to convey what type it can operate on.
- UserDetailsRequestModel would be another POJO class to hold the POST body.
```
@PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
	 produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
public ResponseEntity<UserRest> createUser(@RequestBody UserDetailsRequestModel userDetails)
{
	UserRest user = new UserRest();
	user.setFirstName(userDetails.getFirstName());
	user.setLastName(userDetails.getLastName());
	user.setEmailID(userDetails.getEmailID());
	return new ResponseEntity<UserRest>(user, HttpStatus.OK);
}
```

### Returning status code from method

- To return an HTTP status code from the method, we use ResponseEntity class.
```
@GetMapping(path="/{userId}", produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
public ResponseEntity getUser(@PathVariable String userId)
{
	UserRest user = new UserRest();
	user.setFirstName("Light");
	user.setLastName("Yagami");
	user.setEmailID("lighty@note.com");
	return new ResponseEntity<UserRest>(user, HttpStatus.OK);
}
```

### Validating HTTP Post Request Body
- Hibernate Annotations used for validation
@NotNull
@Email
@Size


### Learnings
- The client need to set Content-Type HTTP header set so that the server can understand how to process the body data. This is especially true for POST and PUT requests as they supply the data in the body of HTTP header.
- The annotation @PathVariable is needed to inject the value in the URL onto the variable that is passed as input to the HTTP API method.
- Returning a ResponseEntity over a simple POJO object is advantageous. https://stackoverflow.com/questions/49673660/return-responseentity-vs-returning-pojo
- The class that are marked as Autowired should be known to Spring using annotations. Example, the Service class needs to be marked with @Service annotation when used in the REST API implementation class.
- **Resolved [org.springframework.web.HttpMediaTypeNotSupportedException: Content type 'application/json' not supported]** comes into picture if the HTTP **Content-Type** is set to application/json and the SpringBoot method for processing the request is marked with the annotations **consumes = MediaType.APPLICATION_XML_VALUE**;
- ![image](https://user-images.githubusercontent.com/42272776/147130050-fd48717c-300a-4a19-b049-9b6f660b5eea.png)



## Reading from databases
---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
### Spring JDBC - Connecting to H2 database
- H2 is an in-memory database for learning more on database connectivities
- Generate a new project using spring initializr and H2, Web and JDBC dependencies.
- In Application.properties, configure the below entries. These are needed to enable the h2 console as well as to fix the database name.
```
spring.datasource.url=jdbc:h2:mem:testdb
spring.data.jpa.respositories.bootstrap-mode=default
spring.h2.console.enabled=true
```
- A file in Resources could be created (schema.sql) to hold SQL instructions that are to be run each time the application is started.
```
create table person
(
   id integer not null,
   name varchar(255) not null,
   location varchar(255),
   birth_date timestamp,
   primary key(id)
);

INSERT INTO PERSON (ID, NAME, LOCATION, BIRTH_DATE )
VALUES(10001,  'Ranga', 'Hyderabad',sysdate());
INSERT INTO PERSON (ID, NAME, LOCATION, BIRTH_DATE )
VALUES(10002,  'James', 'New York',sysdate());
INSERT INTO PERSON (ID, NAME, LOCATION, BIRTH_DATE )
VALUES(10003,  'Pieter', 'Amsterdam',sysdate());
```
- ![image](https://user-images.githubusercontent.com/42272776/140618987-a77e32ef-d397-458e-a98d-17c04fab9605.png)
#### Read
- We create a Dao class called PersonJdbcDao.java. We annotate it using @Repository. This would have JdbcTemplate autowired into it.
- JdbcTemplate has a method called query, which is used to query the database.
```
package com.fireacademy.h2connector.dao;

import com.fireacademy.h2connector.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonJdbcDao
{
    @Autowired
    JdbcTemplate jdbcTemplate;

    // Select * from person;
    public List<Person> findAll()
    {
        // We use a Row Mapper to map the return of query execution ( result set ) with the Person class.
        // Because attribute values in Person and the database table match, we go with BeanPropertyRowMapper.
        // Otherwise, we would have to work with our custom row mappers.
        return jdbcTemplate.query("select * from person", new BeanPropertyRowMapper(Person.class));
    }
}
```
- Next comes the (entity) class Person.
```
package com.fireacademy.h2connector.entities;

import java.util.Date;

public class Person {
    private int id;
    private String name;
    private String location;
    private Date birthDate;

    public int getId() {
        return id;
    }

    // Whenever we use property mappers, we need to use the no argument constructor on that class.
    public Person() {
    }

    @Override
    public String toString() {
        return "\nPerson{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }

    public Person(int id, String name, String location, Date birthDate) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.birthDate = birthDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
```
- SpringBootApplication implements CommandLineRunner. This interface has run method that can run after the SpringApplicationContext is initialized.
```
package com.fireacademy.h2connector;

import com.fireacademy.h2connector.dao.PersonJdbcDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class H2connectorApplication implements CommandLineRunner
{
	private Logger logger = LoggerFactory.getLogger(H2connectorApplication.class);
	@Autowired
	PersonJdbcDao jdbc;

	public static void main(String[] args) {
		SpringApplication.run(H2connectorApplication.class, args);
	}

	// When the Spring Application conext starts, this run method gets invoked.
	@Override
	public void run(String... args) throws Exception
	{
		// In normal application, we would have instantiated PersonJdbcDao ourselves.
		// Spring IoC in action here.
		logger.info("All users -> {}", jdbc.findAll());
	}
}
```
- When compared to plain JDBC, this Spring JDBC is different.
- We dont establish a connection ourselves, we dont write prepared statements, we dont capture result sets, close connections and handle exceptions etc.
- Also Jdbc is not object oriented. Most ORMs are object oriented. This is the case with SpringJdbc as well.

- Database CRUD in Spring JDBC.
- **READ**
```
public Person findById(int id)
{
return (Person) jdbcTemplate.queryForObject("select * from person where id = ?",
				    new Object[]{id},
				    new BeanPropertyRowMapper(Person.class));
}

public List<Person> findByLocation(String location)
{
return jdbcTemplate.query("select * from person where location LIKE ?",
	new Object[]{location},
	new BeanPropertyRowMapper(Person.class));
}
```
- **INSERT**
```
public int insert(Person person)
{
return jdbcTemplate.update("INSERT INTO PERSON (ID, NAME, LOCATION, BIRTH_DATE ) VALUES(?, ?, ?, ? )",
			    new Object[]{
				    person.getId(),
				    person.getName(),
				    person.getLocation(),
				    new Timestamp(person.getBirthDate().getTime())});
}
```

- **UPDATE**
```
public int update(Person person)
{
return jdbcTemplate.update("update person set name =?, location = ?, birth_date = ? where id = ?",
			    new Object[]{
				    person.getName(),
				    person.getLocation(),
				    new Timestamp(person.getBirthDate().getTime()),
				    person.getId()});
}
```

- **DELETE**
public int deleteById(int id)
{
	return jdbcTemplate.update("delete from person where id = ?",
				   new Object[]{id});
}

- **USAGE**
```
// In normal application, we would have instantiated PersonJdbcDao ourselves.
// Spring IoC in action here.
// READ
logger.info("All users -> {}", jdbc.findAll());
logger.info("Specific users -> {}", jdbc.findById(10003));
logger.info("Subset users -> {}", jdbc.findByLocation("India"));

// DELETE
logger.info("Deleting users -> {}", jdbc.deleteById(10002));
logger.info("Deleting users -> {}", jdbc.deleteById(10009));
logger.info("All users -> {}", jdbc.findAll());

// INSERT
logger.info("Deleting users -> {}", jdbc.insert(new Person(10004, "Stephen Fleming", "New Zealand", new Date())));
logger.info("All users -> {}", jdbc.findAll());

// UPDATE
logger.info("Deleting users -> {}", jdbc.update(new Person(10001, "Alistair Cook", "England",new Date())));
logger.info("All users -> {}", jdbc.findAll());
```

- When the names of the columns of the underlying database table and those of the entity class match, then there is no need to add a custom mapper class.
  - Ex: birthDate in Java and birth_date in database.
- In many practical cases, they do not match and so the need for a custom mapper class.
```
class PersonRowMapper implements RowMapper<Person>
{
@Override
public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
    Person person = new Person();
    person.setId(rs.getInt("personID"));
    person.setName(rs.getString("personName"));
    person.setLocation(rs.getString("personLocation"));
    person.setBirthDate(rs.getDate("personBirthDate"));

    return person;
}
}
```
- Update the exiting BeanPropertyRowMapper to use the new PersonRowMapper as shown below.
```
// Select * from person;
public List<Person> findAll()
{
// We use a Row Mapper to map the return of query execution ( result set ) with the Person class.
// Because attribute values in Person and the database table match, we go with BeanPropertyRowMapper.
// Otherwise, we would have to work with our custom row mappers.
return jdbcTemplate.query("select * from person", new PersonRowMapper());
}

public Person findById(int id)
{
return (Person) jdbcTemplate.queryForObject("select * from person where personID = ?",
				    new Object[]{id},
				    new PersonRowMapper());
}
```

## Spring Data JPA
- https://www.youtube.com/watch?v=MaI0_XdpdP8
- Impedence Mismatch - The way the database stores data is not in line with how the java classes store data. Ex: Everything in JDBC world is not an object and is difficult to map it to a row. Also, JDBC is not an ORM ( Mapping objects of a class to rows of the table in database ).
- JPA is a reference, Hibernate is the implementation.
- JPA defines different annotations (defining entities, attributes, mapping relationships and entities etc ) and Hibernate provides the implementations for it.
- Hibernate is an ORM framework.
- JPA expects a default constructor in the java classes ( bean ).
- @Entity annotation from javax.persistence is used to represent a bean as an Entity bean.
- @Id specifies a primary key in the database table.
- @GeneratedValue is used to convey that the value is to be generated for the primary key.
- EntityManager class is used to store the values in entity to the database. This is usually a DAO service.
- The DAO service is marked as an annotation called @Repository. Repository is that class that interacts with the database.
- @Transactional is used to say that each method is involved in a Transaction.
- Objects can be saved to the database using the persist method on the EntityManager class.
- Objects that are saved via this approach are tracked in Persistence Context.
- @PersistenceContext is marked on the EntityManager class to autowire it.
### Example
- We first create an Entity class. Hibernate looks at the Entity classes and creates them when the application loads.
```
@Entity
public class Customer {
    public Customer(String customerName, String customerEmail, String customerBillingAddress,
                    String customerShippingAddress, String doorNo, String streetName, String layout, String city, String pincode) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerBillingAddress = customerBillingAddress;
        this.customerShippingAddress = customerShippingAddress;
        this.doorNo = doorNo;
        this.streetName = streetName;
        this.layout = layout;
        this.city = city;
        this.pincode = pincode;
    }

    public Customer() {
    }

    @Id
    @GeneratedValue
    private int customerId;

    private String customerName;
    private String customerEmail;
    private String customerBillingAddress;
    private String customerShippingAddress;
    private String doorNo;
    private String streetName;
    private String layout;
    private String city;
    private String pincode;
    ...
}
```
- Then we create a DAO class and mark it as a Repository.
```
@Repository
@Transactional
public class CustomerDAOService {

    @PersistenceContext
    private EntityManager entityManager;

    public int insert(Customer customer)
    {
        entityManager.persist(customer);

        return customer.getCustomerId();
    }
}
```
- Finally, we can use this DAO in a REST API using Autowiring.
- **Create**
```
@PostMapping
public String addCustomer(@RequestBody Customer customerDetails) {

        int id = customerDAOService.insert(customerDetails);
        return "Added the customer " + id;
    }
```
- Since we have many tables, many DAO classes would need to be created.
- One way is to create interfaces instead and use them.
- Create an interface extending from JpaRepository.
```
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
```
- Autowire this interface.
```
    @Autowired
    CustomerRepository customerRepository;
```
- Proceed with using this in an REST API
```
    Customer x = customerRepository.save(customerDetails);
    return "Added the customer " + x.getCustomerId();
```
- **Retrieve**
```
    @GetMapping("/{customerId}")
    public String searchCustomer(@PathVariable String customerId) {

        Integer id = Integer.parseInt(customerId);
        Optional<Customer> customer = customerRepository.findById(id);
        return "Searching for the customer " + customer.get().getCustomerName();
    }
```
- **Update**
```
@PutMapping("/{customerId}")
    public String updateCustomer(@PathVariable String customerId, @RequestBody Customer customerDetails) {

        customerDetails.setCustomerId(Integer.parseInt(customerId));
        Customer x = customerRepository.save(customerDetails);
        return "Updated the customer " + x.getCustomerId();
    }
```
- **Delete**
```
@DeleteMapping("/{customerId}")
    public String deleteCustomer(@PathVariable String customerId) {

        Integer id = Integer.parseInt(customerId);
        customerRepository.deleteById(id);
        return "Deleting the customer " + customerId;
    }
```

- Customers have an address, this is 1-1 mapping.
- So we define the 1-1 mapping as an annotation @OneToOne mapping on the owning side of the relationship.
```
@OneToOne( cascade = CascadeType.REMOVE, orphanRemoval = true )
@JoinColumn(name="customerShippingAddress")
private Address address;
```
- Cascade is needed so that when the Customer is deleted, the address gets deleted.
```
@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AddressRepository addressRepository;

    Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> searchCustomer(@PathVariable String customerId) {

        Optional<Customer> customer = customerRepository.findById(Integer.parseInt(customerId));
        if(customer.isEmpty())
        {
            return new ResponseEntity<Customer>(new Customer(), HttpStatus.OK);
        }
        return new ResponseEntity<Customer>(customer.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Customer> addCustomer(@RequestBody CustomerRequestBody customerDetails) {

        Address address = new Address(customerDetails.getDoorNo(),
                customerDetails.getStreetName(),
                customerDetails.getLayout(),
                customerDetails.getCity(),
                customerDetails.getPincode());

        Customer customer = new Customer(customerDetails.getCustomerName(),
                customerDetails.getCustomerEmail(),
                customerDetails.getCustomerBillingAddress(),
                address
                );

        addressRepository.save(address);
        customerRepository.save(customer);
        return new ResponseEntity<Customer>(customer, HttpStatus.CREATED);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable String customerId, @RequestBody CustomerRequestBody customerDetails) {

        Address address = new Address(customerDetails.getDoorNo(),
                customerDetails.getStreetName(),
                customerDetails.getLayout(),
                customerDetails.getCity(),
                customerDetails.getPincode());

        Customer customer = new Customer(customerDetails.getCustomerName(),
                customerDetails.getCustomerEmail(),
                customerDetails.getCustomerBillingAddress(),
                address);
        customer.setCustomerId(Integer.parseInt(customerId));

        addressRepository.save(address);
        customerRepository.save(customer);
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable String customerId) {

        Optional<Customer> customer = customerRepository.findById(Integer.parseInt(customerId));
        customerRepository.deleteById(Integer.parseInt(customerId));
        return new ResponseEntity<Customer>(customer.get(), HttpStatus.OK);
    }
}
```

# Mojo

- ❓How do you write custom queries?.
- ❓How do you do join or insert across multiple tables?.

:memo: TODO
1. Explore Hibernate Jpa

## Spring Cloud
### Spring Cloud Eureka
- The Eureka server Microservice shall have a dependency of Eureka Server.
  - The main Spring application should be annotated with @EnableEurekaServer.
  - We also need to prevent the Eureka Server registering itself as client. So the following configuration is needed.
  - application.yml file content:
  ```
  eureka:
    client:
      register-with-eureka: false
      fetch-registry: false
  server:
    port: 8761
  ```
- The client Microservices that need to register themselves with Eureka will have the Eureka Discovery Client dependency.
  - The main Spring application should be annotated with @EnableEurekaClient.
  ```
  # For Eureka Registration
  spring.application.name=ORDER-Microservice
  eureka.client.register-with-eureka=true
  eureka.client.fetch-registry=true
  eureka.client.service-url.defaultZone=http://localhost:8761/eureka/
  eureka.instance.hostname=localhost
  ```

## Spring Unit Testing
---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
@RunWith(SpringRunner.class)
- Java Context
@ContextConfiguration(classes=SpringApplication.class)
- XML Context
@ContextConfiguration(locations="/applicationContext.xml")


## Spring MVC
---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
Front controller / Dispatcher controller
View resolver
Architecture
@Controller
@RequestMapping(value="/login")
@ResponseBody


## Spring AOP
- For cross cutting concerns like security, logging etc. These are not limited to a single layer alone.
- Checks like logging, user access check can be incorporated at a single place, using @Before annotation instead of having to do the same at all the methods.
- Combination of join point ( specific interception point ) + advice ( what to do ) is an Aspect.
- Pointcut is the expression that is used to do matching.
  - Pointcuts can be defined in seperate files which is useful for large projects.
  -  @Pointcut annotation is used for this.
- Process of adding AOP around the methods is called Weaving and the framework is called Weaver.
- @Before ( logging, access checks etc ), @After, @AfterReturned, @AfterThrowing @Around( performance tracing ) are some relavant annotations.

------------------------------------------------------------------------------------------------------------------------------------------------------------------------

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


# SpringBoot
- A wrapper over Spring to make development easier by including autoconfiguration, embedeed servers etc.
- View Resolvers?. Dispatcher Servlets?.
- Starters (Web, JPA etc) help in ................
- Externalization.
- Actuators to understand analytics - how many times the MS is called, how many times it failed etc.

- @RESTController
- @GetMapping
- Autoconfiguration
  - Is implemented by an autoconiguration jar file. Ex: If SpringMVC Jar is on classpath, it configures the DispatcherServlet. If H2 jar is on classpath, it configures connections etc.

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
	
	
