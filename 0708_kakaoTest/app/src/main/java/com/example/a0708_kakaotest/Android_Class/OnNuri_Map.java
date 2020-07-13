package com.example.a0708_kakaotest.Android_Class;

import android.app.Activity;
import android.widget.Toast;
import com.example.a0708_kakaotest.kakaoMap_Custom.CustomCalloutBalloonAdapter;
import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;
import java.util.List;

import static com.example.a0708_kakaotest.Android_Class.Init_Calss.Init_Data.get_bankData;
import static com.example.a0708_kakaotest.Android_Class.Init_Calss.Init_GPS.getGPS;

public class OnNuri_Map extends MapView {
    private List<String[]> maplist;
    private Activity ac;
    private double latitude;
    private double longitude;

    public OnNuri_Map(Activity activity) {
        super(activity);
        this.ac = activity;
        this.setCalloutBalloonAdapter(new CustomCalloutBalloonAdapter(ac));
    }
    public void input_mapMaker(){
        maplist = get_bankData();
        for(int i = 1 ; i < 80; i++){
            add_maker( Double.parseDouble( maplist.get(i)[9]),Double.parseDouble(maplist.get(i)[8]), maplist.get(i)[4] );
        }
    }
    public void cur_pos() {
        getGPS().getLocation();
        latitude =   getGPS().getLatitude();
        longitude =  getGPS().getLongitude();
        MapPoint mapPoint = MapPoint.mapPointWithGeoCoord(latitude, longitude);
        MapPOIItem marker = new MapPOIItem();
        this.setMapCenterPoint(mapPoint, true);
        this.setZoomLevel(1, true);
        marker.setItemName("현재위치");
        Toast.makeText(ac, latitude + " "+ longitude ,Toast.LENGTH_SHORT).show();
        marker.setTag(0);

        marker.setMapPoint(mapPoint);
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin);
        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        this.addPOIItem(marker);
    }

    public void add_maker(double latitude, double longitude ,String name){
        MapPoint mapPoint = MapPoint.mapPointWithGeoCoord(latitude, longitude);

        MapPOIItem marker = new MapPOIItem();
        marker.setItemName(name);
        marker.setTag(0);
        marker.setMapPoint(mapPoint);
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin);
        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        this.addPOIItem(marker);
    }

}
