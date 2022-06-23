package com.me.harris.jniscratch;

public class Crypto {

    static {
        System.loadLibrary("crypto-lib");
    }

    public native String encrypt(String prim);
    public native String decrypt(String code);
//    public native String encryptWithContext(Object  ctx, String code);



}