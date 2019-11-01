FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/spring-boot-docker-0.0.1.jar app.jar
RUN sh -c 'touch /app.jar'
CMD ["java","-jar","app.jar"]