package com.example.dell.tracktest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by dell on 2017/6/4.
 */

public class DatabaseAdapter {
    private DatabaseHelper dbHelper;

    public DatabaseAdapter(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    // 添加线路跟踪
    public int addTrack(Track track) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // String sql =
        // "insert into track(track_name,create_date,start_loc,end_loc) values(?,?,?,?)";
        // db.execSQL(sql, new
        // Object[]{track.getTrack_name(),track.getCreate_date(),track.getStart_loc(),track.getEnd_loc()});
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.TRACK_NAME, track.getTrack_name());
        values.put(DatabaseHelper.CREATE_DATE, track.getCreate_date());
        values.put(DatabaseHelper.START_LOC, track.getStart_loc());
        values.put(DatabaseHelper.END_LOC, track.getEnd_loc());
        long id = db.insertOrThrow(DatabaseHelper.TABLE_TRACK, null, values);
        db.close();
        return (int) id;
    }

    /**
     * 更新终点地址
     *
     * @param endLoc
     * @param id
     */
    public void updateEndLoc(String endLoc, int id) {
        String sql = "update track set end_loc=? where _id=?";
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL(sql, new Object[] { endLoc, id });
        db.close();
    }

    // 添线路跟踪明细
    public void addTrackDetail(int tid, double lat, double lng) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = "insert into track_detail(tid,lat,lng) values(?,?,?)";
        db.execSQL(sql, new Object[] { tid, lat, lng });
        db.close();
    }

    // 根据ID查询线路跟踪
    public ArrayList<TrackDetail> getTrackDetails(int id) {
        String sql = "select _id,lat,lng from track_detail where tid=? order by _id desc";
        ArrayList<TrackDetail> list = new ArrayList<TrackDetail>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery(sql, new String[] { String.valueOf(id) });
        if (c != null) {
            TrackDetail detail = null;
            while (c.moveToNext()) {
                detail = new TrackDetail(c.getInt(0), c.getDouble(1),
                        c.getDouble(2));
                list.add(detail);
            }
            c.close();
        }
        return list;
    }

    /**
     * 查询所有路线
     *
     * @return
     */
    public ArrayList<Track> getTracks() {
        ArrayList<Track> tracks = new ArrayList<Track>();
        String sql = "select _id,track_name,create_date,start_loc,end_loc from track ";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);
        Track t = null;
        if (c != null) {
            while (c.moveToNext()) {
                t = new Track(c.getInt(0), c.getString(1), c.getString(2),
                        c.getString(3), c.getString(4));
                tracks.add(t);
            }
            c.close();
        }
        db.close();
        return tracks;
    }

    // 根据ID删除线路跟踪
    public void delTrack(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql1 = "delete from track where _id=?";
        String sql2 = "delete from track_detail where tid=?";
        try {
            db.beginTransaction();
            db.execSQL(sql2, new Object[] { id });
            db.execSQL(sql1, new Object[] { id });
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
            if (db != null)
                db.close();
        }
    }

}
