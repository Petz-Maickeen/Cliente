FROM adoptopenjdk/openjdk11:latest

COPY target/Cliente-0.0.1-SNAPSHOT.jar Cliente-1.0.0.jar

ENTRYPOINT ["java","-jar","/Cliente-1.0.0.jar"]