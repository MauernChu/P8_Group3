package p8.group3.ida.aau.p8_group3.Database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;

import p8.group3.ida.aau.p8_group3.Model.Parent;

//This class is for working directly with the database
public class DatabaseHandler extends SQLiteOpenHelper {

    //Variables for database name and database version
    //If we are going to change the structure of the database, we need to upgrade the version.
    private static final int DATABASE_VERSION = 22 ;
    private static final String DATABASE_NAME = "chimp.db";

    //Columns for the Parent table
    public static final String TABLE_PARENT = "parent";
    public static final String COLUMN_PARENTID = "_parentID";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_NUMBERCHILDREN = "numberOfChildren";
    public static final String COLUMN_AGECHILDREN = "ageOfChildren";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_PROFILEPICTURE = "profilePicture";
    public static final String COLUMN_INFOPARENT = "infoAboutParent";
    public static final String COLUMN_TIMECHECKEDIN = "timeCheckedIn";
    public static final String COLUMN_LOCATIONIDCHECKEDIN = "locationIDCheckedIn";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_CITYOFRESIDENCE = "cityOfResidence";

    //Columns for the HobbyList table
    public static final String TABLE_HOBBYLIST = "hobbyList";
    public static final String COLUMN_HOBBYID = "_hobbyID";
    public static final String COLUMN_HOBBYNAME = "hobbyName";
    public static final String COLUMN_HOBBYICON = "hobbyIcon";

    //Columns for ParentHobby table
    public static final String TABLE_PARENTHOBBY = "parentHobby";
    public static final String COLUMN_TRACKHOBBYPARENTID = "trackParentID";
    public static final String COLUMN_TRACKHOBBYID = "trackHobbyID";

    //Columns for the LanguageList table
    public static final String TABLE_LANGUAGELIST = "languageList";
    public static final String COLUMN_LANGUAGEID = "_languageID";
    public static final String COLUMN_LANGUAGE = "language";

    //Columns for ParentLanguage table
    public static final String TABLE_PARENTLANGUAGE = "parentLanguage";
    public static final String COLUMN_TRACKLANGUAGEPARENTID = "trackLanguageParentID";
    public static final String COLUMN_TRACKLANGUAGEID = "trackLanguageID";

    //Columns for the Location table
    public static final String TABLE_LOCATION = "location";
    public static final String COLUMN_LOCATIONID = "_locationID";
    public static final String COLUMN_LOCATIONNAME = "locationName";
    public static final String COLUMN_LOCATIONPICTURE = "locationPicture";
    public static final String COLUMN_LOCATIONDESCRIPTION = "locationDescription";
    public static final String COLUMN_LOCATIONLONGITUDE = "locationLongitude";
    public static final String COLUMN_LOCATIONLATITUDE = "locationLatitude";
    public static final String COLUMN_LOCATIONCATEGORY = "locationCategory";
    public static final String COLUMN_LOCATIONADDRESS = "locationAddress";
    public static final String COLUMN_LOCATIONCITY = "locationCity";

    //Columns for the Rating table
    public static final String TABLE_RATING = "rating";
    public static final String COLUMN_RATINGLOCATIONID = "ratingLocationID";
    public static final String COLUMN_RATINGPARENTID = "ratingParentID";
    public static final String COLUMN_TIMERATINGCREATED = "timeRatingCreated";
    public static final String COLUMN_TIMERATINGEDITED = "timeRatingEdited";
    public static final String COLUMN_RATINGVALUE = "ratingValue";
    public static final String COLUMN_REVIEWTEXT = "reviewText";

    //Columns for the PlannedActivity table
    public static final String TABLE_PLANNEDACTIVITY = "plannedActivity";
    public static final String COLUMN_PLANNEDACTIVITYPARENTID = "plannedActivityParentID";
    public static final String COLUMN_PLANNEDACTIVITYLOCATIONID = "plannedActivityLocationID";
    public static final String COLUMN_PLANNEDACITIVTYTIME = "plannedActivityTime";


    //We need to pass some information to the superclass and that is what we are doing through the
    //constructor.
    //context and factory are "background" information.
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
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
                + COLUMN_LOCATIONIDCHECKEDIN + " INT, "
                + COLUMN_EMAIL + " TEXT NOT NULL, "
                + COLUMN_CITYOFRESIDENCE + " TEXT "
                +");";

    public static final String SQL_CREATE_TABLE_HOBBYLIST = "CREATE TABLE " + TABLE_HOBBYLIST + "("
            + COLUMN_HOBBYID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_HOBBYNAME + " TEXT NOT NULL, "
            + COLUMN_HOBBYICON + " BLOB "
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
            + COLUMN_LOCATIONPICTURE + " TEXT, "
            + COLUMN_LOCATIONDESCRIPTION + " TEXT, "
            + COLUMN_LOCATIONLONGITUDE + " DOUBLE NOT NULL, "
            + COLUMN_LOCATIONLATITUDE + " DOUBLE NOT NULL, "
            + COLUMN_LOCATIONCATEGORY + " TEXT, "
            + COLUMN_LOCATIONADDRESS + " TEXT NOT NULL, "
            + COLUMN_LOCATIONCITY + " TEXT NOT NULL "
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
}
