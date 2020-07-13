package com.example.a0708_kakaotest.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.a0708_kakaotest.Fragment.MenuFragment_leaf_Fragments.*;
import com.example.a0708_kakaotest.R;

import java.util.ArrayList;
import java.util.List;


public class MenuFragment extends Fragment {

    private View view;
    private Animation animation;
    private ViewPager viewPager;
    private FrameLayout in_fr;
    ViewTreeObserver mGlobalLayoutListener;


    float downX;
    float upX;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_menu, container, false);

        return view;
    }

    public class MeasuredViewPager extends ViewPager {


        public MeasuredViewPager(Context context) {
            super(context);
        }

        public MeasuredViewPager(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            int mode = MeasureSpec.getMode(heightMeasureSpec);
            if (mode == MeasureSpec.UNSPECIFIED || mode == MeasureSpec.AT_MOST) {
                super.onMeasure(widthMeasureSpec, heightMeasureSpec);
                int height = 0;
                for (int i = 0; i < getChildCount(); i++) {
                    View child = getChildAt(i);
                    child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
                    int h = child.getMeasuredHeight();
                    if (h > height) height = h;
                }
                heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
            }
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }

    }
}