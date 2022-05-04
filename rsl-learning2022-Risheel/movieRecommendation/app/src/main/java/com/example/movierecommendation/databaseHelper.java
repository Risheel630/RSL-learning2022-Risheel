package com.example.movierecommendation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class databaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Userdata.db";
    private static final String TABLE_USER = "users";

    public databaseHelper( Context context) {
        super(context,DATABASE_NAME, null,DATABASE_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase myDb) {

        myDb.execSQL("create Table users(name TEXT, email TEXT primary key, password TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase myDb, int i, int i1) {
        myDb.execSQL("drop Table if exists users");
    }



   public Boolean addUser(UserModel userModel){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", userModel.getUserName());
        values.put("email", userModel.getUserEmail());
        values.put("password" , userModel.getUserPassword());
        long result  = db.insert(TABLE_USER,  null, values);
        if(result == -1)
            return false;
        return  true;
    }
    public Boolean checkUser(UserModel user)
    {
        SQLiteDatabase myDb = this.getWritableDatabase();
        Cursor cursor=myDb.rawQuery("Select * from users where email = ?",new String[]{user.getUserEmail()});
        return cursor.getCount() > 0;
    }
    public Boolean checkUserAndPassword(UserModel user)
    {
        SQLiteDatabase myDb = this.getWritableDatabase();
        Cursor cursor=myDb.rawQuery("Select * from users where email = ? and password= ?",new String[]{user.getUserEmail(),user.getUserPassword()});
        return cursor.getCount() > 0;
    }

//    UserModel getUser() {
//        SQLiteDatabase db = this.getWritableDatabase();
//        String name = null, email = null, pass = null;
//        Cursor cursor = db.rawQuery("Select * from Userdetails", null);
//
//        if (cursor.getCount() == 0) {
//            name = "";
//            email = "";
//            pass = "";
//        } else {
//
//            while (cursor.moveToNext()) {
//                name = cursor.getString(0);
//                email = cursor.getString(1);
//                pass = cursor.getString(2);
//            }
//
//
//        }
//
//        return new UserModel(name, email, pass);
//    }





}
