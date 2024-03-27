FROM gradle:jdk17 AS builder

WORKDIR /Testtask

COPY build.gradle .
COPY settings.gradle .
COPY src ./src

RUN gradle build -x test

FROM eclipse-temurin:17-jdk

WORKDIR /Testtask

COPY --from=builder /Testtask/build/libs/Testtask-0.0.1-SNAPSHOT.jar .

CMD ["java", "-jar", "Testtask-0.0.1-SNAPSHOT.jar"]