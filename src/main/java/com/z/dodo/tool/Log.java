package com.z.dodo.tool;

public class Log {

    static String TAG = "zzz";

    public static void d(String TAG, String content) {
        System.out.println(TAG + " -> " + content);
    }

    public static void d(String content) {
        System.out.println(TAG + " -> " + content);
    }
}
