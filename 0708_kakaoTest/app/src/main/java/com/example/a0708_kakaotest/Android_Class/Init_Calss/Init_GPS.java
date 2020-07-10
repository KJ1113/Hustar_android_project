package com.example.a0708_kakaotest.Android_Class.Init_Calss;

import android.app.Activity;

import com.example.a0708_kakaotest.Android_Class.GpsTracker;

public class Init_GPS {
    public static GpsTracker gpsTracker;
    public Init_GPS(Activity ac){
        gpsTracker =new GpsTracker(ac);
    }
}
