package com.dmall.acp.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.dmall.acp.constant.DBConstant;
import com.dmall.acp.db.DBService;

/**
 * 学生数据提供者
 * 访问ContentProvider的URL格式是 content://authority/path
 * authority是用来标识ContentProvider,一般以项目包名加文件名命名
 * path其实就是表名
 * Created by yelong on 2017/2/23.
 * mail:354734713@qq.com
 */
public class StudentProvider extends ContentProvider {

    private static final String AUTHORITY = "com.dmall.acp.provider.studentprovider";

    public static final String CONTENT_ITEM = "vnd.android.cursor.item/dmall.student";
    public static final String CONTENT_ITEMS = "vnd.android.cursor.dir/dmall.students";

    private static final int SINGLE_CODE = 1001;
    private static final int MULTI_CODE = 1002;

    private DBService mService;
    private static UriMatcher sMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sMatcher.addURI(AUTHORITY, "student", SINGLE_CODE);
        sMatcher.addURI(AUTHORITY, "student/#", MULTI_CODE);
    }

    @Override
    public boolean onCreate() {
        mService = new DBService(getContext());
        return false;
    }

    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        if (sMatcher.match(uri) == SINGLE_CODE) {
            long id = mService.insert(values);
            return ContentUris.withAppendedId(uri, id);
        } else if (sMatcher.match(uri) == MULTI_CODE) {
            throw new IllegalArgumentException();
        }
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        if (sMatcher.match(uri) == SINGLE_CODE) {
            mService.delete(selection, selectionArgs);
            return 1;
        } else if (sMatcher.match(uri) == MULTI_CODE) {
            // 通过Uri获取Id,根据主键进行删除
            String id = uri.getPathSegments().get(1);
            mService.delete(DBConstant.FIELD_STUDENT_ID + "=" + id, selectionArgs);
            return 1;
        }
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        if (sMatcher.match(uri) == SINGLE_CODE) {
            mService.update(values, selection, selectionArgs);
            return 1;
        } else if (sMatcher.match(uri) == MULTI_CODE) {
            // 通过Uri获取Id,根据主键进行删除
            String id = uri.getPathSegments().get(1);
            mService.update(values, DBConstant.FIELD_STUDENT_ID + "=" + id
                    + (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
            return 1;
        }
        return 0;
    }

    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        if (sMatcher.match(uri) == SINGLE_CODE) {
            return mService.query(projection, selection, selectionArgs, sortOrder);
        } else if (sMatcher.match(uri) == MULTI_CODE) {
            // 通过Uri获取Id,根据主键进行删除
            String id = uri.getPathSegments().get(1);
            return mService.query(projection, DBConstant.FIELD_STUDENT_ID + "=" + id
                    + (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs, sortOrder);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String getType(@NonNull Uri uri) {
        switch (sMatcher.match(uri)) {
            case SINGLE_CODE:
                return CONTENT_ITEM;
            case MULTI_CODE:
                return CONTENT_ITEMS;
            default:
                throw new IllegalArgumentException();
        }
    }


}
