package p8.group3.ida.aau.p8_group3.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.google.android.gms.maps.model.Marker;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import p8.group3.ida.aau.p8_group3.Model.Parent;

public class ParentDAOImpl implements p8.group3.ida.aau.p8_group3.Database.DAO.ParentDAO {

    private SQLiteDatabase database;
    private DatabaseHandler dbHelper;
    private Context parentContext;

    private String[] parentAllColumns = new String[]{
            DatabaseHandler.COLUMN_PARENTID,
            DatabaseHandler.COLUMN_USERNAME,
            DatabaseHandler.COLUMN_NUMBERCHILDREN,
            DatabaseHandler.COLUMN_AGECHILDREN,
            DatabaseHandler.COLUMN_PASSWORD,
            DatabaseHandler.COLUMN_PROFILEPICTURE,
            DatabaseHandler.COLUMN_INFOPARENT,
            DatabaseHandler.COLUMN_TIMECHECKEDIN,
            DatabaseHandler.COLUMN_LOCATIONIDCHECKEDIN,
            DatabaseHandler.COLUMN_EMAIL,
            DatabaseHandler.COLUMN_CITYOFRESIDENCE};


    public ParentDAOImpl(Context context) {
        dbHelper = new DatabaseHandler(context);
    }

    @Override
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    @Override
    public void close() {
        dbHelper.close();
    }

