package com.example.root.habbits.jni;

/**
 * java native方法类
 * Created by root on 2016/5/13.
 */
public class HelloNDK {
    static {
        System.loadLibrary("hellondk");
    }
    public static native String getCLangString();
}
