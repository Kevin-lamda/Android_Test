#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_ssw_com_myapplication_other_CMakeListsHello_stringFromJNI(JNIEnv *env, jobject /* this */) {
    std::string hello = "Hello from C++ by CMakeLists";
    return env->NewStringUTF(hello.c_str());
}
