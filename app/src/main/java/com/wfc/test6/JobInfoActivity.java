package com.wfc.test6;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState;
import com.wfc.test6.base.BaseLceActivity;
import com.wfc.test6.bean.CommentsResult;
import com.wfc.test6.bean.JobInfoResult;
import com.wfc.test6.presenter.JobInfoPresenter;
import com.wfc.test6.view.JobInfoView;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by wangfengchen on 2017/1/3.
 */

public class JobInfoActivity
        extends BaseLceActivity<View, JobInfoResult, JobInfoView, JobInfoPresenter>
        implements JobInfoView {

    private static final String TAG = "JobInfoActivity";
    private String id;
    private TextView nameTv;

    private Map<String, String> commentsParams = new LinkedHashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_info);
        id = "3";
        nameTv = (TextView) findViewById(R.id.job_info_name);
        loadData(false);
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return null;
    }

    @NonNull
    @Override
    public JobInfoPresenter createPresenter() {
        return new JobInfoPresenter();
    }

    @Override
    public void setData(JobInfoResult data) {
        Log.d(TAG, "data " + data.detail.jobName);
        nameTv.setText(data.detail.jobName);
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        getPresenter().getData();
        getPresenter().getComments();
    }

    @Override
    public Map<String, String> params() {
        params.put("id", id);
        return params;
    }

    @Override
    public Map<String, String> commentsParams() {
        commentsParams.put("id", "5");
        return commentsParams;
    }

    @Override
    public void setCommentsData(CommentsResult result) {
        Log.d(TAG, "commentsData " + result.results.get(0).content);
        Log.d(TAG, "t " + Thread.currentThread().getName());
    }
}
