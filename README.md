# pet-adoption
A way to adopt pets

## Local development

Follow the guide below to get started.

### Prerequisites

Before running the app, you will need to set up a few things.

#### Java

You must download and install the Java JDK (the latest version *should* work). You can find JDK downloads [here](https://www.oracle.com/java/technologies/downloads/). After installing, make sure to set your `JAVA_HOME` environment variable. It should be set as the path of the JDK folder.

#### Maven

I have included the Maven wrapper (`./mvnw`), so you shouldn't have to worry about installing Maven yourself.

If you don't want to use the Maven wrapper, you'll need to download and install Maven yourself (the latest version *should* work, but if you want to be precise, use the version of Maven defined in `./.mvn/wrapper/maven-wrapper.properties`). You can find Maven downloads [here](https://maven.apache.org/download.cgi). After downloading the archive/zip, you will need to install it. You can install it anywhere on your system as long as you update your `PATH` environment variable to point to the **bin** folder of your Maven installation.

#### PostgreSQL

You must download and install PostgreSQL (the latest version *should* work). You can find PostgreSQL downloads [here](https://www.postgresql.org/download/). When prompted, set the password to "password". You can set it to whatever you want, but the `dev` Spring profile I've set up assumes the password is "password".

##### pgAdmin

Optionally, you can install pgAdmin - a free GUI for PostgreSQL. You can find downloads [here](https://www.pgadmin.org/download/).

##### Create the database

However you decide to interface with your local PostgreSQL server (E.G. pgAdmin), you will need to create the `pet_adoption_db` database. Just use the defaults and you should be fine.

#### Setup your IDE

I use Visual Studio Code to develop locally (as opposed to STS). I've found that the following Visual Studio Code extensions are helpful:

- Extension Pack for Java (by Microsoft)
- Spring Boot Extension Pack (by VMWare)
- XML (by Red Hat)

#### Download dependencies Running the app

You will need to download the dependencies defined in the `pom.xml` file before you can run the app. If you're using Visual Studio Code and installed the extensions listed above, this should happen automatically for you. Otherwise, you can install dependencies manually by running `./mvnw dependency:resolve`.

### Running the app

Simply run the app as a Spring Boot application with the `dev` Spring profile. Use whatever IDE tool you want to help you with this. Note, I used to simply run `./mvnw spring-boot:run -Dspring-boot.run.profiles=dev -Dspring-boot.run.fork=false`; however `spring-boot.run.fork` was removed in spring boot 3. Now, I use the Spring Boot Dashboard extension in VSCode to run my app with the `dev` profile.

### Helpful tips

#### Formatting

Use `formatter-maven-plugin` to format code consistently. Run it via `./mvnw formatter:format`

### Docker

You can use Docker to run the app locally. A lot of the instructions above still apply (E.G. installing Maven and pgAdmin); however, using Docker means that other developers (or future me) can come in and run the app without having to worry so much about installing the necessary software.

I mostly followed [this guide](https://spring.io/guides/gs/spring-boot-docker) to setup my Docker environment. Also shoutout to the [spring-boot-maven-plugin documentation](https://docs.spring.io/spring-boot/docs/2.3.0.RELEASE/maven-plugin/reference/html/#build-image). 

#### Prerequisites

In order to run the app with Docker, you will, of course, [need to install it](https://docs.docker.com/desktop/install/windows-install/).

#### Running the app with Docker

1. Build the application image by running `./mvnw verify`.
2. Run `docker compose up`
3. When you are finished testing your changes, run `docker compose down`

#### Current quirks/disadvantages

It is quite cumbersome to test changes with my current Docker setup. This is because the application's docker image has to be rebuilt every time a change is made.

Also, `spring-boot-maven-plugin` comes with a `build-image` goal that creates the docker image for me (hence no `Dockerfile`). As convenient as that is, it's difficult to customize.

The `build-image` goal is incredibly slow. If you don't want to use it while packaging the app (in favor of the more traditional `repackage` goal), run `./mvnw verify "-DskipBuildImage=true"`.

I have to use `restart: on-failure` in my `docker-compose.yaml` file for my `petadoption` service because it takes a bit of time for PostgreSQL to start accepting connections. Typically the app will fail to start the first time because it tried to establish a connection with PostgreSQL before it was ready.
