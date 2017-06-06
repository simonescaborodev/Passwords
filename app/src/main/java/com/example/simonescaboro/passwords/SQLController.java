package com.example.simonescaboro.passwords;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


public class SQLController {

    private DBHelper dbHelper;
    private Context ourcontext;
    private SQLiteDatabase database;

    public SQLController(Context c) {
        ourcontext = c;
    }

    public SQLController open() throws SQLException {
        dbHelper = new DBHelper(ourcontext);
        database = dbHelper.getWritableDatabase();
        return this;

    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String username, String password, String email, String site) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DBHelper.PSSW_USERNAME, username);
        contentValue.put(DBHelper.PSSW_PASSWORD, password);
        contentValue.put(DBHelper.PSSW_EMAIL, email);
        contentValue.put(DBHelper.PSSW_SITE, site);
        database.insert(DBHelper.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[] { DBHelper._ID, DBHelper.PSSW_USERNAME,
                DBHelper.PSSW_EMAIL, DBHelper.PSSW_PASSWORD, dbHelper.PSSW_SITE};
        Cursor cursor = database.query(DBHelper.TABLE_NAME, columns, null,
                null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, String username, String password, String email) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.PSSW_USERNAME, username);
        contentValues.put(DBHelper.PSSW_PASSWORD, password);
        contentValues.put(DBHelper.PSSW_EMAIL, email);
        int i = database.update(DBHelper.TABLE_NAME, contentValues,
                DBHelper._ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(DBHelper.TABLE_NAME, DBHelper._ID + "=" + _id, null);
    }
}
