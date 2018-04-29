#include "com_me_harris_jniscratch_NdkJniUtils.h"

JNIEXPORT jstring JNICALL Java_com_me_harris_jniscratch_NdkJniUtils_getCLanguageString
  (JNIEnv *env, jobject obj){
     return (*env)->NewStringUTF(env,"awosome languages ");
  }