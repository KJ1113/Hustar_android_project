package com.example.a0708_kakaotest.Fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.a0708_kakaotest.Android_Class.customMapPOIItem_bank;
import com.example.a0708_kakaotest.R;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;
import java.util.ArrayList;
import java.util.List;
import static com.example.a0708_kakaotest.Android_Class.Init_Calss.Init_Data.get_bankData;
import static com.example.a0708_kakaotest.Android_Class.Init_Calss.Init_Data.get_useData;
import static com.example.a0708_kakaotest.Android_Class.Init_Calss.Init_GPS.getGPS;

public class Place_SaleFragment extends Fragment implements MapView.MapViewEventListener, MapView.POIItemEventListener{
    private SlidingUpPanelLayout slidview;
    private MapView mMapView;
    private View view;
    private List<String[]> maplist;
    private ListView listview ;
    private Spinner spinner_1;
    private Spinner spinner_2;
    private Spinner spinner_3;
    private Button button_1;
    private Button button_2;
    private Button button_3;
    ArrayAdapter<String> arrayAdapter;
    ArrayAdapter<String> arrayAdapter2;
    ArrayAdapter<String> arrayAdapter3;
    private ArrayList cityArrayList = new ArrayList<String>();
    private ArrayList disArrayList = new ArrayList<String>();
    private ArrayList bankList = new ArrayList<String>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_place_sale, container, false);
        this.map_init();
        return view;
    }
    public void map_init(){
        cityArrayListinit();
        disArrayListinit();
        bankArrayListinit();
        button_1 = view.findViewById(R.id.button_1);
        button_2 = view.findViewById(R.id.button_2);
        button_3 = view.findViewById(R.id.button_3);
        spinner_1 = view.findViewById(R.id.spinner_1);
        spinner_2 = view.findViewById(R.id.spinner_2);
        spinner_3 = view.findViewById(R.id.spinner_3);
        slidview = view.findViewById(R.id.slidview);
        listview = view.findViewById(R.id.listView);
        mMapView = view.findViewById(R.id.map_view);
        spinner_1.setAdapter(arrayAdapter);
        spinner_2.setAdapter(arrayAdapter2);
        spinner_3.setAdapter(arrayAdapter3);
        mMapView.setMapViewEventListener(this);
        mMapView.setPOIItemEventListener(this);
        button_1.setOnClickListener(new buttonOnclick_Select());
        button_2.setOnClickListener(new button2Onclick_Select());
        button_3.setOnClickListener(new button3Onclick_Select());
        maplist = get_bankData();
        cur_pos();
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
                inputdisArray(text);
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
                input_mapMaker(city, "","");
            }
            else {
                input_mapMaker(city, dis,"");
            }
        }
    }
    private class button3Onclick_Select implements Button.OnClickListener{
        @Override
        public void onClick(View view) {
            String city = spinner_1.getSelectedItem().toString();
            String dis  = spinner_2.getSelectedItem().toString();
            String bank  = spinner_3.getSelectedItem().toString();
            if(bank.equals("은행")){
                Toast.makeText(getActivity(),"은행을 먼저 설정해주세요",Toast.LENGTH_SHORT).show();
            }else {
                if( !city.equals("시/도") && dis.equals("시/군/구") && !bank.equals("은행")){
                    input_mapMaker(city , "" ,bank );
                }
                else if (city.equals("시/도") && dis.equals("시/군/구") && !bank.equals("은행")) {
                    input_mapMaker("" , "" ,bank );
                }
                else if(!city.equals("시/도") && !dis.equals("시/군/구") && !bank.equals("은행")){
                    input_mapMaker(city , dis ,bank );
                }
            }
        }
    }
    public void disArrayListinit(){
        disArrayList = new ArrayList<String>();
        disArrayList.add("시/군/구");
        arrayAdapter2 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,disArrayList);
    }
    public void bankArrayListinit(){
        bankList = new ArrayList<String>();
        bankList.add("은행");
        bankList.add("신한은행");
        bankList.add("우리은행");
        bankList.add("국민은행");
        bankList.add("대구은행");
        bankList.add("부산은행");
        bankList.add("광주은행");
        bankList.add("전북은행");
        bankList.add("경남은행");
        bankList.add("수협");
        bankList.add("신협");
        bankList.add("농협은행");
        bankList.add("우체국");
        bankList.add("새마을금고");
        bankList.add("IBK기업은행");
        arrayAdapter3 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,bankList);
    }
    public void inputdisArray(String city){
        disArrayList = new ArrayList<String>();
        disArrayList.add("시/군/구");
        if(city.equals("서울")){

        }
        else if(city.equals("경기")){

        }
        else if(city.equals("인천")){

        }
        else if(city.equals("강원")){

        }
        else if(city.equals("충남")){

        }
        else if(city.equals("대전")){

        }
        else if(city.equals("충북")){

        }
        else if(city.equals("부산")){

        }
        else if(city.equals("울산")){

        }
        else if(city.equals("대구")){
            disArrayList.add("중구");
            disArrayList.add("동구");
            disArrayList.add("서구");
            disArrayList.add("남구");
            disArrayList.add("북구");
            disArrayList.add("수성구");
            disArrayList.add("달서구");
            disArrayList.add("달성군");
        }
        else if(city.equals("경북")){

        }
        else if(city.equals("경남")){

        }
        else if(city.equals("전남")){

        }
        else if(city.equals("광주")){

        }
        else if(city.equals("전북")){

        }
        else if(city.equals("제주")){

        }
        else if(city.equals("세종")){

        }
        arrayAdapter2 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,disArrayList);
        spinner_2.setAdapter(arrayAdapter2);
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
    public void input_mapMaker(String city ,String dis ,String bank){
        mMapView.removeAllPOIItems();
        cur_pos();
        if(bank.equals("")){
            if(dis.equals("")){
                for(int i = 1 ; i < maplist.size() ; i++){
                    if(maplist.get(i)[2].equals(city)) {
                        add_maker(Integer.parseInt(maplist.get(i)[0]), maplist.get(i)[2],
                                maplist.get(i)[3], maplist.get(i)[4], maplist.get(i)[5],
                                maplist.get(i)[6], maplist.get(i)[7],
                                Double.parseDouble(maplist.get(i)[9]), Double.parseDouble(maplist.get(i)[8]));
                    }
                }
            }
            else{
                for(int i = 1 ; i < maplist.size() ; i++){
                    if(maplist.get(i)[2].equals(city) && maplist.get(i)[3].equals(dis)) {
                        add_maker(Integer.parseInt(maplist.get(i)[0]), maplist.get(i)[2],
                                maplist.get(i)[3], maplist.get(i)[4], maplist.get(i)[5],
                                maplist.get(i)[6], maplist.get(i)[7],
                                Double.parseDouble(maplist.get(i)[9]), Double.parseDouble(maplist.get(i)[8]));
                    }
                }
            }
        }else{
            if(dis.equals("") && !city.equals("")){
                for(int i = 1 ; i < maplist.size() ; i++){
                    if(maplist.get(i)[2].equals(city) && maplist.get(i)[4].equals(bank)) {
                        add_maker(Integer.parseInt(maplist.get(i)[0]), maplist.get(i)[2],
                                maplist.get(i)[3], maplist.get(i)[4], maplist.get(i)[5],
                                maplist.get(i)[6], maplist.get(i)[7],
                                Double.parseDouble(maplist.get(i)[9]), Double.parseDouble(maplist.get(i)[8]));
                    }
                }
            }
            else if(dis.equals("") && city.equals("")){
                for(int i = 1 ; i < maplist.size() ; i++){
                    if(maplist.get(i)[4].equals(bank)) {
                        add_maker(Integer.parseInt(maplist.get(i)[0]), maplist.get(i)[2],
                                maplist.get(i)[3], maplist.get(i)[4], maplist.get(i)[5],
                                maplist.get(i)[6], maplist.get(i)[7],
                                Double.parseDouble(maplist.get(i)[9]), Double.parseDouble(maplist.get(i)[8]));
                    }
                }
            }
            else if(!dis.equals("") && !city.equals("")){
                for(int i = 1 ; i < maplist.size() ; i++){
                    if(maplist.get(i)[2].equals(city) && maplist.get(i)[3].equals(dis) && maplist.get(i)[4].equals(bank)) {
                        add_maker(Integer.parseInt(maplist.get(i)[0]), maplist.get(i)[2],
                                maplist.get(i)[3], maplist.get(i)[4], maplist.get(i)[5],
                                maplist.get(i)[6], maplist.get(i)[7],
                                Double.parseDouble(maplist.get(i)[9]), Double.parseDouble(maplist.get(i)[8]));
                    }
                }
            }
        }
    }
    public void cur_pos() {
        getGPS().getLocation();
        double latitude =  getGPS().getLatitude();
        double longitude = getGPS().getLongitude();
        MapPoint mapPoint = MapPoint.mapPointWithGeoCoord(latitude, longitude);
        MapPOIItem marker = new MapPOIItem();
        mMapView.setMapCenterPoint(mapPoint, true);
        mMapView.setZoomLevel(1, true);
        marker.setItemName("현재위치");
        Toast.makeText(getActivity(), latitude + " "+ longitude ,Toast.LENGTH_SHORT).show();
        marker.setTag(1);
        marker.setMapPoint(mapPoint);
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin);
        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        mMapView.addPOIItem(marker);
    }
    public void add_maker(int no, String city, String dis,  String bankname ,String name ,String num ,String add , double latitude, double longitude  ){
            MapPoint mapPoint = MapPoint.mapPointWithGeoCoord(latitude, longitude);
            customMapPOIItem_bank marker = new customMapPOIItem_bank(  no,  city,  dis,   bankname , name , num , add ,  latitude,  longitude );
            marker.setItemName(bankname);
            marker.setTag(1);
            marker.setMapPoint(mapPoint);
            marker.setMarkerType(MapPOIItem.MarkerType.BluePin);
            marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
            mMapView.addPOIItem(marker);
    }
    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem ) {
        if(mapPOIItem instanceof customMapPOIItem_bank){
            customMapPOIItem_bank item = (customMapPOIItem_bank)mapPOIItem;
            String[] values = new String[] {"은행명: " + item.bankname, "지점명 : " + item.name , "연락처 : "+ item.num, "주소 : " + item.add };
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, values);
            listview.setAdapter(adapter);
            slidview.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
        }
    }
    @Override
    public void onPOIItemSelected(MapView mapView, MapPOIItem mapPOIItem) { }
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

}