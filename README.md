亲测可用的教程-  [如何使用Android Studio开发Gradle插件](http://blog.csdn.net/sbsujjbcy/article/details/50782830)

[Hello world in groovy](https://dongchuan.gitbooks.io/gradle-user-guide-/build_script_basics/hello_world.html)

plugin 的包名不建议和app的包名一样
运行gradle task需要在gradle的panel中选择对应的任务，**双击是没有用的**，右键然后运行才行
最后的效果是在:app:Tasks:other中多出来一个testTask(这个名字是project.task("testTask",***)这个方法传进去的第一个String参数)，右键运行即可

在PluginImpl中实现程序入口，具体的业务逻辑添加到TimeListener中。每次修改好.groovy文件之后，重新运行一下右侧gradle panel中的uploadArchives这个task。然后跑其他的gradle task，比如assembleDebug，或者直接点击那个run
假如uploadArchive失败了的话，把本地的.repo文件夹删掉，把time（就是那个plugin所在的module中的build文件夹删掉）,把app.build中的依赖我们开发的pulgin的部分删掉，重新跑uploadArchive。总归能成

- 本地开发的gradle插件中使用的Deprecated 的api ，这样在命令行中使用gradle clean 就会失败
> Could not find matching constructor for: org.gradle.util.Clock()

更换掉

[编译耗时统计](https://www.diycode.cc/topics/683)
[gradle 4.4之后Clock 被Deprecated的方案是自己创建一个groovy文件](https://github.com/HujiangTechnology/gradle_plugin_android_aspectjx/pull/75/files#diff-a5277607f48bf80ac7edd5dbafa307ae)

