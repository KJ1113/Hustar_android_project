package com.example.a0708_kakaotest.Android_Class.Init_Calss;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.opencsv.CSVReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;

public class Init_Data {
    private void prepArray(Activity ac) {
        try {
            if (ContextCompat.checkSelfPermission(ac, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(ac,Manifest.permission.READ_EXTERNAL_STORAGE)) {
                } else {
                    ActivityCompat.requestPermissions(ac,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            1);
                }
            }
            AssetManager am = ac.getResources().getAssets() ;
            InputStream csvStream = am.open("BankStandard_data.csv");
            //Toast.makeText(this, "공공데이터를 불러오는중..", Toast.LENGTH_SHORT).show();
            InputStreamReader reader = new InputStreamReader(csvStream, Charset.forName("UTF-8"));
            List<String[]> csv = new CSVReader(reader).readAll();
            //Toast.makeText(this, "공공데이터를 업로드중..", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
