# SensorThings-SweCommon

A set of classes for dealing with the SWE-Common encoded parts of the SensorThings API, such as `TaskingProfile/taskingParameters` and `Task/taskingParameters`.
It also has a GUI built-in to create valid json for both `TaskingProfile/taskingParameters` and `Task/taskingParameters`.

## Download

A pre-compiled jar can be found in our BinTray repository: [SensorThings-SWE-Common-0.2-jar-with-dependencies.jar](https://bintray.com/fraunhoferiosb/Maven/download_file?file_path=de%2Ffraunhofer%2Fiosb%2Filt%2FSensorThings-SWE-Common%2F0.2%2FSensorThings-SWE-Common-0.2-jar-with-dependencies.jar)
Download and double-click.

## Build
```
mvn install
java -jar target/SensorThings-SWE-Common-0.2-SNAPSHOT-jar-with-dependencies.jar
```

## Using with maven

Add the dependency:
```xml
<dependency>
    <groupId>de.fraunhofer.iosb.ilt</groupId>
    <artifactId>SensorThings-SWE-Common</artifactId>
    <version>0.2</version>
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
compile 'de.fraunhofer.iosb.ilt:SensorThings-SWE-Common:0.2'
```

If you do not yet have the FraunhoferIOSB bintray repository, add:
```gradle
repositories {
    maven {
        url  "https://dl.bintray.com/fraunhoferiosb/Maven"
    }
}
```
