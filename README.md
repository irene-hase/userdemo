# Project de.heidelberg/userdemo

Steps to run this project:

1. Start your Docker daemon
2. Execute `./buildAndRun.sh` (Linux/MacOs) or `buildAndRun.bat` (Windows)
3. Wait until Open Liberty is up- and running (e.g. use `docker logs -f CONTAINER_ID`)
4. OR In the $WILDFLY_HOME/bin directory, run the following command ./standalone.sh to start WildFly. (Here I use http://localhost:8080/userdemo/login.xhtml)
5. Visit http://localhost:9080/ OR http://localhost:8100/userdemo/login.xhtml
