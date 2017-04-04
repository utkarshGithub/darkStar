package com.example.utkarshshukla.darkstar.CustomApplication;

import android.app.Application;

import com.activeandroid.ActiveAndroid;

/**
 * Created by Utkarsh Shukla on 04/04/17.
 */


public class AppController extends Application {
    private static boolean activityVisible;
    @Override
    public void onCreate ( ) {
        super.onCreate ( );
        ActiveAndroid.initialize ( this );
    }

    public static boolean isActivityVisible ( ) {
        return activityVisible;
    }

    public static void activityResumed ( ) {
        activityVisible = true;
    }

    public static void activityPaused ( ) {
        activityVisible = false;
    }
}


