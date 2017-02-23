package com.dmall.acp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dmall.acp.constant.DBConstant;

/**
 * 访问数据库服务类
 * Created by yelong on 2017/2/23.
 * mail:354734713@qq.com
 */
public class DBService {
    private DBOpenHelper mOpenHelper;

    public DBService(Context context) {
        mOpenHelper = new DBOpenHelper(context);
    }

    public long insert(ContentValues values) {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        // ContentValues values = new ContentValues();
        // values.put("name", name);
        // values.put("age", age);
        long id = db.insert(DBConstant.TABLE_STUDENT_NAME, DBConstant.FIELE_STUDENT_NAME, values);
        // db.execSQL("insert into student(name,age) values (?,?)",new
        // Object[]{name ,age});
        db.close();
        return id;
    }

    public void delete(String whereClause, String[] whereArgs) {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        db.delete(DBConstant.TABLE_STUDENT_NAME, whereClause, whereArgs);
        db.close();
    }

    public Cursor query(String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        return db.query(DBConstant.TABLE_STUDENT_NAME, projection, selection,
                selectionArgs, null, null, sortOrder);
    }

    public void update(ContentValues values, String whereClause, String[] whereArgs) {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        db.update(DBConstant.TABLE_STUDENT_NAME, values, whereClause, whereArgs);
        db.close();
    }
}
