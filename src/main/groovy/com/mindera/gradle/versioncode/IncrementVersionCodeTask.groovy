package com.mindera.gradle.versioncode

import com.android.build.gradle.api.ApplicationVariant
import com.mindera.gradle.versioncode.utils.VersionCodeService
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

/**
 * Created by renatoalmeida on 23/03/16.
 */
class IncrementVersionCodeTask extends DefaultTask {

    VersionCodePluginExtension extension;

    ApplicationVariant variant;

    @TaskAction
    incrementVersionCode() {

        def appId = variant.applicationId

        println("Incrementing '" + appId + "'")

        def VersionCodeService versionCodeService = new VersionCodeService(
                (String) extension.serviceEndpoint,
                (String) appId,
                (boolean) extension.enabled)

        println(appId + ' -> ' + versionCodeService.incrementVersionCode())
    }
}
