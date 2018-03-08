package com.bernet.hallmark

import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.execution.TaskExecutionGraph
import org.gradle.api.execution.TaskExecutionGraphListener

class MyTaskExecutionGraphListener implements TaskExecutionGraphListener{


    Project project

    MyTaskExecutionGraphListener(Project project) {
        this.project = project
    }

    @Override
    void graphPopulated(TaskExecutionGraph taskExecutionGraph) {
        for (Task task :taskExecutionGraph.getAllTasks()){

            project.logger.warn("find task with group name" + task.getGroup()+ " . task name: " + task.name)
        }
    }
}