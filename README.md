Validation Responsability
=========================

Description
-----------

This project is a proof (*PoC*) of concept about having a user interface (*UI*) in which the user can interact with different business domains (data that have different business ownership or belongs to different business context).

In this scenarios the UI usually have user data input validation. 

> TK


### Context

In this PoC we have three domains:

  - **Customer care**: This domain is owner of customer data.
  - **Finances**: This domain is responsable of financial information and formats, as bank acocunt valid formats, etc.
  - **Logistics**: The owner of all logistics related information, as delivery adresses, address formats, etc.


![Context](doc/images/context.png)

> **Note**: this image should the replaced with one with **Customer Care** instead of *My Account*. My account is an application not a functional domain.


### Microservices

![alt text](doc/images/microservices.png)

  - **My Account**: This domain is owner of customer data. Contains: 
    - **My Account WebApp**: The *My Account* user interface application. 
    - **Customer data validations**: Performs validation about the customer information. Now only checks that all fields (name, email and phone) have value. 
  - **Finances**: Contains entrypoints to perform business validations.
    - **Bank account validation**: Validates if a bank account is valid from *account domain point of view**. In this case is an [IBAN](https://en.wikipedia.org/wiki/International_Bank_Account_Number).
  - **Logistics**: This domain is owner of customer data.
    - **Address validation**: Check that the address is valid from *logistics point of view*. Currently only checks that address length is bigger than four characters.


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
