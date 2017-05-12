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

import p8.group3.ida.aau.p8_group3.Model.Rating;

public class RatingDAOImpl {


    private SQLiteDatabase database;
    private DatabaseHandler dbHelper;

    private String[] locationAllColumns = new String[]{
            DatabaseHandler.COLUMN_TIMERATINGCREATED,
            DatabaseHandler.COLUMN_TIMERATINGEDITED,
            DatabaseHandler.COLUMN_RATINGVALUE,
            DatabaseHandler.COLUMN_REVIEWTEXT,
            DatabaseHandler.COLUMN_RATINGLOCATIONID,
            DatabaseHandler.COLUMN_RATINGPARENTID};


    public RatingDAOImpl(Context context)
    {
        dbHelper = new DatabaseHandler(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }


    public void createOrUpdateRating(Rating rating) {


        int k = rating.getRatingParentID();

        int e = rating.getRatingLocationID();


        database = dbHelper.getReadableDatabase();

        String query2 = "SELECT ratingParentID FROM " + DatabaseHandler.TABLE_RATING;
        Cursor cursor2 = database.rawQuery(query2, null);

        String query3 = "SELECT ratingLocationID FROM " + DatabaseHandler.TABLE_RATING;
        Cursor cursor3 = database.rawQuery(query3, null);

        cursor2.isFirst();
        cursor3.isFirst();
        int h = 0;

        while (cursor2.moveToNext() && cursor3.moveToNext()){


            if (cursor2.getInt(cursor2.getColumnIndex("ratingParentID")) == (k) && cursor3.getInt(cursor3.getColumnIndex("ratingLocationID")) == (e)) {
                h = 1;
            }

        }

        System.out.println(h);

        cursor2.close();

        if( h == 1){

            database = dbHelper.getWritableDatabase();
            ContentValues v = new ContentValues();

            v.put(DatabaseHandler.COLUMN_TIMERATINGCREATED, rating.getTimeRatingCreated());
            v.put(DatabaseHandler.COLUMN_TIMERATINGEDITED, rating.getTimeRatingEdited());
            v.put(DatabaseHandler.COLUMN_RATINGVALUE, rating.getRatingValue());
            v.put(DatabaseHandler.COLUMN_REVIEWTEXT, rating.getReviewText());
            v.put(DatabaseHandler.COLUMN_RATINGLOCATIONID, rating.getRatingLocationID());
            v.put(DatabaseHandler.COLUMN_RATINGPARENTID, rating.getRatingParentID());

            int ratingLocationID = rating.getRatingLocationID();
            int ratingParentID = rating.getRatingParentID();


            String[] arguments = new String[] {String.valueOf(ratingLocationID), String.valueOf(ratingParentID)};


            database.update(DatabaseHandler.TABLE_RATING, v, DatabaseHandler.COLUMN_RATINGLOCATIONID + " = ? AND " + DatabaseHandler.COLUMN_RATINGPARENTID + " = ? " , arguments);

        }

        else{

            ContentValues v = new ContentValues();
            v.put(DatabaseHandler.COLUMN_TIMERATINGCREATED, rating.getTimeRatingCreated());
            v.put(DatabaseHandler.COLUMN_TIMERATINGEDITED, rating.getTimeRatingEdited());
            v.put(DatabaseHandler.COLUMN_RATINGVALUE, rating.getRatingValue());
            v.put(DatabaseHandler.COLUMN_REVIEWTEXT, rating.getReviewText());
            v.put(DatabaseHandler.COLUMN_RATINGLOCATIONID, rating.getRatingLocationID());
            v.put(DatabaseHandler.COLUMN_RATINGPARENTID, rating.getRatingParentID());

            long insertID = database.insert(DatabaseHandler.TABLE_RATING, null, v);
        }



    }



    public float getAverageRating(Marker marker, Hashtable<String, Integer> hashLocationID){

        float averageRating;
        float averageRatingOfPlaces = 0;
        float test2 = 0;

        List<Float> test = new ArrayList<Float>();

        String query2 = "SELECT _locationID FROM " + DatabaseHandler.TABLE_LOCATION;
        Cursor cursor2 = database.rawQuery(query2, null);
        int location_locationid = cursor2.getCount();
        int i;

        for (i = 0; i < location_locationid; i++){
            int g = 0;
            averageRating = 0;
            String query4 = "SELECT ratingLocationID FROM " + DatabaseHandler.TABLE_RATING;
            Cursor cursor4 = database.rawQuery(query4, null);
            cursor4.isFirst();
            String query5 = "SELECT ratingValue FROM " + DatabaseHandler.TABLE_RATING;
            Cursor cursor5 = database.rawQuery(query5, null);
            cursor5.isFirst();

            while (cursor4.moveToNext()){

                if(cursor4.getInt(cursor4.getColumnIndex("ratingLocationID")) == (i+1)){
                    cursor5.moveToPosition(cursor4.getPosition());
                    averageRating = averageRating + cursor5.getInt(cursor5.getColumnIndex("ratingValue"));
                    System.out.println(averageRating);
                    g=g+1;
                } else{
                    System.out.println("false");
                }
            }

            cursor4.close();
            cursor5.close();

            if ( g!= 0 ){
                averageRatingOfPlaces = averageRating / g;
                test.add(averageRatingOfPlaces);
            }
            else {
                averageRatingOfPlaces = 0;
                test.add(averageRatingOfPlaces);
            }
        }

        System.out.println(averageRatingOfPlaces);

        int k = hashLocationID.get(marker.getId());
        
        for (i = 0; i < location_locationid; i++){
            if ( (i+1) == k ){
                test2 = test.get(i);
            }
        }

        return test2;
    }



    public List<Rating> getMyRating(){
        List<Rating> stars = new ArrayList<Rating>();

        Cursor cursor = database.query(DatabaseHandler.TABLE_RATING, locationAllColumns, null, null, null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            Rating rating = cursorToMarker(cursor);
            stars.add(rating);
            cursor.moveToNext();
        }
        cursor.close();
        return stars;
    }

    private Rating cursorToMarker(Cursor cursor) {
        String timeRatingCreatedReal = cursor.getString(0);
        String timeRatingEditedReal = cursor.getString(1);
        float ratingValue = cursor.getFloat(2);
        String reviewTextText = cursor.getString(3);
        int ratingLocationID = cursor.getInt(4);
        int ratingParentID = cursor.getInt(5);

        Rating r = new Rating(timeRatingCreatedReal, timeRatingEditedReal, ratingValue, reviewTextText, ratingLocationID, ratingParentID);

        return r;

    }


}
