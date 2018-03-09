package com.bernet.hallmark

import org.gradle.BuildListener
import org.gradle.BuildResult
import org.gradle.api.Task
import org.gradle.api.execution.TaskExecutionListener
import org.gradle.api.initialization.Settings
import org.gradle.api.invocation.Gradle
import org.gradle.api.tasks.TaskState


class TimeListener implements TaskExecutionListener, BuildListener {
    private Clock clock
    private times = []

    @Override
    void beforeExecute(Task task) {
        clock = new Clock(System.currentTimeMillis())
//        clock.name = "====================name============================"
    }

    @Override
    void afterExecute(Task task, TaskState taskState) {
        def ms = clock.timeInMs
//        print(clock.name+"\n")
//        print("task name = "+task.name+ " task path == "+task.path+"\n")
        times.add([ms, task.path])
        task.project.logger.warn "${task.path} spend ${ms}ms"
    }

    @Override
    void buildFinished(BuildResult result) {
        println "Task spend time:"
        for (time in times) {
            printf "%7sms  %s\n", time
//            if (time[0] >= 50) {
//                printf "%7sms  %s\n", time
//            }
        }
    }

    @Override
    void buildStarted(Gradle gradle) {}

    @Override
    void projectsEvaluated(Gradle gradle) {}

    @Override
    void projectsLoaded(Gradle gradle) {}

    @Override
    void settingsEvaluated(Settings settings) {}
}