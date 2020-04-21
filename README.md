# SensorThings-SweCommon

A set of classes for dealing with the SWE-Common encoded parts of the SensorThings API, such as `TaskingProfile/taskingParameters` and `Task/taskingParameters`.
It also has a GUI built-in to create valid json for both `TaskingProfile/taskingParameters` and `Task/taskingParameters`.

## Download

A pre-compiled jar can be found in our BinTray repository:
[SensorThings-SWE-Common-0.4.jar](https://bintray.com/fraunhoferiosb/Maven/download_file?file_path=de%2Ffraunhofer%2Fiosb%2Filt%2FSensorThings-SWE-Common%2F0.4%2FSensorThings-SWE-Common-0.4.jar)
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
    <version>0.5</version>
</dependency>

```

If you do not yet have the FraunhoferIOSB bintray repository, add:
```xml
<repositories>
    <repository>
        <id>bintray-fraunhoferiosb-Maven</id>
        <url>https://dl.bintray.com/fraunhoferiosb/Maven</url>
        <releases>
            <enabled>true</enabled>
        </releases>
    </repository>
</repositories>
```

## Using with gradle

Add the dependency:
```gradle
compile 'de.fraunhofer.iosb.ilt:SensorThings-SWE-Common:0.5'
```

If you do not yet have the FraunhoferIOSB bintray repository, add:
```gradle
repositories {
    maven {
        url  "https://dl.bintray.com/fraunhoferiosb/Maven"
    }
}
```
