FROM openjdk:latest
COPY ./target/Team8.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "Team8.jar", "db: 3306", "30000"]