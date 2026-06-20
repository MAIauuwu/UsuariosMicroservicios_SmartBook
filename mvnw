#!/bin/sh
# Maven wrapper script for Unix
WRAPPER_JAR="$(dirname "$0")/.mvn/wrapper/maven-wrapper.jar"
if [ ! -f "$WRAPPER_JAR" ]; then
    echo "Maven Wrapper JAR not found."
    exit 1
fi
exec java -cp "$WRAPPER_JAR" org.apache.maven.wrapper.MavenWrapperMain "$@"
