package p8.group3.ida.aau.p8_group3.Database;


import android.database.sqlite.SQLiteDatabase;

public class LocationDAOImpl {

    private SQLiteDatabase database;
    private DatabaseHandler dbHelper;

    private String[] locationAllColumns = new String[]{DatabaseHandler.COLUMN_LOCATIONID,
            DatabaseHandler.COLUMN_LOCATIONNAME, DatabaseHandler.COLUMN_LOCATIONPICTURE,
            DatabaseHandler.COLUMN_LOCATIONDESCRIPTION, DatabaseHandler.COLUMN_LOCATIONLONGITUDE,
            DatabaseHandler.COLUMN_LOCATIONLATITUDE, DatabaseHandler.COLUMN_LOCATIONCATEGORY};



}
