package com.example.a0708_kakaotest.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a0708_kakaotest.Android_Class.menu_FragmentUse_Class.ViewHolderPage;
import com.example.a0708_kakaotest.Android_Class.menu_FragmentUse_Class.ImgDataPage;
import com.example.a0708_kakaotest.R;

import java.util.ArrayList;


public class Fragment_Menu extends Fragment {
    private View view;
    private ViewPager2 viewPager2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_menu, container, false);
        init();
        return view;
    }
    public void init(){
        ArrayList<ImgDataPage> list = new ArrayList<>();
        list.add(new ImgDataPage(R.drawable.backimg1));
        list.add(new ImgDataPage(R.drawable.backimg2));
        list.add(new ImgDataPage(R.drawable.backimg3));
        viewPager2 = view.findViewById(R.id.viewPager);
        viewPager2.setAdapter(new ViewPagerAdapter(list));
    }
    public class ViewPagerAdapter extends RecyclerView.Adapter<ViewHolderPage> {
        private ArrayList<ImgDataPage> listData;
        ViewPagerAdapter(ArrayList<ImgDataPage> data) {
            this.listData = data;
        }
        @Override
        public ViewHolderPage onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            View view = LayoutInflater.from(context).inflate(R.layout.item_viewpage, parent, false);
            return new ViewHolderPage(view);
        }
        @Override
        public void onBindViewHolder(ViewHolderPage holder, int position) {
            if(holder instanceof ViewHolderPage){
                ViewHolderPage viewHolder = (ViewHolderPage) holder;
                viewHolder.onBind(listData.get(position));
            }
        }
        @Override
        public int getItemCount() {
            return listData.size();
        }
    }
}