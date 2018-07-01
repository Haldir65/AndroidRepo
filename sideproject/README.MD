
[服务端使用的proto依赖版本和protoc文件的版本需要保持一致](https://stackoverflow.com/questions/48458101/protoc-producing-invalid-java-code)
<br>
对应的客户端使用的protobuf.jar文件也得是版本一致的，[这个jar文件不是很好找，在maven里面](https://stackoverflow.com/questions/15723099/where-to-find-protobuf-jar-for-using-google-protocol-buffers-in-java)
<br>

这个是生成普通java文件的命令
```
absolute/path/of/protoc-3.6.0-osx-x86_64/bin/protoc -I=proto/ --java_out=/absolute/path/of/the/generated/files/projectname/src/main/  path/of/your/protofiles/*.proto
```

这个是生成java-lit的文件的命令
```
$ protoc --javalite_out=${OUTPUT_DIR} path/to/your/proto/file
```

个人猜测，无论是使用java还是java-lite生成的文件，最终通过网络传输的binary信息都是一致的。另外,java-lite是不是在java的基础上删除了一些冗余的方法。
所以,protobuf可以由后台提供一致的proto文件定义，同时为android和ios平台提供服务。



- 一些比较坑的点
注意的是proto文件有一个import的问题
还有就是protoc生成的java文件是不应该修改的

```proto
syntax = "proto3";// 版本
package com.me.harris.gen; // 命名空间
option java_package = "com.me.harris.gen";//生成java包名
option java_outer_classname = "HobbyProto"; //生成Java类名
option java_multiple_files = true;
```

因为默认java的包名最好是跟package_name对的上的，所以protoc命令在执行的时候就需要把java_out设置为src文件夹中的一个位置，这样就能够自动引用到了

- [ ] 可能把所有的proto写到一个module里面，这样还根据不同业务区分proto位置。
- [ ] 客户端可以通过一个bash脚本去调用 protoc 生成对应的java文件
- [ ] 做一个定时任务，定时拉取proto仓库中的proto，生成jar或者aar文件。一劳永逸
