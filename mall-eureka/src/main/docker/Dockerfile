FROM docker.io/relateiq/oracle-java8
ADD ["mall-eureka.jar","web.jar"]
EXPOSE 8761
ENTRYPOINT ["java","-jar","/web.jar"]