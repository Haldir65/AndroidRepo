package com.me.harris.jniscratch;

public class PackageValidate {
    static {
        System.loadLibrary("validator-lib");
    }

    public static native String getPublicKey(Object obj);
}
