# spring

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


- To remove @SpringBootApplication, add @Configuration and @ComponentScan.
- What all does @SpringBootApplication bring?.

AnnotationConfigApplicationContext ( Spring ) vs ApplicationContext ( SpringBoot )



# To do
- Add dependencies with logback / log4j and see how things work in basic Spring application.
- 







