package com.wfc.test6.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Map;
import java.util.WeakHashMap;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * Created by wangfengchen on 2016/12/30.
 */

public class DbManager {

    private static Map<Class<? extends AbstractDatabaseManager>, AbstractDatabaseManager>
            dbManagers = new WeakHashMap<>();

    public static <T extends AbstractDatabaseManager> T get(Class<T> clazz) {
        T tem = (T) dbManagers.get(clazz);
        if (tem==null) {
            try {
                tem = clazz.newInstance();
                dbManagers.put(clazz, tem);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return tem;
    }
}
