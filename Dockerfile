FROM eclipse-temurin:17-jre-focal

VOLUME /tmp

ENV TZ=Asia/Tehran

RUN  mkdir -p /var/log/avatar-api
RUN  chmod -R 777 /var/log/avatar-api

COPY target/*.jar avatar-api-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-Xdebug","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:1519","-jar","/avatar-api-0.0.1-SNAPSHOT.jar"]