package com.wfc.test6.presenter;

import android.util.Log;

import com.hannesdorfmann.mosby.mvp.rx.lce.MvpLceRxPresenter;
import com.wfc.test6.bean.Job;
import com.wfc.test6.dao.DbManager;
import com.wfc.test6.dao.EnterpriseManager;
import com.wfc.test6.api.JobApi;
import com.wfc.test6.bean.CommentsResult;
import com.wfc.test6.bean.JobInfoResult;
import com.wfc.test6.dao.JobManager;
import com.wfc.test6.view.JobInfoView;

import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by wangfengchen on 2016/12/31.
 */

public class JobInfoPresenter extends MvpLceRxPresenter<JobInfoView, JobInfoResult> {

    private static final String TAG = "JobInfoPresenter";
    private Subscription commentsSubs;

    public void getData(boolean pullToRefresh) {
        if(isViewAttached()) {
            Map<String, String> params = getView().params();
            Observable<JobInfoResult> observable = JobApi.getJobInfo(params);
            subscribe(observable, pullToRefresh);
        }
    }

    public void getComments() {
        if(isViewAttached()) {
            Map<String, String> params = getView().commentsParams();
            Observable<CommentsResult> observable = JobApi.getComments(params);
            commentsSubs = observable
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<CommentsResult>() {
                @Override
                public void onCompleted() {
                    commentsSubs.unsubscribe();
                }

                @Override
                public void onError(Throwable e) {
                    commentsSubs.unsubscribe();
                }

                @Override
                public void onNext(CommentsResult commentsResult) {
                    getView().setCommentsData(commentsResult);
                }
            });
        }
    }

    @Override
    protected void onNext(JobInfoResult data) {
        super.onNext(data);
        Log.d(TAG, "onNext");
        JobManager manager = DbManager.get(JobManager.class);
        Job job = new Job();
        job.setJobName(data.detail.jobName);
        job.setId((long) data.detail.id);
        manager.insert(job);
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        if(!retainInstance
                && commentsSubs!=null
                && commentsSubs.isUnsubscribed()) {
            commentsSubs.unsubscribe();
        }
    }

}
