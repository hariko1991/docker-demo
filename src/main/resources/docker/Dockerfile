FROM openjdk:8u102-jdk
MAINTAINER hariko1991@163.com
COPY @docker.jar@ /@docker.jar@
ENV JAVA_OPTS="\
-server \
-Xmx1g \
-Xms1g \
-Xmn128m \
-XX:SurvivorRatio=8 \
-XX:MetaspaceSize=128m \
-XX:MaxMetaspaceSize=128m \
-XX:+UseParallelGC \
-XX:ParallelGCThreads=4 \
-XX:+UseParallelOldGC \
-XX:+UseAdaptiveSizePolicy \
-XX:+PrintGCDetails \
-XX:+PrintTenuringDistribution \
-XX:+PrintGCTimeStamps \
-XX:+HeapDumpOnOutOfMemoryError \
-XX:HeapDumpPath=/var/log/jvm/ \
-Xloggc:/var/log/jvm/gc.log \
-XX:+UseGCLogFileRotation \
-XX:NumberOfGCLogFiles=5 \
-XX:GCLogFileSize=10M"
#ENTRYPOINT   ["java", "-jar", "JAVA_OPTS=${JAVA_OPTS}", "-Duser.timezone=GMT+8", "-Dfile.encoding=UTF-8", "/@docker.jar@"]
ENTRYPOINT java ${JAVA_OPTS} -Duser.timezone=GMT+8 -Dfile.encoding=UTF-8 -jar /@docker.jar@