package com.mindera.gradle.versioncode

import com.mindera.gradle.versioncode.utils.VersionCodeService
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

/**
 * Created by renatoalmeida on 23/03/16.
 */
class IncrementVersionCodeTask extends DefaultTask {

    String serviceEndpoint

    String appId

    boolean enabled

    @TaskAction
    incrementVersionCode() {

        println("Incrementing '" + appId + "'")

        def VersionCodeService versionCodeService = new VersionCodeService(
                serviceEndpoint,
                appId,
                enabled)

        println(appId + ' -> ' + versionCodeService.incrementVersionCode())
    }
}
