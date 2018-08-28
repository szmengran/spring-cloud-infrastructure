FROM java:8-jre
MAINTAINER Joe <android_li@sina.cn>

ENV JAR_FILE cloud-security-oauth-0.0.1.jar

ADD ./target/$JAR_FILE /app/
CMD java -Xmx200m -jar /app/$JAR_FILE

EXPOSE 10002