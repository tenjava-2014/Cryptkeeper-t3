package com.tenjava.entries.Cryptkeeper.t3.util;

public class Profiler {

    private static long time;

    public static void profile(String name) {
        if (time > 0) {
            if (System.currentTimeMillis() - time > 2L) {
                System.out.println(name + " took " + (System.currentTimeMillis() - time) + "ms!");
            }
            time = 0L;
        } else {
            time = System.currentTimeMillis();
        }
    }
}
