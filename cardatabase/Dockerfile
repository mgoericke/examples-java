FROM maven:3.5.2-jdk-8-alpine AS MAVEN_TOOL_CHAIN
COPY pom.xml /tmp/
COPY src /tmp/src/

WORKDIR /tmp/

RUN mvn package

FROM openjdk:8u212-jdk-alpine
COPY --from=MAVEN_TOOL_CHAIN /tmp/target/cardatabase*.jar app.jar

HEALTHCHECK --interval=1m --timeout=5s --start-period=5m CMD curl -f http://localhost:8081/health || exit 1

CMD ["java", "-jar", "app.jar"]