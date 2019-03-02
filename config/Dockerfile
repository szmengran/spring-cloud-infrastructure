FROM openjdk:8-jre-alpine
MAINTAINER Joe <android_li@sina.cn>

ENV JAR_FILE config.jar

#Set Beijing time zone
RUN echo 'Asia/Shanghai' >/etc/timezone 

ADD ./target/$JAR_FILE /app/
CMD java -Xmx200m -jar /app/$JAR_FILE

HEALTHCHECK --interval=30s --timeout=30s CMD curl -f http://localhost:10001/actuator/health || exit 1

EXPOSE 10001