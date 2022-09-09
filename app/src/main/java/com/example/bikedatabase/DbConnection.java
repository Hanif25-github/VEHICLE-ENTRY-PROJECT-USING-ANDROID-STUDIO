package com.example.bikedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DbConnection extends SQLiteOpenHelper {

    public DbConnection(Context context)
    {
        super(context, "BikeDBase", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase dbase) {
        dbase.execSQL("create Table BikTable(bikeName TEXT primary key, date TEXT, time TEXT)");

    }
    @Override
    public void onUpgrade(SQLiteDatabase dbase, int i, int i1) {

    }
    public boolean insertValues(String bikName, String bikDate, String bikTime)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        //Sort of Opens the database for read/write
        ContentValues contentValues = new ContentValues();
        //Stores the set of values ie,name, date & time
        contentValues.put("bikeName",bikName);
        //Uses KEY-Value Pair to store those values
        contentValues.put("date",bikDate);
        contentValues.put("time",bikTime);
        long result =
                database.insert("BikTable",null,contentValues);
        if(result==-1)    //meaning that the values where not inserted..
            return false;

        else
            return true;
    }

public Cursor RetrieveData(String date, String time){

        SQLiteDatabase database =
        this.getReadableDatabase();
        Cursor cursor = database.rawQuery("Select * from BikTable where date= '"+date+"' AND time='"+time+"'", null);
        return cursor;
        }
}

