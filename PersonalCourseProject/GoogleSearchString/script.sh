#!/usr/bin/env bash

JAVA='java'
CLASSPATH="target/GoogleSearchString-1.0-SNAPSHOT-jar-with-dependencies.jar"
JAVA_CLASS="com.axway.googleSearch.api.Google"
JAVA_ARGS="-cp $CLASSPATH"

USER=$(whoami)
GREETINGS="Welcome"
DAY=$(date +%A)

GOOGLE_STRING=$1
PAGES=$2

echo "$GREETINGS back $USER! Today is $DAY, which is the best day of the entire week!"
echo "Your Bash shell version is: $BASH_VERSION. Enjoy!"

sleep 5
mvn compile
mvn package
mvn install assembly:single
clear

$JAVA $JAVA_ARGS $JAVA_CLASS ${GOOGLE_STRING} ${PAGES}