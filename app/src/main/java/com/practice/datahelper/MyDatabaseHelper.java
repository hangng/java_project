package com.example.n3333.genting_tech_tests.datahelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "dino.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "employee_location";

    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_USERNAME = "Username";
    private static final String COLUMN_PASSWORD = "Password";
    private static final String COLUMN_LOCATION = "Location";
    private static final String COLUMN_CHECK_IN_DATE = "Check In";
    private static final String COLUMN_CHECK_OUT_DATE = "Check Out";
    private static final String COLUMN_DESCRIPTION = "Description";

    private Context context;

    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // create your database tables here

        String query = " CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERNAME + " TEXT, " +
                COLUMN_LOCATION + " TEXT); ";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // upgrade your database here

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
        Log.i("TAG", "checking MyDatabaseHelper onUpgrade");
    }

    public void addUser(String username, String location) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_USERNAME, username);
        contentValues.put(COLUMN_LOCATION, location);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            Log.i("TAG", "checking insert failed");
        } else {
            Log.i("TAG", "checking insert successfully");
            retrieveDB();
        }


    }

    private void retrieveDB() {
        // Open the database
        SQLiteDatabase db = context.openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);

// Perform a query and get a cursor to the result set
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);

// Iterate over the rows in the result set and log each row
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME));
            String location = cursor.getString(cursor.getColumnIndex(COLUMN_LOCATION));
            Log.d("TAG", "Row: id=" + id + ", name=" + name + ", location=" + location);
        }

// Close the cursor and the database
        cursor.close();
        db.close();


    }

}
