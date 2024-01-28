FROM openjdk:latest
COPY ./target/DevOps_Team8-2.0-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "DevOps_Team8-2.0-jar-with-dependencies.jar"]