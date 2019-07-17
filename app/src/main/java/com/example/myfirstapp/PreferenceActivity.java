package com.example.myfirstapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PreferenceActivity extends SQLiteOpenHelper {

    private static final String TAG = "PreferenceHelper";

    private static final String TABLE_NAME = "pref_table";
    private static final String COL1 = "ID";
    private static final String COL2 = "friends";

    public PreferenceActivity(Context context) {
        super(context, TABLE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY, " +
                COL2 +" TEXT)";
        db.execSQL(createTable);
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, 1);
        contentValues.put(COL2, "true");

        Log.d(TAG, "addData: Adding " + COL2 + " to " + TABLE_NAME);
        //if date as inserted incorrectly it will return -1
        try {
            long result = db.insert(TABLE_NAME, null, contentValues);
        }
        catch (Exception e){
            System.out.println(e.getStackTrace());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    /**
     * Returns all the data from database
     * @return
     */
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " +COL2+ " FROM " + TABLE_NAME +
                " WHERE " + COL1 + " = '" + 1 + "'" ;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    /**
     * Returns only the ID that matches the name passed in
     * @param name
     * @return
     */
    public Cursor getItemID(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL1 + " FROM " + TABLE_NAME +
                " WHERE " + COL2 + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    /**
     * Updates the name field
     * @param preference
     */
    public void updatePref(String preference){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL2 +
                " = '" + preference + "' WHERE " + COL1 + " = '" + 1 + "'" ;
        Log.d(TAG, "updateName: query: " + query);
        Log.d(TAG, "updateName: Setting preference Friends to " + preference);
        db.execSQL(query);
    }

    /**
     * Delete from database
     * @param id
     * @param name
     */
    public void deleteName(int id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL1 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + name + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + name + " from database.");
        db.execSQL(query);
    }

}