FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD target/decisionjournal-*.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar -Dspring.profiles.active=dev  /app.jar" ]
EXPOSE 3030