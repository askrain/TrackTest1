package com.example.dell.tracktest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dell on 2017/6/4.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static String DB_NAME = "track.db";
    // 表名
    public static String TABLE_TRACK = "track";
    public static String TABLE_TRACK_DETAIL = "track_detail";

    // 字段
    public static String ID = "_id";
    // 跟踪表
    public static String TRACK_NAME = "track_name";
    public static String CREATE_DATE = "create_date";
    public static String START_LOC = "start_loc";
    public static String END_LOC = "end_loc";

    // 明细表
    public static String TID = "tid";// 线路的ID
    public static String LAT = "lat";// 纬度
    public static String LNG = "lng";// 经度

    private static String CREATE_TABLE_TRACK = "create table track(_id integer primary key autoincrement,track_name text,create_date text,start_loc text,end_loc text)";
    private static String CREATE_TABLE_TRACK_DETAIL = "create table track_detail(_id integer primary key autoincrement,tid integer not null,lat real,lng real)";

    private static int VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_TRACK);
        db.execSQL(CREATE_TABLE_TRACK_DETAIL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            db.execSQL("drop table if exists track");
            db.execSQL("drop table if exists track_detail");
            db.execSQL(CREATE_TABLE_TRACK);
            db.execSQL(CREATE_TABLE_TRACK_DETAIL);
        }
    }
}
