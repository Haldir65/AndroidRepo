package com.me.harris.jniscratch;

public class NdkJniUtils {

    static {
        System.loadLibrary("awesomeJni");   //defaultConfig.ndk.moduleName
    }

    public native String getCLanguageString();
}
