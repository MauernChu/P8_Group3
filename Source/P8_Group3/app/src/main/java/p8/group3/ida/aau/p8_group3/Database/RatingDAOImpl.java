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
