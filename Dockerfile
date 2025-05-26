FROM maven:3.9.4-amazoncorretto-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:22-jdk-lim-buster

WORKDIR /app

COPY --from=build /app/target/quarkus-app/lib/ app/lib/

EXPOSE 8080

CMD ["java", "-jar", "wuarkus-run.jar"]