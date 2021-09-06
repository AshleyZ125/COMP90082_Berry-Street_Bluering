create schema project;
create table project.user(
UID int not null auto_increment primary key,
email varchar(50) not null,
username varchar(20) not null,
password varchar(20) not null,
role varchar(20) not null check(role in ('supervisor', 'LEC'))
);
create table project.quiz(
QID int not null auto_increment primary key,
topic varchar(100) not null,
overview varchar(1000) not null,
status int not null,
creatorID int not null,
foreign key(creatorID) references user(UID)
);
create table project.question(
qID int not null auto_increment primary key, 
qContent varchar(200) not null,
options varchar(1000) not null,
quizID int not null,
foreign key(quizID) references quiz(QID)
);
create table project.feedback(
FID int not null auto_increment primary key, 
scoreRange varchar(20) not null,
remark varchar(1000) not null,
quiz_feed_ID int not null,
foreign key(quiz_feed_ID) references quiz(QID)
);
create table project.record(
RID int not null auto_increment primary key, 
rdate date not null,
quizContent varchar(1000) not null,
savedReflection varchar(500),
feedID int not null,
userID int,# Anonymous UID=null
foreign key(userID) references user(UID),
foreign key(feedID) references feedback(FID)
);
create table project.share(
SID int not null auto_increment primary key, 
sender int not null,
receiver int not null,
recordID int not null,
shareReflection varchar(1000),
foreign key(recordID) references record(RID)
);
create table project.experience(
EID int not null auto_increment primary key, 
eContent varchar(1000) not null,
eDate date not null
);
