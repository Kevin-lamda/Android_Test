//
// Created by Kevin Hunter on 2018/10/31.
//

#include <jni.h>
extern "C"
JNIEXPORT jstring JNICALL Java_ssw_com_myapplication_HelloLibrary_sayHello(JNIEnv *env, jobject instance) {
    //从 C++ 传字符串到 Java
    return env->NewStringUTF("Hello from C++");
}
