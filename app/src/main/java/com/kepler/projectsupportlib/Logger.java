package com.kepler.projectsupportlib;

import android.util.Log;

import com.kepler.studentportal.BuildConfig;


/**
 * Created by special on 5/12/17.
 */

public class Logger {

    public static final int DIALOG_ERROR = 1;
    public static final int DIALOG_ALERT = 2;
    private static final String TAG=Logger.class.getSimpleName();
    public static void print(Exception e) {
        if (BuildConfig.DEBUG) {
            e.printStackTrace();
        }
    }public static void print(Throwable e) {
        if (BuildConfig.DEBUG) {
            e.printStackTrace();
        }
    }

    public static void print(String msg) {
        if (BuildConfig.DEBUG) {
            System.out.println(msg);
        }
    }

    public static void e(String TAG, String msg) {
        if (BuildConfig.DEBUG) {
            Log.e(TAG, msg);
        }
    } public static void d(String TAG, String msg) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, msg);
        }
    }public static void e(String msg) {
        if (BuildConfig.DEBUG) {
            Log.e(TAG, msg);
        }
    } public static void d( String msg) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, msg);
        }
    }
}
