package p8.group3.ida.aau.p8_group3.Presenter;

import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.view.View;
import android.widget.Button;
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

import java.util.ArrayList;

import p8.group3.ida.aau.p8_group3.R;

public class MapsPage extends BaseActivity implements OnMapReadyCallback {
    private static int MY_LOCATION_REQUEST_CODE ;
    private GoogleMap locationMap;

    private GpsTracker gpsTracker;
    private Location mLocation;
    double latitude, longitude;


    private BottomSheetBehavior bottomSheetBehavior;
    private View bottomSheet;


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

    //Variable to store title information about marker
    String titleNoerresundbyLibraryMarker;
    private String[] permissions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_map_page);

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


        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(latitude, longitude);
        locationMap.addMarker(new MarkerOptions().position(sydney).title("I'm here..."));
        locationMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));



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


        // Creates the focus on the map to be in Aalborg
        final LatLng aalborgLocation = new LatLng(57.046707, 9.935932);
        CameraPosition cameraPositionAalborg = CameraPosition.builder().target(aalborgLocation).zoom(12).tilt(45).bearing(0).build();
        locationMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPositionAalborg));
        Marker marker = locationMap.addMarker(new MarkerOptions().position(aalborgLocation));

        //Add markers to library category
        final Button libraries = (Button) findViewById(R.id.libraries);
        libraries.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                locationMap.clear();


                if (sheetShowing){
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                }

                Marker mNoerresunbyLibrary = locationMap.addMarker(new MarkerOptions()
                        .position(new LatLng(57.057619, 9.923992))
                        .title("Noerresundby Library")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.book1))
                );

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

                if (sheetShowing){
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                }

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

                if (sheetShowing){
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                }

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

                if (sheetShowing){
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                }

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

    }
        //Method for getting the description of the location, when marker is clicked (both the getInfoWindow method and getInfoContents method)
     /*    locationMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
           @Override
            public View getInfoWindow(Marker marker) {
                View view = getLayoutInflater().inflate(R.layout.info_window_maps, null);
                TextView name = (TextView) view.findViewById(R.id.txtname);
                name.setText(titleNoerresundbyLibraryMarker);
                TextView adress = (TextView) view.findViewById(R.id.txtAdress);
                adress.setText("Playground");
                TextView number = (TextView) view.findViewById(R.id.txtnumber);
                number.setText("700");
                return view;
            }

            @Override
            public View getInfoContents(Marker marker) {
                return null;
            }
        });
    */


        public void updateBottomSheetContent(Marker marker) {
            View view = getLayoutInflater().inflate(R.layout.pop_up_info, null);
            TextView adress = (TextView) bottomSheet.findViewById(R.id.txtAdress);
            adress.setText("Playground");

            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

        }



}

