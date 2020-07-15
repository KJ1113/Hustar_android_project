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
        cur_pos(1);
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
        bankList.add("농협");
        bankList.add("우체국");
        bankList.add("새마을금고");
        bankList.add("IBK기업은행");
        arrayAdapter3 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,bankList);
    }
    public void inputdisArray(String city){
        disArrayList = new ArrayList<String>();
        disArrayList.add("시/군/구");
        if(city.equals("서울")){
            disArrayList.add("강남구");
            disArrayList.add("강동구");
            disArrayList.add("강북구");
            disArrayList.add("강서구");
            disArrayList.add("관악구");
            disArrayList.add("광진구");
            disArrayList.add("구로구");
            disArrayList.add("금천구");
            disArrayList.add("노원구");
            disArrayList.add("도봉구");
            disArrayList.add("동대문구");
            disArrayList.add("동작구");
            disArrayList.add("마포구");
            disArrayList.add("서대문구");
            disArrayList.add("서초구");
            disArrayList.add("성동구");
            disArrayList.add("성북구");
            disArrayList.add("송파구");
            disArrayList.add("양천구");
            disArrayList.add("영등포구");
            disArrayList.add("용산구");
            disArrayList.add("은평구");
            disArrayList.add("종로구");
            disArrayList.add("중구");
            disArrayList.add("중랑구");

        }
        else if(city.equals("경기")){
            disArrayList.add("성남시");
            disArrayList.add("화성시");
            disArrayList.add("수원시");
            disArrayList.add("고양시");
            disArrayList.add("김포시");
            disArrayList.add("안양시");
            disArrayList.add("용인시");
            disArrayList.add("의정부시");
            disArrayList.add("평택시");
            disArrayList.add("안산시");
            disArrayList.add("구리시");
            disArrayList.add("광명시");
            disArrayList.add("시흥시");
            disArrayList.add("이천시");
            disArrayList.add("광주시");
            disArrayList.add("파주시");
            disArrayList.add("포천시");
            disArrayList.add("과천시");
            disArrayList.add("군포시");
            disArrayList.add("안성시");
            disArrayList.add("하남시");
            disArrayList.add("오산시");
            disArrayList.add("양주시");
            disArrayList.add("의왕시");
            disArrayList.add("남양주시");
            disArrayList.add("동두천시");
            disArrayList.add("부천시");
            disArrayList.add("여주군");
            disArrayList.add("연천군");
            disArrayList.add("가평군");
            disArrayList.add("양평군");
            disArrayList.add("분당구");
            disArrayList.add("여주시");
        }
        else if(city.equals("인천")){
            disArrayList.add("부평구");
            disArrayList.add("남동구");
            disArrayList.add("서구");
            disArrayList.add("중구");
            disArrayList.add("계양구");
            disArrayList.add("연수구");
            disArrayList.add("동구");
            disArrayList.add("강화군");
            disArrayList.add("남구");
            disArrayList.add("옹진군");
        }
        else if(city.equals("강원")){
            disArrayList.add("춘천시");
            disArrayList.add("강릉시");
            disArrayList.add("원주시");
            disArrayList.add("속초시");
            disArrayList.add("동해시");
            disArrayList.add("홍천군");
            disArrayList.add("횡성군");
            disArrayList.add("영월군");
            disArrayList.add("평창군");
            disArrayList.add("정선군");
            disArrayList.add("철원군");
            disArrayList.add("화천군");
            disArrayList.add("양구군");
            disArrayList.add("인제군");
            disArrayList.add("고성군");
            disArrayList.add("양양군");
            disArrayList.add("삼척시");
            disArrayList.add("태백시");
        }
        else if(city.equals("충남")){
            disArrayList.add("천안시");
            disArrayList.add("논산시");
            disArrayList.add("당진시");
            disArrayList.add("아산시");
            disArrayList.add("서산시");
            disArrayList.add("보령시");
            disArrayList.add("금산군");
            disArrayList.add("계룡시");
            disArrayList.add("공주시");
            disArrayList.add("부여군");
            disArrayList.add("서천군");
            disArrayList.add("청양군");
            disArrayList.add("홍성군");
            disArrayList.add("예산군");
            disArrayList.add("태안군");
            disArrayList.add("당진군");
            disArrayList.add("홍성시");
        }
        else if(city.equals("대전")){
            disArrayList.add("중구");
            disArrayList.add("서구");
            disArrayList.add("대덕구");
            disArrayList.add("동구");
            disArrayList.add("유성구");
        }
        else if(city.equals("충북")){
            disArrayList.add("청주시");
            disArrayList.add("충주시");
            disArrayList.add("제천시");
            disArrayList.add("청원구");
            disArrayList.add("음성군");
            disArrayList.add("옥천군");
            disArrayList.add("진천군");
            disArrayList.add("흥덕구");
            disArrayList.add("보은군");
            disArrayList.add("영동군");
            disArrayList.add("괴산군");
            disArrayList.add("증평군");
            disArrayList.add("단양군");
            disArrayList.add("상당구");
            disArrayList.add("서원구");
            disArrayList.add("청원군");
        }
        else if(city.equals("부산")){
            disArrayList.add("동구");
            disArrayList.add("사상구");
            disArrayList.add("동래구");
            disArrayList.add("강서구");
            disArrayList.add("해운대구");
            disArrayList.add("기장군");
            disArrayList.add("부산진구");
            disArrayList.add("사하구");
            disArrayList.add("중구");
            disArrayList.add("연제구");
            disArrayList.add("금정구");
            disArrayList.add("영도구");
            disArrayList.add("수영구");
            disArrayList.add("남구");
            disArrayList.add("북구");
            disArrayList.add("서구");
        }
        else if(city.equals("울산")){
            disArrayList.add("중구");
            disArrayList.add("남구");
            disArrayList.add("동구");
            disArrayList.add("울주군");
            disArrayList.add("북구");
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
            disArrayList.add("구미시");
            disArrayList.add("포항시");
            disArrayList.add("경주시");
            disArrayList.add("영천시");
            disArrayList.add("안동시");
            disArrayList.add("경산시");
            disArrayList.add("김천시");
            disArrayList.add("영주시");
            disArrayList.add("칠곡군");
            disArrayList.add("군위군");
            disArrayList.add("의성군");
            disArrayList.add("청송군");
            disArrayList.add("영양군");
            disArrayList.add("영덕군");
            disArrayList.add("청도군");
            disArrayList.add("고령군");
            disArrayList.add("성주군");
            disArrayList.add("상주시");
            disArrayList.add("문경시");
            disArrayList.add("예천군");
            disArrayList.add("봉화군");
            disArrayList.add("울진군");
            disArrayList.add("울릉군");
        }
        else if(city.equals("경남")){
            disArrayList.add("창원시");
            disArrayList.add("진주시");
            disArrayList.add("통영시");
            disArrayList.add("사천시");
            disArrayList.add("고성군");
            disArrayList.add("거창군");
            disArrayList.add("밀양시");
            disArrayList.add("양산시");
            disArrayList.add("거제시");
            disArrayList.add("창녕군");
            disArrayList.add("남해군");
            disArrayList.add("김해시");
            disArrayList.add("함안군");
            disArrayList.add("합천군");
            disArrayList.add("의령군");
            disArrayList.add("함양군");
            disArrayList.add("산청군");
            disArrayList.add("하동군");
            disArrayList.add("마산시");
            disArrayList.add("진해시");
            disArrayList.add("중구");
            disArrayList.add("남구");
        }
        else if(city.equals("전남")){
            disArrayList.add("강진군");
            disArrayList.add("고흥군");
            disArrayList.add("광양시");
            disArrayList.add("나주시");
            disArrayList.add("순천시");
            disArrayList.add("무안군");
            disArrayList.add("담양군");
            disArrayList.add("영암군");
            disArrayList.add("목포시");
            disArrayList.add("보성군");
            disArrayList.add("여수시");
            disArrayList.add("영광군");
            disArrayList.add("완도군");
            disArrayList.add("장성군");
            disArrayList.add("장흥군");
            disArrayList.add("함평군");
            disArrayList.add("해남군");
            disArrayList.add("화순군");
            disArrayList.add("곡성군");
            disArrayList.add("구례군");
            disArrayList.add("진도군");
            disArrayList.add("신안군");
        }
        else if(city.equals("광주")){
            disArrayList.add("북구");
            disArrayList.add("동구");
            disArrayList.add("서구");
            disArrayList.add("남구");
            disArrayList.add("광산구");
        }
        else if(city.equals("전북")){
            disArrayList.add("군산시");
            disArrayList.add("전주시");
            disArrayList.add("익산시");
            disArrayList.add("정읍시");
            disArrayList.add("남원시");
            disArrayList.add("진안군");
            disArrayList.add("무주군");
            disArrayList.add("장수군");
            disArrayList.add("임실군");
            disArrayList.add("순창군");
            disArrayList.add("고창군");
            disArrayList.add("부안군");
            disArrayList.add("김제시");
            disArrayList.add("완주군");
        }
        else if(city.equals("제주")){
            disArrayList.add("제주시");
            disArrayList.add("서귀포시");
        }
        else if(city.equals("세종")){
            disArrayList.add("세종시");
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

        if(bank.equals("")){
            if(dis.equals("")){
                for(int i = 1 ; i < maplist.size() ; i++){
                    if(maplist.get(i)[2].equals(city)) {
                        add_maker(Integer.parseInt(maplist.get(i)[0]), maplist.get(i)[2],
                                maplist.get(i)[3], maplist.get(i)[4], maplist.get(i)[5],
                                maplist.get(i)[6], maplist.get(i)[7],
                                Double.parseDouble(maplist.get(i)[9]), Double.parseDouble(maplist.get(i)[8]),8);
                    }
                }
            }
            else{
                for(int i = 1 ; i < maplist.size() ; i++){
                    if(maplist.get(i)[2].equals(city) && maplist.get(i)[3].equals(dis)) {
                        add_maker(Integer.parseInt(maplist.get(i)[0]), maplist.get(i)[2],
                                maplist.get(i)[3], maplist.get(i)[4], maplist.get(i)[5],
                                maplist.get(i)[6], maplist.get(i)[7],
                                Double.parseDouble(maplist.get(i)[9]), Double.parseDouble(maplist.get(i)[8]),6);
                    }
                }
            }
            cur_pos(0);
        }else{ // 은행만으로 검색했을때
            if(dis.equals("") && !city.equals("")){
                for(int i = 1 ; i < maplist.size() ; i++){
                    if(maplist.get(i)[2].equals(city) && maplist.get(i)[4].equals(bank)) {
                        add_maker(Integer.parseInt(maplist.get(i)[0]), maplist.get(i)[2],
                                maplist.get(i)[3], maplist.get(i)[4], maplist.get(i)[5],
                                maplist.get(i)[6], maplist.get(i)[7],
                                Double.parseDouble(maplist.get(i)[9]), Double.parseDouble(maplist.get(i)[8]),0);
                    }
                }
            }
            else if(dis.equals("") && city.equals("")){
                for(int i = 1 ; i < maplist.size() ; i++){
                    if(maplist.get(i)[4].equals(bank)) {
                        add_maker(Integer.parseInt(maplist.get(i)[0]), maplist.get(i)[2],
                                maplist.get(i)[3], maplist.get(i)[4], maplist.get(i)[5],
                                maplist.get(i)[6], maplist.get(i)[7],
                                Double.parseDouble(maplist.get(i)[9]), Double.parseDouble(maplist.get(i)[8]),0);
                    }
                }
            }
            else if(!dis.equals("") && !city.equals("")){
                for(int i = 1 ; i < maplist.size() ; i++){
                    if(maplist.get(i)[2].equals(city) && maplist.get(i)[3].equals(dis) && maplist.get(i)[4].equals(bank)) {
                        add_maker(Integer.parseInt(maplist.get(i)[0]), maplist.get(i)[2],
                                maplist.get(i)[3], maplist.get(i)[4], maplist.get(i)[5],
                                maplist.get(i)[6], maplist.get(i)[7],
                                Double.parseDouble(maplist.get(i)[9]), Double.parseDouble(maplist.get(i)[8]),0);
                    }
                }
            }
            // 현재 위치 중심으로 전국적으로 뷰 보여줌
            // 커밋용
            cur_pos(11);
        }
    }
    //zz
    public void cur_pos(int lv) {
        getGPS().getLocation();
        double latitude =  getGPS().getLatitude();
        double longitude = getGPS().getLongitude();
        MapPoint mapPoint = MapPoint.mapPointWithGeoCoord(latitude, longitude);
        MapPOIItem marker = new MapPOIItem();
        marker.setItemName("현재위치");
        Toast.makeText(getActivity(), latitude + " "+ longitude ,Toast.LENGTH_SHORT).show();
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
    public void add_maker(int no, String city, String dis,  String bankname ,String name ,String num ,String add , double latitude, double longitude  , int ZoomLv){
            MapPoint mapPoint = MapPoint.mapPointWithGeoCoord(latitude, longitude);
            customMapPOIItem_bank marker = new customMapPOIItem_bank(  no,  city,  dis,   bankname , name , num , add ,  latitude,  longitude );
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