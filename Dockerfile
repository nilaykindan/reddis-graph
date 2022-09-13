FROM maven:3.6.3-openjdk-8 AS builder
COPY pom.xml /
COPY src ./src
COPY agentlib/elastic-apm-agent-1.28.4.jar /apm-agent.jar
RUN mvn clean install
FROM openjdk:8

ENV NAME=dynamic-device-updater
ENV VERSION=1.0.0-SNAPSHOT
ENV APM_URL=http://
COPY --from=builder /target/$NAME-$VERSION.jar /
COPY .env /
COPY logback.xml /

CMD ["sh", "-c", "java","-javaagent:apm-agent.jar", \
"-Delastic.apm.service_name=dynamic-device-updater -Delastic.apm.application_packages=main.java \
-Delastic.apm.server_urls=$APM_URL","-jar", "/$NAME-$VERSION.jar $ID"]
