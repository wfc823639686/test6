package com.wfc.test6.dao;

import com.wfc.test6.bean.Enterprise;
import com.wfc.test6.dao.AbstractDatabaseManager;

import de.greenrobot.dao.AbstractDao;

/**
 * Created by wangfengchen on 2016/12/30.
 */

public class EnterpriseManager extends AbstractDatabaseManager<Enterprise, Long> {

    @Override
    public AbstractDao<Enterprise, Long> getAbstractDao() {
        return daoSession.getEnterpriseDao();
    }
}
