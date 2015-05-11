# gradle-versioncode-plugin

Gradle plugin to manage an application version code inside its continuous integration lifecycle.

This plugin was developed for the REST service: [version-code-service].


## Usage

### Setup buildscript Dependencies

Note that it's not yet available in Maven Central, but soon will be.

Add it to your buildscript dependencies:

```groovy

buildscript {

    repositories {
        (...)

        maven {
	        // Temporary maven repository, until it is not available in Maven Central
            url uri('http://joaoprudencio.com/m2/repository')
        }
    }
    
    dependencies {
        classpath 'com.mindera.gradle.versioncode:gradle-versioncode-plugin:1.0'
    }
}
```

### Tasks Configuration

```groovy

appVersionCode {

    appId = <app identifier>
    serviceEndpoint = <version code service endpoint>
}
```

### Increment App's Version Code

Use the task 'incrementVersionCode'. For instance, in the command line type:

```sh
./gradlew incrementVersionCode
```

### Retrieve App's Current Version Code


```groovy
import com.mindera.gradle.versioncode.utils.VersionCodeService

apply plugin: 'com.mindera.gradle.versioncode'

VersionCodeService versionCodeService = new VersionCodeService(<version code service endpoint>, <app identifier>)

return versionCodeService.currentVersionCode()

```

## Dependencies
  - [version-code-service]

## License
version-code-service is available under the MIT license. See the LICENSE file for more info.

[version-code-service]: https://github.com/Mindera/version-code-service