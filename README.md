[![Release](https://jitpack.io/v/Mindera/gradle-versioncode-plugin.svg)](https://jitpack.io/#Mindera/gradle-versioncode-plugin)

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
        classpath 'com.github.Mindera:gradle-versioncode-plugin:1.3'
    }
}
```

Apply it:

```groovy
apply plugin: 'com.mindera.gradle.versioncode'
```

If you are using android flavors, this will generate a task for each combination of flavor and build type that are not debuggable.

For instance, if you have:

```groovy
productFlavors {
        pro {
            applicationId = "com.example.my.pkg.pro"
        }
        free {
            applicationId = "com.example.my.pkg.free"
        }
    }
```

This plugin will generate the tasks:

```groovy
incrementVersionCodeProRelease
incrementVersionCodeFreeRelease
```

Note: When using flavors, if you set the **appVersionCode.appId** it will be used for ervery flavor, if not it will revert back to the value in  **applicationId**.

### Tasks Configuration

```groovy
appVersionCode {
    appId = <app identifier> (optional if you are using flavors)
    serviceEndpoint = <version code service endpoint>
}
```

### Increment App's Version Code

Use the task 'incrementVersionCode'. For instance, in the command line type:

```sh
$ ./gradlew incrementVersionCode
```
If you're using android flavors, you could run:

```sh
$ ./gradlew incrementVersionCodeProRelease
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
