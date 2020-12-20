FROM docker pull adoptopenjdk/openjdk8:x86_64-alpine-jre8u-nightly
EXPOSE 8080
ADD target/crud.jar crud.jar
ENTRYPOINT ["JAVA","-JAR","crud.jar"] 