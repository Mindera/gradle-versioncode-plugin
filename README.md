# gradle-versioncode-plugin

Gradle plugin to manage an application version code inside its continuous integration lifecycle.

This plugin was developed for the REST service: [version-code-service].

## Usage
### Setup buildscript Dependencies

The plugin is available in [JitPack](https://jitpack.io/). Just add the following to your buildscript dependencies:

```groovy
buildscript {

    repositories {
    	....
        maven {
            url "https://jitpack.io"
        }
    }
    
    dependencies {
    	...
        classpath 'com.github.Mindera:gradle-versioncode-plugin::1.1'
    }
}
```

Apply it:

```groovy
apply plugin: 'com.mindera.gradle.versioncode'
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
$ ./gradlew incrementVersionCode
```

### Retrieve App's Current Version Code

```groovy
import com.mindera.gradle.versioncode.utils.VersionCodeService
apply plugin: 'com.mindera.gradle.versioncode'
(...)
VersionCodeService versionCodeService = new VersionCodeService(<version code service endpoint>, <app identifier>)
return versionCodeService.currentVersionCode()
```

## Dependencies
  - [version-code-service]

## License
version-code-service is available under the MIT license. See the LICENSE file for more info.

[version-code-service]: https://github.com/Mindera/version-code-service
