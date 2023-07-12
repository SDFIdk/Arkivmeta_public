FROM maven:3.9.3-eclipse-temurin-17-alpine as builder

WORKDIR /arkivmeta

COPY pom.xml .

RUN mvn -e -B dependency:resolve

COPY src ./src

RUN mvn -e -B package

FROM eclipse-temurin:17.0.7_7-jdk-jammy

RUN apt update -y && apt upgrade -y

RUN mkdir -p src/main/resources

COPY --from=builder /arkivmeta/target/arkivmeta.jar /arkivmeta.jar