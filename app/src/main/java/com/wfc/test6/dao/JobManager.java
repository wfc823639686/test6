package com.wfc.test6.dao;

import com.wfc.test6.bean.Enterprise;
import com.wfc.test6.bean.Job;

import de.greenrobot.dao.AbstractDao;

/**
 * Created by wangfengchen on 2016/12/30.
 */

public class JobManager extends AbstractDatabaseManager<Job, Long> {

    @Override
    public AbstractDao<Job, Long> getAbstractDao() {
        return daoSession.getJobDao();
    }
}
