package com.example.a0708_kakaotest.Fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.a0708_kakaotest.Android_Class.kakaoMapUse_Class.CustomPOIItem_Bank;
import com.example.a0708_kakaotest.Android_Class.kakaoMapUse_Class.Make_Marker;
import com.example.a0708_kakaotest.Android_Class.menu_FragmentUse_Class.Return_Citys_Array;
import com.example.a0708_kakaotest.R;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;
import java.util.ArrayList;
import java.util.List;
import static com.example.a0708_kakaotest.Android_Class.Init_Calss.Init_Data.get_bankData;
import static com.example.a0708_kakaotest.Android_Class.Init_Calss.Init_GPS.getGPS;

public class Fragment_Bank extends Fragment implements MapView.MapViewEventListener, MapView.POIItemEventListener{
    private SlidingUpPanelLayout slidview;
    private MapView mMapView;
    private View view;
    private List<String[]> maplist;
    private ListView listview ;
    private Spinner spinner_1;
    private Spinner spinner_2;
    private Spinner spinner_3;
    private Button button_2;
    private Make_Marker make_marker;
    ArrayAdapter<String> arrayAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_bank, container, false);
        this.map_init();
        return view;
    }
    public void map_init(){
        button_2 = view.findViewById(R.id.button_1);
        spinner_1 = view.findViewById(R.id.spinner_1);
        spinner_2 = view.findViewById(R.id.spinner_2);
        spinner_3 = view.findViewById(R.id.spinner_3);
        slidview = view.findViewById(R.id.slidview);
        listview = view.findViewById(R.id.listView);
        mMapView = view.findViewById(R.id.map_view);
        cityArrayListinit();
        disArrayListinit();
        bankArrayListinit();
        mMapView.setMapViewEventListener(this);
        mMapView.setPOIItemEventListener(this);
        button_2.setOnClickListener(new button2Onclick_Select());
        spinner_1.setOnItemSelectedListener(new spinner_1_SelectListener());
        maplist = get_bankData();
        make_marker =new Make_Marker(mMapView);
        make_marker.cur_pos(1);
    }
    private class spinner_1_SelectListener implements Spinner.OnItemSelectedListener{
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String text = spinner_1.getSelectedItem().toString();
            if(!text.equals("시/도")){
                inputdisArray(text);
            }
        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }
    private class button2Onclick_Select implements Button.OnClickListener{
        @Override
        public void onClick(View view) {
            String city = spinner_1.getSelectedItem().toString();
            String dis  = spinner_2.getSelectedItem().toString();
            String bank  = spinner_3.getSelectedItem().toString();
            if(city.equals("시/도") && dis.equals("시/군/구") && bank.equals("은행")){
                Toast.makeText(getActivity(), "검색 설정을 완료 해주세요", Toast.LENGTH_SHORT).show();
                return;
            }
            else{
                if( !city.equals("시/도") && dis.equals("시/군/구") && bank.equals("은행")){
                    Toast.makeText(getActivity(), "시/군/구 설정을 완료 해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                if( !city.equals("시/도") && !dis.equals("시/군/구") && bank.equals("은행")){
                    input_mapMaker(city, dis, ""); //
                    return;
                }
                if( city.equals("시/도") && dis.equals("시/군/구") && !bank.equals("은행")){
                    input_mapMaker("", "", bank); //
                    return;
                }
                if( !city.equals("시/도") && dis.equals("시/군/구") && !bank.equals("은행")){
                    input_mapMaker(city, "", bank);
                    return;
                }
                if( !city.equals("시/도") && !dis.equals("시/군/구") && !bank.equals("은행")){
                    input_mapMaker(city, dis, bank);
                    return;
                }
            }
        }
    }
    private void add_maker(int i , int zoomlv){
        make_marker.add_Bank_marker(Integer.parseInt(maplist.get(i)[0]), maplist.get(i)[2], maplist.get(i)[3],
                maplist.get(i)[4], maplist.get(i)[5], maplist.get(i)[6], maplist.get(i)[7],
                Double.parseDouble(maplist.get(i)[9]), Double.parseDouble(maplist.get(i)[8]),zoomlv);
    }
    private void input_mapMaker(String city ,String dis ,String bank){
        mMapView.removeAllPOIItems();
        if( !city.equals("") && !dis.equals("") && bank.equals("")){
            for(int i = 1 ; i < maplist.size() ; i++){
                if(maplist.get(i)[2].equals(city) && maplist.get(i)[3].equals(dis)) {
                    this.add_maker(i,6);
                }
            }
            make_marker.cur_pos(0);
            return;
        }
        if( city.equals("") && dis.equals("") && !bank.equals("")){
            for(int i = 1 ; i < maplist.size() ; i++){
                if(maplist.get(i)[4].equals(bank)) {
                    this.add_maker(i,9);
                }
            }
            make_marker.cur_pos(0);
            return;
        }
        if( !city.equals("") && dis.equals("") && !bank.equals("")){
            for(int i = 1 ; i < maplist.size() ; i++){
                if(maplist.get(i)[2].equals(city) && maplist.get(i)[4].equals(bank)) {
                    this.add_maker(i,6);
                }
            }
            make_marker.cur_pos(0);
            return;
        }
        if( !city.equals("") && !dis.equals("") && !bank.equals("")){
            for(int i = 1 ; i < maplist.size() ; i++){
                if(maplist.get(i)[2].equals(city) && maplist.get(i)[3].equals(dis) && maplist.get(i)[4].equals(bank)){
                    this.add_maker(i,5);
                }
            }
            make_marker.cur_pos(0);
            return;
        }
    }
    public void disArrayListinit(){
        ArrayList disArrayList = new ArrayList<String>();
        disArrayList.add("시/군/구");
        arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,disArrayList);
        spinner_2.setAdapter(arrayAdapter);
    }
    public void bankArrayListinit(){
        ArrayList<String> bankList = new Return_Citys_Array().Bank_ArrayListinit();
        arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,bankList);
        spinner_3.setAdapter(arrayAdapter);
    }
    public void inputdisArray(String city){
        ArrayList<String> disArrayList = new Return_Citys_Array().Bank_DisArrayList(city);
        arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,disArrayList);
        spinner_2.setAdapter(arrayAdapter);
    }
    public void cityArrayListinit(){
        ArrayList<String> cityArrayList = new Return_Citys_Array().Bank_cityArrayList();
        arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,cityArrayList);
        spinner_1.setAdapter(arrayAdapter);
    }
    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem ) {
        if(mapPOIItem instanceof CustomPOIItem_Bank){
            CustomPOIItem_Bank item = (CustomPOIItem_Bank)mapPOIItem;
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