FROM openjdk:11

ADD target/spring-argo-backend.jar spring-argo-backend.jar

ENTRYPOINT ["java", "-jar", "/spring-argo-backend.jar"]

EXPOSE 8080