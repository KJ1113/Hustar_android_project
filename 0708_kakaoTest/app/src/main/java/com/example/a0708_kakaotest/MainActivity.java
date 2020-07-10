package com.example.a0708_kakaotest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.a0708_kakaotest.Android_Class.Init_Calss.Init_GPS;
import com.example.a0708_kakaotest.Android_Class.Init_Calss.Init_Permisson;
import com.example.a0708_kakaotest.Fragment.*;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private MenuFragment menuFragment;
    private Use_ToFragment use_ToFragment;
    private Place_SaleFragment place_SaleFragment;
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
        Init_GPS init_GPS = new Init_GPS(this);
        Init_Permisson init_Permisson = new Init_Permisson(this);

        menuFragment = new MenuFragment();
        use_ToFragment = new Use_ToFragment();
        place_SaleFragment = new Place_SaleFragment();
        fragmentManager = getSupportFragmentManager();

        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, menuFragment).commit();
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new ItemSelectedListener());
    }
    private class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            switch(menuItem.getItemId()) {
                case R.id.item_1:
                    transaction.replace(R.id.frameLayout, menuFragment).commit();
                    break;
                case R.id.item_2:
                    transaction.replace(R.id.frameLayout, place_SaleFragment).commit();
                    break;
                case R.id.item_3:
                    transaction.replace(R.id.frameLayout, use_ToFragment).commit();
                    break;
                case R.id.item_4:
                    transaction.replace(R.id.frameLayout, use_ToFragment).commit();
                    break;
            }
            return true;
        }
    }
}