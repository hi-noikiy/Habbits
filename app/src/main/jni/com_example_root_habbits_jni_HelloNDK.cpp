//
// Created by root on 2016/5/13.
//
//这个程序实现了使用jni的方法返回一个字符串
#include <jni.h>
#include <string.h>
#include "com_example_root_habbits_jni_HelloNDK.h"
JNIEXPORT jstring JNICALL Java_com_example_root_habbits_jni_HelloNDK_getCLangString
  (JNIEnv *env, jclass){
    return env->NewStringUTF("Hello From JNI!");
  }
