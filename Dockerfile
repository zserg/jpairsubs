FROM openjdk:12-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
# ENTRYPOINT ["ls", "-l", "app/lib-ext"]
ENTRYPOINT ["java","-cp","conf:app:app/lib/*:app/lib-ext/*","com.zserg.jpairsubs.JpairsubsApplication"]

