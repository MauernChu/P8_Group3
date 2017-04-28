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
    private static final int DATABASE_VERSION = 19;
    private static final String DATABASE_NAME = "chimp.db";

    //Columns for the Parent table
    private static final String TABLE_PARENT = "parent";
    private static final String COLUMN_PARENTID = "_parentID";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_NUMBERCHILDREN = "numberOfChildren";
    private static final String COLUMN_AGECHILDREN = "ageOfChildren";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_PROFILEPICTURE = "profilePicture";
    private static final String COLUMN_INFOPARENT = "infoAboutParent";
    private static final String COLUMN_TIMECHECKEDIN = "timeCheckedIn";
    private static final String COLUMN_LOCATIONIDCHECKEDIN = "locationIDCheckedIn";

    //Columns for the HobbyList table
    private static final String TABLE_HOBBYLIST = "hobbyList";
    private static final String COLUMN_HOBBYID = "_hobbyID";
    private static final String COLUMN_HOBBYNAME = "hobbyName";

    //Columns for ParentHobby table
    private static final String TABLE_PARENTHOBBY = "parentHobby";
    private static final String COLUMN_TRACKHOBBYPARENTID = "trackParentID";
    private static final String COLUMN_TRACKHOBBYID = "trackHobbyID";

    //Columns for the LanguageList table
    private static final String TABLE_LANGUAGELIST = "languageList";
    private static final String COLUMN_LANGUAGEID = "_languageID";
    private static final String COLUMN_LANGUAGE = "language";

    //Columns for ParentLanguage table
    private static final String TABLE_PARENTLANGUAGE = "parentLanguage";
    private static final String COLUMN_TRACKLANGUAGEPARENTID = "trackLanguageParentID";
    private static final String COLUMN_TRACKLANGUAGEID = "trackLanguageID";

    //Columns for the Location table
    private static final String TABLE_LOCATION = "location";
    private static final String COLUMN_LOCATIONID = "_locationID";
    private static final String COLUMN_LOCATIONNAME = "locationName";
    private static final String COLUMN_LOCATIONPICTURE = "locationPicture";
    private static final String COLUMN_LOCATIONDESCRIPTION = "locationDescription";
    private static final String COLUMN_LOCATIONLONGITUDE = "locationLongitude";
    private static final String COLUMN_LOCATIONLATITUDE = "locationLatitude";

    //Columns for the Rating table
    private static final String TABLE_RATING = "rating";
    private static final String COLUMN_RATINGLOCATIONID = "ratingLocationID";
    private static final String COLUMN_RATINGPARENTID = "ratingParentID";
    private static final String COLUMN_TIMERATINGCREATED = "timeRatingCreated";
    private static final String COLUMN_TIMERATINGEDITED = "timeRatingEdited";
    private static final String COLUMN_RATINGVALUE = "ratingValue";
    private static final String COLUMN_REVIEWTEXT = "reviewText";

    //Columns for the PlannedActivity table
    private static final String TABLE_PLANNEDACTIVITY = "plannedActivity";
    private static final String COLUMN_PLANNEDACTIVITYPARENTID = "plannedActivityParentID";
    private static final String COLUMN_PLANNEDACTIVITYLOCATIONID = "plannedActivityLocationID";
    private static final String COLUMN_PLANNEDACITIVTYTIME = "plannedActivityTime";


    //We need to pass some information to the superclass and that is what we are doing through the
    //constructor.
    //context and factory are "background" information.
    public databaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    public static final String SQL_CREATE_TABLE_PARENT = "CREATE TABLE " + TABLE_PARENT + "("
                + COLUMN_PARENTID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_USERNAME + " TEXT NOT NULL, "
                + COLUMN_NUMBERCHILDREN + " TEXT NOT NULL, "
                + COLUMN_AGECHILDREN + " TEXT NOT NULL, "
                + COLUMN_PASSWORD + " TEXT NOT NULL, "
                + COLUMN_PROFILEPICTURE + " BLOB, "
                + COLUMN_INFOPARENT + " TEXT, "
                + COLUMN_TIMECHECKEDIN + " REAL, "
                + COLUMN_LOCATIONIDCHECKEDIN + " INT "
                +");";

    public static final String SQL_CREATE_TABLE_HOBBYLIST = "CREATE TABLE " + TABLE_HOBBYLIST + "("
            + COLUMN_HOBBYID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_HOBBYNAME + " TEXT NOT NULL "
            +");";

    public static final String SQL_CREATE_TABLE_PARENTHOBBY = "CREATE TABLE " + TABLE_PARENTHOBBY + "("
            + COLUMN_TRACKHOBBYPARENTID + " INTEGER NOT NULL, "
            + COLUMN_TRACKHOBBYID + " INTEGER NOT NULL, "
            + " FOREIGN KEY ("+COLUMN_TRACKHOBBYPARENTID+") REFERENCES "+TABLE_PARENT+" ("+COLUMN_PARENTID+"), "
            + " FOREIGN KEY ("+COLUMN_TRACKHOBBYID+") REFERENCES "+TABLE_HOBBYLIST+" ("+COLUMN_HOBBYID+"));";

    public static final String SQL_CREATE_TABLE_LANGUAGELIST = "CREATE TABLE " + TABLE_LANGUAGELIST + "("
            + COLUMN_LANGUAGEID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_LANGUAGE + " TEXT NOT NULL "
            +");";

    public static final String SQL_CREATE_TABLE_PARENLANGUAGE = "CREATE TABLE " + TABLE_PARENTLANGUAGE + "("
            + COLUMN_TRACKLANGUAGEPARENTID + " INTEGER NOT NULL, "
            + COLUMN_TRACKLANGUAGEID + " INTEGER NOT NULL, "
            + " FOREIGN KEY ("+COLUMN_TRACKLANGUAGEPARENTID+") REFERENCES "+TABLE_PARENT+" ("+COLUMN_PARENTID+"), "
            + " FOREIGN KEY ("+COLUMN_TRACKLANGUAGEID+") REFERENCES "+TABLE_LANGUAGELIST+" ("+COLUMN_LANGUAGEID+"));";

    public static final String SQL_CREATE_TABLE_LOCATION = "CREATE TABLE " + TABLE_LOCATION + "("
            + COLUMN_LOCATIONID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_LOCATIONNAME + " TEXT NOT NULL, "
            + COLUMN_LOCATIONPICTURE + " BLOB, "
            + COLUMN_LOCATIONDESCRIPTION + " TEXT, "
            + COLUMN_LOCATIONLONGITUDE + " DOUBLE NOT NULL, "
            + COLUMN_LOCATIONLATITUDE + " DOUBLE NOT NULL "
            +");";

    public static final String SQL_CREATE_TABLE_RATING = "CREATE TABLE " + TABLE_RATING + "("
            + COLUMN_TIMERATINGCREATED + "REAL NOT NULL, "
            + COLUMN_TIMERATINGEDITED + "REAL NOT NULL, "
            + COLUMN_RATINGVALUE + "INT NOT NULL, "
            + COLUMN_REVIEWTEXT + "TEXT, "
            + COLUMN_RATINGLOCATIONID + " INTEGER NOT NULL, "
            + COLUMN_RATINGPARENTID + " INTEGER NOT NULL, "
            + " FOREIGN KEY ("+COLUMN_RATINGLOCATIONID+") REFERENCES "+TABLE_LOCATION+" ("+COLUMN_LOCATIONID+"), "
            + " FOREIGN KEY ("+COLUMN_RATINGPARENTID+") REFERENCES "+TABLE_PARENT+" ("+COLUMN_PARENTID+"));";

    public static final String SQL_CREATE_TABLE_PLANNEDACTIVITY = "CREATE TABLE " + TABLE_PLANNEDACTIVITY + "("
            + COLUMN_PLANNEDACITIVTYTIME + " TEXT NOT NULL, "
            + COLUMN_PLANNEDACTIVITYLOCATIONID + " INT NOT NULL, "
            + COLUMN_PLANNEDACTIVITYPARENTID + " INT NOT NULL, "
            + " FOREIGN KEY ("+COLUMN_PLANNEDACTIVITYLOCATIONID+") REFERENCES "+TABLE_LOCATION+" ("+COLUMN_LOCATIONID+"), "
            + " FOREIGN KEY ("+COLUMN_PLANNEDACTIVITYPARENTID+") REFERENCES "+TABLE_PARENT+" ("+COLUMN_PARENTID+"));";



    //The first time we are going to create this database for the first time it is going to call this method
    public void onCreate(SQLiteDatabase database){
        database.execSQL(SQL_CREATE_TABLE_PARENT);
        database.execSQL(SQL_CREATE_TABLE_HOBBYLIST);
        database.execSQL(SQL_CREATE_TABLE_PARENTHOBBY);
        database.execSQL(SQL_CREATE_TABLE_LANGUAGELIST);
        database.execSQL(SQL_CREATE_TABLE_PARENLANGUAGE);
        database.execSQL(SQL_CREATE_TABLE_LOCATION);
        database.execSQL(SQL_CREATE_TABLE_RATING);
        database.execSQL(SQL_CREATE_TABLE_PLANNEDACTIVITY);
    }



    //if the version is upgraded it is going to call this method
    //In this method we are going to delete the existing database, and then we need to create the
    //database again by using the method above (with new code - a new version).
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PARENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HOBBYLIST);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PARENTHOBBY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LANGUAGELIST);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PARENTLANGUAGE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOCATION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RATING);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLANNEDACTIVITY);
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
