# spring

@Primary
@Component
@Scope("prototype")
- ConfigureableBeanFactory
@Autowired
@Qualifier("bubble")
Autowiring by name

Bean - Singleton Bean / prototype / Request and Session
Business Service
Dependency Injection
Inverstion of control
Container / ApplicationContext

# Reading from the properties files
@PropertySource("classpath:app.properties")

@Value("${external.service.url}")
