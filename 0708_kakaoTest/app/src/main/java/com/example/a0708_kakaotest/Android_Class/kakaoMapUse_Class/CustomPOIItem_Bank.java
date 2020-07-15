package com.example.a0708_kakaotest.Android_Class.kakaoMapUse_Class;

import net.daum.mf.map.api.MapPOIItem;

public class CustomPOIItem_Bank extends MapPOIItem {
    public int no;
    public String name;
    public String add;
    double latitude;
    double longitude;
    public String city;
    public String dis;
    public String bankname;
    public String num;
    public CustomPOIItem_Bank(int no, String city, String dis, String bankname , String name , String num , String add , double latitude, double longitude ) {
        this.no = no;
        this.name = name;
        this.add = add;
        this.latitude = latitude;
        this.longitude = longitude;
        this.city = city;
        this.dis = dis;
        this.bankname =bankname;
        this.num =num;
    }
}
