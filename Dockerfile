FROM openjdk:latest
EXPOSE 8080
WORKDIR /isr/local/bin/
COPY ./target/KafkaGroupStudy-0.0.1-SNAPSHOT.jar webapp.jar
CMD ["java","-Dspring.profiles.active=dodocker-demo","-jar","webapp.jar"]