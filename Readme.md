# bankless


## Commands

```
// run local
./mvnw clean compile test
./mvnw spring-boot:run

// run .jar file
./mvnw package
java -jar target/bankless-java-0.1.0.jar

// format code
./mvnw spring-javaformat:apply
```


### HTTP-API

Base URL: `http://localhost:8080`. Endpoints described in:
- `postman/bankless-java.postman_collection`
- `swagger/bankless-java.yaml`

## References

* [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/2.1.6.RELEASE/reference/html/index.html)
* [JaCoCo Java + Maven Example](https://www.mkyong.com/maven/jacoco-java-code-coverage-maven-example/)
* [Lombok Features](https://projectlombok.org/features/all)
* [Code formatting](https://github.com/spring-io/spring-javaformat)
* [Swagger editor](https://editor.swagger.io/)