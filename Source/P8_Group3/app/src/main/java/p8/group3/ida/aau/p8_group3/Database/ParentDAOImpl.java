package p8.group3.ida.aau.p8_group3.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import p8.group3.ida.aau.p8_group3.Model.Parent;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ParentDAOImpl implements p8.group3.ida.aau.p8_group3.Database.DAO.ParentDAO {

    private SQLiteDatabase database;
    private DatabaseHandler dbHelper;
    private Context parentContext;
    private String[] parentAllColumns = new String[]{DatabaseHandler.COLUMN_PARENTID,
            DatabaseHandler.COLUMN_USERNAME, DatabaseHandler.COLUMN_NUMBERCHILDREN,
            DatabaseHandler.COLUMN_AGECHILDREN, DatabaseHandler.COLUMN_PASSWORD,
            DatabaseHandler.COLUMN_PROFILEPICTURE, DatabaseHandler.COLUMN_INFOPARENT,
            DatabaseHandler.COLUMN_TIMECHECKEDIN, DatabaseHandler.COLUMN_LOCATIONIDCHECKEDIN,
            DatabaseHandler.COLUMN_EMAIL, DatabaseHandler.COLUMN_CITYOFRESIDENCE};

    public ParentDAOImpl(Context context)
    {
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
        long insertID = database.insert(DatabaseHandler.TABLE_PARENT, null, values);
        Cursor cursor = database.query(DatabaseHandler.TABLE_PARENT, parentAllColumns,
                DatabaseHandler.COLUMN_PARENTID + "=" + insertID, null, null, null, null);
        cursor.moveToFirst();
        Parent testParent = cursorToParent(cursor);
        cursor.close();
        return testParent;
    }

    private Parent cursorToParent(Cursor cursor) {
        int parentID = cursor.getInt(0);
        String username = cursor.getString(1);
        String password = cursor.getString(4);
        String email = cursor.getString(9);
        int numberOfChildren = cursor.getInt(2);
        String ageOfChildren = cursor.getString(3);
        Parent parent = new Parent(parentID, username, password, email, numberOfChildren, ageOfChildren);
        return parent;


    }
    public String loginCheckCredentials(String username) {
        database = dbHelper.getReadableDatabase();
        String query = "SELECT username, password FROM " + DatabaseHandler.TABLE_PARENT;
        Cursor cursor = database.rawQuery(query, null);
        String usernameDb, passwordDb;
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
}
