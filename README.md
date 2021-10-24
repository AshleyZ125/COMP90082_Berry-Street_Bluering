# **COMP90082_Berry-Street_Bluering**
## **Software Project 2021 - Team Bluering**
## **Project Overview**
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
  /berrystreet
      - Frontend VUE application
  /backend
      - Backend SpringBoot framework
  /database
      - Database creation and data insertion script
  /documents
      - API documents for system 
      - Project documents including user stories, architecture and so on. (Shown as PDF)
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


## **Change Log**
### [1.0]-2021-09-14

#### Added

1. Github repository establishment: https://github.com/AshleyZ125/COMP90082_Berry-Street_Bluering (week 5, from 2021-08-23 to 2021-08-29, the following are the same)
2. Front-end Vue establishment
3. Back-end SpringBoot and MyBatis establishment
4. Database structure implementation
5. Supervisor registers (week 6, from 2021-08-30 to 2021-09-05, the following are the same)
6. Supervisor logs in
7. Supervisor resets password
8. Supervisor resets email
9. Supervisor creates different categories of quizzes (week 7, from 2021-09-06 to 2021-09-12, the following are the same)
10. LEC views different categories of quizzes
11. LEC selects and starts quizzes
12. LEC takes quizzes

#### Changed

1. Database structure changes (week 7, from 2021-09-06 to 2021-09-12)
- Delete ‘options’ table and use object to store options in ‘questions’ table
- Adds a integer key in options object for the convenience of creating quizzes in front end

#### Removed
1. Database structure, delete ‘options’ table (week 7, from 2021-09-06 to 2021-09-12)
 

### [2.0]-2021-10-19

#### Added

1. LEC views feedback from completed quiz (week 9, from 2021-09-27 to 2021-10-03, the following are the same)
2. LEC registers
3. LEC logs in
4. LEC visits profile and update account information, including updating username, resetting email and password
5. Supervisor updates previous quizzes
6. Supervisor deletes previous quizzes
7. Supervisor configures the visibility of quizzes to LECs
8. LEC writes diary after the quiz (week 10, from 2021-10-04 to 2021-10-10, the following are the same)
9. LEC saves the feedback
10. LEC shares the feedback
11. Supervisor updates personal information, including updating username, resetting email and password and combining the work in V1.0
12. Supervisor: my LEC page containing records of quizzes taken by LECs
13. Supervisor views single quiz feedback from the LEC
14. LEC views the past feedback (week 11, from 2021-10-11 to 2021-10-17, the following are the same)
15. LEC provides suggestions on the quiz and user experience
16. LEC deletes an account
17. Supervisor views LECs’ suggestions on the quiz and user experience

#### Changed

1. LEC taking quizzes changes: add previous and next button during quizzes (week 9, from 2021-09-27 to 2021-10-03)
2. The header of some pages changes, including selectQuestion.vue and getFeedback.vue (week 10, from 2021-10-04 to 2021-10-10)
3. Database structure changes
- Delete foreign key relation of user ID in ‘record’ table to ‘user’ table (week 10, from 2021-10-04 to 2021-10-10)
- Update constraints of role in ‘user’ table and add ‘deleted’ type (week 11, from 2021-10-11 to 2021-10-17)


#### Fixed

1. Fix share and save functions logic in the back-end to ensure the correctness of the functions (week 10, from 2021-10-04 to 2021-10-10, the following is the same)
2. Fix LEC’s front end pages information transmission.
