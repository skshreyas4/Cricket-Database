package com.example.shreyassk.sqlitedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseClass  extends SQLiteOpenHelper {
    private String TAG = "DATABASE";
    //database version
    private static final int DATABASE_VERSION = 1;
    //DATABASE NAME
    private static final String DATABASE_NAME = "infoData";
    //table name
    private static final String TABLE_NAME = "publicinfo";
    //colomn name
    private static final String NAME = "name";
    private static final String PH_NUMBER = "ph_number";
    private static final String KEY_ID = "id";
    //string query
    private static final String CREATE_QUERY = "CREATE TABLE " + TABLE_NAME + "("
            + KEY_ID + " INTEGER PRIMARY KEY," + NAME + " TEXT,"
            + PH_NUMBER + " TEXT" + ")";
    private static final String DROP_QUERY = "DROP TABLE IF EXISTS " + TABLE_NAME;
    private static final String SELECT_QUERY = "SELECT * FROM " + TABLE_NAME;


    ModuleClass moduleClass = new ModuleClass();

    public DatabaseClass(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);//factory(i.e  own cursor is null

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_QUERY);
        onCreate(db);
    }

    public void addDetails(String Name, int phnum) {
        SQLiteDatabase database = this.getWritableDatabase();
        // need content values for putting data
        ContentValues values = new ContentValues();
        values.put(NAME, Name);
        values.put(PH_NUMBER, phnum);
        //insert a single row
        database.insert(TABLE_NAME, null, values);
        Log.i(TAG, "INSERTED");
    }

    public ArrayList<ModuleClass> retriveData() {
        SQLiteDatabase database = this.getReadableDatabase();
        ArrayList<ModuleClass> arrayList = new ArrayList<>();
        Cursor cursor = database.rawQuery(SELECT_QUERY, null);
        Log.i(TAG, cursor.getCount() + "");
        if (cursor.moveToFirst()) {
            //adding a contact to list
            for (int i = 0; i < cursor.getCount(); i++) {
                ModuleClass moduleClass = new ModuleClass(); //import line of code
                moduleClass.Id = Integer.parseInt(cursor.getString(0));
                moduleClass.Name = cursor.getString(1);
                moduleClass.Phnum = cursor.getString(2);
                arrayList.add(moduleClass);
                cursor.moveToNext();

            }
        }
        cursor.close();
        database.close();
        return arrayList;
    }
}
