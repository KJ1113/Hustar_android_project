package com.example.a0708_kakaotest.Fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a0708_kakaotest.Android_Class.customMapPOIItem_use;
import com.example.a0708_kakaotest.R;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import net.daum.mf.map.api.CalloutBalloonAdapter;
import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.util.ArrayList;
import java.util.List;
import static com.example.a0708_kakaotest.Android_Class.Init_Calss.Init_Data.get_bankData;
import static com.example.a0708_kakaotest.Android_Class.Init_Calss.Init_Data.get_useData;
import static com.example.a0708_kakaotest.Android_Class.Init_Calss.Init_GPS.getGPS;
public class Use_ToFragment extends Fragment implements MapView.MapViewEventListener, MapView.POIItemEventListener{
    private SlidingUpPanelLayout slidview;
    private MapView mMapView;
    private View view;
    private ListView listview ;
    private List<String[]> maplist;
    private Spinner spinner_1;
    private Spinner spinner_2;
    private Button button_1;
    private Button button_2;
    private ArrayList cityArrayList = new ArrayList<String>();
    private ArrayList disArrayList = new ArrayList<String>();
    ArrayAdapter<String> arrayAdapter;
    ArrayAdapter<String> arrayAdapter2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_use_to, container, false);
        slidview = view.findViewById(R.id.slidview);
        this.map_init();
        return view;
    }
    public void disArrayListinit(){
        disArrayList = new ArrayList<String>();
        disArrayList.add("시/군/구");
        arrayAdapter2 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,disArrayList);
    }
    public void cityArrayListinit(){
        cityArrayList = new ArrayList<String>();
        cityArrayList.add("시/도");
        cityArrayList.add("서울");
        cityArrayList.add("경기");
        cityArrayList.add("인천");
        cityArrayList.add("강원");
        cityArrayList.add("충남");
        cityArrayList.add("대전");
        cityArrayList.add("충북");
        cityArrayList.add("부산");
        cityArrayList.add("울산");
        cityArrayList.add("대구");
        cityArrayList.add("경북");
        cityArrayList.add("경남");
        cityArrayList.add("전남");
        cityArrayList.add("광주");
        cityArrayList.add("전북");
        cityArrayList.add("제주");
        cityArrayList.add("세종");
        arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,cityArrayList);
    }
    public void inputdisArray(String city){
        disArrayList = new ArrayList<String>();
        disArrayList.add("시/군/구");
        //

        //
        arrayAdapter2 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,disArrayList);
        spinner_2.setAdapter(arrayAdapter2);
    }
    public void map_init(){
        cityArrayListinit();
        disArrayListinit();
        button_1 = view.findViewById(R.id.button_1);
        button_2 = view.findViewById(R.id.button_2);
        spinner_1 = view.findViewById(R.id.spinner_1);
        spinner_2 = view.findViewById(R.id.spinner_2);
        slidview = view.findViewById(R.id.slidview);
        listview = view.findViewById(R.id.listView);
        mMapView = view.findViewById(R.id.map_view);
        mMapView.setMapViewEventListener(this);
        mMapView.setPOIItemEventListener(this);
        button_1.setOnClickListener(new buttonOnclick_Select());
        button_2.setOnClickListener(new button2Onclick_Select());
        maplist = get_useData();
        cur_pos();
        //input_mapMaker();
        //Toast.makeText(getActivity(),"사이즈 : " + maplist.size() , Toast.LENGTH_SHORT).show();
    }
    private class buttonOnclick_Select implements Button.OnClickListener{
        @Override
        public void onClick(View view) {
            String text = spinner_1.getSelectedItem().toString();
            if(text.equals("시/도")){
                Toast.makeText(getActivity(),"시/도 를 설정해주세요",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getActivity(),text + "데이터를 불러오는중..",Toast.LENGTH_SHORT).show();
                //inputdisArray(text);
            }
        }
    }
    private class button2Onclick_Select implements Button.OnClickListener{
        @Override
        public void onClick(View view) {
            String city = spinner_1.getSelectedItem().toString();
            String dis  = spinner_2.getSelectedItem().toString();
            if(city.equals("시/도")){
                Toast.makeText(getActivity(),"시/도 를 먼저 설정해주세요",Toast.LENGTH_SHORT).show();
            }else if(dis.equals("시/군/구")) {
                input_mapMaker(city, "");
            }
            else {
                input_mapMaker(city, dis);
            }
        }
    }

    public void input_mapMaker(String city ,String dis){
        mMapView.removeAllPOIItems();
        cur_pos();
        if(dis.equals("")){
            for(int i = 1 ; i < maplist.size() ; i++){
                if(maplist.get(i)[5].equals(city)) {
                    add_maker(Integer.parseInt(maplist.get(i)[0]) ,maplist.get(i)[1],
                            maplist.get(i)[2] ,Double.parseDouble(maplist.get(i)[3]) ,Double.parseDouble(maplist.get(i)[4 ]) ,maplist.get(i)[5] ,maplist.get(i)[6]);
                }
            }
        }
        else{
            for(int i = 1 ; i < maplist.size() ; i++){
                if(maplist.get(i)[5].equals(city) && maplist.get(i)[6].equals(dis)) {
                    add_maker(Integer.parseInt(maplist.get(i)[0]) ,maplist.get(i)[1],
                            maplist.get(i)[2] ,Double.parseDouble(maplist.get(i)[3]) ,Double.parseDouble(maplist.get(i)[4 ]) ,maplist.get(i)[5] ,maplist.get(i)[6]);
                }
            }
        }
    }
    public void cur_pos() {
        getGPS().getLocation();
        double latitude =   getGPS().getLatitude();
        double longitude =  getGPS().getLongitude();
        MapPoint mapPoint = MapPoint.mapPointWithGeoCoord(latitude, longitude);
        MapPOIItem marker = new MapPOIItem();
        mMapView.setMapCenterPoint(mapPoint, true);
        mMapView.setZoomLevel(1, true);
        marker.setItemName("현재위치");
        marker.setTag(0);
        marker.setMapPoint(mapPoint);
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin);
        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        mMapView.addPOIItem(marker);
    }
    public void add_maker( int no, String name, String add, double latitude, double longitude, String city, String dis){
        MapPoint mapPoint = MapPoint.mapPointWithGeoCoord(latitude, longitude);
        MapPOIItem marker = new customMapPOIItem_use(  no,  name,  add,  latitude,  longitude,  city,  dis);
        marker.setItemName(name);
        marker.setTag(0);
        marker.setMapPoint(mapPoint);
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin);
        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        mMapView.addPOIItem(marker);
    }
    @Override
    public void onMapViewInitialized(MapView mapView) { }
    @Override
    public void onMapViewCenterPointMoved(MapView mapView, MapPoint mapPoint) { }
    @Override
    public void onMapViewZoomLevelChanged(MapView mapView, int i) { }
    @Override
    public void onMapViewSingleTapped(MapView mapView, MapPoint mapPoint) { }
    @Override
    public void onMapViewDoubleTapped(MapView mapView, MapPoint mapPoint) { }
    @Override
    public void onMapViewLongPressed(MapView mapView, MapPoint mapPoint) { }
    @Override
    public void onMapViewDragStarted(MapView mapView, MapPoint mapPoint) { }
    @Override
    public void onMapViewDragEnded(MapView mapView, MapPoint mapPoint) { }
    @Override
    public void onMapViewMoveFinished(MapView mapView, MapPoint mapPoint) { }
    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem, MapPOIItem.CalloutBalloonButtonType calloutBalloonButtonType) {}
    @Override
    public void onDraggablePOIItemMoved(MapView mapView, MapPOIItem mapPOIItem, MapPoint mapPoint) {}
    @Override
    public void onPOIItemSelected(MapView mapView, MapPOIItem mapPOIItem) { }
    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem) {
        if(mapPOIItem instanceof customMapPOIItem_use){
            customMapPOIItem_use item = (customMapPOIItem_use)mapPOIItem;
            String[] values = new String[] {"시장명 : " + item.name, "주소 : " + item.add,"시/도 : "+ item.city, "시/군/구 : " + item.dis};
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, values);
            listview.setAdapter(adapter);
            slidview.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
        }
    }
}