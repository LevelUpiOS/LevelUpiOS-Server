FROM gradle:latest AS builder
WORKDIR /

COPY build.gradle.kts settings.gradle.kts ./
RUN gradle build -x test --no-daemon || true

COPY src src
RUN gradle build -x test

FROM azul/zulu-openjdk-alpine:17
COPY --from=builder /build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]