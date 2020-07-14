package com.example.a0708_kakaotest.kakaoMap_Custom;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a0708_kakaotest.R;

import net.daum.mf.map.api.CalloutBalloonAdapter;
import net.daum.mf.map.api.MapPOIItem;


public class CustomCalloutBalloonAdapter implements CalloutBalloonAdapter {
    private final View mCalloutBalloon;
    public CustomCalloutBalloonAdapter(Activity ac) {
        mCalloutBalloon = ac.getLayoutInflater().inflate(R.layout.custom_callout_balloon, null);
    }
    @Override
    public View getCalloutBalloon(MapPOIItem poiItem) {

        return mCalloutBalloon;
    }
    @Override
    public View getPressedCalloutBalloon(MapPOIItem poiItem) {
        return null;
    }
}


