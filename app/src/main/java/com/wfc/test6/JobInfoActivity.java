package com.wfc.test6;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState;
import com.wfc.test6.api.JobApi;
import com.wfc.test6.base.BaseLceActivity;
import com.wfc.test6.bean.CommentsResult;
import com.wfc.test6.bean.Job;
import com.wfc.test6.bean.JobInfoResult;
import com.wfc.test6.bean.Result;
import com.wfc.test6.dao.DbManager;
import com.wfc.test6.dao.JobManager;
import com.wfc.test6.presenter.JobInfoPresenter;
import com.wfc.test6.view.JobInfoView;

import java.util.LinkedHashMap;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wangfengchen on 2017/1/3.
 */

public class JobInfoActivity
        extends BaseLceActivity<SwipeRefreshLayout, JobInfoResult, JobInfoView, JobInfoPresenter>
        implements JobInfoView, View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "JobInfoActivity";
    private String id;
    private TextView nameTv;
    private Button btn;
    Subscription postSubs;

    private Map<String, String> commentsParams = new LinkedHashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_info);
        id = "3";
        nameTv = (TextView) findViewById(R.id.job_info_name);
        btn = (Button) findViewById(R.id.job_info_post_btn);
        contentView.setOnRefreshListener(this);
        btn.setOnClickListener(this);
        loadData(true);

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
        contentView.setRefreshing(false);
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        getPresenter().getData(pullToRefresh);
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

    @Override
    public void onClick(View v) {
        Log.e(TAG, "post");
        Map<String, String> map = new LinkedHashMap<>();
        map.put("id", "3");
        map.put("jobName", "23424");
        Observable<Result> observable = JobApi.postJob(map);
        postSubs = observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Result>() {
            @Override
            public void onCompleted() {
                postSubs.unsubscribe();
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "post error " + e);
                postSubs.unsubscribe();
            }

            @Override
            public void onNext(Result result) {
                Log.e(TAG, "post result " + result.msg);
            }
        });
    }

    @Override
    public void onRefresh() {
        loadData(true);
    }
}
