# SensorThings-SweCommon  [![Build Status](https://github.com/FraunhoferIOSB/SensorThings-SweCommon/workflows/Maven%20Build/badge.svg)](https://github.com/FraunhoferIOSB/SensorThings-SweCommon/actions)

A set of classes for dealing with the SWE-Common encoded parts of the SensorThings API, such as `TaskingProfile/taskingParameters` and `Task/taskingParameters`.
It also has a GUI built-in to create valid json for both `TaskingProfile/taskingParameters` and `Task/taskingParameters`.

## Download

A pre-compiled jar can be found in Maven Central:
[SensorThings-SWE-Common-0.6.jar](https://repo1.maven.org/maven2/de/fraunhofer/iosb/ilt/SensorThings-SWE-Common/0.6/SensorThings-SWE-Common-0.6.jar)
Download and double-click.

## Build
```
mvn install
```

## Using with maven

Add the dependency:
```xml
<dependency>
    <groupId>de.fraunhofer.iosb.ilt</groupId>
    <artifactId>SensorThings-SWE-Common</artifactId>
    <version>0.6</version>
</dependency>

```

## Using with gradle

Add the dependency:
```gradle
compile 'de.fraunhofer.iosb.ilt:SensorThings-SWE-Common:0.5'
```
