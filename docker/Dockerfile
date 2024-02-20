FROM maven:3.9.6-eclipse-temurin-21-alpine as builder

RUN apk upgrade --available --no-cache

WORKDIR /arkivmeta

COPY pom.xml .

RUN mvn -e -B dependency:resolve

COPY src ./src

RUN mvn -e -B package

FROM eclipse-temurin:21.0.2_13-jdk-alpine

RUN apk upgrade --available --no-cache

RUN mkdir -p src/main/resources

COPY --from=builder /arkivmeta/target/arkivmeta.jar /arkivmeta.jar