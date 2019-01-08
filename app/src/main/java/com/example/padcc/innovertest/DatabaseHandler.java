package com.example.padcc.innovertest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by padcc on 29/09/2018.
 */

public class DatabaseHandler extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "RegistrationDB";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String TABLE_VISITORS = "Registered";

    // User Table Columns names

    private static final String KEY_VISITOR_FIRSTNAME = "visitor_firstname";
    private static final String KEY_VISITOR_LASTNAME = "visitor_lastname";
    private static final String KEY_EMAIL = "email";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_VISITOR_TABLE = "CREATE TABLE " + TABLE_VISITORS + "("

                + KEY_VISITOR_FIRSTNAME + " TEXT,"
                + KEY_VISITOR_LASTNAME + " TEXT,"
                + KEY_EMAIL + " TEXT" +")";
        sqLiteDatabase.execSQL(CREATE_VISITOR_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public Long insertVisitor (people visitor) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

      //  contentValues.put(KEY_VISITOR_ID,visitor.getVisitorId());
        contentValues.put(KEY_VISITOR_FIRSTNAME, visitor.getFname());
        contentValues.put(KEY_VISITOR_LASTNAME,visitor.getLname());
        contentValues.put(KEY_EMAIL,visitor.getEmail());


        Long result= db.insert(TABLE_VISITORS, null, contentValues);
        return result;
    }






    public ArrayList<people> getAllVisitors() {
        ArrayList<people> visitors = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_VISITORS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                people visitor = new people();

                visitor.setFname(cursor.getString(cursor.getColumnIndex(KEY_VISITOR_FIRSTNAME)));
                visitor.setLname(cursor.getString(cursor.getColumnIndex(KEY_VISITOR_LASTNAME)));
                visitor.setEmail(cursor.getString(cursor.getColumnIndex(KEY_EMAIL)));

                visitors.add(visitor);
            } while (cursor.moveToNext());
        }
        // close db connection
        db.close();
        // return notes list
        return visitors;
    }
}
