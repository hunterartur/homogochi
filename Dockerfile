FROM maven:3.9.3-amazoncorretto-17 as builder
WORKDIR /app
COPY . /app/.
RUN mvn -f /app/pom.xml package -Dmaven.test.skip=true -Dos.detected.classifier=linux-x86_64

FROM amazoncorretto:17-al2-native-jdk
WORKDIR /app
COPY --from=builder /app/target/*.jar /app/core.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","core.jar"]