    @Override
    public Parent createParent(Parent parent) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHandler.COLUMN_USERNAME, parent.getUsername());
        values.put(DatabaseHandler.COLUMN_PASSWORD, parent.getPassword());
        values.put(DatabaseHandler.COLUMN_EMAIL, parent.getEmail());
        values.put(DatabaseHandler.COLUMN_NUMBERCHILDREN, parent.getNumberOfChildren());
        values.put(DatabaseHandler.COLUMN_AGECHILDREN, parent.getAgeOfChildren());
        values.put(DatabaseHandler.COLUMN_CITYOFRESIDENCE, parent.getCityOfResidence());
        long insertID = database.insert(DatabaseHandler.TABLE_PARENT, null, values);
        Cursor cursor = database.query(DatabaseHandler.TABLE_PARENT, parentAllColumns,
                DatabaseHandler.COLUMN_PARENTID + "=" + insertID, null, null, null, null);
        cursor.moveToFirst();
        Parent createParent = cursorToParent(cursor);
        cursor.close();
        return createParent;
    }


    private Parent cursorToParent(Cursor cursor) {
        int parentID = cursor.getInt(0);
        String username = cursor.getString(1);
        String password = cursor.getString(4);
        String email = cursor.getString(9);
        int numberOfChildren = cursor.getInt(2);
        String ageOfChildren = cursor.getString(3);
        String cityOfResidence = cursor.getString(10);
        Parent parent = new Parent(parentID, username, password, email, numberOfChildren, ageOfChildren, cityOfResidence);
        return parent;
    }

    private Parent fetchParentInformation(Cursor cursor) {
        int parentID = cursor.getInt(0);

        String username = cursor.getString(1);

        int numberOfChildren = cursor.getInt(2);
        String ageOfChildren = cursor.getString(3);

        String password = cursor.getString(4);
        String profilePicture = cursor.getString(5);
        String infoAboutParent = cursor.getString(6);
        int locationIDCheckedIn = cursor.getInt(8);
        String email = cursor.getString(9);
        String cityOfResidence = cursor.getString(10);
        String timeCheckedIn = cursor.getString(7);
        DateFormat timeCheckedInToDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date timeCheckedInToDate = null;
        if(timeCheckedIn != null) {
            try {
                timeCheckedInToDate = timeCheckedInToDateFormat.parse(timeCheckedIn);
            } catch (ParseException e) {
            }
        }
        Parent fetchParent = new Parent(parentID, username, numberOfChildren, ageOfChildren, password, profilePicture, infoAboutParent, locationIDCheckedIn, email, cityOfResidence, timeCheckedInToDate);
        return fetchParent;
    }

    public String loginCheckCredentials(String username) {
        database = dbHelper.getReadableDatabase();
        String query = "SELECT username, password FROM " + DatabaseHandler.TABLE_PARENT;
        Cursor cursor = database.rawQuery(query, null);
        String usernameDb;
        String passwordDb;
        passwordDb = "not found";

        if (cursor.moveToFirst()) {
            do {
                usernameDb = cursor.getString(0);

                if (usernameDb.equals(username)) {
                    passwordDb = cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }
        return passwordDb;
    }


    public Parent retrieveInformationAboutParent(String password) {
        database = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM " + DatabaseHandler.TABLE_PARENT + " WHERE " + DatabaseHandler.COLUMN_PASSWORD + " = ? ";
        Cursor cursor = database.rawQuery(query, new String[]{password}, null);
        cursor.moveToFirst();
        Parent databaseParent = fetchParentInformation(cursor);
        cursor.close();
        return databaseParent;
    }

    //method for inserting new data to the database
    public void editProfile(Parent parent) {
        database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHandler.COLUMN_USERNAME, parent.getUsername());
        values.put(DatabaseHandler.COLUMN_NUMBERCHILDREN, parent.getNumberOfChildren());
        values.put(DatabaseHandler.COLUMN_AGECHILDREN, parent.getAgeOfChildren());
        values.put(DatabaseHandler.COLUMN_CITYOFRESIDENCE, parent.getCityOfResidence());
        //values.put(DatabaseHandler.COLUMN_LANGUAGE, parent.get());
        values.put(DatabaseHandler.COLUMN_INFOPARENT, parent.getInfoAboutParent());
        //values.put(DatabaseHandler.COLUMN_HOBBY, parent.getUsername());
        String parentPassword = parent.getPassword();
        String[] arguments = new String[]{parentPassword};
        database.update(DatabaseHandler.TABLE_PARENT, values, DatabaseHandler.COLUMN_PASSWORD + " = ? ", arguments);

    }

    @Override
    public void checkInNow(Parent parent, int j) {

        database = dbHelper.getWritableDatabase();
        ContentValues dateValues = new ContentValues();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+2"));
        Date date = new Date();
        int l = j;
        dateValues.put(DatabaseHandler.COLUMN_TIMECHECKEDIN, dateFormat.format(date));
        dateValues.put(DatabaseHandler.COLUMN_LOCATIONIDCHECKEDIN, l);
        String parentPassword = parent.getPassword();
        String[] arguments = new String[]{parentPassword};
        database.update(DatabaseHandler.TABLE_PARENT, dateValues, DatabaseHandler.COLUMN_PASSWORD + " = ? ", arguments);

    }


    @Override
    public void checkOut(String loginPassword, Marker marker) {

        database = dbHelper.getWritableDatabase();
        ContentValues dateValues = new ContentValues();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+2"));
        Date date = new Date();

        String parentPassword =loginPassword;
        //String parentPassword = parent.getPassword();
        String[] arguments = new String[]{parentPassword};

        final Calendar calendar = Calendar.getInstance();
        final Calendar calendar1 = Calendar.getInstance();

        calendar.setTime(date);

        Date timeOfCheckIn = calendar.getTime();

        calendar1.add(Calendar.SECOND, 5);
        long timeOfCheckOut = calendar1.getTimeInMillis();

        System.out.println(timeOfCheckOut);
        System.out.println(timeOfCheckIn);

        boolean t = false;

        while (t == false){

            final Calendar calendar2 = Calendar.getInstance();

            long time = calendar2.getTimeInMillis();


            if (timeOfCheckOut <= time ){
                dateValues.putNull(DatabaseHandler.COLUMN_TIMECHECKEDIN);
                dateValues.putNull(DatabaseHandler.COLUMN_LOCATIONIDCHECKEDIN);
                // dateValues.put(DatabaseHandler.COLUMN_TIMECHECKEDIN, );
                // dateValues.put(DatabaseHandler.COLUMN_LOCATIONIDCHECKEDIN, );

                database.update(DatabaseHandler.TABLE_PARENT, dateValues, DatabaseHandler.COLUMN_PASSWORD + " = ? ", arguments);
                t = true;
            }

        }
    }



    @Override
    public void checkOut(Parent parent, Marker marker) {
        database = dbHelper.getWritableDatabase();
        ContentValues dateValues = new ContentValues();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+2"));
        Date date = new Date();

        //String parentPassword =loginPassword;
        String parentPassword = parent.getPassword();
        String[] arguments = new String[]{parentPassword};

        final Calendar calendar = Calendar.getInstance();
        final Calendar calendar1 = Calendar.getInstance();

        calendar.setTime(date);

        Date timeOfCheckIn = calendar.getTime();

        calendar1.add(Calendar.SECOND, 5);
        long timeOfCheckOut = calendar1.getTimeInMillis();

        System.out.println(timeOfCheckOut);
        System.out.println(timeOfCheckIn);

        boolean t = false;

        while (t = false){

            final Calendar calendar2 = Calendar.getInstance();

            long time = calendar2.getTimeInMillis();


            if (timeOfCheckOut <= time ){
                dateValues.putNull(DatabaseHandler.COLUMN_TIMECHECKEDIN);
                dateValues.putNull(DatabaseHandler.COLUMN_LOCATIONIDCHECKEDIN);
                // dateValues.put(DatabaseHandler.COLUMN_TIMECHECKEDIN, );
                // dateValues.put(DatabaseHandler.COLUMN_LOCATIONIDCHECKEDIN, );

                database.update(DatabaseHandler.TABLE_PARENT, dateValues, DatabaseHandler.COLUMN_PASSWORD + " = ? ", arguments);
                t = true;
            }

        }
    }

}







