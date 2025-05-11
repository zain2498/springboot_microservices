We have three microservices namely:

Product service --> PS
Inventory service --> IS
Stock Service --> SS


Each microservice can hav its own database and each microservice can communicate with another microservice as well and it can also communicate with multiple microservices.

like PS can communicate to IS as well as SS same goes with other


There are two types of communication between them :

Synchronous
Asynchronous


Synchronous :
	In the case of synchronous, we can use the HTTP protocol to make an HTTP request from one microservice to the other microservice

Asynchronous :
	In the case of asynchronous communication, we have to use a message broker for asynchronous communication between them multiple microservices.

Each microservice can developed, deployed and scaled separately.

Each micro service can exposed its REST Apis and each microservice can use another microservice's REST API using HTTP protocol.


Whenever client request to API gateway it will rerout the request to the relevant microservice and client can send request using web, mobile or desktop application.


All the micro services we have, should be registered in the 'SERVICE REGISTRY' so that the API GATEWAY will discover about each micro service using service registry.

CONFIG SERVER will externalize the configurations of all the microservices present in the microservice project.Its primary purpose is to centralize the configuration management for microservices,

Distributed technique ki help se hum flow of request ju k multiple services se propagate hu rhi huti hain usko track kr sakte hain path or un services k path ko trace kr sakte hain k koi b specific req kin kin services se huti hwi ayi ha isey problem ko identify krna, failures ko pakarna, bottlenecks findout krna or overall system ko monitor karna easy hujata ha developer or operator k liye or system ki performance be behtar hujati hay.


Each microsercie can be loosely coupled.



																	Monolith Application

All components are part of single unit.
Everything is developed, deployed and scaled as 1 unit.
App must be written in one tech stack
1 single artifact (WAR File), so you must redoploy the entire application on each update
these are the standard ways to developd monolith application.

if we need to make a one line change then we have to package the entire application as WAR file and redploy it to the server which will be very time consuming and coslty operation.

																Challenges of Monolith Application

Application is too large and complex. (large code base)
Parts or components are more tightly coupled to each other.
You can only scale the entire app instead of a specific service or feature(Higher infrastructure cost)
Difficult if services need different dependency version
Release process takes longer, on every change the entire application needs to be tested and entire application needs to be built and deployed.
Bug in one module can potentially bring down the entire application.

																Benefits of using Microservices

how to break down the application ?
The best practice is to break down the application into components or into microservices based on the "business functionality" and not technical functionalities
Separation of concern  means 1 service for 1 job
A very important characteristic of each microservice is that they should be self contained and independent from each other this means that each service should be developed, deployed and scaled independently without any tight dependencies on any other services even though they are part of the same application.
Relase process dont take any longer time.
can independently scaled up highly used services
Technolgy updates/wewrite becomes easier.

what code goes where ?

How many services we create ?

How big/small services should be ?

how does services should communicate ?



whenever the microservice calls the other microservice and that service is down or not available due to which our service stops and waits for response and continuously calling we used circuit break to reduce and restric the number of calls

service registry and discovery maitains the hostname and port of all the registered microservices and its instances and api gateway discovered the hostname and port of the particular microservice from the service register which also keep tracks of how many services and its instances are up and running and which microservice is down.


Distributed Tracing can help us to indentify and trace the complete call hierarchy from client to serverices


These are the common design patterns which we typically used in every microservice project:
Circuit Breaker
Distributed Tracing
Config servers
Service Registry and discovery
API Gateway

and in order to achieve these patterns we need to write the code manually but in Spring we have the Spring Cloud module which provides us the implementation of all these design patterns automatically. Spring cloud provide us tools and modules like spring clould config module provides the config server patterns which implements this commonly used patterns in a microservice project.

Spring cloud Stream module is used for asynchronous communication between different services.


we can use three different ways to call from one microservices to another using :
Rest Template
WebClient
Spring Cloud openFeign

spring cloud provides Spring Cloud Netflix Eureka Server for the implementation of Service Registry and discovery pattern in our spring boot microservices project
Spring cloud provide Spring cloud config server module in order to implement config server design pattern in our spring boot project which externalizes the configuration of all microservices into a central place and here we use github repository.
Spring cloud provide spring cloud gateway module in order to implment APIGateway
Spring cloud provide spring cloud sleuth module in order to achieve spring disctributed tracing our spring boot application. Spring cloud sleuth used zipkin which provides user interface to visualize the trace information of services on web.


Spring Cloud openFeign is a declarative Client: Feign creates a dynamic implementation of an interface decorated with JAX RS and Spring MVC annotations

@EnableFeignClients annotation enables component scanning for interfaces that declare they are Feign clients.

spring cloud eureka server internally provides spring cloud load balancer to automatically handle the load balancer to multiple instances of a microservice
instead of this @FeignClient(url = "http://localhost:9091", value = "DEPARTMENT-SERVICE")
we use this @FeignClient(name = "DEPARTMENT-SERVICE") and it will automatically detect via spring cloud eureka internally call spring cloud load balancer and this will track which instance of department service is running it will route the request to that instance
