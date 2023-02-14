#!/bin/sh
if [ $(docker ps -a -f name=userdemo | grep -w userdemo | wc -l) -eq 1 ]; then
  docker rm -f userdemo
fi
mvn clean package && docker build -t de.heidelberg/userdemo .
docker run -d -p 9080:9080 -p 9443:9443 --name userdemo de.heidelberg/userdemo
