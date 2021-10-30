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



