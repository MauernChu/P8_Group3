package p8.group3.ida.aau.p8_group3;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by christosmentzelos on 13/04/2017.
 */

public class Map extends Fragment implements OnMapReadyCallback{


    GoogleMap mGoogleMap;
    MapView mMapView;
    View mView;

    public Map(){
       //epithdes keno
    }


    @Override
    public void onCreate (Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.map_page, container, false);
        return mView;

    }


    @Override
    public void onViewCreated (View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        mMapView = (MapView) mView.findViewById(R.id.map);

        if (mMapView != null){

            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);

        }

    }




        @Override
    public void onMapReady(GoogleMap googleMap) {

            MapsInitializer.initialize(getContext());
            mGoogleMap = googleMap;
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

            googleMap.addMarker(new MarkerOptions().position(new LatLng(40.68, -74.04)));
            CameraPosition test = CameraPosition.builder().target(new LatLng(40.68, -74.04)).zoom(5).tilt(1).bearing(1).build();

            googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(test));

    }
}
