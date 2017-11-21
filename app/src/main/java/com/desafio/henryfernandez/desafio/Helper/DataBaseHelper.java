package com.desafio.henryfernandez.desafio.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.desafio.henryfernandez.desafio.Models.Person;

import java.net.PortUnreachableException;

/**
 * Created by user on 20/11/17.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "Challeng.db";

    //Person
    private String TABLE_PERSON = "PERSON_TABLE";
    private static String COL_2 = "NAME";
    private static String COL_3 = "PHONE";
    private static String COL_4 = "EMAIL";
    //

    //Organization
    private String TABLE_ORGANIZATION = "ORGANIZATION_TABLE";
    private static String COL_O_2 = "NAME";
    private static String COL_O_3 = "ADDRESS";
    private static String COL_O_4 = "PHONE";
    //

    public DataBaseHelper(Context context) {
        super(context,DATABASE_NAME,null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_PERSON + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, PHONE TEXT, EMAIL TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_ORGANIZATION + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, ADDRESS TEXT, PHONE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_PERSON);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_ORGANIZATION);
    }

    public boolean insertData(String name, String phone, String email){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,phone);
        contentValues.put(COL_4,email);
        long result = this.getWritableDatabase().insertOrThrow("PERSON_TABLE","",contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public Cursor getAllDataPersons(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor reuslt = db.rawQuery("Select * from " + TABLE_PERSON, null);
        return reuslt;
    }

    public boolean insertDataOrganization(String name, String address, String phone){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_O_2,name);
        contentValues.put(COL_O_3,address);
        contentValues.put(COL_O_4,phone);
        long result = this.getWritableDatabase().insertOrThrow(TABLE_ORGANIZATION,"",contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public Cursor getAllDataOrganizations(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor reuslt = db.rawQuery("Select * from " + TABLE_ORGANIZATION, null);
        return reuslt;
    }
}
