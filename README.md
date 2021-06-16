## Spring Boot Project
Repository created to store course code from Alura. The main target here is to learn how to write a spring boot application.

### Access the project root directory
```console 
$ cd <path to the root directory>
```

### To run only the test by command line
```console
$ mvn test
```

### Generate the project artifact  
```console
$ mvn clean package
```

### Generate the Docker image
```console
$ docker build -t alura/forum .
```

### Verify whether docker image has been generated
```console
$docker images
```

### Run application through Docker image / container
```console
$ docker run -p 8080:8080 -e SPRING_PROFILES_ACTIVE=prod -e FORUM_DATABASE_URL='jdbc:h2:mem:alura-forum' -e FORUM_DATABASE_USERNAME='sa' -e FORUM_DATABASE_PASSWORD='' -e FORUM_JWT_SECRET='123456' alura/forum
```