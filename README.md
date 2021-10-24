# **COMP90082_Berry-Street_Bluering**
## **Software Project 2021 - Team Bluering**
This repository contains the source code for the COMP90082 Software Project course at the University of Melbourne. The LEx Mirror application for Y-change's strategy.

Y-change's strategy is to recruit, train and hire young life experience consultants (LEC) for external consulting. LEC provides professional advice and guidance and establishes solutions for the community system that affects disadvantaged youth. Y-change is currently seeking to create a tool to help LEC reflect on and improve their professional and personal skills. The project will implement an online testing platform for LEC specific skill areas, which is called LEx Mirror.

By applying the LEx Mirror platform, the application is able to provide online tests for professional and personal skills. The interface of the project is easy to use, interactive,visually engaging, and access because young people with life experience tend to stay in the comfort zone. In addition, the project is able to achieve the ability to test and reflect on feedback without logging in, as well as to protect privacy, and the system will only share the content that participants want to share. 

## **Project Demo**

Demo Link: [https://confluence.cis.unimelb.edu.au:8443/pages/viewpage.action?pageId=83149268](https://confluence.cis.unimelb.edu.au:8443/pages/viewpage.action?pageId=83149268)

## **Contributors**
- Zixin Zhang
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
      - API documents for system 
```
## **Installation**
### **System Requirements**
Front-end Tools:
- ElementUI: based on Visual Studio Code (Version 1.60)
- vue-cli: based on node.js (Version 14.17.6). Detail installation of vue-cli can be seen in the Front-end Setting below.

Back-end Tools:
- JAVA IDE: for example, IntelliJ IDEA (Version 2021.2.1)
- Maven environment(optional): detail and link can be seen in the Back-end Setting below.

Database Tool:
- MySQL: MySQL Workbench (Version 8.0.26)

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
    url: jdbc:mysql://127.0.1:3306/project?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8
```

Finally, you can choose the `berryStreet.bluering.backend.DemoApplication` class as the IDE Run Configuration. Then build and run it. :-)

### **Database Setting**

Please run the `/database/create.sql` script in your MySQL Connection to create the database. 

The `/database/insert.sql` script will insert some testing data to your project schema. If you want to see the sample of this system, please run it,too. 

