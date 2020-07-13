package com.example.a0708_kakaotest.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a0708_kakaotest.R;

import net.daum.mf.map.api.MapView;


public class Use_ToFragment extends Fragment {
    private MapView mapView;
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_use_to, container, false);
        this.map_init();
        return view;
    }
    public void map_init(){
        //mapView = new MapView(getActivity());
        //ViewGroup mapViewContainer = (ViewGroup) view.findViewById(R.id.map_view);
        //mapViewContainer.addView(mapView);
    }
}