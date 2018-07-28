//
// Created by Fermi on 2018/7/28.
// 用于[校验apk签名](http://wxmylife.com/2017/03/22/Android%E9%80%9A%E8%BF%87JNI%E5%8A%A0%E5%AF%86%EF%BC%8C%E5%B9%B6%E5%AE%9E%E7%8E%B0APP%E7%AD%BE%E5%90%8D%E9%AA%8C%E8%AF%81/)
//
#include <jni.h>
#include <string.h>
#include <stdio.h>
#include "com_me_harris_jniscratch_PackageValidate.h"

/**
 *这个key是和服务器之间通信的秘钥
 */
const char* AUTH_KEY = "super_secret_awesome";

/**
 * 发布的app 签名,只有和本签名一致的app 才会返回 AUTH_KEY
 * 这个RELEASE_SIGN的值是上一步用java代码获取的值
 */
const char* RELEASE_SIGN = "308203373082021fa0030201020204548d5e94300d06092a864886f70d01010b0500304c310b3009060355040613023231310b3009060355040813025056310b300906035504071302534131143012060355040b130b57617465726d656c6c6f6e310d300b060355040313044a616d65301e170d3138303732383032303730305a170d3433303732323032303730305a304c310b3009060355040613023231310b3009060355040813025056310b300906035504071302534131143012060355040b130b57617465726d656c6c6f6e310d300b060355040313044a616d6530820122300d06092a864886f70d01010105000382010f003082010a0282010100d6291881b5cb444426718e386babeadc3b0b5d4309e64a87b4d7633e4080fef2810e46a880459a301899dcd8fe44367a5b3b732df486eb2e04f7f887bc40ca0218845a75c4a6fef6d375f6d381a7e007e38a8183e66ef104b7087f1149f370419c6c9413d08b2f17c5a6e561ff35a8f9f1cfc9315144cae3cfb79fa512774bd1ae29b2d285c30ee29fb144252f15467a26ed6d50f8d587e2316a5fb523a4120e033c8e567f6b58cff747c407295dad4660fdc945a51a3d293500c2d9382f2316d0f63f3c5ab22ca5bbe83c807922793005194594303cc089681c02d272720fb007b04fe6c1c573a9a2cb8d9138938a0a2af878173becb33732df73a5175930690203010001a321301f301d0603551d0e04160414044a866b3573b495d9008d4a810ddb7ec343ba64300d06092a864886f70d01010b050003820101008f84295471f93c4415f6e4c1786edbb94017c8615d07239798f5f7125d72c2d0ea828a46895d0420eda7cfb555a466ea5205c9823803644ef2a5d5de464a38164575179400a7d699b1b41d3a2a3aa84d884e8b83a48657da45df12cfd054ef52e962af169e564636a0a2d72df1b45969c1f491e09d7e24f6b1776f87e174cc87b0c414bf2967d7d855cd63cfcfa7b79c8d312300a917a0c82163b2311d793c780d403977a6c66d57781811e6b5ce74f81b810bbeae07ef5eb917bf3dbe57a2abaeddcb422157834db6297a42787038fa3b346d1313eff6cd76c8ceecb6afcdb0e134891e678caa3059c7eaccde8331a2feb63efa7ec5b205c9cf560660227a1d";

/**
 * 发布的app 签名 的HashCode
 */
const int RELEASE_SIGN_HASHCODE = -332752192;


JNIEXPORT jstring JNICALL Java_com_me_harris_jniscratch_PackageValidate_getPublicKey
        (JNIEnv *env, jclass jclazz, jobject contextObject){


    jclass native_class = env->GetObjectClass(contextObject);
    jmethodID pm_id = env->GetMethodID(native_class, "getPackageManager", "()Landroid/content/pm/PackageManager;");
    jobject pm_obj = env->CallObjectMethod(contextObject, pm_id);
    jclass pm_clazz = env->GetObjectClass(pm_obj);
    // 得到 getPackageInfo 方法的 ID
    jmethodID package_info_id = env->GetMethodID(pm_clazz, "getPackageInfo","(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;");
    jclass native_classs = env->GetObjectClass(contextObject);
    jmethodID mId = env->GetMethodID(native_classs, "getPackageName", "()Ljava/lang/String;");
    jstring pkg_str = static_cast<jstring>(env->CallObjectMethod(contextObject, mId));
    // 获得应用包的信息
    jobject pi_obj = env->CallObjectMethod(pm_obj, package_info_id, pkg_str, 64);
    // 获得 PackageInfo 类
    jclass pi_clazz = env->GetObjectClass(pi_obj);
    // 获得签名数组属性的 ID
    jfieldID signatures_fieldId = env->GetFieldID(pi_clazz, "signatures", "[Landroid/content/pm/Signature;");
    jobject signatures_obj = env->GetObjectField(pi_obj, signatures_fieldId);
    jobjectArray signaturesArray = (jobjectArray)signatures_obj;
    jsize size = env->GetArrayLength(signaturesArray);
    jobject signature_obj = env->GetObjectArrayElement(signaturesArray, 0);
    jclass signature_clazz = env->GetObjectClass(signature_obj);

    //第一种方式--检查签名字符串的方式
    jmethodID string_id = env->GetMethodID(signature_clazz, "toCharsString", "()Ljava/lang/String;");
    jstring str = static_cast<jstring>(env->CallObjectMethod(signature_obj, string_id));
    char *c_msg = (char*)env->GetStringUTFChars(str,0);

    if(strcmp(c_msg,RELEASE_SIGN)==0)//签名一致  返回合法的 api key，否则返回错误
    {
        return (env)->NewStringUTF(AUTH_KEY);
    }else
    {
        return (env)->NewStringUTF("wrong signature!");
    }

    //第二种方式--检查签名的hashCode的方式
    /*
    jmethodID int_hashcode = env->GetMethodID(signature_clazz, "hashCode", "()I");
    jint hashCode = env->CallIntMethod(signature_obj, int_hashcode);
    if(hashCode == RELEASE_SIGN_HASHCODE)
    {
        return (env)->NewStringUTF(AUTH_KEY);
    }else{
        return (env)->NewStringUTF("错误");
    }
     */


}

