package com.wfc.test6;

import com.wfc.test4.bean.User;

import de.greenrobot.dao.AbstractDao;

/**
 * Created by wangfengchen on 2016/12/30.
 */

public class UserManager extends AbstractDatabaseManager<User, Long> {

    @Override
    public AbstractDao<User, Long> getAbstractDao() {
        return daoSession.getUserDao();
    }
}
