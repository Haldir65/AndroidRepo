- [ ] hook instrumentation
- [ ] write your own backend supplying protobuf lite service. 用protobuf-gradle-plugin在另一个module中将.proto文件编译成.java文件，最终打成aar。
但这个protobackend在后台手中，后台不一定会使用gradle编译项目。

 所以还是要用这个命令行去搞，比如说写一个脚本一个文件一个文件去执行这个命令
 > $ protoc --javalite_out=${OUTPUT_DIR} path/to/your/proto/file

