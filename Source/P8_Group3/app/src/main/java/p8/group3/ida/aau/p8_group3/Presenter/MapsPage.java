package p8.group3.ida.aau.p8_group3.Presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.TimePicker;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import p8.group3.ida.aau.p8_group3.Database.DAO.ParentDAO;
import p8.group3.ida.aau.p8_group3.Database.LocationDAOImpl;
import p8.group3.ida.aau.p8_group3.Database.ParentDAOImpl;
import p8.group3.ida.aau.p8_group3.Database.PlannedActivityDAOImpl;
import p8.group3.ida.aau.p8_group3.Database.RatingDAOImpl;
import p8.group3.ida.aau.p8_group3.Model.Parent;
import p8.group3.ida.aau.p8_group3.Model.Rating;
import p8.group3.ida.aau.p8_group3.R;

import static p8.group3.ida.aau.p8_group3.R.id.dialog_ratingbar;
import static p8.group3.ida.aau.p8_group3.R.id.ratingBar;

public class MapsPage extends AppCompatActivity implements OnMapReadyCallback {
    private static int MY_LOCATION_REQUEST_CODE ;
    private GoogleMap locationMap;

    private String loginUsername;
    private String loginPassword;

    Context context = this;
    LocationDAOImpl data;
    RatingDAOImpl ratingData;
    ParentDAO parentData;
    PlannedActivityDAOImpl CheckInLaterData;

    // private GpsTracker gpsTracker;
    // private Location mLocation;
    // double latitude, longitude;


    private BottomSheetBehavior bottomSheetBehavior;
    private View bottomSheet;

    private AlertDialog dialog;
    private AlertDialog dialogSecond;
    private AlertDialog dialogRating;

    // Store time and date of CheckInLater
    public int storeDay;
    public int storeMonth;
    public int storeYear;
    public int storeHour;
    public int storeMinute;
    public int[] storeCheckInlaterData;

    private Marker markerCity2;
    private Hashtable<String, String>hashCity;
    private Hashtable<String, String>hashAddress;
    private Hashtable<String, String>hashPicture;
    private Hashtable<String, String>hashCategory;
    public Hashtable<String, Integer>hashLocationID;
    final Hashtable <Integer, String> locationCategories = new Hashtable<Integer, String>();
    private Hashtable<Integer, Double> hashTest = new Hashtable<>();
    private Hashtable<Integer, String> checkInLaterDate = new Hashtable<>();

    float averageRating;
    public int j;


    //Variable to store permissions
    private String[] permissions;
    // private static final int PERMISSION_ACCESS_COARSE_LOCATION = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_map_page);

        hashCity = new Hashtable<String, String>();
        hashAddress = new Hashtable<String, String>();
        hashPicture = new Hashtable<String, String>();
        hashCategory = new Hashtable<String, String>();
        hashLocationID = new Hashtable<String, Integer>();
        hashTest = new Hashtable<Integer, Double>();



        // Ask for permission
        /*
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.ACCESS_COARSE_LOCATION },
                    PERMISSION_ACCESS_COARSE_LOCATION);
        }*/


        data = new LocationDAOImpl(context);
        try{
            data.open();
        }   catch (Exception e){
            Log.i("Error", "Data");
        }

        ratingData = new RatingDAOImpl(context);
        try{
            ratingData.open();
        }   catch (Exception e){
            Log.i("Error", "Data");
        }

        parentData = new ParentDAOImpl(context);
        try{
            parentData.open();
        }   catch (Exception e){
            Log.i("Error", "Data");
        }


