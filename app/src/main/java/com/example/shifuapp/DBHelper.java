package com.example.shifuapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Абай on 20.04.2019.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";
    private static final String DATABASE_NAME = "ShifuDB";

    private static final String TABLE_NAME = "Addresses";
    private static final String COL1 = "ID";
    private static final String COL2 = "name";
    private static final String COL3 = "address";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        String createTable = "CREATE TABLE " + TABLE_NAME + " (_id INTEGER PRIMARY KEY, " + COL2 +" TEXT, " + COL3 + " TEXT)";
//        String createTable2 = "CREATE TABLE Addresses (_id INTEGER PRIMARY KEY, name TEXT, address TEXT)";
    //    db.execSQL(createTable);
        db.execSQL("create table Addresses (_id integer primary key, name text, address text )");
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        onCreate(db);
    }


}
