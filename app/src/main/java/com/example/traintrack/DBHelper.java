package com.example.traintrack;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.core.app.NotificationCompat;

import java.sql.Date;
import java.sql.Timestamp;

public class DBHelper extends SQLiteOpenHelper {


    public static final String DBNAME = "traintrackUserData.db";

    public DBHelper(Context context) {
        super(context, "traintrackUserData.db", null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase myDB) {
        // The following query will create a table 'users'
        myDB.execSQL("create Table users(username TEXT, password TEXT, name TEXT, weight INT, age INT, goal INT, height INT, weightDate TEXT, primary key(username, weightDate))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase myDB, int oldVersion, int newVersion) {
        myDB.execSQL("drop Table if exists users");

    }

    // This method will insert the username and password but make empty field for the user attributes
    // This is so that the null fields can be filled in later, but for now, are kept empty.
    public Boolean insertData(String username, String password){

        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);


        long result = myDB.insert("users", null, contentValues);

        return result != -1;
    }

    public Boolean insertUserData(String USERNAME, int WEIGHT, int AGE , int GOAL , int HEIGHT){

        SQLiteDatabase myDB = this.getWritableDatabase();
        Date current = new Date(System.currentTimeMillis());
        try {
            Timestamp stamp = new Timestamp(System.currentTimeMillis());
            System.out.println(stamp);

            // creates a string SQL injection that can later be executed
            @SuppressLint("DefaultLocale")
            String command = String.format("UPDATE users\n" +
                    "SET name = '%s', weight = '%d', age = '%d', goal='%d', height='%d', weightDate='%s'\n" +
                    "WHERE username = '%s'", USERNAME, WEIGHT, AGE, GOAL, HEIGHT, USERNAME, stamp);

            myDB.execSQL(command);

        } catch (SQLException ignored){
            return false;
        }

        return true;

    }

    // this method will check if the user exists in the table.
    public Boolean checkUsername(String username) {

        SQLiteDatabase myDB = getWritableDatabase();
        Cursor cursor = myDB.rawQuery("Select * from users where username = ?", new String[] {username});
        //if the user exists
        return cursor.getCount() < 0;
    }

    public Boolean checkLogin(String username, String password){
    SQLiteDatabase myDB = this.getWritableDatabase();
    Cursor cursor = myDB.rawQuery("Select * from users where username = ? and password = ?", new String[]{username, password});
        return cursor.getCount() > 0;

    }

}
