package com.example.a0708_kakaotest.Android_Class.Init_Calss;
import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;
import com.opencsv.CSVReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Init_Data {
    private static List<String[]> csv_bank;
    private static List<String[]> csv_use;
    private static List<String[]> csv_bank_fav;
    private static List<String[]> csv_use_fav;

    public Init_Data(Activity ac){
        prepArray(ac);
    }
    public static List<String[]> get_bankData(){
        return csv_bank;
    }
    public static List<String[]> get_useData(){
        return csv_use;
    }
    public static List<String[]> get_bankData_fav(){ return csv_bank_fav; }
    public static List<String[]> get_useData_fav(){
        return csv_use_fav;
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

        try {
            String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/csv";
            String filename = "BankStandard_data_fav.csv";
            File dir = new File(dirPath);
            if(!dir.exists()){
                dir.mkdir();
            }
            File file = new File(dirPath+"/"+filename);
            if(!file.exists()) {
                file.createNewFile();
                BufferedWriter bufWriter = null;
                bufWriter = Files.newBufferedWriter(Paths.get(dirPath+"/"+filename), Charset.forName("x-windows-949"));
                for(int i =0 ; i < 10 ; i++){
                    bufWriter.write("1");
                    bufWriter.write(",");
                }
                bufWriter.write( "1");
                bufWriter.write( "\n");
                bufWriter.close();
            }


            FileInputStream a = new FileInputStream(file);
            InputStreamReader reader = new InputStreamReader(new FileInputStream(file), Charset.forName("x-windows-949"));
            csv_bank_fav = new CSVReader(reader).readAll();

        } catch (Exception e) {
            e.printStackTrace();
        }
        /////////////////////////////////////////////////////////////////////////

        try {

            String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/csv";
            String filename = "MarketStandard_data_fav.csv";
            File dir = new File(dirPath);
            if(!dir.exists()){
                dir.mkdir();
            }
            File file = new File(dirPath+"/"+filename);
            if(!file.exists()) {
                file.createNewFile();
                BufferedWriter bufWriter = null;
                bufWriter = Files.newBufferedWriter(Paths.get(dirPath+"/"+filename), Charset.forName("x-windows-949"));
                for(int i =0 ; i < 6 ; i++){
                    bufWriter.write("1");
                    bufWriter.write(",");
                }
                bufWriter.write( "1");
                bufWriter.write( "\n");
                bufWriter.close();
            }



            FileInputStream a = new FileInputStream(file);
            InputStreamReader reader = new InputStreamReader(new FileInputStream(file), Charset.forName("x-windows-949"));
            csv_use_fav = new CSVReader(reader).readAll();
            Toast.makeText(ac, Integer.toString(csv_bank_fav.size()), Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }


        Toast.makeText(ac, "공공데이터를 업로드중..", Toast.LENGTH_SHORT).show();
    }
}
