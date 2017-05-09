package p8.group3.ida.aau.p8_group3.Database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import java.sql.Blob;
import java.util.List;
import java.util.ArrayList;

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
