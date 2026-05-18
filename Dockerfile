FROM eclipse-temurin:21-jdk-alpine AS builder
WORKDIR /app
COPY .mvn/ .mvn/
COPY mvnw.cmd pom.xml ./
COPY src src/
RUN java -cp ".mvn/wrapper/maven-wrapper.jar" org.apache.maven.wrapper.MavenWrapperMain clean package -DskipTests || true

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
