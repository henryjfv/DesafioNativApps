package com.desafio.henryfernandez.desafio.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
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

    //Deal
    private String TABLE_DEAL = "DEAL_TABLE";
    private static String COL_D_2 = "TITLE";
    private static String COL_D_3 = "DESCRIPTION";
    private static String COL_D_4 = "ORGANIZATIONS";
    private static String COL_D_5 = "PERSONS";
    private static String COL_D_6 = "VALUE";
    private static String COL_D_7 = "DATE";
    private static String COL_D_8 = "STATE";
    //

    //Deal
    private String TABLE_ACTIVITY = "ACTIVITY_TABLE";
    private static String COL_A_2 = "DESCRIPTION";
    private static String COL_A_3 = "TYPE";
    private static String COL_A_4 = "ORGANIZATION";
    private static String COL_A_5 = "PERSON";
    private static String COL_A_6 = "DEAL";
    private static String COL_A_7 = "DATE";
    private static String COL_A_8 = "HOUR";
    //

    public DataBaseHelper(Context context) {
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_PERSON + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, PHONE TEXT, EMAIL TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_ORGANIZATION + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, ADDRESS TEXT, PHONE TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_DEAL +
                " (ID INTEGER PRIMARY KEY AUTOINCREMENT, TITLE TEXT, DESCRIPTION TEXT, ORGANIZATIONS TEXT," +
                " PERSONS TEXT, VALUE TEXT, DATE TEXT, STATE TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_ACTIVITY +
                " (ID INTEGER PRIMARY KEY AUTOINCREMENT, DESCRIPTION TEXT, TYPE TEXT, ORGANIZATION TEXT," +
                " PERSON TEXT, DEAL TEXT, DATE TEXT, HOUR TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_PERSON);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_ORGANIZATION);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_DEAL);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_ACTIVITY);
    }

    public boolean insertData(String name, String phone, String email){
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_2,name);
            contentValues.put(COL_3,phone);
            contentValues.put(COL_4,email);
            long result = this.getWritableDatabase().insertOrThrow("PERSON_TABLE","",contentValues);
            if(result != -1){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Cursor getAllDataPersons(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor reuslt = db.rawQuery("Select * from " + TABLE_PERSON, null);
        return reuslt;
    }

    public boolean insertDataOrganization(String name, String address, String phone){
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_O_2,name);
            contentValues.put(COL_O_3,address);
            contentValues.put(COL_O_4,phone);
            long result = this.getWritableDatabase().insertOrThrow(TABLE_ORGANIZATION,"",contentValues);
            if(result != -1){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Cursor getAllDataOrganizations(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor reuslt = db.rawQuery("Select * from " + TABLE_ORGANIZATION, null);
        return reuslt;
    }

    public boolean insertDataDeal(String title, String description, String organizations, String persons,
                                  String value, String date, String state){
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_D_2,title);
            contentValues.put(COL_D_3,description);
            contentValues.put(COL_D_4,organizations);
            contentValues.put(COL_D_5,persons);
            contentValues.put(COL_D_6,value);
            contentValues.put(COL_D_7,date);
            contentValues.put(COL_D_8,state);
            long result = this.getWritableDatabase().insertOrThrow(TABLE_DEAL,"",contentValues);
            if(result != -1){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Cursor getAllDataDeal(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor reuslt = db.rawQuery("Select * from " + TABLE_DEAL, null);
        return reuslt;
    }

    public boolean insertDataActivity(String descripcion, String type, String organization, String person,
                                  String deal, String date, String hour){
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_A_2,descripcion);
            contentValues.put(COL_A_3,type);
            contentValues.put(COL_A_4,organization);
            contentValues.put(COL_A_5,person);
            contentValues.put(COL_A_6,deal);
            contentValues.put(COL_A_7,date);
            contentValues.put(COL_A_8,hour);
            long result = this.getWritableDatabase().insertOrThrow(TABLE_ACTIVITY,"",contentValues);
            if(result != -1){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Cursor getAllActivities(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor reuslt = db.rawQuery("Select * from " + TABLE_ACTIVITY, null);
        return reuslt;
    }
}
