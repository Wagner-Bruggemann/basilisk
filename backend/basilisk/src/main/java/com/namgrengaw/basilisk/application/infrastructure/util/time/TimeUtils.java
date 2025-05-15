package com.namgrengaw.basilisk.application.infrastructure.util.time;

public abstract class TimeUtils {

    public static String formatedDate(long interval) {
        return inHours(interval) + ":" + inMinutes(interval) + ":" + inSeconds(interval);
    }

    public static int inHours(long interval) {
        return (int) interval / 3600;
    }

    public static int inMinutes(long interval) {
        return (int) interval % 3600 / 60;
    }

    public static int inSeconds(long interval) {
        return (int) interval % 60;
    }

}
