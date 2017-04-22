package p8.group3.ida.aau.p8_group3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsPage extends BaseActivity implements OnMapReadyCallback {
    private GoogleMap locationMap;

    //Arraylists to store the location positions for each category
    final ArrayList<LatLng> libraryMarkerPosition = new ArrayList<LatLng>();
    final ArrayList<LatLng> playgroundMarkerPosition = new ArrayList<LatLng>();
    final ArrayList<LatLng> movieMarkerPosition = new ArrayList<LatLng>();
    final ArrayList<LatLng> parkMarkerPosition = new ArrayList<LatLng>();

    //Arraylists to store the Marker for each category
    final ArrayList<Marker> libraryMarker = new ArrayList<Marker>();
    final ArrayList<Marker> playgroundMarker = new ArrayList<Marker>();
    final ArrayList<Marker> movieMarker = new ArrayList<Marker>();
    final ArrayList<Marker> parkMarker = new ArrayList<Marker>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_map_page);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     **/
    @Override
    public void onMapReady(final GoogleMap googleMap) {
        locationMap = googleMap;

        // Creates the focus on the map to be in Aalborg
        final LatLng aalborgLocation = new LatLng(57.046707, 9.935932);
        CameraPosition cameraPositionAalborg = CameraPosition.builder().target(aalborgLocation).zoom(12).tilt(45).bearing(0).build();
        locationMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPositionAalborg));

        //Add markers to library category
        final Button libraries = (Button) findViewById(R.id.libraries);
        libraries.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                locationMap.clear();

                Marker mNoerresunbyLibrary = locationMap.addMarker(new MarkerOptions()
                        .position(new LatLng(57.057619, 9.923992))
                        .title("Noerresundby Library")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.book1))
                );

                LatLng noerresundbyLibraryPosition = mNoerresunbyLibrary.getPosition();
                libraryMarkerPosition.add(noerresundbyLibraryPosition);
                libraryMarker.add(mNoerresunbyLibrary);

                Marker mAalborgMainLibrary = locationMap.addMarker(new MarkerOptions()
                        .position(new LatLng(57.047269, 9.928126))
                        .title("Aalborg Main Library")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.book1))
                );

                LatLng aalborgMainLibraryPosition = mAalborgMainLibrary.getPosition();
                libraryMarkerPosition.add(aalborgMainLibraryPosition);
                libraryMarker.add(mAalborgMainLibrary);

                Marker mVejgaardLibrary = locationMap.addMarker(new MarkerOptions()
                        .position(new LatLng(57.041489, 9.951695))
                        .title("Vejgaard Library")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.book1))
                );

                LatLng vejgaardLibraryPosition = mVejgaardLibrary.getPosition();
                libraryMarkerPosition.add(vejgaardLibraryPosition);
                libraryMarker.add(mVejgaardLibrary);

                for (LatLng collectedLibraryMarkerPosition : libraryMarkerPosition) {
                    CameraPosition n = CameraPosition.builder().target(collectedLibraryMarkerPosition).zoom(12).tilt(45).bearing(0).build();
                    locationMap.moveCamera(CameraUpdateFactory.newCameraPosition(n));
                }
            }
        });

        //Add markers to playground category
        final Button playgrounds = (Button) findViewById(R.id.playgrounds);
        playgrounds.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                locationMap.clear();

                Marker mPlaygroundAalborgCenter = locationMap.addMarker(new MarkerOptions()
                        .position(new LatLng(57.042001, 9.915755))
                        .title("Playground Aalborg Center")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.playground2))
                );

                LatLng playgroundAalborgCenterPosition = mPlaygroundAalborgCenter.getPosition();
                playgroundMarkerPosition.add(playgroundAalborgCenterPosition);
                playgroundMarker.add(mPlaygroundAalborgCenter);

                Marker mPlaygroundSolbyen = locationMap.addMarker(new MarkerOptions()
                        .position(new LatLng(57.042912, 9.881086))
                        .title("Playground Solbyen")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.playground2))
                );

                LatLng playgroundSolbyenPosition = mPlaygroundSolbyen.getPosition();
                playgroundMarkerPosition.add(playgroundSolbyenPosition);
                playgroundMarker.add(mPlaygroundSolbyen);

                Marker mPlaygroundOestreAnlaeg = locationMap.addMarker(new MarkerOptions()
                        .position(new LatLng(57.045442, 9.940251))
                        .title("Playground Oestre Anlaeg")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.playground2))
                );

                LatLng playgroundOestreAnlaegPosition = mPlaygroundOestreAnlaeg.getPosition();
                playgroundMarkerPosition.add(playgroundOestreAnlaegPosition);
                playgroundMarker.add(mPlaygroundOestreAnlaeg);

                for (LatLng collectedPlaygroundMarkerPosition : playgroundMarkerPosition) {
                    CameraPosition n = CameraPosition.builder().target(collectedPlaygroundMarkerPosition).zoom(12).tilt(45).bearing(0).build();
                    locationMap.moveCamera(CameraUpdateFactory.newCameraPosition(n));
                }
            }
        });

        //Add markers to cinema category
        final Button cinema = (Button) findViewById(R.id.movies);
        cinema.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                locationMap.clear();

                Marker mCinemaKennedyArkaden = locationMap.addMarker(new MarkerOptions()
                        .position(new LatLng(57.042392, 9.918497))
                        .title("Cinema Kennedy Arkaden")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.movie))
                );

                LatLng cinemaKennedyArkadePosition = mCinemaKennedyArkaden.getPosition();
                movieMarkerPosition.add(cinemaKennedyArkadePosition);
                movieMarker.add(mCinemaKennedyArkaden);

                for (LatLng collectedMovieMarkerPosition : movieMarkerPosition) {
                    CameraPosition n = CameraPosition.builder().target(collectedMovieMarkerPosition).zoom(12).tilt(45).bearing(0).build();
                    locationMap.moveCamera(CameraUpdateFactory.newCameraPosition(n));
                }

            }
        });

        //Add markers to park category
        final Button park = (Button) findViewById(R.id.forests);
        park.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                locationMap.clear();

                Marker mParkSohngaardsholmsparken = locationMap.addMarker(new MarkerOptions()
                        .position(new LatLng(56.802179, 9.855545))
                        .title("CSohngaardsholmsparken")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.forest))
                );

                LatLng sohngaardsholmsparkenPosition = mParkSohngaardsholmsparken.getPosition();
                parkMarkerPosition.add(sohngaardsholmsparkenPosition);
                parkMarker.add(mParkSohngaardsholmsparken);

                for (LatLng collectedParkMarkerPosition : parkMarkerPosition) {
                    CameraPosition n = CameraPosition.builder().target(collectedParkMarkerPosition).zoom(12).tilt(45).bearing(0).build();
                    locationMap.moveCamera(CameraUpdateFactory.newCameraPosition(n));
                }

            }
        });


        //Method for getting the description of the location, when marker is clicked (both the getInfoWindow method and getInfoContents method)
        locationMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                View view = getLayoutInflater().inflate(R.layout.info_window_maps, null);
                TextView name = (TextView) view.findViewById(R.id.txtname);
                name.setText("");
                TextView adress = (TextView) view.findViewById(R.id.txtname);
                adress.setText("Playground");
                TextView number = (TextView) view.findViewById(R.id.txtname);
                number.setText("700");
                return view;
            }

            @Override
            public View getInfoContents(Marker marker) {
                return null;
            }
        });
    }
}








      /*







         */




       /* final Button libraries = (Button) findViewById(R.id.libraries);
        libraries.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) { */



       /*         libraryMarkers.add(new LatLng(57.047505, 10.003304));
                libraryMarkers.add(new LatLng(57.039661, 9.997811));
                libraryMarkers.add(new LatLng(57.050867, 9.968285));
                libraryMarkers.add(new LatLng(57.037046, 9.953866));



                for(LatLng location : libraryMarkers) {
                    locationPageMap.addMarker(new MarkerOptions()
                            .position(new LatLng(57.047505, 10.003304))
                            .title("Libraries")
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.book)));

                }

                    for(LatLng location2 : libraryMarkers){
                        locationPageMap.addMarker(new MarkerOptions()
                                .position(new LatLng(57.037046, 9.953866))
                                .title("cameraPositionAalborg"));

                } */


// for (LatLng m: libraryMarkers) {
//    locationPageMap.addMarker(new MarkerOptions().position(m));
//   CameraPosition n = CameraPosition.builder().target(m).zoom(12).tilt(45).bearing(0).build();
//  locationPageMap.moveCamera(CameraUpdateFactory.newCameraPosition(n));
//}


//mMap.moveCamera(CameraUpdateFactory.newLatLng(aalborgLocation));


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