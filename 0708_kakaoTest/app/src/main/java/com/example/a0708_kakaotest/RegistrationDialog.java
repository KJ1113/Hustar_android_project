package com.example.a0708_kakaotest;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.a0708_kakaotest.Android_Class.kakaoMapUse_Class.Make_Marker;
import com.example.a0708_kakaotest.Fragment.Fragment_Bank;
import com.example.a0708_kakaotest.Fragment.Fragment_Market;
import com.opencsv.CSVWriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static com.example.a0708_kakaotest.Android_Class.Init_Calss.Init_Data.get_bankData_fav;
import static com.example.a0708_kakaotest.Android_Class.Init_Calss.Init_Data.get_useData;
import static com.example.a0708_kakaotest.Android_Class.Init_Calss.Init_Data.get_useData_fav;


public class RegistrationDialog  extends AppCompatActivity {

    private Context context;
    private Fragment_Market fragment_Market;
    private Fragment_Bank fragment_Bank;
    private Make_Marker make_marker;
    private Fragment fragment;

    private EditText message_1;
    private EditText message_2;
    private EditText message_3;
    private EditText message_4;
    private Button okButton;
    private Button cancelButton ;


    public RegistrationDialog(Context context , Fragment fragment) {
        this.context = context;

        if(fragment instanceof Fragment_Market){
            this.fragment_Market = (Fragment_Market)fragment;
            make_marker = fragment_Market.getMake_Marker();
        }else{
            this.fragment_Bank = (Fragment_Bank)fragment;
            make_marker = fragment_Bank.getMake_Marker();
        }
    }

    // 호출할 다이얼로그 함수를 정의한다.
    public void callFunction() {

        final Dialog dlg = new Dialog(context);
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dlg.setContentView(R.layout.custom_dialog);
        dlg.show();

        // 커스텀 다이얼로그의 각 위젯들을 정의한다.
        message_1 = (EditText) dlg.findViewById(R.id.mesgase1); //이름
        message_2 = (EditText) dlg.findViewById(R.id.mesgase2); // 시
        message_3 = (EditText) dlg.findViewById(R.id.mesgase3); //시군구
        message_4 = (EditText) dlg.findViewById(R.id.mesgase3); // 주소
        okButton = (Button) dlg.findViewById(R.id.okButton);
        cancelButton = (Button) dlg.findViewById(R.id.cancelButton);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BufferedWriter bufWriter = null;


                if(fragment_Market != null){
                    String[] inputs = new String[7];
                    int no = get_useData_fav().size(); inputs[0] = (Integer.toString(no));
                    String name = message_1.getText().toString(); inputs[1] = (name);
                    String add= message_4.getText().toString(); inputs[2] = (add);
                    double latitude = make_marker.getCurlatitude(); inputs[3] =(Double.toString(latitude));
                    double longitude =make_marker.getCurlongitude(); inputs[4]=(Double.toString(longitude));
                    String city= message_2.getText().toString(); inputs[5]=(city);
                    String dis = message_3.getText().toString(); inputs[6]=(dis);
                    int zoomlv =2;
                    make_marker.fav_add_Market_marker(no,name,add,latitude,longitude,city,dis,zoomlv );
                    get_useData_fav().add(inputs);

                    String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/csv";
                    String filename = "MarketStandard_data_fav.csv";

                    // 키킼..이해못하겠지 고통받아라
                    try {
                        bufWriter = Files.newBufferedWriter(Paths.get(dirPath+"/"+filename), Charset.forName("x-windows-949"));
                        for(int i =0 ; i < get_useData_fav().size() ; i++){
                            for(int j = 0 ; j < get_useData_fav().get(i).length-1; j++){
                                bufWriter.write(get_useData_fav().get(i)[j]+",");
                            }
                            bufWriter.write( get_useData_fav().get(i)[get_useData_fav().get(i).length-1]);
                            bufWriter.write( "\n");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }finally {
                        try {
                            bufWriter.flush();
                            bufWriter.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                else{
                    //11
                    //int no, String city, String dis,  String bankname ,String name ,String num ,String add , double latitude, double longitude  , int ZoomLv
                    String[] inputs = new String[10];
                    int no= get_bankData_fav().size(); inputs[0] = (Integer.toString(no)); inputs[1] = "?";
                    String city= message_2.getText().toString(); inputs[2] = (city);
                    String dis = message_3.getText().toString(); inputs[3] = (dis);
                    String bankname = message_1.getText().toString(); inputs[4] = (bankname);
                    String name = message_1.getText().toString(); inputs[5] = (name);
                    String num ="000-000-0000"; inputs[6] = (num);
                    String add = message_4.getText().toString(); inputs[7] = (add);
                    double latitude = make_marker.getCurlatitude(); inputs[9] =(Double.toString(latitude));
                    double longitude =make_marker.getCurlongitude(); inputs[8] =(Double.toString(longitude));
                    int zoomlv =2;
                    make_marker.fav_add_Bank_marker( no,  city,  dis,   bankname , name , num , add ,  latitude,  longitude  ,  zoomlv);
                    get_bankData_fav().add(inputs);
                    String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/csv";
                    String filename = "BankStandard_data_fav.csv";


                    try {
                        bufWriter = Files.newBufferedWriter(Paths.get(dirPath+"/"+filename), Charset.forName("x-windows-949"));
                        for(int i =0 ; i < get_bankData_fav().size() ; i++){
                            for(int j = 0 ; j < get_bankData_fav().get(i).length-1; j++){
                                bufWriter.write(get_bankData_fav().get(i)[j]+",");
                            }
                            bufWriter.write( get_bankData_fav().get(i)[get_bankData_fav().get(i).length-1]);
                            bufWriter.write( "\n");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }finally {
                        try {
                            bufWriter.flush();
                            bufWriter.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                Toast.makeText(context, "즐겨찾기가 등록되었습니다!", Toast.LENGTH_SHORT).show();
                // 커스텀 다이얼로그를 종료한다.
                dlg.dismiss();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "취소 했습니다.", Toast.LENGTH_SHORT).show();
                dlg.dismiss();
            }
        });
    }



}
