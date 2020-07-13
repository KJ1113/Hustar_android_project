package com.example.a0708_kakaotest.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.a0708_kakaotest.R;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import static com.example.a0708_kakaotest.Android_Class.Init_Calss.Init_GPS.getGPS;

public class Place_SaleFragment extends Fragment {
    private MapView mapView;
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_use_to, container, false);
        //this.map_init();
        return view;
    }
    public void map_init(){
        //mapView = new MapView(getActivity());
        //ViewGroup mapViewContainer = (ViewGroup) view.findViewById(R.id.map_view);
        //mapViewContainer.addView(mapView);
        cur_pos();
    }
    public void cur_pos() {
        double latitude = getGPS().getLatitude();
        double longitude = getGPS().getLongitude();
        Toast.makeText(getActivity(), "현재위치 \n위도 " + latitude + "\n경도 " + longitude, Toast.LENGTH_LONG).show();

        MapPoint mapPoint = MapPoint.mapPointWithGeoCoord(latitude, longitude);
        MapPOIItem marker = new MapPOIItem();
        mapView.setMapCenterPoint(mapPoint, true);
        marker.setItemName("현재위치");
        marker.setTag(0);
        marker.setMapPoint(mapPoint);
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin);
        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        mapView.addPOIItem(marker);
    }
}