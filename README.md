# **COMP90082_Berry-Street_Bluering**
## **Software Project 2021 - Team Bluering**
This repository contains the source code for the COMP90082 Software Project course at the University of Melbourne.

## **Contributors**
- Zixin Zhang 	1087336
- He Yang
- Jing Wang
- Yingda Cui
- Yujing Guan

## **Project Structure**
```
  /frontend
      - frontend VUE application
  /backend
      - backend SpringBoot framework
  /database
      - database creation and data insertion script
  /documents
      - documents for system API 
```
## **Deployment**
### **Front-end Setting**
Install node modules:
```
npm install
```
Run the program   
```
npm run serve  
```
Build program to production 
```
npm run build  
```
### **Back-end Setting**

Firstly, you need to import the `/backend` package to your Java IDE. This is a SpringBoot system which support the backend functions for the quiz application.

Next, you need to reimport jar files according to the `/backend/pom.xml` file. This step requires the Maven environment, which is integrated in IntelliJ IDEA. If your IDE do not have Maven, please download and setting it.

- Apache Maven download link: [https://maven.apache.org/download.cgi](https://maven.apache.org/download.cgi)
- Documents for install and configuration: [https://maven.apache.org/users/index.html](https://maven.apache.org/users/index.html)

The datasource configuration in the `/backend/src/main/resources/application.yaml` file should be modified to match your database setting. You may need to replace the value of username and password to the database you create for this project. If your database is a remote database, the code `localhost:3306` should be changed to corresponding actual IP and port number.
```
spring:
  datasource:
    username: root
    password: 123456
    url: jdbc:mysql://localhost:3306/project?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8
```
Finally, you can choose the `berryStreet.bluering.backend.DemoApplication` class as the IDE Run Configuration. Then build and run it. :-)
