package com.example.a0708_kakaotest;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a0708_kakaotest.Android_Class.Init_Calss.Init_Data;
import com.example.a0708_kakaotest.Android_Class.Init_Calss.Init_GPS;
import com.example.a0708_kakaotest.Fragment.*;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private Fragment_Menu menuFragment;
    private Fragment_Market use_ToFragment;
    private Fragment_Bank place_SaleFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private BottomNavigationView bottomNavigationView;
    private onBackPressedListener mOnKeyBackPressedListener; ;
    private Toast toast;
    private long backKeyPressedTime = 0;
    private String[] permissions = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE, // 기기, 사진, 미디어, 파일 엑세스 권한
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };
    private static final int MULTIPLE_PERMISSIONS = 101;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= 23) { // 안드로이드 6.0 이상일 경우 퍼미션 체크
           // checkPermissions();
        }

        this.init();
    }
    private void init(){
        new Init_GPS(this);
        new Init_Data(this);
        //
        menuFragment = new Fragment_Menu();
        use_ToFragment = new Fragment_Market();
        place_SaleFragment = new Fragment_Bank();
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();

        transaction.replace(R.id.frameLayout, menuFragment);
        transaction.addToBackStack(null);
        transaction.commit();

        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new ItemSelectedListener());
    }

    public void setOnKeyBackPressedListener(onBackPressedListener listener) {
        this.mOnKeyBackPressedListener = listener;
    } // In MyActivity

    @Override
    public void onBackPressed() {
        if(mOnKeyBackPressedListener!=null){
            mOnKeyBackPressedListener.BackPressed();
        }else{
            //super.onBackPressed();
            if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
                backKeyPressedTime = System.currentTimeMillis();
                toast = Toast.makeText(this, "\'뒤로\' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
                toast.show();
                return;
            }
            if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
                this.finish();
                toast.cancel();
            }
        }
    }

    private class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            TextView titleText;
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            switch(menuItem.getItemId()) {
                case R.id.item_1:
                    transaction.replace(R.id.frameLayout, menuFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                    titleText = findViewById(R.id.titleText);
                    titleText.setText("온누리상품권 알리미");
                    break;
                case R.id.item_2:
                    transaction.replace(R.id.frameLayout,  use_ToFragment );
                    transaction.addToBackStack(null);
                    transaction.commit();
                    titleText = findViewById(R.id.titleText);
                    titleText.setText("가맹점 찾기");
                    break;
                case R.id.item_3:
                    transaction.replace(R.id.frameLayout, place_SaleFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                    titleText = findViewById(R.id.titleText);
                    titleText.setText("판매점 찾기");
                    break;
                case R.id.item_4:
                    //transaction.replace(R.id.frameLayout, testfrm).commit();
                    Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://me2.do/GP4I0oAq"));
                    startActivity(myIntent);
                    titleText = findViewById(R.id.titleText);
                    titleText.setText("Q&A");
                    break;
            }
            return true;
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MULTIPLE_PERMISSIONS: {
                if (grantResults.length > 0) {
                    for (int i = 0; i < permissions.length; i++) {
                        if (permissions[i].equals(this.permissions[i])) {
                            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                                showToast_PermissionDeny();
                            }
                        }
                    }
                } else {
                    showToast_PermissionDeny();
                }
                return;
            }
        }

    }
    private void showToast_PermissionDeny() {
        Toast.makeText(this, "권한 요청에 동의 해주셔야 이용 가능합니다. 설정에서 권한 허용 하시기 바랍니다.", Toast.LENGTH_SHORT).show();
        finish();
    }
}