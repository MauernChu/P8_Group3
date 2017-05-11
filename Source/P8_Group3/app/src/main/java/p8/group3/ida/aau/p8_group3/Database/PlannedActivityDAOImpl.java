package p8.group3.ida.aau.p8_group3.Database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import p8.group3.ida.aau.p8_group3.Model.PlannedActivity;

public class PlannedActivityDAOImpl {


    private SQLiteDatabase database;
    private DatabaseHandler dbHelper;

    private String[] locationAllColumns = new String[]{
            DatabaseHandler.COLUMN_PLANNEDACITIVTYTIME,
            DatabaseHandler.COLUMN_PLANNEDACTIVITYLOCATIONID,
            DatabaseHandler.COLUMN_PLANNEDACTIVITYPARENTID};


    public PlannedActivityDAOImpl(Context context)
    {
        dbHelper = new DatabaseHandler(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }


    public void createPlannedActivity (PlannedActivity plannedActivity) {
        ContentValues v = new ContentValues();
        v.put(DatabaseHandler.COLUMN_PLANNEDACITIVTYTIME, plannedActivity.getPlannedActivityTime());
        v.put(DatabaseHandler.COLUMN_PLANNEDACTIVITYLOCATIONID, plannedActivity.getPlannedActivityLocationID());
        v.put(DatabaseHandler.COLUMN_PLANNEDACTIVITYPARENTID, plannedActivity.getPlannedActivityParentID());

        long insertID = database.insert(DatabaseHandler.TABLE_PLANNEDACTIVITY, null, v);

    }




    public List<PlannedActivity> getMyPlannedActivity(){
        List<PlannedActivity> plan = new ArrayList<PlannedActivity>();

        Cursor cursor = database.query(DatabaseHandler.TABLE_PLANNEDACTIVITY, locationAllColumns, null, null, null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            PlannedActivity plannedActivity = cursorToMarker(cursor);
            plan.add(plannedActivity);
            cursor.moveToNext();
        }
        cursor.close();
        return plan;
    }

    private PlannedActivity cursorToMarker(Cursor cursor) {
        String plannedActivityTime = cursor.getString(0);
        int plannedActivityLocationID = cursor.getInt(1);
        int plannedActivityParentID = cursor.getInt(2);


        PlannedActivity p = new PlannedActivity(plannedActivityTime, plannedActivityLocationID, plannedActivityParentID);

        return p;

    }


}
