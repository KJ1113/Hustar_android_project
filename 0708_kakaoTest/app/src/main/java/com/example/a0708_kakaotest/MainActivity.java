package com.example.a0708_kakaotest;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.a0708_kakaotest.Android_Class.Init_Calss.Init_Data;
import com.example.a0708_kakaotest.Android_Class.Init_Calss.Init_GPS;
import com.example.a0708_kakaotest.Android_Class.Init_Calss.Init_Permisson;
import com.example.a0708_kakaotest.Fragment.*;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MainActivity extends AppCompatActivity {
    private Fragment_Menu menuFragment;
    private Fragment_Market use_ToFragment;
    private Fragment_Bank place_SaleFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.init();
    }
    private void init(){
        new Init_Permisson(this);
        new Init_GPS(this);
        new Init_Data(this);
        //
        menuFragment = new Fragment_Menu();
        use_ToFragment = new Fragment_Market();
        place_SaleFragment = new Fragment_Bank();
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, menuFragment).commit();
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new ItemSelectedListener());
        //getHashKey();
    }

    private void getHashKey() {
        PackageInfo packageInfo = null;
        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packageInfo == null)
            Log.e("KeyHash", "KeyHash:null");

        for (Signature signature : packageInfo.signatures) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            } catch (NoSuchAlgorithmException e) {
                Log.e("KeyHash", "Unable to get MessageDigest. signature=" + signature, e);
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
                    transaction.replace(R.id.frameLayout, menuFragment).commit();
                    titleText = findViewById(R.id.titleText);
                    titleText.setText("온누리상품권 알리미");
                    break;
                case R.id.item_2:
                    transaction.replace(R.id.frameLayout,  use_ToFragment ).commit();
                    titleText = findViewById(R.id.titleText);
                    titleText.setText("가맹점 찾기");
                    break;
                case R.id.item_3:
                    transaction.replace(R.id.frameLayout, place_SaleFragment).commit();
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
}