ADDI_CFLAGS="-marm"
API=19
PLATFORM=arm-linux-androideabi
CPU=armv7-a ##这里只编译armv7的
NDK=/home/harris/Documents/github/android-ndk-r16b # 修改成自己本地的ndk路径。
SYSROOT=$NDK/platforms/android-$API/arch-arm/
ISYSROOT=$NDK/sysroot
ASM=$ISYSROOT/usr/include/$PLATFORM
TOOLCHAIN=$NDK/toolchains/$PLATFORM-4.9/prebuilt/linux-x86_64
OUTPUT=android-build-single-abi #自己指定一个输出目录，用来放生成的文件的。
function build
{
./configure \
--prefix=$OUTPUT \
--enable-shared \
--disable-static \
--disable-doc \
--disable-ffmpeg \
--disable-ffplay \
--disable-ffprobe \
--disable-avdevice \
--disable-doc \
--disable-symver \
--cross-prefix=$TOOLCHAIN/bin/arm-linux-androideabi- \
--target-os=android \
--arch=arm \
--enable-cross-compile \
--sysroot=$SYSROOT \
--extra-cflags="-I$ASM -isysroot $ISYSROOT -Os -fpic -marm" \
--extra-ldflags="-marm" \
$ADDITIONAL_CONFIGURE_FLAG
  make clean
  make -j4
  make install
}

build


