Validation Responsability
=========================

Descriptions
------------

### Context

![Context](doc/images/context.png)

### Microservices

![alt text](doc/images/microservices.png)

### Subsystems

![alt text](doc/images/subsystems.png)


Modules/Microservices
---------------------

  - My Account
  - Finances
  - Logistics


Run
---

In each sub-project (finances, logistics and myaccount):

  $ mvn clean package exec:java

To access the application:
 
  http://localhost:8080
