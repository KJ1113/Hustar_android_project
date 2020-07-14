package com.example.a0708_kakaotest.Fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.a0708_kakaotest.Android_Class.customMapPOIItem_bank;
import com.example.a0708_kakaotest.Android_Class.customMapPOIItem_use;
import com.example.a0708_kakaotest.R;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import net.daum.mf.map.api.CalloutBalloonAdapter;
import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_place_sale, container, false);
        this.map_init();
        return view;
    }
    public void map_init(){
        slidview = view.findViewById(R.id.slidview);
        listview = view.findViewById(R.id.listView);
        mMapView = new MapView(getActivity());
        ViewGroup mapViewContainer = (ViewGroup) view.findViewById(R.id.map_view);
        mapViewContainer.addView(mMapView);
        mMapView.setMapViewEventListener(this);
        mMapView.setPOIItemEventListener(this);
        cur_pos();
        input_mapMaker();
        //Toast.makeText(getActivity(),"사이즈 : " + maplist.size() , Toast.LENGTH_SHORT).show();
    }
    public void input_mapMaker(){
        maplist = get_bankData();
        for(int i = 1 ; i < maplist.size() ; i++){
                add_maker(Integer.parseInt(maplist.get(i)[0]),
                        maplist.get(i)[2],
                        maplist.get(i)[3],
                        maplist.get(i)[4],
                        maplist.get(i)[5],
                        maplist.get(i)[6],
                        maplist.get(i)[7],
                        Double.parseDouble(maplist.get(i)[9]),
                        Double.parseDouble(maplist.get(i)[8]));
            // 시도 maplist.get(i)[2]
            // 시군구 maplist.get(i)[3]
            // 은행명
            //add_maker(int no, String city, String dis,  String bankname ,String name ,String num ,String add , double latitude, double longitude  )
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
        Toast.makeText(getActivity(), latitude + " "+ longitude ,Toast.LENGTH_SHORT).show();
        marker.setTag(1);
        marker.setMapPoint(mapPoint);
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin);
        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        mMapView.addPOIItem(marker);
    }
    public void add_maker(int no, String city, String dis,  String bankname ,String name ,String num ,String add , double latitude, double longitude  ){

        if(bankname.equals("대구은행")){
            MapPoint mapPoint = MapPoint.mapPointWithGeoCoord(latitude, longitude);
            customMapPOIItem_bank marker = new customMapPOIItem_bank(  no,  city,  dis,   bankname , name , num , add ,  latitude,  longitude );
            marker.setItemName(bankname);
            marker.setTag(1);
            marker.setMapPoint(mapPoint);
            marker.setMarkerType(MapPOIItem.MarkerType.BluePin);
            marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
            mMapView.addPOIItem(marker);
        }

    }

    @Override
    public void onPOIItemSelected(MapView mapView, MapPOIItem mapPOIItem) {

    }
    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem ) {
        if(mapPOIItem instanceof customMapPOIItem_bank){
            customMapPOIItem_bank item = (customMapPOIItem_bank)mapPOIItem;
            String[] values = new String[] {"은행명: " + item.bankname, "지점명 : " + item.name , "연락처 : "+ item.num, "주소 : " + item.add , "시/도 : "+ item.city, "시/군/구 : " + item.dis};
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, values);
            listview.setAdapter(adapter);
            slidview.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
        }
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

}