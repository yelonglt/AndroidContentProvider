package com.dmall.acp;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.dmall.acp.constant.DBConstant;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * 学生数据提供者测试类
 * Created by yelong on 2017/2/23.
 * mail:354734713@qq.com
 */
@RunWith(AndroidJUnit4.class)
public class StudentProviderTest {

    @Test
    public void testProviderInsert() throws Exception {
        Context context = InstrumentationRegistry.getTargetContext();
        Uri url = Uri.parse("content://com.dmall.acp.provider.studentprovider/student");
        ContentResolver resolver = context.getContentResolver();

        ContentValues values = new ContentValues();
        values.put(DBConstant.FIELE_STUDENT_NAME, "张涛");
        values.put(DBConstant.FIELE_STUDENT_AGE, 28);
        resolver.insert(url, values);

        values = new ContentValues();
        values.put(DBConstant.FIELE_STUDENT_NAME, "李发");
        values.put(DBConstant.FIELE_STUDENT_AGE, 26);
        resolver.insert(url, values);

        values = new ContentValues();
        values.put(DBConstant.FIELE_STUDENT_NAME, "叶龙");
        values.put(DBConstant.FIELE_STUDENT_AGE, 26);
        resolver.insert(url, values);

    }

    @Test
    public void testProviderDelete() throws Exception {
        Context context = InstrumentationRegistry.getTargetContext();
        Uri url = Uri.parse("content://com.dmall.acp.provider.studentprovider/student");
        ContentResolver resolver = context.getContentResolver();
        resolver.delete(url, DBConstant.FIELE_STUDENT_AGE + " = ?", new String[]{String.valueOf(26)});
    }

    @Test
    public void testProviderUpdate() throws Exception {
        Context context = InstrumentationRegistry.getTargetContext();
        Uri url = Uri.parse("content://com.dmall.acp.provider.studentprovider/student");
        ContentResolver resolver = context.getContentResolver();

        ContentValues values = new ContentValues();
        values.put(DBConstant.FIELE_STUDENT_NAME, "大哥");
        resolver.update(url, values, DBConstant.FIELD_STUDENT_ID + " = ?",
                new String[]{String.valueOf(1)});
    }

    @Test
    public void testProviderQuery() throws Exception {
        Context context = InstrumentationRegistry.getTargetContext();
        Uri url = Uri.parse("content://com.dmall.acp.provider.studentprovider/student");
        ContentResolver resolver = context.getContentResolver();

        Cursor cursor = resolver.query(url,
                new String[]{DBConstant.FIELD_STUDENT_ID,
                        DBConstant.FIELE_STUDENT_NAME,
                        DBConstant.FIELE_STUDENT_AGE},
                DBConstant.FIELD_STUDENT_ID + " = ?",
                new String[]{"1", "2", "3"}, DBConstant.FIELE_STUDENT_AGE + " asc");
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex(DBConstant.FIELE_STUDENT_NAME));
                int age = cursor.getInt(cursor.getColumnIndex(DBConstant.FIELE_STUDENT_AGE));
                System.out.println("student--> " + name + " : " + age);
            }
            cursor.close();
        }
    }
}
