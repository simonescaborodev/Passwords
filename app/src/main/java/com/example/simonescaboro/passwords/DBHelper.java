package com.example.simonescaboro.passwords;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.ParcelUuid;

public class DBHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME  = "PASSWORDS";

    public static final String _ID = "_id";
    public static final String PSSW_USERNAME = "username";
    public static final String PSSW_EMAIL = "email";
    public static final String PSSW_PASSWORD = "password";
    public static final String PSSW_SITE = "site";


    public static final String DB_NAME = "PSSW_MY.db";

    static final int DB_VERSION = 2;

    private static final String  CREATE_TABLE= "CREATE TABLE " + TABLE_NAME + "("
            + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PSSW_USERNAME + " TEXT NOT NULL, "
            + PSSW_EMAIL + " TEXT, "
            + PSSW_SITE + " TEXT, "
            + PSSW_PASSWORD + " TEXT);";

    public DBHelper(Context context) {
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + TABLE_NAME);
        onCreate(db);
    }

}