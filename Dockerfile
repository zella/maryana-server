FROM openjdk:10

HEALTHCHECK CMD curl --fail http://localhost:$HTTP_PORT/status || exit 1

ADD target/scala-2.12/maryana-server-assembly-0.1.0-SNAPSHOT.jar /app/maryana-server-assembly-0.1.0-SNAPSHOT.jar

ENTRYPOINT java $JAVA_OPTS -jar /app/maryana-server-assembly-0.1.0-SNAPSHOT.jar