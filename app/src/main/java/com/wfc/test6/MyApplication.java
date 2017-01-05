package com.wfc.test6;

import android.app.Application;

import com.wfc.test6.dao.AbstractDatabaseManager;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * Created by wangfengchen on 2016/12/30.
 */

public class MyApplication extends Application {



    @Override
    public void onCreate() {
        super.onCreate();
//        DbManager.getInstance().setupDatabase(this);
        AbstractDatabaseManager.initOpenHelper(this);//初始化数据库
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;
    }



}
