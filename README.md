# spring

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
	
JDBC
SpringJDBC
JPA
SpringDataJPA
	
	
	
	
