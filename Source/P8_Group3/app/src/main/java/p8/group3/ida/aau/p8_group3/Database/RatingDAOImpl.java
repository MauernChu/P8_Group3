package p8.group3.ida.aau.p8_group3.Database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
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


    public void createRating (Rating rating) {
        ContentValues v = new ContentValues();
        v.put(DatabaseHandler.COLUMN_TIMERATINGCREATED, rating.getTimeRatingCreated());
        v.put(DatabaseHandler.COLUMN_TIMERATINGEDITED, rating.getTimeRatingEdited());
        v.put(DatabaseHandler.COLUMN_RATINGVALUE, rating.getRatingValue());
        v.put(DatabaseHandler.COLUMN_REVIEWTEXT, rating.getReviewText());
        v.put(DatabaseHandler.COLUMN_RATINGLOCATIONID, rating.getRatingLocationID());
        v.put(DatabaseHandler.COLUMN_RATINGPARENTID, rating.getRatingParentID());

        long insertID = database.insert(DatabaseHandler.TABLE_RATING, null, v);

    }


    public List<Double> getAverageRating (){

        double averageRating = 0;

        List<Double> averageRatingOfPlaces = new ArrayList<Double>();


        String query = "SELECT ratingLocationID FROM " + DatabaseHandler.TABLE_RATING;

        Cursor cursor = database.rawQuery(query, null);

        String query1 = "SELECT ratingValue FROM " + DatabaseHandler.TABLE_RATING;

        Cursor cursor1 = database.rawQuery(query1, null);

        String query2 = "SELECT _locationID FROM " + DatabaseHandler.TABLE_LOCATION;

        Cursor cursor2 = database.rawQuery(query2, null);


        int rating_ratingvalue = cursor1.getCount();
        int ratinglocationid = cursor.getCount();
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

                if( cursor4.getInt(cursor4.getColumnIndex("ratingLocationID")) == (i+1) ){

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

                averageRating = averageRating / g;
                averageRatingOfPlaces.add(averageRating);

            }
            else {
                averageRating = 0;
                averageRatingOfPlaces.add(averageRating);
            }

        }

        System.out.println(averageRatingOfPlaces);
        return averageRatingOfPlaces;

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
