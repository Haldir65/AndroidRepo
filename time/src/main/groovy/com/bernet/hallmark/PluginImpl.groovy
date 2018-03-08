package com.bernet.hallmark

import com.bernet.hallmark.timelog.TimeLogTask
import org.gradle.api.Plugin
import org.gradle.api.Project

public class PluginImpl implements Plugin<Project> {

    void apply(Project project) {
//        project.task('testTask') << {
//            println "Hello gradle plugin"
//        }
//        project.gradle.addListener(new TimeListener())
//        project.gradle.addProjectEvaluationListener(new MyProjectEvaluationListner())
        project.getGradle().taskGraph.addTaskExecutionGraphListener(new MyTaskExecutionGraphListener(project) )

    }
}