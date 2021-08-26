create schema project;
create table project.user(
UID int not null primary key,
email varchar(50) not null,
username varchar(20) not null,
password varchar(20) not null,
role varchar(20) not null check(role in ('supervisor', 'LEC'))
);
create table project.quiz(
QID int not null primary key,
topic varchar(100) not null,
overview varchar(500) not null
);
create table project.question(
qID int not null primary key, 
qContent varchar(200) not null,
quizID int not null,
foreign key(quizID) references quiz(QID)
);
create table project.options(
OID int not null primary key, 
oContent varchar(200) not null,
score int not null,
questionID int not null,
foreign key(questionID) references question(qID)
);
create table project.feedback(
FID int not null primary key, 
scoreRange varchar(20) not null,
remark varchar(500) not null,
quiz_feed_ID int not null,
foreign key(quiz_feed_ID) references quiz(QID)
);
create table project.record(
RID int not null primary key, 
rdate date not null,
quizContent varchar(1000) not null,
savedReflection varchar(500),
feedID int not null,
userID int,# Anonymous UID=null
isSaved char(1) not null check(isSaved in('Y', 'N')),
foreign key(feedID) references feedback(FID)
);
create table project.share(
SID int not null primary key, 
sender int not null,
receiver int not null,
recordID int not null,
shareReflection varchar(500),
foreign key(recordID) references record(RID)
);
create table project.experience(
EID int not null primary key, 
eContent varchar(1000) not null,
eDate date not null
);

