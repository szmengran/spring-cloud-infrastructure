FROM java:8-jre
MAINTAINER Joe <android_li@sina.cn>

ENV JAR_FILE config.jar

ADD ./target/$JAR_FILE /app/
CMD java -Xmx200m -jar /app/$JAR_FILE

EXPOSE 10001