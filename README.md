## Install

- install [Intellij](https://www.jetbrains.com/idea/) IDE tool.
- instead of Intellij [Spring-sts](https://spring.io/tools/sts) IDE tool.
- install [Mysql](https://dev.mysql.com/downloads/mysql/) server.
- install [Gradle](https://gradle.org/gradle-download/)
- instead of Gradle [Maven](https://maven.apache.org/download.cgi)
- install [JAVA 8 JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

## Setting

- Mysql [user setting](http://dev.mysql.com/doc/refman/5.7/en/adding-users.html)
- username: dsd
- password: password

In Mysql create user and grant privileges
```
$ create user 'dsd'@'%' identified by 'password';
$ grant all privileges on *.* to 'dsd'@'%';
$ flush privileges;
```
In Mysql create Databse
```
$ create database calendar_feed;
```
Rest of tables are made by JPA.

## Run
### Command line
```
$ gradle bootRun
```

### Spring-sts
![Run. Step 1](https://github.com/kty1965/calendar-feed/blob/master/images/1.png)
![Run. Step 2](https://github.com/kty1965/calendar-feed/blob/master/images/2.png)
![Run. Step 3](https://github.com/kty1965/calendar-feed/blob/master/images/3.png)
![Run. Step 4](https://github.com/kty1965/calendar-feed/blob/master/images/4.png)
