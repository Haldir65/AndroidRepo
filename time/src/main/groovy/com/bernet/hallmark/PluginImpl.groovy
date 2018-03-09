package com.bernet.hallmark

import com.intellij.diagnostic.PluginException
import net.lingala.zip4j.core.ZipFile
import net.lingala.zip4j.model.ZipParameters
import org.apache.commons.io.FileUtils
import org.gradle.api.Plugin
import org.gradle.api.Project


public class PluginImpl implements Plugin<Project> {

    void apply(Project project) {
//        project.task('testTask') << {
//            println "Hello gradle plugin"
//        }
        project.gradle.addListener(new TimeListener())
//        project.gradle.addProjectEvaluationListener(new MyProjectEvaluationListner())
        project.getGradle().taskGraph.addTaskExecutionGraphListener(new MyTaskExecutionGraphListener(project) )
        project.android.sourceSets.main.java.srcDirs.each { srcDir->
            println("==srcDir: ${srcDir}") //输出当前app/src/main/java/所在的绝对路径
        }
//       project.extensions.create("makeChannel", MakeChannelParams)
        println("==================got you==============")
        if (!project.plugins.hasPlugin("com.android.application")) {
            throw new PluginException(
                    "'com.android.application' plugin must be applied", null)
        } else {
            project.plugins.each { Plugin item ->
              println(item.class.toString())
            }
        }




        project.task('makeChannel').doLast {
            println("==================do last==============")
            File channelFile = project.file(project.makeChannel.channelFile)
            if (!channelFile.exists()) {
                println("channelFile路径错误")
                return
            }
            FileReader reader = new FileReader(channelFile.absolutePath)
            File inputApk = project.file(project.makeChannel.inputApk)
            if (!inputApk.exists()) {
                println("inputApk路径错误")
                return
            }
            File output = project.file(project.makeChannel.outputDir)
            if (!output.exists()) {
                output.mkdir()
            }
            println("==================hey there ==============")
            BufferedReader br = new BufferedReader(reader)
            String str = null
            long time = System.currentTimeMillis() / 1000
            while ((str = br.readLine()) != null) {
                String channel = str.trim()
                def apkName = channel + ".signed.apk"
                File outFile = new File(output, apkName)
                copy(inputApk, outFile)
                ZipFile zipFile = new ZipFile(outFile)
                ZipParameters parameters = new ZipParameters()
                parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE)
                parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL)
                parameters.setRootFolderInZip("META-INF/")
                File newFile = new File(output, project.makeChannel.baseChannelName + "_" + channel);
                if (!newFile.exists()) {
                    newFile.createNewFile()
                }
                zipFile.addFile(newFile, parameters)
                println("生成文件 " + apkName)
                newFile.delete()
            }
            println("耗时" + (System.currentTimeMillis() / 1000 - time) + "秒");
            br.close()
            reader.close()
        }
    }


    private static void copy(File sourceFilePath, File copyFilePath) {
        try {
            FileUtils.copyFile(sourceFilePath, copyFilePath)
        } catch (IOException e) {
            e.printStackTrace()
        }
    }




    private static class MakeChannelParams {
        String channelFile = "../appbuild/channels"// channel文件目录地址
        String inputApk = "" // 输入apk路径
        String outputDir = "../appbuild/output" // 输出文件目录
        String baseChannelName = "channel" // 默认channel
    }



}