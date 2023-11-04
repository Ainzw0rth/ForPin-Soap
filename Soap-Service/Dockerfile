FROM maven:3.6.3-jdk-11
WORKDIR /java-app
COPY . .
RUN mvn clean compile assembly:single
EXPOSE 1234
CMD ["java", "-jar", "target/soap-1.0-SNAPSHOT-jar-with-dependencies.jar"]
