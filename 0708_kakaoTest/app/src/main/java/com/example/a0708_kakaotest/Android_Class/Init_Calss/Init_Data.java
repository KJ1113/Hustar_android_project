package com.example.a0708_kakaotest.Android_Class.Init_Calss;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.opencsv.CSVReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;

public class Init_Data {
    private static List<String[]> csv_bank;
    private static List<String[]> csv_use;
    public Init_Data(Activity ac){
        prepArray(ac);
    }
    public static List<String[]> get_bankData(){
        return csv_bank;
    }
    public static List<String[]> get_useData(){
        return csv_use;
    }
    private void prepArray(Activity ac) {
        try {
            AssetManager am = ac.getResources().getAssets() ;
            InputStream csvStream = am.open("BankStandard_data.csv");
            InputStreamReader reader = new InputStreamReader(csvStream, Charset.forName("x-windows-949"));
            csv_bank = new CSVReader(reader).readAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            AssetManager am = ac.getResources().getAssets() ;
            InputStream csvStream = am.open("MarketStandard_data.csv");
            InputStreamReader reader = new InputStreamReader(csvStream, Charset.forName("x-windows-949"));
            csv_use = new CSVReader(reader).readAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Toast.makeText(ac, "공공데이터를 업로드중..", Toast.LENGTH_SHORT).show();
    }
}
