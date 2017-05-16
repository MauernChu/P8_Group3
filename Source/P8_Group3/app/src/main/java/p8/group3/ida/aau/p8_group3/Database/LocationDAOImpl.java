package p8.group3.ida.aau.p8_group3.Database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import p8.group3.ida.aau.p8_group3.Model.Location;

public class LocationDAOImpl {

    private SQLiteDatabase database;
    private DatabaseHandler dbHelper;

    private String[] locationAllColumns = new String[]{
            DatabaseHandler.COLUMN_LOCATIONID,
            DatabaseHandler.COLUMN_LOCATIONNAME,
            DatabaseHandler.COLUMN_LOCATIONDESCRIPTION,
            DatabaseHandler.COLUMN_LOCATIONLONGITUDE,
            DatabaseHandler.COLUMN_LOCATIONLATITUDE,
            DatabaseHandler.COLUMN_LOCATIONCATEGORY,
            DatabaseHandler.COLUMN_LOCATIONADDRESS,
            DatabaseHandler.COLUMN_LOCATIONCITY,
            DatabaseHandler.COLUMN_LOCATIONPICTURE,
            DatabaseHandler.COLUMN_AVERAGERATING,
            DatabaseHandler.COLUMN_PARENTSCURRENTLYCHECKEDIN
    };



    public LocationDAOImpl(Context context)
    {
        dbHelper = new DatabaseHandler(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }


    public void createLocation (Location location) {
        ContentValues v = new ContentValues();
        v.put(DatabaseHandler.COLUMN_LOCATIONID, location.getLocationID());
        v.put(DatabaseHandler.COLUMN_LOCATIONNAME, location.getLocationName());
        v.put(DatabaseHandler.COLUMN_LOCATIONDESCRIPTION, location.getLocationDescription());
        v.put(DatabaseHandler.COLUMN_LOCATIONLONGITUDE, location.getLocationLongitude());
        v.put(DatabaseHandler.COLUMN_LOCATIONLATITUDE, location.getLocationLatitude());
        v.put(DatabaseHandler.COLUMN_LOCATIONCATEGORY, location.getLocationCategory());
        v.put(DatabaseHandler.COLUMN_LOCATIONADDRESS, location.getLocationAddress());
        v.put(DatabaseHandler.COLUMN_LOCATIONCITY, location.getLocationCity());
        v.put(DatabaseHandler.COLUMN_LOCATIONPICTURE, location.getLocationPicture());
        v.put(DatabaseHandler.COLUMN_AVERAGERATING, location.getAverageRating());
        v.put(DatabaseHandler.COLUMN_PARENTSCURRENTLYCHECKEDIN, location.getParentsCurrentlyCheckedIn());

        long insertID = database.insert(DatabaseHandler.TABLE_LOCATION, null, v);

    }



    public List<Location> getMyMarkers(){
    List<Location> markers = new ArrayList<Location>();

        Cursor cursor = database.query(DatabaseHandler.TABLE_LOCATION, locationAllColumns, null, null, null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            Location location = cursorToMarker(cursor);
            markers.add(location);
            cursor.moveToNext();
        }
        cursor.close();
        return markers;

    }

    public int getPeopleCheckedInNow (Marker marker, Hashtable<String, Integer> hashLocationID){

        int peopleInNow = 0;
        final int locationID = hashLocationID.get(marker.getId());

        System.out.println(locationID);

        String query2 = "SELECT parentsCurrentlyCheckedIn FROM " + DatabaseHandler.TABLE_LOCATION;
        Cursor cursor2 = database.rawQuery(query2, null);

        String query3 = "SELECT _locationID FROM " + DatabaseHandler.TABLE_LOCATION;
        Cursor cursor3 = database.rawQuery(query3, null);


        cursor2.isFirst();
        cursor3.isFirst();

        while (cursor3.moveToNext()){

                if (locationID == cursor3.getInt(cursor3.getColumnIndex("_locationID"))){

                    int k = cursor3.getPosition();

                    cursor2.moveToPosition(k);

                    peopleInNow = cursor2.getInt(cursor2.getColumnIndex("parentsCurrentlyCheckedIn"));

                }

            }

        cursor2.close();
        cursor3.close();

        return peopleInNow;

    }


    public void numberOfPeopleCheckedIn () {

        List<Integer> numOfPplCheckedIn = new ArrayList<>();


        String query2 = "SELECT _locationID FROM " + DatabaseHandler.TABLE_LOCATION;
        Cursor cursor2 = database.rawQuery(query2, null);
        int location_locationid = cursor2.getCount();

        int i;
        int j;
        int h = 0;


        for (i = 0; i < location_locationid; i++) {

            Integer k = 0;

            String query5 = "SELECT locationIDCheckedIn FROM " + DatabaseHandler.TABLE_PARENT;
            Cursor cursor5 = database.rawQuery(query5, null);
            cursor5.isFirst();

            String query4 = "SELECT _parentID FROM " + DatabaseHandler.TABLE_PARENT;
            Cursor cursor4 = database.rawQuery(query4, null);
            cursor4.isFirst();

            while (cursor4.moveToNext()) {

                cursor5.moveToNext();

                int l = cursor5.getInt(cursor5.getColumnIndex("locationIDCheckedIn"));
                System.out.println(l);

                if (l == (i + 1)) {

                    k = k + 1;

                } else {
                    System.out.println("false");
                }
            }

            cursor4.close();
            cursor5.close();

            numOfPplCheckedIn.add(k);

            String query = "SELECT _locationID FROM " + DatabaseHandler.TABLE_LOCATION;
            Cursor cursor = database.rawQuery(query, null);

            h = numOfPplCheckedIn.get(i);

            int locationID = i+1;

            ContentValues values = new ContentValues();
            values.put(DatabaseHandler.COLUMN_PARENTSCURRENTLYCHECKEDIN, h);


            String[] arguments = new String[] {String.valueOf(locationID)};

            database.update(DatabaseHandler.TABLE_LOCATION, values, DatabaseHandler.COLUMN_LOCATIONID + " = ? ", arguments);

        }

    }



    private Location cursorToMarker(Cursor cursor) {
        int locationID = cursor.getInt(0);
        String locationName = cursor.getString(1);
        String locationDescription = cursor.getString(2);
        Double locationLongitude = cursor.getDouble(3);
        Double locationLatitude = cursor.getDouble(4);
        String locationCategory = cursor.getString(5);
        String locationAddress = cursor.getString(6);
        String locationCity = cursor.getString(7);
        String locationPicture = cursor.getString(8);
        Double averageRating = cursor.getDouble(9);
        int parentsCurrentlyCheckedIn = cursor.getInt(10);


        Location l = new Location(locationID, locationName, locationDescription, locationLongitude, locationLatitude, locationCategory, locationAddress, locationCity , locationPicture, averageRating, parentsCurrentlyCheckedIn );

        return l;

    }


}
