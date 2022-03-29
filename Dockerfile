FROM gradle:7.4.1-jdk17-alpine

WORKDIR /home/gradle/src

COPY api api
COPY domain domain
COPY settings.gradle settings.gradle
copy config config

RUN gradle build --no-daemon

CMD ["gradle", "run"]
