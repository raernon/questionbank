# Backend for QuestionBank

## Build
```
> mvn clean package
```
Or download questionbank-1.0.jar from Releases.

## Install
1. Create quiz database structure in mysql database:
```
> mysql <docs/create_database.sql
```

2. Write quiz questions and answers and upload it to the database:
```
> mysql <docs/sample_questions.sql
```

3. Configure the port and database connection of backend.

Place an `application.yaml` file next to the questionbank-1.0.jar
with the following sheme and edit its contents:
```yaml
server:
  port: 8090
  
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/questionbank
    username: root
    password: root
```

## Start
```
> java -jar questionbank-1.0.jar 
```

## Notes for frontend developers

You can reach the OpenAPI documentation of this backend on the following url if the application runs:
`http://host:port/swagger-ui.html`
