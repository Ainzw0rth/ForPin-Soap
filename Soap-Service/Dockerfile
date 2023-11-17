FROM maven:3.6.3-amazoncorretto-8
WORKDIR /java-app
COPY . .
RUN mvn clean compile assembly:single
CMD ["java", "-jar", "target/soap-1.0-SNAPSHOT-jar-with-dependencies.jar"]
