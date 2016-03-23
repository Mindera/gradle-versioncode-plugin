package com.mindera.gradle.versioncode

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.ProjectConfigurationException

/**
 *  Created by ricardovieira on 05/05/15.
 */
class VersionCodePlugin implements Plugin<Project> {

    public static final String GRADLE_GROUP = "Increment Version Code"

    void apply(Project project) {

        def log = project.logger

        if (!hasAndroidPlugin(project)) {
            throw new ProjectConfigurationException(
                    "The 'com.android.application' plugin is required.", null)
        }

        def extension = project.extensions.create("appVersionCode", VersionCodePluginExtension)

        project.android.applicationVariants.all { variant ->

            if (variant.buildType.isDebuggable()) {
                log.debug("Skipping debuggable build type ${variant.buildType.name}.")
                return
            }

            def buildTypeName = variant.buildType.name.capitalize()
            println buildTypeName

            def productFlavorNames = variant.productFlavors.collect { it.name.capitalize() }
            if (productFlavorNames.isEmpty()) {
                productFlavorNames = [""]
            }
            def productFlavorName = productFlavorNames.join('')

            def variationName = "${productFlavorName}${buildTypeName}"

            def incrementVersionCodeTaskName = "incrementVersionCode${variationName}"

            def incrementVersionCodeTask = project.tasks.
                    create(incrementVersionCodeTaskName, IncrementVersionCodeTask)
            incrementVersionCodeTask.extension = extension
            incrementVersionCodeTask.variant = variant
            incrementVersionCodeTask.group = GRADLE_GROUP
        }
    }

    /**
     * Check if android plugin is applied
     * @param project Project
     * @return plugin applied
     */
    static boolean hasAndroidPlugin(Project project) {
        return project.plugins.hasPlugin("com.android.application")
    }
}

