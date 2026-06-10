FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /workspace

COPY pom.xml .
RUN mvn -B dependency:go-offline

COPY src ./src
RUN mvn -B clean package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app

ENV TZ=Asia/Shanghai
COPY --from=build /workspace/target/*.jar app.jar

EXPOSE 7070
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
