FROM adoptopenjdk/openjdk11:latest
VOLUME /tmp
ADD WebArchive-0.0.1-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
# unclear: java.security.egd