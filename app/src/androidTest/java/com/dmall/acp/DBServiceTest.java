package com.dmall.acp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.dmall.acp.constant.DBConstant;
import com.dmall.acp.db.DBService;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * 数据库服务测试类
 * Created by yelong on 2017/2/23.
 * mail:354734713@qq.com
 */
@RunWith(AndroidJUnit4.class)
public class DBServiceTest {

    @Test
    public void testInsert() throws Exception {
        Context context = InstrumentationRegistry.getTargetContext();
        DBService service = new DBService(context);
        ContentValues values = new ContentValues();
        values.put(DBConstant.FIELE_STUDENT_NAME, "叶龙");
        values.put(DBConstant.FIELE_STUDENT_AGE, 28);
        service.insert(values);
    }

    @Test
    public void testDelete() throws Exception {
        Context context = InstrumentationRegistry.getTargetContext();
        DBService service = new DBService(context);
        service.delete(DBConstant.FIELE_STUDENT_AGE + " = ?", new String[]{String.valueOf(30)});
    }

    @Test
    public void testUpdate() throws Exception {
        Context context = InstrumentationRegistry.getTargetContext();
        DBService service = new DBService(context);
        ContentValues values = new ContentValues();
        values.put(DBConstant.FIELE_STUDENT_NAME, "张三");
        service.update(values, DBConstant.FIELD_STUDENT_ID + " = ?",
                new String[]{String.valueOf(1)});
    }

    @Test
    public void testQuery() throws Exception {
        Context context = InstrumentationRegistry.getTargetContext();
        DBService service = new DBService(context);
        Cursor cursor = service.query(null, null, null, DBConstant.FIELE_STUDENT_AGE + " asc");
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(DBConstant.FIELE_STUDENT_NAME));
            int age = cursor.getInt(cursor.getColumnIndex(DBConstant.FIELE_STUDENT_AGE));
            System.out.println("student--> " + name + " : " + age);
        }
        cursor.close();
    }
}
