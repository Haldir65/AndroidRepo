[移植FFmpeg-4.0.3到Android平台](https://juejin.im/post/5c32f171e51d45523f04a9d3)
[ffmpeg移植](https://github.com/ywp0919/FFmpeg4Cmake) 照着这个实现的就可以了
[编译并裁剪 FFmpeg 在 Android 上做视频编辑](https://juejin.im/post/5b1f655b5188257d3a69782d)
[微信Android视频编码爬过的那些坑.md](https://github.com/WeMobileDev/article/blob/master/微信Android视频编码爬过的那些坑.md)
[使用Android Studio开发FFmpeg的正确姿势](https://www.jianshu.com/p/0a7f3175c1b9)
[ffmpeg-command-wrapper](https://github.com/alidili/FFmpeg4Android)

一些爬坑感悟：
不要尝试在windows上编译so文件，浪费时间
最好的方式是提供ffmpeg的java层command-wrapper
不要使用android studio里面下载的ndk,自己去ndk官网下载一个完整的
ndk也有很多bug, 并不是最新的ndk就是适合的
ffmpeg一直在积极的开发，所以编译脚本在换了一个ffmpeg版本之后可能就会报错。
报错主要是因为ffmpeg修改了api，某个头文件找不到
CMakeList.txt文件是一个，还有就是.configure需要搭配的flag，非常麻烦
在linux上编译.so文件需要10分钟左右的时间，这视机器配置而变化。尽量修改完备了再去编译。
