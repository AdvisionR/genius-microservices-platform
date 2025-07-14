# ------------------------------------------------
# 🔧 Build stage
# ------------------------------------------------
FROM maven:3.9.9-eclipse-temurin-21-alpine AS build
WORKDIR /app

COPY . .
RUN mvn -pl api-gateway -am clean package -DskipTests

# ------------------------------------------------
# 🐛 Development stage (with debug)
# ------------------------------------------------
FROM amazoncorretto:21-alpine AS dev
WORKDIR /app
COPY --from=build /app/api-gateway/target/api-gateway-*.jar app.jar
EXPOSE 8889
EXPOSE 5007
ENV JVM_OPTS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005"
ENV SPRING_PROFILES_ACTIVE=dev
ENTRYPOINT ["sh", "-c", "java $JVM_OPTS -jar app.jar"]

# ------------------------------------------------
# 🚀 Production stage
# ------------------------------------------------
FROM amazoncorretto:21-alpine AS prod
WORKDIR /app
COPY --from=build /app/api-gateway/target/api-gateway-*.jar app.jar
COPY ../../opentelemetry-javaagent.jar /app/opentelemetry-javaagent.jar
EXPOSE 8889
ENV SPRING_PROFILES_ACTIVE=prod
ENTRYPOINT ["java", "-javaagent:/app/opentelemetry-javaagent.jar", "-Dotel.exporter.otlp.endpoint=http://otel-collector:4317", "-Dotel.resource.attributes=service.name=api-gateway", "-jar", "app.jar"]
