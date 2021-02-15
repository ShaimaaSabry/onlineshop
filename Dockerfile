FROM maven:3-jdk-11 AS build
COPY . /app
WORKDIR /app
RUN mvn clean package

FROM openjdk:11-jre-slim
COPY --from=build app/target/customer-0.0.1-SNAPSHOT.jar app.jar
COPY --from=build app/sample.db sample.db
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]