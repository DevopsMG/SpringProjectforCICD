FROM centos:7

COPY target/ebstack-0.0.1-SNAPSHOT.jar /root/ebstack-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["/usr/bin/java"]

#ENV testvar=localhost

EXPOSE 8085

