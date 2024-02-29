FROM openjdk:8-jre-slim
WORKDIR /app
VOLUME /tmp
COPY target/*.jar E-learning.jar
ENTRYPOINT ["java","-jar","/E-learning.jar"]
EXPOSE 8080
