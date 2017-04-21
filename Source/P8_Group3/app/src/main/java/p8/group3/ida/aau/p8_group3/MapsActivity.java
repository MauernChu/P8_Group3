package p8.group3.ida.aau.p8_group3;

import android.os.Bundle;


import android.view.View;
import android.widget.Button;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends BaseActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Aalborg and move the camera
        LatLng aalborg = new LatLng(57.046707, 9.935932);
        CameraPosition test = CameraPosition.builder().target(aalborg).zoom(12).tilt(45).bearing(0).build();
        mMap.addMarker(new MarkerOptions().position(aalborg).title("Marker in Aalborg"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(aalborg));
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(test));


        //Tranumparken, 9220 Aalborg Øst
       /* LatLng playground1 = new LatLng(57.047505, 10.003304);
        CameraPosition play1 = CameraPosition.builder().target(playground1).zoom(12).tilt(45).bearing(0).build();
        mMap.addMarker(new MarkerOptions().position(playground1).title("Villys Legeplads"));
        // mMap.moveCamera(CameraUpdateFactory.newCameraPosition(play1));

        //Sebberundsvej, 9220 Aalborg Øst
        LatLng playground2 = new LatLng(57.039661, 9.997811);
        CameraPosition play2 = CameraPosition.builder().target(playground2).zoom(12).tilt(45).bearing(0).build();
        mMap.addMarker(new MarkerOptions().position(playground2).title("Naturlegeplads"));
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(play2));

       // Kælkebakken ved Tove Ditlevsens Vej
        LatLng playground3 = new LatLng(57.050867, 9.968285);
        CameraPosition play3 = CameraPosition.builder().target(playground3).zoom(12).tilt(45).bearing(0).build();
        mMap.addMarker(new MarkerOptions().position(playground3).title("legeplads"));
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(play3));

        //  Sohngårdsholmsparken

        LatLng playground4 = new LatLng(57.037046, 9.953866);
        CameraPosition play4 = CameraPosition.builder().target(playground4).zoom(12).tilt(45).bearing(0).build();
        mMap.addMarker(new MarkerOptions().position(playground4).title("Sohngårdsholmsparken"));
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(play4));    */


        final ArrayList<LatLng> myMarkers = new ArrayList<LatLng>();



        final Button libraries = (Button) findViewById(R.id.libraries);
        libraries.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                myMarkers.add(new LatLng(57.047505, 10.003304));
                myMarkers.add(new LatLng(57.039661, 9.997811));
                myMarkers.add(new LatLng(57.050867, 9.968285));
                myMarkers.add(new LatLng(57.037046, 9.953866));

                for(LatLng location : myMarkers){
                    mMap.addMarker(new MarkerOptions()
                            .position(location)
                            .title("Playgrounds"));
                }


                for (LatLng m: myMarkers) {
                    mMap.addMarker(new MarkerOptions().position(m));
                    CameraPosition n = CameraPosition.builder().target(m).zoom(12).tilt(45).bearing(0).build();
                    mMap.moveCamera(CameraUpdateFactory.newCameraPosition(n));
                }



            }
        });



    }
}
