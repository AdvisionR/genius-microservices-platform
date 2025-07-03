# ------------------------------------------------
# 🔧 Build stage
# ------------------------------------------------
FROM maven:3.9.9-eclipse-temurin-21-alpine AS build
WORKDIR /app

COPY . .
RUN mvn -pl user-service -am clean package -DskipTests


# ------------------------------------------------
# 🐛 Development stage (with debug)
# ------------------------------------------------
FROM amazoncorretto:21-alpine AS dev
WORKDIR /app
COPY --from=build /app/user-service/target/user-service-*.jar app.jar
EXPOSE 9091
EXPOSE 5006
ENV JVM_OPTS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5006"
ENV SPRING_PROFILES_ACTIVE=dev
ENTRYPOINT ["sh", "-c", "java $JVM_OPTS -jar app.jar"]

# ------------------------------------------------
# 🚀 Production stage
# ------------------------------------------------
FROM amazoncorretto:21-alpine AS prod
WORKDIR /app
COPY --from=build /app/user-service/target/user-service-*.jar app.jar
EXPOSE 9091
ENV SPRING_PROFILES_ACTIVE=prod
ENTRYPOINT ["java", "-jar", "app.jar"]
