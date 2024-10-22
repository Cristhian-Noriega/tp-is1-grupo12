#
# Build stage
#
FROM maven:3.8.3-openjdk-17 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Run stage
#
FROM openjdk:17
COPY --from=build /home/app/target/order-app-0.0.1-SNAPSHOT.jar /usr/local/lib/order-app-0.0.1-SNAPSHOT.jar
EXPOSE 8080
USER 10014
ENTRYPOINT ["java","-jar","/usr/local/lib/order-app-0.0.1-SNAPSHOT.jar"]