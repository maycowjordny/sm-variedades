FROM amazoncorretto:21 AS build
WORKDIR /app

COPY build.gradle settings.gradle ./
COPY gradle gradle
COPY gradlew .
COPY src src

RUN chmod +x ./gradlew
RUN ./gradlew build -x test

FROM amazoncorretto:21
VOLUME /tmp

COPY --from=build /app/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]
EXPOSE 8080
