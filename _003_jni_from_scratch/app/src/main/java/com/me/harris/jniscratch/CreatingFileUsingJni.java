package com.me.harris.jniscratch;

public class CreatingFileUsingJni {

    static {
        System.loadLibrary("fileutility-lib");
    }
    public native String createFileUsingJni(String filepath,String fileContent);
}
