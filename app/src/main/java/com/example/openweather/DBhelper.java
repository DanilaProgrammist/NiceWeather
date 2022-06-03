package com.example.openweather;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBhelper extends SQLiteOpenHelper {

    public   static final  int DATABASE_NUM = 1;
    public   static final String DB_NAME = "HISTORY";
    public   static final String TABLE_CONTACTS = "contacts";

    public   static final String KEY_ID = "_id";
    public   static final String CITY = "city";
    public   static final String TEMP = "temp";

    public DBhelper(@Nullable Context context, @Nullable String name, int version) {
        super(context, name, null, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ TABLE_CONTACTS + "(" + KEY_ID + " integer," + TEMP + "integer," + CITY + "text" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+TABLE_CONTACTS);
        onCreate(db);
    }
}
