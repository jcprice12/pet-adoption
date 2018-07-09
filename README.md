# pet-adoption
A way to adopt pets

### Local Development
There are two apps. One is the Angular app, the other is the Spring Boot App.
##### Spring Boot Application
You can run the project as a spring boot application.
- Right now, the project is configured to use MySql. I'm developing on Windows so I used the MySQL Installer for windows found here: https://dev.mysql.com/downloads/installer/
- Connection information can be found in the application.properties file.
- Starting with MySQL 8.0.4, they have changed the default authentication plugin for MySQL server from mysql_native_password to caching_sha2_password. To connect to the database, I had to run the following sql: 
`ALTER USER 'springuser'@'localhost' IDENTIFIED WITH mysql_native_password BY 'Password123!';`