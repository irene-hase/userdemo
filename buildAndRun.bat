@echo off
call mvn clean package
call docker build -t de.heidelberg/userdemo .
call docker rm -f userdemo
call docker run -d -p 9080:9080 -p 9443:9443 --name userdemo de.heidelberg/userdemo