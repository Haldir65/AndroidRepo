package com.me.harris
import org.gradle.api.Plugin
import org.gradle.api.Project
import com.android.build.gradle.api.TestVariant
import com.android.build.gradle.api.UnitTestVariant
import com.android.build.gradle.internal.variant.ApplicationVariantData
import com.android.build.gradle.internal.api.ApplicationVariantImpl

import org.aspectj.bridge.IMessage
import org.aspectj.bridge.MessageHandler
import org.aspectj.tools.ajc.Main
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.compile.JavaCompile

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

                    JavaCompile javaCompile = variant.javaCompile

                    javaCompile.doLast {
                        String[] args = ["-showWeaveInfo",
                                         "-1.5",
                                         "-inpath", javaCompile.destinationDir.toString(),
                                         "-aspectpath", javaCompile.classpath.asPath,
                                         "-d", javaCompile.destinationDir.toString(),
                                         "-classpath", javaCompile.classpath.asPath,
                                         "-bootclasspath", project.android.bootClasspath.join(
                                File.pathSeparator)]
                        MessageHandler handler = new MessageHandler(true);
                        new Main().run(args, handler)

                        def log = project.logger
                        for (IMessage message : handler.getMessages(null, true)) {
                            switch (message.getKind()) {
                                case IMessage.ABORT:
                                case IMessage.ERROR:
                                case IMessage.FAIL:
                                    log.error message.message, message.thrown
                                    break
                                case IMessage.WARNING:
                                case IMessage.INFO:
                                    log.info message.message, message.thrown
                                    break;
                                case IMessage.DEBUG:
                                    log.debug message.message, message.thrown
                                    break;
                            }
                        }
                        project.logger.error "DebuggerPlugin: javaCompile ended"

                    }



                    ApplicationVariantData apkVariantData = variant.getProperty('variantData')
                    ApplicationVariantData variantData = variant.getVariantData()
                    TestVariant testVariant = variant.getTestVariant()
                    UnitTestVariant unitTestVariant = variant.getUnitTestVariant()
                }
            }
        }
    }
}
