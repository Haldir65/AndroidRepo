# Sets the minimum version of CMake required to build your native library.
# This ensures that a certain set of CMake features is available to
# your build.

cmake_minimum_required(VERSION 3.4.1)

# Specifies a library name, specifies whether the library is STATIC or
# SHARED, and provides relative paths to the source code. You can
# define multiple libraries by adding multiple add.library() commands,
# and CMake builds them for you. When you build your app, Gradle
# automatically packages shared libraries with your APK.

add_library( # Specifies the name of the library.
             crypto-lib   ## this is the name of the generated .so file

             # Sets the library as a shared library.
             SHARED

             # Provides a relative path to your source file(s).
             #src/main/jni/native-lib.cpp
             src/main/jni/Crypto.c
             )


add_library( # Specifies the name of the library.
             validator-lib   ## this is the name of the generated .so file

             # Sets the library as a shared library.
             SHARED

             # Provides a relative path to your source file(s).
             src/main/jni/validator.cpp
             )

add_library( # Specifies the name of the library.
        fileutility-lib   ## this is the name of the generated .so file

        # Sets the library as a shared library.
        SHARED

        # Provides a relative path to your source file(s).
        src/main/jni/com_me_harris_jniscratch_CreatingFileUsingJni.c
        )

### Library that already exists on Android Platform
find_library( # Defines the name of the path variable that stores the
              # location of the NDK library.
              log-lib

              # Specifies the name of the NDK library that
              # CMake needs to locate.
              log )


# Links your native library against one or more other native libraries.
target_link_libraries( # Specifies the target library.
                       crypto-lib

                       # Links the log library to the target library.
                       ${log-lib} )