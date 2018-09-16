FROM centos:7
RUN yum install -y java
COPY target/ebstack-0.0.1-SNAPSHOT.jar /root/ebstack-0.0.1-SNAPSHOT.jar
#ENV testvar=localhost
EXPOSE 8085
ENTRYPOINT ["/usr/bin/java"]
