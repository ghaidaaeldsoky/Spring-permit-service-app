# Development Image
#FROM maven:3.9.7-eclipse-temurin-17
#
#WORKDIR /app
#
## انسخ pom.xml الأول عشان dependencies تتحمل مرة واحدة
#COPY pom.xml .
#RUN mvn -q -DskipTests dependency:go-offline
#
## انسخ الكود
#COPY src ./src
#
## نشغل المشروع مباشرة بالـ Maven (spring-boot:run)
#CMD ["mvn", "spring-boot:run"]
#
#FROM openjdk:17-jdk-slim
#WORKDIR /app
#COPY ./target/permit-service-0.0.1-SNAPSHOT.jar app.jar
#EXPOSE 8080
#ENTRYPOINT ["java", "-jar", "app.jar"]


# Build stage
FROM maven:3.9.7-eclipse-temurin-17 AS build
WORKDIR /workspace/app
COPY pom.xml .
RUN mvn -q -DskipTests dependency:go-offline
COPY src ./src
RUN mvn -DskipTests package

# Runtime stage
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
RUN adduser -D appuser
USER appuser
COPY --from=build /workspace/app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]