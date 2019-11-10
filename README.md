# trustflag-backend
Spring boot app that connects to Mysql database. Includes controllers for basic CRUD operations on User and Flag entities

### One time set up
##### Dependencies
- install maven (https://maven.apache.org/install.html)
- install java (1.8 and above - https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- install mysql (5.7 - https://dev.mysql.com/doc/refman/5.7/en/installing.html)
##### Create 'flags' database
- create database `flags`
- change root password to `''`
- seed with dump file `flags_2019-11-10.sql`

### Build
```bash
mvn clean install
```
### Run
```bash
$ ./run-local.sh
```
