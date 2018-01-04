package com.example.amrez.habittracker.SqlData;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by amrez on 11/6/2017.
 */

public class DbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "habittracker.db";
    private static final int DATABASE_VERSION = 1;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_HABITS_TABLE = "CREATE TABLE IF  NOT EXISTS " + Contract.DataEntry.TABLE_NAME + "("
                + Contract.DataEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Contract.DataEntry.COLUMN_NAME + " TEXT,"
                + Contract.DataEntry.AGE + " INTEGER,"
                + Contract.DataEntry.BIO + " TEXT);";
        db.execSQL(SQL_CREATE_HABITS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
