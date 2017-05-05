package p8.group3.ida.aau.p8_group3.Presenter;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import p8.group3.ida.aau.p8_group3.Database.LocationDAOImpl;
import p8.group3.ida.aau.p8_group3.R;

public class MapsPage extends BaseActivity implements OnMapReadyCallback {
    private static int MY_LOCATION_REQUEST_CODE ;
    private GoogleMap locationMap;

    Context context = this;
    LocationDAOImpl data;

    private GpsTracker gpsTracker;
    private Location mLocation;
    double latitude, longitude;



    private BottomSheetBehavior bottomSheetBehavior;
    private View bottomSheet;

    private Marker markerCity2;
    private Hashtable<String, String>hashCity;
    private Hashtable<String, String>hashAddress;
    private Hashtable<String, String>hashPicture;
    private Hashtable<String, String>hashCategory;

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



    final Hashtable <Integer, String> test = new Hashtable<Integer, String>();

    //Variable to store title information about marker
    String titleNoerresundbyLibraryMarker;
    private String[] permissions;

    private static final int PERMISSION_ACCESS_COARSE_LOCATION = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_map_page);

        hashCity = new Hashtable<String, String>();
        hashAddress = new Hashtable<String, String>();
        hashPicture = new Hashtable<String, String>();
        hashCategory = new Hashtable<String, String>();


        // Ask for permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.ACCESS_COARSE_LOCATION },
                    PERMISSION_ACCESS_COARSE_LOCATION);
        }


        data = new LocationDAOImpl(context);
        try{
            data.open();
        }   catch (Exception e){
            Log.i("Error", "Data");
        }

        gpsTracker = new GpsTracker(getApplicationContext());
        mLocation = gpsTracker.getLocation();

        latitude = mLocation.getLatitude();
        longitude = mLocation.getLongitude();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        bottomSheet = findViewById(R.id.bottom_sheet);
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        bottomSheetBehavior.setPeekHeight(550);
        bottomSheetBehavior.setHideable(true);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);


        final RatingBar simpleRatingBar = (RatingBar) findViewById(R.id.ratingBar);
        Button submitButton = (Button) findViewById(R.id.submitRating);

        // perform click event on button
        submitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // get values and then displayed in a toast
                String totalStars = "Total Stars:: " + simpleRatingBar.getNumStars();
                String rating = "Rating :: " + simpleRatingBar.getRating();
                Toast.makeText(getApplicationContext(), totalStars + "\n" + rating, Toast.LENGTH_LONG).show();
            }
        });
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

        final List<p8.group3.ida.aau.p8_group3.Model.Location> l = data.getMyMarkers();


        for (int i = 0; i < l.size(); i++){
            LatLng lat = new LatLng(l.get(i).getLocationLatitude(), l.get(i).getLocationLongitude());

            markerCity2 = locationMap.addMarker(new MarkerOptions()
                    .title(l.get(i).getLocationName())
                    .snippet(l.get(i).getLocationAddress())
                    .position(lat));
            hashCity.put(markerCity2.getId(),l.get(i).getLocationCity());
            hashAddress.put(markerCity2.getId(),l.get(i).getLocationAddress());
            hashPicture.put(markerCity2.getId(),l.get(i).getLocationPicture());
            hashCategory.put(markerCity2.getId(),l.get(i).getLocationCategory());

            test.put(i,l.get(i).getLocationCategory());


        }



        // Add a marker at user's position and move the camera
        final LatLng userPosition = new LatLng(latitude, longitude);


        //locationMap.addCircle(new CircleOptions().center(userPosition));

        locationMap.addMarker(new MarkerOptions().position(userPosition).title("I'm here...").icon(BitmapDescriptorFactory.fromResource(R.drawable.gps_marker)));
        locationMap.moveCamera(CameraUpdateFactory.newLatLng(userPosition));



        locationMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                updateBottomSheetContent(marker);
                return true;
            }
        });

        locationMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
            }
        });




        final boolean sheetShowing = true;

        Button button4 = (Button) findViewById(R.id.button2);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sheetShowing) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                }
            }
        });


        Button button5 = (Button) findViewById(R.id.button3);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sheetShowing) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                }
            }
        });


        // Creates the focus on the map to be at user's position
        CameraPosition cameraPositionUser = CameraPosition.builder().target(userPosition).zoom(12).tilt(45).bearing(0).build();
        locationMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPositionUser));


        //Add markers to library category
        final Button libraries = (Button) findViewById(R.id.libraries);
        libraries.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                locationMap.clear();

                if (sheetShowing){
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                }


                    for (int i = 0; i < l.size(); i++){

                        if( test.get(i).equals("library")){

                            LatLng lat = new LatLng(l.get(i).getLocationLatitude(), l.get(i).getLocationLongitude());


                            markerCity2 = locationMap.addMarker(new MarkerOptions()
                                    .title(l.get(i).getLocationName())
                                    .snippet(l.get(i).getLocationAddress())
                                    .position(lat).icon(BitmapDescriptorFactory.fromResource(R.drawable.book1)));
                            hashCity.put(markerCity2.getId(),l.get(i).getLocationCity());
                            hashAddress.put(markerCity2.getId(),l.get(i).getLocationAddress());
                            hashPicture.put(markerCity2.getId(),l.get(i).getLocationPicture());

                        }

                    }

                locationMap.addMarker(new MarkerOptions().position(userPosition).title("I'm here...").icon(BitmapDescriptorFactory.fromResource(R.drawable.gps_marker)));
                CameraPosition cameraPositionUser = CameraPosition.builder().target(userPosition).zoom(12).tilt(45).bearing(0).build();
                locationMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPositionUser));

                };

        });


              /*
                Marker mNoerresunbyLibrary = locationMap.addMarker(new MarkerOptions()
                        .position(new LatLng(57.057619, 9.923992))
                        .title("Noerresundby Library")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.book1))
                );

             //   locationMap.addMarker(new MarkerOptions().position(userPosition).title("I'm here..."));
             //   CameraPosition cameraPositionUser = CameraPosition.builder().target(userPosition).zoom(12).tilt(45).bearing(0).build();
             //   locationMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPositionUser));

                titleNoerresundbyLibraryMarker = mNoerresunbyLibrary.getTitle();
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


                */




        //Add markers to playground category
        final Button playgrounds = (Button) findViewById(R.id.playgrounds);
        playgrounds.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                locationMap.clear();

                if (sheetShowing){
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                }


                for (int i = 0; i < l.size(); i++){

                    if( test.get(i).equals("playground")){

                        LatLng lat = new LatLng(l.get(i).getLocationLatitude(), l.get(i).getLocationLongitude());

                        markerCity2 = locationMap.addMarker(new MarkerOptions()
                                .title(l.get(i).getLocationName())
                                .snippet(l.get(i).getLocationAddress())
                                .position(lat).icon(BitmapDescriptorFactory.fromResource(R.drawable.playground2)));
                        hashCity.put(markerCity2.getId(),l.get(i).getLocationCity());
                        hashAddress.put(markerCity2.getId(),l.get(i).getLocationAddress());
                        hashPicture.put(markerCity2.getId(),l.get(i).getLocationPicture());

                    }

                }

                locationMap.addMarker(new MarkerOptions().position(userPosition).title("I'm here...").icon(BitmapDescriptorFactory.fromResource(R.drawable.gps_marker)));
                CameraPosition cameraPositionUser = CameraPosition.builder().target(userPosition).zoom(12).tilt(45).bearing(0).build();
                locationMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPositionUser));

            };

        });

                /*

             //   locationMap.addMarker(new MarkerOptions().position(userPosition).title("I'm here..."));
             //   CameraPosition cameraPositionUser = CameraPosition.builder().target(userPosition).zoom(12).tilt(45).bearing(0).build();
             //   locationMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPositionUser));

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
                    locationMap.addMarker(new MarkerOptions().position(userPosition).title("I'm here...").icon(BitmapDescriptorFactory.fromResource(R.drawable.gps_marker)));
                    CameraPosition cameraPositionUser = CameraPosition.builder().target(userPosition).zoom(12).tilt(45).bearing(0).build();
                    locationMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPositionUser));
                }
            }
        });

        */

        //Add markers to swimming pool category
        final Button swimmingPool = (Button) findViewById(R.id.swimming);
        swimmingPool.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                locationMap.clear();

                if (sheetShowing){
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                }


                for (int i = 0; i < l.size(); i++){


                    if( test.get(i).equals("swimming pool")){

                        LatLng lat = new LatLng(l.get(i).getLocationLatitude(), l.get(i).getLocationLongitude());

                        markerCity2 = locationMap.addMarker(new MarkerOptions()
                                .title(l.get(i).getLocationName())
                                .snippet(l.get(i).getLocationAddress())
                                .position(lat).icon(BitmapDescriptorFactory.fromResource(R.drawable.swimming)));
                        hashCity.put(markerCity2.getId(),l.get(i).getLocationCity());
                        hashAddress.put(markerCity2.getId(),l.get(i).getLocationAddress());
                        hashPicture.put(markerCity2.getId(),l.get(i).getLocationPicture());

                    }

                }

                locationMap.addMarker(new MarkerOptions().position(userPosition).title("I'm here...").icon(BitmapDescriptorFactory.fromResource(R.drawable.gps_marker)));
                CameraPosition cameraPositionUser = CameraPosition.builder().target(userPosition).zoom(12).tilt(45).bearing(0).build();
                locationMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPositionUser));

            };

        });


                /*

                Marker mCinemaKennedyArkaden = locationMap.addMarker(new MarkerOptions()
                        .position(new LatLng(57.042392, 9.918497))
                        .title("Cinema Kennedy Arkaden")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.movie))
                );

                LatLng cinemaKennedyArkadePosition = mCinemaKennedyArkaden.getPosition();
                movieMarkerPosition.add(cinemaKennedyArkadePosition);
                movieMarker.add(mCinemaKennedyArkaden);

                for (LatLng collectedMovieMarkerPosition : movieMarkerPosition) {
                    locationMap.addMarker(new MarkerOptions().position(userPosition).title("I'm here...").icon(BitmapDescriptorFactory.fromResource(R.drawable.gps_marker)));
                    CameraPosition cameraPositionUser = CameraPosition.builder().target(userPosition).zoom(12).tilt(45).bearing(0).build();
                    locationMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPositionUser));
                }

            }
        });   */

        //Add markers to park category
        final Button park = (Button) findViewById(R.id.forests);
        park.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                locationMap.clear();


                if (sheetShowing){
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                }


                for (int i = 0; i < l.size(); i++){



                    if( test.get(i).equals("park")){

                        LatLng lat = new LatLng(l.get(i).getLocationLatitude(), l.get(i).getLocationLongitude());

                        markerCity2 = locationMap.addMarker(new MarkerOptions()
                                .title(l.get(i).getLocationName())
                                .snippet(l.get(i).getLocationAddress())
                                .position(lat).icon(BitmapDescriptorFactory.fromResource(R.drawable.forest)));
                        hashCity.put(markerCity2.getId(),l.get(i).getLocationCity());
                        hashAddress.put(markerCity2.getId(),l.get(i).getLocationAddress());
                        hashPicture.put(markerCity2.getId(),l.get(i).getLocationPicture());

                    }

                }

                locationMap.addMarker(new MarkerOptions().position(userPosition).title("I'm here...").icon(BitmapDescriptorFactory.fromResource(R.drawable.gps_marker)));
                CameraPosition cameraPositionUser = CameraPosition.builder().target(userPosition).zoom(12).tilt(45).bearing(0).build();
                locationMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPositionUser));

            };

        });

                /*

                Marker mParkSohngaardsholmsparken = locationMap.addMarker(new MarkerOptions()
                        .position(new LatLng(56.802179, 9.855545))
                        .title("CSohngaardsholmsparken")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.forest))
                );

                LatLng sohngaardsholmsparkenPosition = mParkSohngaardsholmsparken.getPosition();
                parkMarkerPosition.add(sohngaardsholmsparkenPosition);
                parkMarker.add(mParkSohngaardsholmsparken);

                for (LatLng collectedParkMarkerPosition : parkMarkerPosition) {
                    locationMap.addMarker(new MarkerOptions().position(userPosition).title("I'm here...").icon(BitmapDescriptorFactory.fromResource(R.drawable.gps_marker)));
                    CameraPosition cameraPositionUser = CameraPosition.builder().target(userPosition).zoom(12).tilt(45).bearing(0).build();
                    locationMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPositionUser));
                }

            }
        });     */

    }


        public void updateBottomSheetContent(Marker marker) {

            View view = getLayoutInflater().inflate(R.layout.pop_up_info, null);
            List<p8.group3.ida.aau.p8_group3.Model.Location> l = data.getMyMarkers();


            TextView locationName = (TextView) bottomSheet.findViewById(R.id.txtLocationName);
            locationName.setText(marker.getTitle());

            TextView locationCity = (TextView) bottomSheet.findViewById(R.id.txtCity);
            locationCity.setText(hashCity.get(marker.getId()));

            TextView locationAddress = (TextView) bottomSheet.findViewById(R.id.txtAddress);
            locationAddress.setText(hashAddress.get(marker.getId()));

            ImageView locationPicture = (ImageView) bottomSheet.findViewById(R.id.infowindow);
            Picasso.with(this).load(hashPicture.get(marker.getId())).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(locationPicture, new com.squareup.picasso.Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError() {

                }
            });

                   // setText(hashAddress.get(marker.getId()));


            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

        }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_ACCESS_COARSE_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // All good!
                } else {
                    Toast.makeText(this, "Need your location!", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }



}

