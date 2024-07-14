# FROM maven:3.8.5-openjdk-17 AS build
# COPY . .
# RUN mvn clean package -DskipTests

# FROM openjdk:17.0.1-jdk-slim
# COPY --from=build /target/marriage-0.0.1-SNAPSHOT.jar marriage.jar
# EXPOSE 8080
# ENTRYPOINT ["java","-jar","marriage.jar"]


# مرحلة البناء
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# مرحلة التشغيل
FROM openjdk:17.0.1-jdk-slim
WORKDIR /app
COPY --from=build /app/target/marriage-0.0.1-SNAPSHOT.jar marriage.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","marriage.jar"]
