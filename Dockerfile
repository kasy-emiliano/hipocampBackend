FROM maven:3.5.8-openjdk:8-jre-slim as build
COPY . .
RUN mvn clean package -DskipTests
FROM openjdk:8-jre-slim
COPY --from=build/target/E-learning-0.0.1-SNAPSHOT.jar E-learning.jar

ENTRYPOINT ["java","-jar","/E-learning.jar"]
EXPOSE 8080
