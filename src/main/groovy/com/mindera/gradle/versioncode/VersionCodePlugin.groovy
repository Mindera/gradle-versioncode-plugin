package com.mindera.gradle.versioncode

import org.gradle.api.Plugin
import org.gradle.api.Project
import com.mindera.gradle.versioncode.utils.VersionCodeService

/**
*  Created by ricardovieira on 05/05/15.
*/
class VersionCodePlugin implements Plugin<Project> {

    void apply(Project project) {

        project.extensions.create("appVersionCode", VersionCodePluginExtension)

        project.task('incrementVersionCode') << {

            def VersionCodeService versionCodeService = new VersionCodeService(
                    (String) project.appVersionCode.serviceEndpoint,
                    (String) project.appVersionCode.appId)

            println(versionCodeService.incrementVersionCode())
        }
    }
}

class VersionCodePluginExtension {
    def String appId
    def String serviceEndpoint
}