       /* gpsTracker = new GpsTracker(getApplicationContext());
        mLocation = gpsTracker.getLocation();

        latitude = mLocation.getLatitude();
        longitude = mLocation.getLongitude();*/

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        bottomSheet = findViewById(R.id.bottom_sheet);
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        bottomSheetBehavior.setPeekHeight(550);
        bottomSheetBehavior.setHideable(true);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);


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
            hashLocationID.put(markerCity2.getId(),l.get(i).getLocationID());
            locationCategories.put(i,l.get(i).getLocationCategory());
            // hashTest.put(i,l.get(i).getAverageRating());
            // System.out.println(r);
        }


        Button logoutButtonMapPage = (Button) findViewById(R.id.logoutButtonMapPage);
        logoutButtonMapPage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent profileIntent = new Intent(view.getContext(), LoginPage.class);
                startActivityForResult(profileIntent, 0);
                Toast.makeText(MapsPage.this, "You have been logged out.", Toast.LENGTH_SHORT).show();
            }
        });

        Button profileButtonMapPage = (Button) findViewById(R.id.profileButtonMapPage);
        profileButtonMapPage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent mapIntent = new Intent(view.getContext(), ProfilePage.class);
                Bundle bundle = getIntent().getExtras();
                loginUsername = bundle.getString("loginUsername");
                loginPassword = bundle.getString("loginPassword");
                mapIntent.putExtra("loginUsername", loginUsername);
                mapIntent.putExtra("loginPassword", loginPassword);
                startActivityForResult(mapIntent, 0);
            }
        });

        // Add a marker at user's position and move the camera
        //final LatLng userPosition = new LatLng(latitude, longitude);


        parentData.checkOut(loginPassword, markerCity2);

        final LatLng userPosition = new LatLng(57, 9.95);

       /* locationMap.addMarker(new MarkerOptions().position(userPosition).title("I'm here...").icon(BitmapDescriptorFactory.fromResource(R.drawable.gps_marker)));
        locationMap.moveCamera(CameraUpdateFactory.newLatLng(userPosition));*/

        // Creates the focus on the map to be at user's position
        CameraPosition cameraPositionUser = CameraPosition.builder().target(userPosition).zoom(12).tilt(45).bearing(0).build();
        locationMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPositionUser));


        locationMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                updateBottomSheetContent(marker);
                loadCheckInLater(marker);
                submitRating(marker);
                peopleCheckedInNow(marker);

                j=passLocationId(marker, hashLocationID);

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

       /* Button button4 = (Button) findViewById(R.id.checkInLater);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sheetShowing) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                }
            }
        });*/


        Button button5 = (Button) findViewById(R.id.button3);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = getIntent().getExtras();
                loginPassword = bundle.getString("loginPassword");
                Parent mapParent = parentData.retrieveInformationAboutParent(loginPassword);

                parentData.checkInNow(mapParent, j);

                Date dateTest = mapParent.getTimeChekedIn();
                if (dateTest != null) {
                    DateFormat dateFormat = new SimpleDateFormat("dd/MM HH:mm");
                    Toast.makeText(MapsPage.this, "You are now checked in to this location at time: " + dateFormat.format(dateTest), Toast.LENGTH_SHORT).show();
                }
                    if (sheetShowing) {
                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                    }
                }

        });


        //Add markers to library category
        final Button libraries = (Button) findViewById(R.id.libraries);
        libraries.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                locationMap.clear();

                if (sheetShowing){
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                }


                for (int i = 0; i < l.size(); i++){

                    if( locationCategories.get(i).equals("library")){

                        LatLng lat = new LatLng(l.get(i).getLocationLatitude(), l.get(i).getLocationLongitude());


                        markerCity2 = locationMap.addMarker(new MarkerOptions()
                                .title(l.get(i).getLocationName())
                                .snippet(l.get(i).getLocationAddress())
                                .position(lat).icon(BitmapDescriptorFactory.fromResource(R.drawable.book1)));
                        hashCity.put(markerCity2.getId(),l.get(i).getLocationCity());
                        hashAddress.put(markerCity2.getId(),l.get(i).getLocationAddress());
                        hashPicture.put(markerCity2.getId(),l.get(i).getLocationPicture());
                        hashCategory.put(markerCity2.getId(),l.get(i).getLocationCategory());
                        hashLocationID.put(markerCity2.getId(),l.get(i).getLocationID());
                        locationCategories.put(i,l.get(i).getLocationCategory());

                    }

                }

                locationMap.addMarker(new MarkerOptions().position(userPosition).title("I'm here...").icon(BitmapDescriptorFactory.fromResource(R.drawable.gps_marker)));
                CameraPosition cameraPositionUser = CameraPosition.builder().target(userPosition).zoom(12).tilt(45).bearing(0).build();
                locationMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPositionUser));

            };

        });



        //Add markers to playground category
        final Button playgrounds = (Button) findViewById(R.id.playgrounds);
        playgrounds.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                locationMap.clear();

                if (sheetShowing){
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                }


                for (int i = 0; i < l.size(); i++){

                    if( locationCategories.get(i).equals("playground")){

                        LatLng lat = new LatLng(l.get(i).getLocationLatitude(), l.get(i).getLocationLongitude());

                        markerCity2 = locationMap.addMarker(new MarkerOptions()
                                .title(l.get(i).getLocationName())
                                .snippet(l.get(i).getLocationAddress())
                                .position(lat).icon(BitmapDescriptorFactory.fromResource(R.drawable.playground2)));
                        hashCity.put(markerCity2.getId(),l.get(i).getLocationCity());
                        hashAddress.put(markerCity2.getId(),l.get(i).getLocationAddress());
                        hashPicture.put(markerCity2.getId(),l.get(i).getLocationPicture());
                        hashCategory.put(markerCity2.getId(),l.get(i).getLocationCategory());
                        hashLocationID.put(markerCity2.getId(),l.get(i).getLocationID());
                        locationCategories.put(i,l.get(i).getLocationCategory());

                    }

                }

                locationMap.addMarker(new MarkerOptions().position(userPosition).title("I'm here...").icon(BitmapDescriptorFactory.fromResource(R.drawable.gps_marker)));
                CameraPosition cameraPositionUser = CameraPosition.builder().target(userPosition).zoom(12).tilt(45).bearing(0).build();
                locationMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPositionUser));

            };

        });


        //Add markers to swimming pool category
        final Button swimmingPool = (Button) findViewById(R.id.swimming);
        swimmingPool.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                locationMap.clear();

                if (sheetShowing){
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                }


                for (int i = 0; i < l.size(); i++){


                    if( locationCategories.get(i).equals("swimming pool")){

                        LatLng lat = new LatLng(l.get(i).getLocationLatitude(), l.get(i).getLocationLongitude());

                        markerCity2 = locationMap.addMarker(new MarkerOptions()
                                .title(l.get(i).getLocationName())
                                .snippet(l.get(i).getLocationAddress())
                                .position(lat).icon(BitmapDescriptorFactory.fromResource(R.drawable.swimming)));
                        hashCity.put(markerCity2.getId(),l.get(i).getLocationCity());
                        hashAddress.put(markerCity2.getId(),l.get(i).getLocationAddress());
                        hashPicture.put(markerCity2.getId(),l.get(i).getLocationPicture());
                        hashCategory.put(markerCity2.getId(),l.get(i).getLocationCategory());
                        hashLocationID.put(markerCity2.getId(),l.get(i).getLocationID());
                        locationCategories.put(i,l.get(i).getLocationCategory());

                    }

                }

                locationMap.addMarker(new MarkerOptions().position(userPosition).title("I'm here...").icon(BitmapDescriptorFactory.fromResource(R.drawable.gps_marker)));
                CameraPosition cameraPositionUser = CameraPosition.builder().target(userPosition).zoom(12).tilt(45).bearing(0).build();
                locationMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPositionUser));

            };

        });



        //Add markers to park category
        final Button park = (Button) findViewById(R.id.forests);
        park.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                locationMap.clear();


                if (sheetShowing){
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                }

                for (int i = 0; i < l.size(); i++){

                    if( locationCategories.get(i).equals("park")){

                        LatLng lat = new LatLng(l.get(i).getLocationLatitude(), l.get(i).getLocationLongitude());

                        markerCity2 = locationMap.addMarker(new MarkerOptions()
                                .title(l.get(i).getLocationName())
                                .snippet(l.get(i).getLocationAddress())
                                .position(lat).icon(BitmapDescriptorFactory.fromResource(R.drawable.forest)));
                        hashCity.put(markerCity2.getId(),l.get(i).getLocationCity());
                        hashAddress.put(markerCity2.getId(),l.get(i).getLocationAddress());
                        hashPicture.put(markerCity2.getId(),l.get(i).getLocationPicture());
                        hashCategory.put(markerCity2.getId(),l.get(i).getLocationCategory());
                        hashLocationID.put(markerCity2.getId(),l.get(i).getLocationID());
                        locationCategories.put(i,l.get(i).getLocationCategory());

                    }

                }

                locationMap.addMarker(new MarkerOptions().position(userPosition).title("I'm here...").icon(BitmapDescriptorFactory.fromResource(R.drawable.gps_marker)));
                CameraPosition cameraPositionUser = CameraPosition.builder().target(userPosition).zoom(12).tilt(45).bearing(0).build();
                locationMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPositionUser));

            }

        });


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

        ratingBarFunctions(marker);

        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

    }


    public void ratingBarFunctions(Marker marker) {

        final RatingBar simpleRatingBar = (RatingBar) findViewById(ratingBar);

        averageRating = ratingData.getAverageRating(marker, hashLocationID);
        simpleRatingBar.setRating(averageRating);

        final int locID = hashLocationID.get(marker.getId());

        final int parentID = parentLoggedIn();

    }



    public int parentLoggedIn(){
        Bundle bundle = getIntent().getExtras();
        loginUsername = bundle.getString("loginUsername");
        loginPassword = bundle.getString("loginPassword");
        Parent mapParent = parentData.retrieveInformationAboutParent(loginPassword);
        int parentID = mapParent.getParentID();

        return parentID;
    }


    public int peopleCheckedInNow (Marker marker){

        int peopleIn = 0;

        peopleIn = data.getPeopleCheckedInNow(marker, hashLocationID);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Number of people currently present: " + peopleIn);

        return peopleIn;
    }

    public void submitRating (Marker marker){

        final int locID = hashLocationID.get(marker.getId());
        final int parentID = parentLoggedIn();

        Button ratePlace = (Button) findViewById(R.id.submitRating);
        ratePlace.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                final AlertDialog.Builder submitRate = new AlertDialog.Builder(MapsPage.this);
                final View mView = getLayoutInflater().inflate(R.layout.rate_of_place, null);


                Button submitRating = (Button) mView.findViewById(R.id.dialog_submitRating);
                Button cancelRating = (Button) mView.findViewById(R.id.dialog_cancelRating);

                submitRate.setView(mView);

                dialog = submitRate.create();
                dialog.show();

                submitRating.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        final RatingBar simpleRatingBar1 = (RatingBar) mView.findViewById(dialog_ratingbar);

                        float f;
                        f = simpleRatingBar1.getRating();
                        Rating rating1 = new Rating("255", "255", f, "blabla", locID, parentID);
                        ratingData.createOrUpdateRating(rating1);

                        String totalStars = "Total Stars:: " + simpleRatingBar1.getNumStars();
                        String rating2 = "Rating :: " + simpleRatingBar1.getRating();

                        Toast.makeText(getApplicationContext(), totalStars + "\n" + rating2, Toast.LENGTH_LONG).show();

                        dialog.dismiss();


                    }
                });


                cancelRating.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {

                        dialog.dismiss();

                    }
                });
            }
        });
    }




    public void loadCheckInLater( Marker marker){

        Button mShowDialog = (Button) findViewById(R.id.checkInLater);
        mShowDialog.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {


                final AlertDialog.Builder secondBuilder = new AlertDialog.Builder(MapsPage.this);
                final AlertDialog.Builder mBuilder = new AlertDialog.Builder(MapsPage.this);
                final View mView = getLayoutInflater().inflate(R.layout.check_in_later_popup, null);
                final View secondView = getLayoutInflater().inflate(R.layout.check_in_later_popup_second_view, null);
                Button approve = (Button) mView.findViewById(R.id.approve);
                Button cancel = (Button) mView.findViewById(R.id.cancel);
                Button approveSecond = (Button) secondView.findViewById(R.id.approveSecond);
                Button cancelSecond = (Button) secondView.findViewById(R.id.cancelSecond);
                final TimePicker timepicker = (TimePicker) findViewById(R.id.chooseTime);
                final DatePicker datepicker = (DatePicker) findViewById(R.id.chooseDate);


                mBuilder.setView(mView);
                dialog = mBuilder.create();

                secondBuilder.setView(secondView);
                dialogSecond = secondBuilder.create();

                dialog.show();

                approve.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Here goes code that activates when you push APPROVE
                        dialog.dismiss();
                        dialogSecond.show();

                    }
                });

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Here goes code that activates when you push CANCEL
                        dialog.dismiss();
                    }
                });

                cancelSecond.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Here goes code that activates when you push CANCEL
                        dialogSecond.dismiss();
                        dialog.show();
                    }
                });


                approveSecond.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Here goes code that activates when you push APPROVE
                       /* storeHour = timepicker.getHour();
                        storeMinute = timepicker.getMinute();
                        storeMonth = datepicker.getMonth();
                        storeYear = datepicker.getYear();
                        storeDay = datepicker.getDayOfMonth();*/
                        /*
                        storeCheckInlaterData[0] = storeMinute;
                        storeCheckInlaterData[1] = storeHour;
                        storeCheckInlaterData[2] = storeDay;
                        storeCheckInlaterData[3] = storeMonth;
                        storeCheckInlaterData[4] = storeYear;*/

                    }
                });


            }
        });
    }

    public int passLocationId (Marker marker, Hashtable<String, Integer> hashLocationID){

        int j = hashLocationID.get(marker.getId());

        return j;

    }

   /*
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
    }   */



}

