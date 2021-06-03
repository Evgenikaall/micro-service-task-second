FROM maven:3.5-jdk-8 AS build

COPY src src
COPY pom.xml .

EXPOSE 8081

RUN mvn clean
RUN mvn install


ENTRYPOINT ["mvn", "spring-boot:run"]