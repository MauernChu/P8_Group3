package p8.group3.ida.aau.p8_group3.Database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;

import p8.group3.ida.aau.p8_group3.model.Parent;

//This class is for working directly with the database
public class databaseHandler extends SQLiteOpenHelper {

    //Variables for database name and database version
    //If we are going to change the structure of the database, we need to upgrade the version.
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "chimp.db";


    //Columns for the Parent table
    private static final String TABLE_PARENT = "parent";
    private static final String COLUMN_PARENTID = "_parentID";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_NUMBERCHILDREN = "numberOfChildren";
    private static final String COLUMN_AGECHILDREN = "ageOfChildren";
    private static final String COLUMN_INFOPARENT = "infoAboutParent";

    //We need to pass some information to the superclass and that is what we are doing through the
    //constructor.
    //context and factory are "background" information.
    public databaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    //The first time we are going to create this database for the first time it is going to call this method
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_PARENT + "(" +
                COLUMN_PARENTID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERNAME + " TEXT NOT NULL " +
                /*
                COLUMN_NUMBERCHILDREN + "" +
                COLUMN_AGECHILDREN + " " +
                COLUMN_INFOPARENT + " " +
                */
                ");";
        db.execSQL(query);

    }



    //if the version is upgraded it is going to call this method
    //In this method we are going to delete the existing database, and then we need to create the
    //database again by using the method above (with new code - a new version).
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PARENT);
        onCreate(db);

    }

    /*
    Add new row to the table
    In order to add a new pwrent to the database we are going to pass in a parent object.
    ContentValues is a class build in to android, which allows you to set a bunch of different values
    for different columns, and insert them in one statement.
    */
    public void addParentToDatabase(Parent parent) {
        ContentValues values = new ContentValues();
        //values.put just states that get_username should be stored COLUMN_USERNAME.
        //Here we could add more elements to the value object (all the things we need to store)
        values.put(COLUMN_USERNAME, parent.get_username());
        //db (SQLiteDatabase object) is now equal to the database we are going to write to
        SQLiteDatabase db = getWritableDatabase();
        //This line is going to insert a new parent into the table
        db.insert(TABLE_PARENT, null, values);
        //Closing database
        db.close();
    }

    //Method for deleting a product in the database
    public void deleteParentFromDatabase(String username) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_PARENT + " WHERE " + COLUMN_USERNAME + "=\"" + username + "\";");

    }

    //print out the databse as string
    public String databaseToString() {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        //1 means that every condition is met (every row from the database)
        String query = "SELECT * FROM " + TABLE_PARENT + " WHERE 1;";

        //Cursor point to a location in the results
        Cursor c = db.rawQuery(query, null);
        //Move to the first row in your result
        c.moveToFirst();

        //Goes through everything in the database, and stores the usernames in the dbString.
        while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex("username"))!=null){
                dbString += c.getString(c.getColumnIndex("username"));
                //Placing the usernames on another line.
                dbString += "\n";
            }
            c.moveToNext();
        }
        db.close();
        return dbString;
    }

}
