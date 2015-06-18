package com.rajeshbatth.android_testing;

/**
 * Author: Rajesh Batth
 * Date: 19-Jun-2015.
 */
public class TestUtils {

    public static void safeSleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
