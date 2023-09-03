FROM adoptopenjdk:8
VOLUME [ "/tmp" ]
ADD /target/filereader-vqui.de-0.0.2-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT [ "java", "-Djava.security.egd=file:/dev/urandom", "-jar", "app.jar" ]
