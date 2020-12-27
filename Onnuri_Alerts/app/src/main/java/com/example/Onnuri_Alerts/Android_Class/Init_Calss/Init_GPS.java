package com.example.Onnuri_Alerts.Android_Class.Init_Calss;

import android.app.Activity;

import com.example.Onnuri_Alerts.Android_Class.kakaoMapUse_Class.GpsTracker;

public class Init_GPS {
    private static GpsTracker gpsTracker;
    public Init_GPS(Activity ac){
        gpsTracker = GpsTracker.getInstance(ac);
    }
    public static GpsTracker getGPS(){
        return gpsTracker;
    }
}
