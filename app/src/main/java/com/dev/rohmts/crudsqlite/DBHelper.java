package com.dev.rohmts.crudsqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DBHelper extends SQLiteOpenHelper {

    public static final String tableName = "data_employee";
    public static final String columnId = "_id";
    public static final String columnFirstName = "firstName";
    public static final String columnLastname = "lastName";
    public static final String columnSalary = "salary";
    private static final String dbName = "employee.db";
    private static final int dbVersion = 1;

    private static final String dbCreate = "CREATE TABLE "
            +tableName+" ("
            +columnId+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +columnFirstName+" TEXT, "
            +columnLastname+" TEXT, "
            +columnSalary+" NUMERIC);";

    public DBHelper(Context context) {
        super(context, dbName, null, dbVersion);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(dbCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        /*Log.w(DBHelper.class.getName(), "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");*/
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+tableName);
        onCreate(sqLiteDatabase);
    }
}
