FROM maven:3.9.4-amazoncorretto-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:22-jdk-slim-buster

WORKDIR /app

COPY --from=build /app/target/quarkus-app/lib/ /app/lib/
COPY --from=build /app/target/quarkus-app/*.jar /app/
COPY --from=build /app/target/quarkus-app/app/ /app/app/
COPY --from=build /app/target/quarkus-app/quarkus/ /app/quarkus/
COPY src/main/resources/test-ecommerce-a80ea-firebase-adminsdk-fbsvc-66b560f708.json /app/src/main/resources/

EXPOSE 8080

CMD ["java", "-jar", "quarkus-run.jar"]