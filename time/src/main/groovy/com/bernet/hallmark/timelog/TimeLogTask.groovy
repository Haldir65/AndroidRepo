package com.bernet.hallmark.timelog

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import com.bernet.hallmark.InjectUtils

public class TimeLogTask extends DefaultTask {

    @TaskAction
    public onAction() {
        println("TimeLog onAction")

        classesPath = 'app\\build\\intermediates\\classes\\debug\\com\\me\\harris\\myapplication'

        InjectUtils.injectDir(classesPath
                , "com\\me\\harris\\myapplication"
                , "android.util.Log.i(TAG, \"onCreate: \" + System.currentTimeMillis());")

    }
}