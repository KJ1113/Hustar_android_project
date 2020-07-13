package com.example.a0708_kakaotest.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.a0708_kakaotest.Fragment.MenuFragment_leaf_Fragments.*;
import com.example.a0708_kakaotest.R;

import java.util.ArrayList;
import java.util.List;


public class MenuFragment extends Fragment {

    private View view;
    private ViewPager mViewPager;
    private PagerAdapter pagerAdapter;

    private Leaf_Fragment_1 leaf_Fragment_1 = new Leaf_Fragment_1();
    private Leaf_Fragment_2 leaf_Fragment_2 = new Leaf_Fragment_2();
    private Leaf_Fragment_3 leaf_Fragment_3 = new Leaf_Fragment_3();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_menu, container, false);
        return view;
    }
    public void setupViewPager(ViewPager viewPager) {
        pagerAdapter.addFragment(leaf_Fragment_1);
        pagerAdapter.addFragment(leaf_Fragment_2);
        pagerAdapter.addFragment(leaf_Fragment_3);
        viewPager.setAdapter(pagerAdapter);
    }
    public class PagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        public void addFragment(Fragment fragment){
            mFragmentList.add(fragment);
        }
        public PagerAdapter(FragmentManager fm){
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }
        @Override
        public int getCount() {
            return mFragmentList.size() ;
        }
    }
}