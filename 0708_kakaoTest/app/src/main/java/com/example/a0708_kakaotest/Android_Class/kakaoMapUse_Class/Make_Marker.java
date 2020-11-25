package com.example.a0708_kakaotest.Android_Class.kakaoMapUse_Class;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import static com.example.a0708_kakaotest.Android_Class.Init_Calss.Init_GPS.getGPS;

public class Make_Marker {
    private MapView mMapView;
    private MapPoint mapPoint;
    private MapPOIItem current_mapPOIItem;
    private CustomPOIItem_Market customPOIItem_Market;
    private CustomPOIItem_Bank customPOIItem_Bank;

    double latitude ;
    double longitude ;
    public  Make_Marker(MapView mMapView){
        this.mMapView = mMapView;
        latitude=0;
        longitude=0;
    }
    public MapPOIItem get_current_mapPOIItem(){
        return current_mapPOIItem;
    }
    public void add_Current_marker(int lv) {
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

        mapPoint = MapPoint.mapPointWithGeoCoord(lat, longt);
        current_mapPOIItem = new MapPOIItem();
        current_mapPOIItem.setItemName("현재위치");
        current_mapPOIItem.setTag(1);
        current_mapPOIItem.setMapPoint(mapPoint);
        current_mapPOIItem.setMarkerType(MapPOIItem.MarkerType.YellowPin);
        current_mapPOIItem.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        mMapView.addPOIItem(current_mapPOIItem);
        if(lv != 0 ){
            mMapView.setMapCenterPoint(mapPoint, true);
            mMapView.setZoomLevel(lv, true);
        }
    }
    public void add_Market_marker( int no, String name, String add, double latitude, double longitude, String city, String dis, int zoomlv){
        mapPoint = MapPoint.mapPointWithGeoCoord(latitude, longitude);
        customPOIItem_Market = new CustomPOIItem_Market(  no,  name,  add,  latitude,  longitude,  city,  dis);
        customPOIItem_Market.setItemName(name);
        customPOIItem_Market.setTag(0);
        customPOIItem_Market.setMapPoint(mapPoint);
        customPOIItem_Market.setMarkerType(MapPOIItem.MarkerType.BluePin);
        customPOIItem_Market.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        mMapView.addPOIItem(customPOIItem_Market);
        mMapView.setMapCenterPoint(mapPoint, true);
        mMapView.setZoomLevel(zoomlv, true);
    }

    public void add_Bank_marker(int no, String city, String dis,  String bankname ,String name ,String num ,String add , double latitude, double longitude  , int ZoomLv){
        mapPoint = MapPoint.mapPointWithGeoCoord(latitude, longitude);
        customPOIItem_Bank = new CustomPOIItem_Bank(  no,  city,  dis,   bankname , name , num , add ,  latitude,  longitude );
        customPOIItem_Bank.setItemName(bankname + " ( "+name+" )");
        customPOIItem_Bank.setTag(1);
        customPOIItem_Bank.setMapPoint(mapPoint);
        customPOIItem_Bank.setMarkerType(MapPOIItem.MarkerType.BluePin);
        customPOIItem_Bank.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        mMapView.addPOIItem(customPOIItem_Bank);
        if(ZoomLv!=0){
            mMapView.setMapCenterPoint(mapPoint, true);
            mMapView.setZoomLevel(ZoomLv, true);
        }
    }
}
