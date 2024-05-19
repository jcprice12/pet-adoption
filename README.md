# pet-adoption
A way to adopt pets

## Local development

Follow the guide below to get started.

### Prerequisites

Before running the app, you will need to set up a few things.

#### Java

You must download and install the Java JDK (the latest version *should* work). You can find JDK downloads [here](https://www.oracle.com/java/technologies/downloads/). After installing, make sure to set your `JAVA_HOME` environment variable. It should be set as the path of the JDK folder.

#### Maven

You must download and install Apache Maven (the latest version *should* work). You can find Maven downloads [here](https://maven.apache.org/download.cgi). After downloading the archive/zip, you will need to install it. You can install it anywhere on your system as long as you update your `PATH` environment variable to point to the **bin** folder of your Maven installation.

#### PostgreSQL

You must download and install PostgreSQL (the latest version *should* work). You can find PostgreSQL downloads [here](https://www.postgresql.org/download/). When prompted, set the password to "password". You can set it to whatever you want, but the `dev` Spring profile I've set up assumes the password is "password".

##### pgAdmin

Optionally, you can install pgAdmin - a free GUI for PostgreSQL. You can find downloads [here](https://www.pgadmin.org/download/).

##### Create the database

However you decide to interface with your local PostgreSQL server (E.G. pgAdmin), you will need to create the `pet_adoption_db` database. Just use the defaults and you should be fine.

#### Environment variables

You will need to set the following environment variables:

| Variable   | Value |
| ---------- | ----- |
| JWT_SECRET | cat   |

### Visual Studio Code

I use Visual Studio Code to develop locally (as opposed to STS). I've found that the following Visual Studio Code extensions are helpful:

- Extension Pack for Java
- Spring Boot Extension Pack
- XML

### Running the app

You will need to download the dependencies defined in the `pom.xml` file before you can run the app. If you're using Visual Studio Code and installed the extensions listed above, this should happen automatically for you. Otherwise, you can install dependencies manually by running `mvn dependency:resolve`.

Simply run the app as a Spring Boot application with the `dev` Spring profile. Use whatever IDE tool you want to help you with this, or simply run it with the mvn CLI: `mvn spring-boot:run -Dspring-boot.run.profiles=dev -Dspring-boot.run.fork=false`. Note, the `-Dspring-boot.run.fork=false` addition is because I develop on Windows and I run into this issue described [here](https://github.com/spring-projects/spring-boot/issues/17766).
