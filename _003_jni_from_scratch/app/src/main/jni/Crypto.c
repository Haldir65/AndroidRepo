//
// Created by Fermi on 2018/7/28.
//
#include "com_me_harris_jniscratch_Crypto.h"
# include <stdio.h>
# include <stdlib.h>
# include <string.h>



JNIEXPORT jstring JNICALL Java_com_me_harris_jniscratch_Crypto_encrypt
        (JNIEnv * env, jobject obj, jstring str) {
    char *prim = (*env)->GetStringUTFChars(env, str, 0);
    char code[100];
    int i,n,len;
    char temp_char,trans_char;
    int temp_num,trans_num;
    len = strlen(prim);
    for (int i = 0; i < len; ++i)
    {
        code[i] = prim[i] + 1;
    }

    for(i = 0;i < len;i++) {
        temp_char = prim[i];                   //Consider about the corresponding number of character
        if (temp_char <= 'z' && temp_char >= 'a')
            temp_num = temp_char - 'a' + 1;
        else if (temp_char <= 'Z' && temp_char >= 'A')
            temp_num = temp_char - 'A' + 27;

        trans_num = temp_num*3 % 52;

        if (trans_num > 26 && trans_num <= 52)         //Transform number to character
            trans_char = 'A' + trans_num - 27;
        else if (trans_num > 0 && trans_num <= 26)
            trans_char = 'a' + trans_num - 1;

        code[i] = trans_char;
    }
    code[len] = '\0';
    return (*env)->NewStringUTF(env, code);
}


JNIEXPORT jstring JNICALL Java_com_me_harris_jniscratch_Crypto_decrypt
        (JNIEnv * env, jobject obj, jstring str) {
    char *code = (*env)->GetStringUTFChars(env, str, 0);
    char prim[100];
    int i,n,len;
    char temp_char,trans_char;
    int temp_num,trans_num;
    len = strlen(code);

    for(i = 0;i < len;i++) {
        temp_char = code[i];             //Consider about the corresponding digit of character
        if (temp_char <= 'z' && temp_char >= 'a')
            temp_num = temp_char - 'a' + 1;
        else if (temp_char <= 'Z' && temp_char >= 'A')
            temp_num = temp_char - 'A' + 27;

        n = temp_num % 3;  //Mode 3 and compute primitive number
        switch(n) {
            case 0:
                trans_num = temp_num/3;
                break;
            case 1:
                trans_num = 35 + temp_num/3;
                break;
            case 2:
                trans_num = 18 + temp_num/3;
                break;
            default:
                break;
        }
        //Transform number to character
        if (trans_num > 26 && trans_num <= 52)
            trans_char = 'A' + trans_num - 27;
        else if (trans_num > 0 && trans_num <= 26)
            trans_char = 'a' + trans_num - 1;

        prim[i] = trans_char;
    }
    prim[len] = '\0';
    return (*env)->NewStringUTF(env, prim);
}