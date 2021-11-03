# Getting Started
## Tutorial Springboot project

Spring boot test project with api call and crude operations
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Building a Crude operations using postgresql, JPA and Hibernate]

## Requirments to run the project
1. install postgresql on your local machine
2. create **tutorial** database and **tutorials** table under it using PGAdmin
```sql
CREATE TABLE IF NOT EXISTS public.tutorials
(
    id bigint NOT NULL,
    title character varying(50) COLLATE pg_catalog."default",
    description text COLLATE pg_catalog."default",
    published boolean,
    CONSTRAINT tutorials_pkey PRIMARY KEY (id)
)
```
3. get the code using
```$
$ git clone https://github.com/vivekn4u/tutorial.git
```
```dir
cd tutorial
```
4. configure **application.properties** file with postgresql url to connect tutorial database
```
spring.datasource.url= jdbc:postgresql://localhost:5432/tutorial
```
5. You can run the application using IDE ar by running maven commend
```cmd
mvn spring-boot:run
```

## Test using postman application
Note- configure **Content-Type** as **application/json** in request header
```
/* get all tutorial list*/
GET call - http://localhost:8085/api/tutorials

/* get tutorial by id*/
GET call - http://localhost:8085/api/tutorials/4

/* add tutorial in database*/
POST call - http://localhost:8085/api/tutorials

/* update tutorial in database*/
PUT call - http://localhost:8085/api/tutorials/5

/* delete tutorial from database*/
DELETE call - http://localhost:8085/api/tutorials/4

/* get list of published tutorial*/
GET call - http://localhost:8085/api/tutorials/published

/* get list of tutorial as per page number and row*/
GET call - http://localhost:8085/api/tutorials?page=1&rows=2
GET call - http://localhost:8085/api/tutorials/published?page=1&rows=2

/* get astronomy data by location using api call*/
GET call - http://localhost:8085/api/astronomy
```
