package com.bernet.hallmark

import org.gradle.api.Project
import org.gradle.api.ProjectEvaluationListener
import org.gradle.api.ProjectState

class MyProjectEvaluationListner implements ProjectEvaluationListener {

    @Override
    void beforeEvaluate(Project project) {
        print("beforeEvaluate $project.path +\n")
    }

    @Override
    void afterEvaluate(Project project, ProjectState projectState) {
        print("after Evaluate $project.path + \n")

    }
}