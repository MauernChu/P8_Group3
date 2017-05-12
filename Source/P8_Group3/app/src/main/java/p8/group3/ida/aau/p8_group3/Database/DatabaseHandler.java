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
    private static final int DATABASE_VERSION = 42 ;
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
    public static final String COLUMN_HOBBYLIST = "hobbyList";
    public static final String COLUMN_lANGUAGELIST = "languageList";

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
    public static final String COLUMN_AVERAGERATING = "averageRating";
    public static final String COLUMN_PARENTSCURRENTLYCHECKEDIN = "parentsCurrentlyCheckedIn";

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
                + COLUMN_LOCATIONIDCHECKEDIN + " TEXT, "
                + COLUMN_EMAIL + " TEXT NOT NULL, "
                + COLUMN_CITYOFRESIDENCE + " TEXT "
                + COLUMN_HOBBYLIST + " TEXT "
                + COLUMN_lANGUAGELIST + " TEXT "
                +");";


    public static final String SQL_CREATE_TABLE_LOCATION = "CREATE TABLE " + TABLE_LOCATION + "("
            + COLUMN_LOCATIONID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_LOCATIONNAME + " TEXT NOT NULL, "
            + COLUMN_LOCATIONPICTURE + " TEXT NOT NULL, "
            + COLUMN_LOCATIONDESCRIPTION + " TEXT, "
            + COLUMN_LOCATIONLONGITUDE + " DOUBLE NOT NULL, "
            + COLUMN_LOCATIONLATITUDE + " DOUBLE NOT NULL, "
            + COLUMN_LOCATIONCATEGORY + " TEXT, "
            + COLUMN_LOCATIONADDRESS + " TEXT NOT NULL, "
            + COLUMN_LOCATIONCITY + " TEXT NOT NULL, "
            + COLUMN_AVERAGERATING + " DECIMAL, "
            + COLUMN_PARENTSCURRENTLYCHECKEDIN + " INTEGER "
            +");";

    public static final String SQL_CREATE_TABLE_RATING = "CREATE TABLE " + TABLE_RATING + "("
            + COLUMN_TIMERATINGCREATED + " REAL NOT NULL, "
            + COLUMN_TIMERATINGEDITED + " REAL NOT NULL, "
            + COLUMN_RATINGVALUE + " INTEGER NOT NULL, "
            + COLUMN_REVIEWTEXT + " TEXT, "
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
        createOnCreateTables(database);
        InsertInitialLocationRows(database);
        InsertInitialParentRows(database);
    }

    private void createOnCreateTables(SQLiteDatabase database) {
        database.execSQL(SQL_CREATE_TABLE_PARENT);
        database.execSQL(SQL_CREATE_TABLE_LOCATION);
        database.execSQL(SQL_CREATE_TABLE_RATING);
        database.execSQL(SQL_CREATE_TABLE_PLANNEDACTIVITY);
    }

    private void InsertInitialLocationRows(SQLiteDatabase database) {
        //Havnefront Location
        ContentValues initialLocationHavnefrontValues = new ContentValues();
        initialLocationHavnefrontValues.put(DatabaseHandler.COLUMN_LOCATIONNAME, "Østre Anlæg Legeplads");
        initialLocationHavnefrontValues.put(DatabaseHandler.COLUMN_LOCATIONLONGITUDE, 9.939690);
        initialLocationHavnefrontValues.put(DatabaseHandler.COLUMN_LOCATIONLATITUDE, 57.043719);
        initialLocationHavnefrontValues.put(DatabaseHandler.COLUMN_LOCATIONCATEGORY, "playground");
        initialLocationHavnefrontValues.put(DatabaseHandler.COLUMN_LOCATIONADDRESS, "Bonnesensgade");
        initialLocationHavnefrontValues.put(DatabaseHandler.COLUMN_LOCATIONCITY, "9000 Aalborg");
        initialLocationHavnefrontValues.put(DatabaseHandler.COLUMN_LOCATIONPICTURE, "http://www.takepart.com/sites/default/files/styles/tp_gallery_slide/public/Dragon_9-itok=VtWnRjSf.jpg");
        database.insert(DatabaseHandler.TABLE_LOCATION, null, initialLocationHavnefrontValues);

        //Slotspladsen Location
        ContentValues initialLocationSlotspladsenValues = new ContentValues();
        initialLocationSlotspladsenValues.put(DatabaseHandler.COLUMN_LOCATIONNAME, "Jomfru Ane Parken");
        initialLocationSlotspladsenValues.put(DatabaseHandler.COLUMN_LOCATIONLONGITUDE, 9.922097);
        initialLocationSlotspladsenValues.put(DatabaseHandler.COLUMN_LOCATIONLATITUDE, 57.051100);
        initialLocationSlotspladsenValues.put(DatabaseHandler.COLUMN_LOCATIONCATEGORY, "park");
        initialLocationSlotspladsenValues.put(DatabaseHandler.COLUMN_LOCATIONADDRESS, "Aalborg Centrum");
        initialLocationSlotspladsenValues.put(DatabaseHandler.COLUMN_LOCATIONCITY, "Aalborg");
        initialLocationSlotspladsenValues.put(DatabaseHandler.COLUMN_LOCATIONPICTURE, "http://www.lonelyplanet.com/news/wp-content/uploads/2016/08/Monstrum2-630x394.jpg");
        database.insert(DatabaseHandler.TABLE_LOCATION, null, initialLocationSlotspladsenValues);
    }

    private void InsertInitialParentRows(SQLiteDatabase database){
        ContentValues initialParentValues = new ContentValues();
        initialParentValues.put(DatabaseHandler.COLUMN_USERNAME, "jannik");
        initialParentValues.put(DatabaseHandler.COLUMN_PASSWORD, "jannik");
        initialParentValues.put(DatabaseHandler.COLUMN_EMAIL, "jannik");
        initialParentValues.put(DatabaseHandler.COLUMN_CITYOFRESIDENCE, "Aalborg");
        initialParentValues.put(DatabaseHandler.COLUMN_NUMBERCHILDREN, 1);
        initialParentValues.put(DatabaseHandler.COLUMN_AGECHILDREN, "One");
        database.insert(DatabaseHandler.TABLE_PARENT, null, initialParentValues);
        initialParentValues.put(DatabaseHandler.COLUMN_USERNAME, "christos");
        initialParentValues.put(DatabaseHandler.COLUMN_PASSWORD, "christos");
        initialParentValues.put(DatabaseHandler.COLUMN_EMAIL, "christos");
        initialParentValues.put(DatabaseHandler.COLUMN_CITYOFRESIDENCE, "Aalborg");
        initialParentValues.put(DatabaseHandler.COLUMN_NUMBERCHILDREN, 1);
        initialParentValues.put(DatabaseHandler.COLUMN_AGECHILDREN, "One");
        database.insert(DatabaseHandler.TABLE_PARENT, null, initialParentValues);
        initialParentValues.put(DatabaseHandler.COLUMN_USERNAME, "katarina");
        initialParentValues.put(DatabaseHandler.COLUMN_PASSWORD, "katarina");
        initialParentValues.put(DatabaseHandler.COLUMN_EMAIL, "katarina");
        initialParentValues.put(DatabaseHandler.COLUMN_CITYOFRESIDENCE, "Aalborg");
        initialParentValues.put(DatabaseHandler.COLUMN_NUMBERCHILDREN, 1);
        initialParentValues.put(DatabaseHandler.COLUMN_AGECHILDREN, "One");
        database.insert(DatabaseHandler.TABLE_PARENT, null, initialParentValues);
        initialParentValues.put(DatabaseHandler.COLUMN_USERNAME, "camilla");
        initialParentValues.put(DatabaseHandler.COLUMN_PASSWORD, "camilla");
        initialParentValues.put(DatabaseHandler.COLUMN_EMAIL, "camilla");
        initialParentValues.put(DatabaseHandler.COLUMN_CITYOFRESIDENCE, "Aalborg");
        initialParentValues.put(DatabaseHandler.COLUMN_NUMBERCHILDREN, 1);
        initialParentValues.put(DatabaseHandler.COLUMN_AGECHILDREN, "One");
        database.insert(DatabaseHandler.TABLE_PARENT, null, initialParentValues);
        initialParentValues.put(DatabaseHandler.COLUMN_USERNAME, "mette");
        initialParentValues.put(DatabaseHandler.COLUMN_PASSWORD, "mette");
        initialParentValues.put(DatabaseHandler.COLUMN_EMAIL, "mette");
        initialParentValues.put(DatabaseHandler.COLUMN_CITYOFRESIDENCE, "Aalborg");
        initialParentValues.put(DatabaseHandler.COLUMN_NUMBERCHILDREN, 1);
        initialParentValues.put(DatabaseHandler.COLUMN_AGECHILDREN, "One");
        database.insert(DatabaseHandler.TABLE_PARENT, null, initialParentValues);
    }



    //if the version is upgraded it is going to call this method
    //In this method we are going to delete the existing database, and then we need to create the
    //database again by using the method above (with new code - a new version).
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PARENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOCATION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RATING);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLANNEDACTIVITY);
        onCreate(db);

    }
}
