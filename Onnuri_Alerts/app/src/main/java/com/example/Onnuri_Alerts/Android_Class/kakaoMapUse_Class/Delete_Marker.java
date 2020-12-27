package com.example.Onnuri_Alerts.Android_Class.kakaoMapUse_Class;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapView;

public class Delete_Marker {
    private MapView mMapView;
    public Delete_Marker(MapView mMapView){
        this.mMapView = mMapView;
    }
    public void del_Current(MapPOIItem mapPOIItem){
        mMapView.removePOIItem(mapPOIItem);
    }
}
