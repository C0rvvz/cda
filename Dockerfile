FROM openjdk:21-slim
WORKDIR /opt
ENV PORT=8080
ENV SPRING_PROFILES_ACTIVE=test
EXPOSE 8080
COPY target/*.jar /opt/app.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=test", "-jar", "app.jar"]