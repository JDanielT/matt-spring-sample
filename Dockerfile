FROM maven:3.8.4-openjdk-17-slim

WORKDIR /app

COPY pom.xml .

COPY src ./src

RUN mvn clean install -DskipTests

COPY target/spring-sample-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]