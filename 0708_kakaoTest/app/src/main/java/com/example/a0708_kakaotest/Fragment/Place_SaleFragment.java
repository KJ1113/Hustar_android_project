package com.example.a0708_kakaotest.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.a0708_kakaotest.R;

import net.daum.mf.map.api.CalloutBalloonAdapter;
import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;
import java.util.List;
import static com.example.a0708_kakaotest.Android_Class.Init_Calss.Init_Data.get_bankData;
import static com.example.a0708_kakaotest.Android_Class.Init_Calss.Init_GPS.getGPS;

public class Place_SaleFragment extends Fragment {
    private MapView mapView;
    private View view;
    public List<String[]> maplist;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_use_to, container, false);
        this.map_init();
        return view;
    }
    public void map_init(){
        mapView = new MapView(getActivity());
        mapView.setCalloutBalloonAdapter(new CustomCalloutBalloonAdapter());
        ViewGroup mapViewContainer = (ViewGroup) view.findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);
        cur_pos();
        input_mapMaker(10);
        //add_maker(35.21200140096,128.588723077959);
    }
    public void cur_pos() {
        double latitude = getGPS().getLatitude();
        double longitude = getGPS().getLongitude();
        MapPoint mapPoint = MapPoint.mapPointWithGeoCoord(latitude, longitude);
        MapPOIItem marker = new MapPOIItem();
        mapView.setMapCenterPoint(mapPoint, true);
        mapView.setZoomLevel(1, true);
        marker.setItemName("현재위치\n 온누리 사용가능 \n");
        marker.setTag(0);

        marker.setMapPoint(mapPoint);
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin);
        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        mapView.addPOIItem(marker);

    }
    public void input_mapMaker(int length){
        maplist = get_bankData();
        for(int i = 1 ; i < 50; i++){
            add_maker( Double.parseDouble( maplist.get(i)[9]),Double.parseDouble(maplist.get(i)[8]), maplist.get(i)[4] );
        }
    }
    public void add_maker(double latitude, double longitude ,String name){
        //128.588723077959,35.21200140096
        MapPoint mapPoint = MapPoint.mapPointWithGeoCoord(latitude, longitude);
        MapPOIItem marker = new MapPOIItem();
        marker.setItemName(name);
        marker.setTag(0);

        marker.setMapPoint(mapPoint);
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin);
        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        mapView.addPOIItem(marker);
    }


    class CustomCalloutBalloonAdapter implements CalloutBalloonAdapter {
        private final View mCalloutBalloon;

        public CustomCalloutBalloonAdapter() {
            mCalloutBalloon = getLayoutInflater().inflate(R.layout.custom_callout_balloon, null);
        }

        @Override
        public View getCalloutBalloon(MapPOIItem poiItem) {
            //((ImageView) mCalloutBalloon.findViewById(R.id.imageView)).setImageResource(R.drawable.balloon);
            //((TextView) mCalloutBalloon.findViewById(R.id.text_name)).setText(poiItem.getItemName());
            //((TextView) mCalloutBalloon.findViewById(R.id.text_Text)).setText("Custom CalloutBalloon");
            return mCalloutBalloon;
        }

        @Override
        public View getPressedCalloutBalloon(MapPOIItem poiItem) {
            return null;
        }
    }
}