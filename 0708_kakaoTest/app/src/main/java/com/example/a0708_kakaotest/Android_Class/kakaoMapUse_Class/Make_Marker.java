package com.example.a0708_kakaotest.Android_Class.kakaoMapUse_Class;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import static com.example.a0708_kakaotest.Android_Class.Init_Calss.Init_GPS.getGPS;

public class Make_Marker {
    private MapView mMapView;

    double latitude ;
    double longitude ;
    public  Make_Marker(MapView mMapView){
        this.mMapView = mMapView;
        latitude=0;
        longitude=0;
    }
    public void cur_pos(int lv) {
        getGPS().getLocation();
        double lat ;
        double longt ;
        if(latitude==0 && longitude==0){
            lat =  getGPS().getLatitude();
            longt = getGPS().getLongitude();
            latitude =lat;
            longitude =longt;
        }
        else{
            lat =latitude;
            longt = longitude;
        }

        MapPoint mapPoint = MapPoint.mapPointWithGeoCoord(lat, longt);
        MapPOIItem marker = new MapPOIItem();
        marker.setItemName("현재위치");
        marker.setTag(1);
        marker.setMapPoint(mapPoint);
        marker.setMarkerType(MapPOIItem.MarkerType.YellowPin);
        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        mMapView.addPOIItem(marker);
        if(lv != 0 ){
            mMapView.setMapCenterPoint(mapPoint, true);
            mMapView.setZoomLevel(lv, true);
        }
    }
    public void add_Market_marker( int no, String name, String add, double latitude, double longitude, String city, String dis, int zoomlv){
        MapPoint mapPoint = MapPoint.mapPointWithGeoCoord(latitude, longitude);
        MapPOIItem marker = new CustomPOIItem_Market(  no,  name,  add,  latitude,  longitude,  city,  dis);
        marker.setItemName(name);
        marker.setTag(0);
        marker.setMapPoint(mapPoint);
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin);
        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        mMapView.addPOIItem(marker);
        mMapView.setMapCenterPoint(mapPoint, true);
        mMapView.setZoomLevel(zoomlv, true);
    }

    public void add_Bank_marker(int no, String city, String dis,  String bankname ,String name ,String num ,String add , double latitude, double longitude  , int ZoomLv){
        MapPoint mapPoint = MapPoint.mapPointWithGeoCoord(latitude, longitude);
        CustomPOIItem_Bank marker = new CustomPOIItem_Bank(  no,  city,  dis,   bankname , name , num , add ,  latitude,  longitude );
        marker.setItemName(bankname + " ( "+name+" )");
        marker.setTag(1);
        marker.setMapPoint(mapPoint);
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin);
        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        mMapView.addPOIItem(marker);
        if(ZoomLv!=0){
            mMapView.setMapCenterPoint(mapPoint, true);
            mMapView.setZoomLevel(ZoomLv, true);
        }
    }
}
