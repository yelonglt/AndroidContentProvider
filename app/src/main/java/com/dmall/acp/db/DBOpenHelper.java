package com.dmall.acp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.dmall.acp.constant.DBConstant;

/**
 * 数据库帮助类
 * Created by yelong on 2017/2/23.
 * mail:354734713@qq.com
 */
public class DBOpenHelper extends SQLiteOpenHelper {

    public DBOpenHelper(Context context) {
        super(context, DBConstant.DATABASE_NAME, null, DBConstant.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists " +
                DBConstant.TABLE_STUDENT_NAME + "(" +
                DBConstant.FIELD_STUDENT_ID + " integer primary key autoincrement," +
                DBConstant.FIELE_STUDENT_NAME + " varchar(20) not null," +
                DBConstant.FIELE_STUDENT_AGE + " integer not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
