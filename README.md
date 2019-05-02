# Persistence-ORM

Demonstration of how to persistet data to different databases using different ORM.

## Requirements

### Hibernate

Requirements:
1. The MySQL Connector/J library must be added to the project. (https://dev.mysql.com/downloads/connector/j/)
2. The required jar files of Hibernate must be added to the project. (http://hibernate.org/orm/releases/5.4/)
2. A database named "messagerepository" must be created and hosted at "localhost:3306". The username must be "root" and the password must be blank ("").
3. A table named "message" must be added via the following SQL statement:
CREATE TABLE Message (
id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
message VARCHAR (50),
PRIMARY KEY (id)
);

### ORMLite

Requirements:
1. The MySQL Connector/J library must be added to the project. (https://dev.mysql.com/downloads/connector/j/)
2. The ormlite-jdbc-5.1.jar and ormlite-core-5.1.jar must be added to the project. (http://ormlite.com/releases/)
3. A database named "ormliteexample" must be created and hosted at "localhost:3306". The username must be "root" and the password must be blank ("").
