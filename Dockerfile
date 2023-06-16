FROM openjdk:8-alpine
ADD build/libs/users-0.0.1-SNAPSHOT.jar /usr/share/users-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["/usr/bin/java", "-jar", "/usr/share/users-0.0.1-SNAPSHOT.jar"]