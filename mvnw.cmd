@echo off
setlocal enabledelayedexpansion
set "WRAPPER_JAR=%~dp0.mvn\wrapper\maven-wrapper.jar"
set "MAVEN_PROJECTBASEDIR=%~dp0"

if not exist "%WRAPPER_JAR%" (
    echo Maven Wrapper JAR not found. Download from:
    echo https://repo.maven.apache.org/maven2/org/apache/maven/wrapper/maven-wrapper/3.2.0/maven-wrapper-3.2.0.jar
    exit /b 1
)

java -cp "%WRAPPER_JAR%" org.apache.maven.wrapper.MavenWrapperMain %*
