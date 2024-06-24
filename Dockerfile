FROM openjdk:21
WORKDIR /app
COPY /target/credit-card-management-system-0.0.1-SNAPSHOT.jar /app/app.jar
ENTRYPOINT ["java","-jar","app.jar"]