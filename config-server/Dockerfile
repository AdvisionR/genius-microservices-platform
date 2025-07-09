# build stage
FROM maven:3.9.9-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# runtime stage
FROM amazoncorretto:21-alpine
WORKDIR /app
RUN apk add --no-cache curl
COPY --from=build /app/config-server/target/config-server-*.jar app.jar
COPY ../../opentelemetry-javaagent.jar /app/opentelemetry-javaagent.jar
EXPOSE 8888
ENTRYPOINT ["java", "-javaagent:/app/opentelemetry-javaagent.jar", "-Dotel.exporter.otlp.endpoint=http://otel-collector:4317", "-Dotel.resource.attributes=service.name=config-server", "-jar", "app.jar"]