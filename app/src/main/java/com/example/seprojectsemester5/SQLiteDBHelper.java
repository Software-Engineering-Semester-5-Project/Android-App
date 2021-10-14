package com.example.seprojectsemester5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteDBHelper extends SQLiteOpenHelper {
    public SQLiteDBHelper(Context context) {
        super(context, "Survey.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Data(aadharNumber TEXT primary key, name TEXT, age TEXT, pin TEXT, phone TEXT, gender TEXT, survey TEXT, disease TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Data");
    }

    public Boolean insertData(String aadharNumber, String name, String age, String pin, String phone, String gender, String survey, String disease){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("aadharNumber", aadharNumber);
        contentValues.put("name", name);
        contentValues.put("age", age);
        contentValues.put("pin", pin);
        contentValues.put("phone", phone);
        contentValues.put("gender", gender);
        contentValues.put("survey", survey);
        contentValues.put("disease", disease);
        long result = DB.insert("Data", null, contentValues);
        return result == -1 ? false : true;
    }

    public Cursor getData(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Data", null);
        return cursor;
    }
}
