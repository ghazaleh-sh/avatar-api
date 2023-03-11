FROM adoptopenjdk/openjdk11

VOLUME /tmp

ENV TZ=Asia/Tehran

RUN  mkdir -p /var/log/remote-config
RUN  chmod -R 777 /var/log/remote-config

COPY target/*.jar remote-config-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-Xdebug","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:1518","-jar","/avatar-api-0.0.1-SNAPSHOT.jar"]