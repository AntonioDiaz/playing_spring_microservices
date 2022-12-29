# Spring Microservices - Udemy

<h2> Index </h2>  

- [Links](#links)
- [1. Introduction to Microservices Architecture](#1-introduction-to-microservices-architecture)
- [2. Microservices \& Spring](#2-microservices--spring)
- [3. Right sizing Microservices \& Identifying boundaries](#3-right-sizing-microservices--identifying-boundaries)
- [4. Getting started with creation of accounts, loands and cards microservices](#4-getting-started-with-creation-of-accounts-loands-and-cards-microservices)
- [5. Docker: who to build, deploy, scale our microservices using](#5-docker-who-to-build-deploy-scale-our-microservices-using)
  - [Docker images](#docker-images)
  - [Docker commands](#docker-commands)
  - [Buildpacks](#buildpacks)
  - [Docker hub](#docker-hub)
  - [Docker Compose](#docker-compose)
- [6. Deep Dive on Cloud Native Apps \& 12factors](#6-deep-dive-on-cloud-native-apps--12factors)
- [7. Configurations managements in microservices](#7-configurations-managements-in-microservices)
  - [Reading properties from local](#reading-properties-from-local)
  - [Reading properties from repository](#reading-properties-from-repository)
  - [Config microservice to read properties from config server](#config-microservice-to-read-properties-from-config-server)
  - [Refresh properties](#refresh-properties)
  - [Encryption \& decryption properties](#encryption--decryption-properties)
- [8. Service discovery \& registration](#8-service-discovery--registration)
  - [Example: Eureka Server](#example-eureka-server)
  - [Example: Microservice Eureka client](#example-microservice-eureka-client)
  - [Eureka: unregister microservice](#eureka-unregister-microservice)
  - [Heartbeat mechanism to Eureka server for clients](#heartbeat-mechanism-to-eureka-server-for-clients)
  - [Feign Client to invoke other microservices](#feign-client-to-invoke-other-microservices)
  - [Update Docker images](#update-docker-images)
- [9. Making microservices resilent](#9-making-microservices-resilent)
  - [Circuit breaker pattern](#circuit-breaker-pattern)
  - [Circuit breaker pattern implementation](#circuit-breaker-pattern-implementation)
  - [Retry pattern](#retry-pattern)
  - [Rate Limitter pattern](#rate-limitter-pattern)
  - [Bulkhead pattern](#bulkhead-pattern)
- [10. Handling rounting \& cross cutting concerns in microservices](#10-handling-rounting--cross-cutting-concerns-in-microservices)
  - [Spring Cloud Gateway](#spring-cloud-gateway)
  - [Implement service as Spring Cloud Gateway](#implement-service-as-spring-cloud-gateway)
  - [Customize routing](#customize-routing)
  - [Tracing \& Logging](#tracing--logging)
  - [Update Docker Compose](#update-docker-compose)
- [11. Distributed tracing \& log aggregation in microservices](#11-distributed-tracing--log-aggregation-in-microservices)
- [12. Monitoring microservices metrics \& health](#12-monitoring-microservices-metrics--health)
- [13. Automatic self-healing, scaling, deployments using Kubernetes](#13-automatic-self-healing-scaling-deployments-using-kubernetes)
- [14. Deploying all microsevices into k8s cluster](#14-deploying-all-microsevices-into-k8s-cluster)
- [15. Deep Dive on Helm](#15-deep-dive-on-helm)
- [16. Securing microservices using k8s service](#16-securing-microservices-using-k8s-service)
- [17. Securing microservices using OAuth2 client credentials grant flow](#17-securing-microservices-using-oauth2-client-credentials-grant-flow)
- [18. Securing microservices using OAuth2 client Authorization code grant flow](#18-securing-microservices-using-oauth2-client-authorization-code-grant-flow)
- [19. Introduction to K8s ingress \& service Mesh (Istio)](#19-introduction-to-k8s-ingress--service-mesh-istio)
- [20. Thank you \& congratulations](#20-thank-you--congratulations)

## Links
* Course Udemy  
https://www.udemy.com/course/master-microservices-with-spring-docker-kubernetes/  

* Oficial Repo  
https://github.com/eazybytes/microservices-with-spring-sectionwise-code  


## 1. Introduction to Microservices Architecture
https://drive.google.com/file/d/1JXyE1jOX0GEd1WWm5ShxKjkW2XlqBEsM/view?usp=share_link

## 2. Microservices & Spring
https://drive.google.com/file/d/1M7bqXiCUi1OzNvoClEVUEdqlarESYfiv/view?usp=share_link

## 3. Right sizing Microservices & Identifying boundaries
https://drive.google.com/file/d/1TAwGoKeWqnbb7umSPZyaTHrsJSN16tfK/view?usp=share_link

## 4. Getting started with creation of accounts, loands and cards microservices
Spring Boot review.

## 5. Docker: who to build, deploy, scale our microservices using
https://drive.google.com/file/d/1z5gSyhPKgY54PXpMP3Z89nfuDROWhP6t/view?usp=share_link

Using Docker
* Virtual Machines VS Containers
<img src="https://antoniodiaz.github.io/images/microservices/microservices_virtualmachines_vs_containers.png" width="800"/>  

* Docker architecture
<img src="https://antoniodiaz.github.io/images/microservices/intro_docker.png" width="800"/>  

### Docker images
Create a file named `Dockerfile` (without extension) on root folder
Example:
```dockerfile
  #Start with a base image containing Java runtime
FROM openjdk:17-jdk-slim as build

#Information around who maintains the image
MAINTAINER eazybytes.com

# Add the application's jar to the container
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

#execute the application
ENTRYPOINT ["java","-jar","/accounts-0.0.1-SNAPSHOT.jar"]
```
### Docker commands
`mvn clean install` -> generate jar file  
`docker images`-> show images   
`docker build . -t eazybytes/accounts`  
`docker image inspect 706`  
`docker run -p 8080:8080 eazybytes/accounts`  
`docker ps` -> show the containers  
`docker ps -a` -> show the containers even stoped  
`docker logs -f 706`  
`docker stop 706`  
`docker kill 706` -> stop instantly  
`docker start 706 xxx`  
`docker pause xxx`  
`docker unpause xxx`  
`docker container inspect 706`  
`docker stats`  
`docker rm xxx` -> remove container 

### Buildpacks
* Create an image without any docker definition.
* https://buildpacks.io/
* Example:
  * Update `pom.xml`
```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <configuration>
                <image>
                    <name>eazybytes/${project.artifactId}</name>
                </image>
            </configuration>
        </plugin>
    </plugins>
</build>
```  
* Run command  
  `mvn spring-boot:build-image`

### Docker hub
https://hub.docker.com/  
`docker push docker.io/adiazarroyo/accounts:latest`  

<img src="https://antoniodiaz.github.io/images/microservices/docker_hub.png" width="800"/>  

### Docker Compose
* https://docs.docker.com/compose/gettingstarted/  
  
* Start and stop all the microservices with a single command.
  * `docker-compose up`
  * `docker-compose down`  

* Example file name `docker-compose.yml`  
```yml
version: "3.8"

services:

  accounts:
    image: eazybytes/accounts:latest
    mem_limit: 700m
    ports:
      - "8080:8080"
    networks:
      - eazybank-network
    
  loans:
    image: eazybytes/loans:latest
    mem_limit: 700m
    ports:
      - "8090:8090"
    networks:
      - eazybank-network
    
  cards:
    image: eazybytes/cards:latest
    mem_limit: 700m
    ports:
      - "9000:9000"
    networks:
      - eazybank-network
    
networks:
  eazybank-network:
```


## 6. Deep Dive on Cloud Native Apps & 12factors
https://drive.google.com/file/d/1LkgLX0OHsE9qKtLyVgNx5rI_hf575_FT/view?usp=share_link  

https://12factor.net/

## 7. Configurations managements in microservices
https://drive.google.com/file/d/1z9hP2WdREh_hdKb1eHcESUJiCe6NAzHa/view?usp=share_link

Steps:
1. Create spring project with dependences: Actuator and Config Server  
<img src="https://antoniodiaz.github.io/images/microservices/config_server.png" width="800"/> 

2. Add annotation: `@EnableConfigServer`  
```java   
 @SpringBootApplication 
 @EnableConfigServer 
 public class ConfigServerApplication { 
   public static void main(String[] args) { 
     SpringApplication.run(ConfigServerApplication.class, args); 
   } 
 } 
 ```
### Reading properties from local
* Create `config` folder under `resources`  
<img src="https://antoniodiaz.github.io/images/microservices/config_server_files.png" width="400"/>  

* Update `application.properties`  
```properties
spring.profiles.active=native
spring.cloud.config.server.native.search-locations=classpath:/config
```
<img src="https://antoniodiaz.github.io/images/microservices/config_accounts.png" width="400"/>  

### Reading properties from repository
* Create a repository on Github:  
<img src="https://antoniodiaz.github.io/images/microservices/config_server_git.png" width="400"/>  

* Update `application.properties`  
```properties
spring.profiles.active=git
spring.cloud.config.server.git.uri=https://github.com/AntonioDiaz/playing_spring_microservices.git
spring.cloud.config.server.git.clone-on-start=true
spring.cloud.config.server.git.default-label=master
spring.cloud.config.server.git.search-paths=config
```

### Config microservice to read properties from config server
* Adding dependencies to `pom.xml`
  * Spring Cloud
  * Spring Cloud Config
```xml
<dependencyManagement>
  <dependencies>
      ...
      <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-config</artifactId>
      </dependency>
  </dependencies>
  <dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-dependencies</artifactId>
            <version>${spring-cloud.version}</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

* Adding properties
```properties
spring.application.name=accounts
spring.profiles.active=prod
spring.config.import=optional:configserver:http://localhost:8071
```

* Create bean to store the properties:
```java
@Configuration
@ConfigurationProperties(prefix = "accounts")
@Getter
@Setter
@ToString
public class AccountsServiceConfig {
  private String welcome;
}
```  

### Refresh properties
* Add annotation `@RefreshScope`
* Expose Actuator endpoint `refresh`, on application.properties

  `management.endpoints.web.exposure.include=refresh`


* Invoke post to refresh properties
  
<img src="https://antoniodiaz.github.io/images/microservices/refresh_scope.png" width="800"/>  

### Encryption & decryption properties
* On properties server add the key to `bootstrap.yml`
```yml
encrypt:
  key: xxxxxxxx
```
* The server exposes 2 enpoints to encrypt and decryp properties  
  * POST: http://localhost:8071/encrypt  
  * POST: http://localhost:8071/decrypt  
  
<img src="https://antoniodiaz.github.io/images/microservices/properties_encrypt.png" width="800"/>  

* On the properties file add de property encrypted with the value returned by de endpoint, add `{cipher}` before the value to say the server this property is encrypted. 
```properties
accounts.password={cipher}87be552649d61b025b42e4f3a2272e5873da64b2652f9da6bde18ad77adb7e17
```  
## 8. Service discovery & registration
https://drive.google.com/file/d/1lhIo4iszxHKwiI5yr5y0wcCmIhKYoqj7/view?usp=share_link

<img src="https://antoniodiaz.github.io/images/microservices/service_discovery.png" width="800"/>  

<img src="https://antoniodiaz.github.io/images/microservices/service_discovery_pattern.png" width="600"/> 

### Example: Eureka Server
* Create microservice project: a new Spring Boot Project and add dependences: 
  * `Actuator`
  * `Eureka Server`
  * `Config Client`  
<img src="https://antoniodiaz.github.io/images/microservices/eureka_config_server.png" width="800"/>  

* Add annotation `@EnableEurekaServer`
* Add properties to application properties:
```properties
spring.application.name=eurekaserver
spring.config.import=optional:configserver:http://localhost:8071
```
* On property for project add new file for eureka server properties:
```properties
server.port=8070
eureka.instance.hostname=localhost
eureka.client.registerWithEureka=false
eureka.client.fetchRegistry=false
eureka.client.serviceUrl.defaultZone=http://${eureka.instance.hostname}:${server.port}/eureka/
```  
* Start Eureka Server project and have a look to the Eureka dashboard:  
<img src="https://antoniodiaz.github.io/images/microservices/eureka.png" width="800"/>  

### Example: Microservice Eureka client
* Add dependencies to `pom.xml`, for now only is required: `spring-cloud-starter-netflix-eureka-client`
```xml
<dependency>
  <groupId>org.springframework.cloud</groupId>
  <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
<dependency>
  <groupId>org.springframework.cloud</groupId>
  <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
```

* Adding properties:
```properties
eureka.instance.preferIpAdress = true
eureka.client.registerWithEureka = true
eureka.client.fetchRegistry = true
eureka.client.serviceUrl.defaultZone = http://localhost:8070/eureka/

info.app.name=Microservice for ACCOUNTS
info.app.description=My first microservice for accounts.
info.app.version=1.0.0

management.info.env.enabled = true

endpoints.shutdown.enabled = true
management.endpoint.shutdown.enabled = true
```  
* Start Accounts projects  

<img src="https://antoniodiaz.github.io/images/microservices/eureka_dashboard.png" width="800"/>

* Details about of the service
http://localhost:8070/eureka/apps/accounts

### Eureka: unregister microservice
* Make a POST to: http://192.168.0.12:8100/actuator/shutdown


### Heartbeat mechanism to Eureka server for clients
Every 30 seconds client check the server and returns an error in case the Eureka server does not respond to the heartbeat.

<img src="https://antoniodiaz.github.io/images/microservices/heartbeat_eureka_error.png" width="800"/>

### Feign Client to invoke other microservices
* We want, from microservice Accounts, invoke method from other microservices Loands and Cards.
* Add dependency of `OpenFeign` to pom.xml
```xml
<dependency>
  <groupId>org.springframework.cloud</groupId>
  <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
```
* Add `@EnableFeignClients` to `AccountsApplication`  
* Create Feign client to invoke other microservice endpoint:
```java
@FeignClient("cards")
public interface CardsFeignClients {
  @RequestMapping(method = RequestMethod.GET, value="all-cards", consumes = "application/json")
  List<Card> getAllCards();
}
```  
### Update Docker images
* For projects: accounts, loans, config-server and eureka-server
  * Generate the jar file:  
    `mvn clean install -Dmaven.test.skip=true`  
  * Generate the Docker image:  
    `docker build . -t adiazarroyo/accounts`  
* On Cards
  * `mvn spring-boot:build-image -Dmaven.test.skip=true`  
<img src="https://antoniodiaz.github.io/images/microservices/all_docker_images.png" width="800"/>

* Push images to Docker Hub
```shell
docker push docker.io/adiazarroyo/accounts
```
<img src="https://antoniodiaz.github.io/images/microservices/all_docker_hub_images.png" width="800"/>

* Update docker compose
  * Adding new service:
```yml
eurekaserver:
image: adiazarroyo/eurekaserver:latest
mem_limit: 700m
ports:
  - "8070:8070"
networks:
  - adiaz-network
depends_on:
  - configserver
deploy:
  restart_policy:
    condition: on-failure
    delay: 15s
    max_attempts: 5
    window: 240s
environment:
  SPRING_PROFILES_ACTIVE: default
  SPRING_CONFIG_IMPORT: configserver:http://configserver:8071/
```
* On the previous service add the new one on depens_on seccion, and the environment.
```yml
 accounts:
    image: adiazarroyo/accounts:latest
    mem_limit: 700m
    ports:
      - "8080:8000"
    networks:
      - adiaz-network
    depends_on:
      - configserver
      - eurekaserver
    deploy:
      restart_policy:
        condition: on-failure
        delay: 30s
        max_attempts: 5
        window: 350s
    environment:
      SPRING_PROFILES_ACTIVE: default
      SPRING_CONFIG_IMPORT: configserver:http://configserver:8071/
      EUREKA_CLIENT_SERVICEURL_DEFAULTZONE: http://eurekaserver:8070/eureka
```


## 9. Making microservices resilent
https://drive.google.com/file/d/1AbEmLa_Q-jQSPjqneUPhIg_Ehpiz-oYD/view?usp=share_link

<img src="https://antoniodiaz.github.io/images/microservices/resilence_patterns.png" width="800"/>

### Circuit breaker pattern
<img src="https://antoniodiaz.github.io/images/microservices/circuit_breaker_pattern.png" width="800"/>

<img src="https://antoniodiaz.github.io/images/microservices/circuit_breaker_status.png" width="800"/>


### Circuit breaker pattern implementation
* Add dependency to `pom.xml`
```xml
<dependency>
    <groupId>io.github.resilience4j</groupId>
    <artifactId>resilience4j-spring-boot2</artifactId>
    <version>2.0.2</version>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-aop</artifactId>
</dependency>
```
* Add `CircuitBreaker` on method that is invoking the microservice can can fail:
```java
@RequestMapping("/cards")
@CircuitBreaker(name = "detailsForCustomerSupportApp")
public List<Card> cards() {
  return cardsFeignClients.getAllCards();
}
```  
* On `application.yaml` add the next properties:  
```yaml
resilience4j:
    circuitbreaker:
        configs:
            default:
                registerHealthIndicator: true
        instances:
            detailsForCustomerSupportApp:
                failureRateThreshold: 50
                minimumNumberOfCalls: 5
                permittedNumberOfCallsInHalfOpenState: 2
                waitDurationOnOpenState: 30000
```  
* Url to check status and events:  
http://localhost:8000/actuator/circuitbreakers  
http://localhost:8000/actuator/circuitbreakerevents  
<img src="https://antoniodiaz.github.io/images/microservices/circuit_breaker_events.png" width="800"/>

* Fallback: create private method that receives same parameters that the one you want to protect, and another `Throwable` with the exception:
```java
private List<Card> cardsFallbackMethod(Throwable t) {
  return List.of();
}
```
* Add the `fallbackMethod` to annotation `@CircuitBreaker`
```java
@CircuitBreaker(
  name = "detailsForCustomerSupportApp", 
  fallbackMethod = "cardsFallbackMethod")
```

### Retry pattern
<img src="https://antoniodiaz.github.io/images/microservices/retry_pattern.png" width="800"/>

* Method annotation
```java
@RequestMapping("/cards-retry")
@Retry(name = "cards-retry")
public List<Card> cardsWithRetry() {
  log.info("Calling cards-retry");
  return cardsFeignClients.getAllCards();
}
```  
* Properties for `application.yaml`  
```yaml
resilience4j:
  retry:
    configs:
      default:
        registerHealthIndicator: true
    instances:
      cards-retry:
        maxRetryAttempts: 3
        waitDuration: 2000
```

* Url to check status and events:
http://localhost:8000/actuator/retryevents

### Rate Limitter pattern
<img src="https://antoniodiaz.github.io/images/microservices/rate_limitter_pattern.png" width="800"/>

* Method:
```java
@GetMapping("/hello")
@RateLimiter(name = "hello", fallbackMethod = "helloLimit")
public String hello() {
  log.info("Calling cards-rate-limit");
  return "hello";
}
```
* Properties:
```yml
resilience4j:
  ratelimiter:
    metrics:
      enabled: true
    instances:
      hello:
        allow-health-indicator-to-fail: true
        event-consumer-buffer-size: 50
        limit-for-period: 5
        limit-refresh-period: 60s
        register-health-indicator: true
        subscribe-for-events: true
        timeout-duration: 0s
```
### Bulkhead pattern
<img src="https://antoniodiaz.github.io/images/microservices/bulkhead_pattern.png" width="800"/>

## 10. Handling rounting & cross cutting concerns in microservices
https://drive.google.com/file/d/1fytRJcWtI4ytSZA8958dIrsdNNb99nQT/view?usp=share_link

<img src="https://antoniodiaz.github.io/images/microservices/chapter_10.png" width="800"/>

### Spring Cloud Gateway
https://spring.io/projects/spring-cloud-gateway  
<img src="https://antoniodiaz.github.io/images/microservices/spring_cloud_gateway.png" width="800"/>

<img src="https://antoniodiaz.github.io/images/microservices/spring_cloud_gateway_internal_architecture.png" width="800"/>

### Implement service as Spring Cloud Gateway
* Create new Spring Boot project with this dependencies:
  * `Gateway`
  * `Actuator` 
  * `Eureka discovery client`
  * `Config Client`
  * `Dev Tools`

* Add plugin to pom.xml go generate Docker image:
```xml
<plugin>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-maven-plugin</artifactId>
    <configuration>
        <image>
            <name>adiazarroyo/${project.artifactId}</name>
        </image>
    </configuration>
</plugin>
```
* Add annotation to register microservice on Eureka server: `@EnableFeignClients`
* Adding properties to `properties.yml`
```yml
spring:
  cloud:
    gateway:
      discovery:
        locator:
          lowerCaseServiceId: true
          enabled: true
  config:
    import: optional:configserver:http://localhost:8071
  application:
    name: gatewayserver
info:
  app:
    name: Gateway Server Microservice
    description: Adiaz Bank Microservice Application
    version: 1.0.0
management:
  endpoints:
    web:
      exposure:
        include: "*"
  info:
    java:
      enabled: true
    env:
      enabled: true
logging:
  level:
    com.adiaz.gatewayservice: DEBUG
```
* Create properties file on github config repository, file must be call with same name than `spring.application.name` from the `application.yml` of the project:
```properties
server.port = 8072
eureka.instance.preferIpAddress = true
eureka.client.registerWithEureka = true
eureka.client.fetchregistry = true
eureka.client.serviceUrl.defaultZone = http://localhost:8070/eureka/
```

* Start projects config_server and eureka_server and then the gateway  
<img src="https://antoniodiaz.github.io/images/microservices/gateway_on_eureka_dashboard.png" width="800"/>

<img src="https://antoniodiaz.github.io/images/microservices/spring_gateway_routes.png" width="800"/>


* Gateway request example
<img src="https://antoniodiaz.github.io/images/microservices/gateway_accounts.png" width="400"/>

### Customize routing
* Adding a Context path to the url:
* On `GatewayServerApplication` create the bean `RouteLocator`
```java
  @Bean
  public RouteLocator myRoutes(RouteLocatorBuilder builder) {
    return builder.routes()
            .route(p ->
                    p.path("/adiaz/loans/**")
                            .filters(f -> f
                                    .rewritePath("/adiaz/loans/(?<segment>.*)", "/${segment}")
                                    .addRequestHeader("X-Response-Time", new Date().toString()))
                            .uri("lb://LOANS"))
            .build();
  }
```
<img src="https://antoniodiaz.github.io/images/microservices/gateway_context.png" width="400"/>

### Tracing & Logging
* Adding a header to the request and response in a filter on the Gateway, like this:  
https://github.com/eazybytes/microservices-with-spring-sectionwise-code/blob/master/section_10/gatewayserver/src/main/java/com/eaztbytes/gatewayserver/filters/TraceFilter.java  
  
<img src="https://antoniodiaz.github.io/images/microservices/gateway_add_header_request.png" width="800"/>
  
<img src="https://antoniodiaz.github.io/images/microservices/gateway_add_header_response.png" width="800"/>
  

* Then the it's possible to read the header:
<img src="https://antoniodiaz.github.io/images/microservices/gateway_reading_header.png" width="600"/>


### Update Docker Compose
https://github.com/eazybytes/microservices-with-spring-sectionwise-code/blob/master/section_10/accounts/docker-compose/default/docker-compose.yml

<img src="https://antoniodiaz.github.io/images/microservices/gateway_docker_compose.png" width="600"/>

## 11. Distributed tracing & log aggregation in microservices
https://drive.google.com/file/d/1YruqrgcYmyjzaGfYantxM7ZK0e2lwh_F/view?usp=share_link

<img src="https://antoniodiaz.github.io/images/microservices/tracing_log.png" width="600"/>

## 12. Monitoring microservices metrics & health
https://drive.google.com/file/d/115DU7wwXAH_6TEIz1akk9jxzko2YCsgl/view?usp=share_link 

## 13. Automatic self-healing, scaling, deployments using Kubernetes

## 14. Deploying all microsevices into k8s cluster

## 15. Deep Dive on Helm

## 16. Securing microservices using k8s service

## 17. Securing microservices using OAuth2 client credentials grant flow

## 18. Securing microservices using OAuth2 client Authorization code grant flow

## 19. Introduction to K8s ingress & service Mesh (Istio)

## 20. Thank you & congratulations
