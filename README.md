亲测可用的教程-  [如何使用Android Studio开发Gradle插件](http://blog.csdn.net/sbsujjbcy/article/details/50782830)
[Hello world in groovy](https://dongchuan.gitbooks.io/gradle-user-guide-/build_script_basics/hello_world.html)

plugin 的包名不建议和app的包名一样
运行gradle task需要在gradle的panel中选择对应的任务，**双击是没有用的**，右键然后运行才行
最后的效果是在:app:Tasks:other中多出来一个testTask(这个名字是project.task("testTask",***)这个方法传进去的第一个String参数)，右键运行即可

