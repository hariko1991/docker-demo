FROM openjdk:8u102-jdk
MAINTAINER hariko1991@163.com
ADD @docker.jar@ docker-demo-1.0.jar
ENTRYPOINT   ["java", "-jar", "-Duser.timezone=GMT+8", "-Dfile.encoding=UTF-8", "/docker-demo-1.0.jar"]