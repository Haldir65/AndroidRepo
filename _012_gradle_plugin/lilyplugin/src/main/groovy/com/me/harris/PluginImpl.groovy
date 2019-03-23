package com.me.harris
import org.gradle.api.Plugin
import org.gradle.api.Project
import com.android.build.gradle.api.TestVariant
import com.android.build.gradle.api.UnitTestVariant
import com.android.build.gradle.internal.variant.ApplicationVariantData
import com.android.build.gradle.internal.api.ApplicationVariantImpl

public class PluginImpl implements Plugin<Project> {
    void apply(Project project) {
        project.task('testTask').doLast{
            println "Hello gradle plugin"
        }

        project.afterEvaluate {
            project.logger.error "afterEvaluated"
            if (project.plugins.hasPlugin("com.android.application")) {
                def android = project.extensions.getByName("android")
                android.applicationVariants.all {ApplicationVariantImpl variant ->
                    project.logger.error "DebuggerPlugin:${variant}"
                    ApplicationVariantData apkVariantData = variant.getProperty('variantData')
                    ApplicationVariantData variantData = variant.getVariantData()
                    TestVariant testVariant = variant.getTestVariant()
                    UnitTestVariant unitTestVariant = variant.getUnitTestVariant()
                }
            }
        }
    }
}
