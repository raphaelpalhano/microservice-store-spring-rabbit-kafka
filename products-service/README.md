# Store Products Microservice (Spring boot)


## Services

### Product Service 

 Create and View products, acts as Product Catalog


### Order Service

Can order products


### Inventory Service 

Can check if product is in stock or not.


### Notification Service

Can send notifications, after order is placed.


### Communications

Sync and Assync

Order Service, Inventory service and Notification Service are going to interact with each other.



## Architecture 

### Microservice

![microsservice](/docs/architech-microservice-store.png)

### Logical
![Logical](/docs//logical%20architecture.png)


## Application

### Start

docker compose up -d

mvn spring-boot:run

