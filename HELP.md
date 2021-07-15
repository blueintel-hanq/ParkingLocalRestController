# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.5.2/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.5.2/gradle-plugin/reference/html/#build-image)

### Additional Links

These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)
## liquibase
### gradlew 명령어
#### liquibase 실행 
gradlew update 
#### liquibase rollback tag
gradlew rollback -PliquibaseCommandValue=ver_0.0.0
#### liquibase document
gradlew dbDoc -PliquibaseCommandValue=dbDoc
