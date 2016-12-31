package com.wfc.test6.presenter;

import com.hannesdorfmann.mosby.mvp.rx.lce.MvpLceRxPresenter;
import com.wfc.test6.api.JobApi;
import com.wfc.test6.bean.JobListResult;
import com.wfc.test6.view.JobListView;

/**
 * Created by wangfengchen on 2016/12/31.
 */

public class JobListPresenter extends MvpLceRxPresenter<JobListView, JobListResult> {

    public void getData() {
        subscribe(JobApi.getJobList(getView().params()), true);
    }
}
