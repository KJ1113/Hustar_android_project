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
    public Init_Data(Activity ac){
        prepArray(ac);
    }
    public static List<String[]> get_bankData(){
        return csv_bank;
    }
    private void prepArray(Activity ac) {
        try {

            AssetManager am = ac.getResources().getAssets() ;
            InputStream csvStream = am.open("BankStandard_data.csv");
            //Toast.makeText(ac, "공공데이터를 불러오는중..", Toast.LENGTH_SHORT).show();
            InputStreamReader reader = new InputStreamReader(csvStream, Charset.forName("x-windows-949"));
            csv_bank = new CSVReader(reader).readAll();
            Toast.makeText(ac, "공공데이터를 업로드중..", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
