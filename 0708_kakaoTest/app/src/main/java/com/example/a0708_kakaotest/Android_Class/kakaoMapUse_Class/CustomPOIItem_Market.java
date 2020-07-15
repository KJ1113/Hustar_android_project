package com.example.a0708_kakaotest.Android_Class.kakaoMapUse_Class;

import net.daum.mf.map.api.MapPOIItem;
public class CustomPOIItem_Market extends MapPOIItem {
    // 시장명,소재지도로명주소,위도,경도,시/도,시/군/구
    public int no;
    public String name;
    public String add;
    double latitude;
    double longitude;
    public String city;
    public String dis;
    public CustomPOIItem_Market(int no, String name, String add, double latitude, double longitude, String city, String dis) {
        this.no = no;
        this.name = name;
        this.add = add;
        this.latitude = latitude;
        this.longitude = longitude;
        this.city = city;
        this.dis = dis;
    }
}
