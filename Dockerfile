FROM openjdk:8-jre-slim
COPY target/*.jar E-learning.jar
ENTRYPOINT ["java","-jar","/E-learning.jar"]
EXPOSE 8080
