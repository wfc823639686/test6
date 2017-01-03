package com.wfc.test6.presenter;

import com.hannesdorfmann.mosby.mvp.rx.lce.MvpLceRxPresenter;
import com.wfc.test6.api.JobApi;
import com.wfc.test6.bean.CommentsResult;
import com.wfc.test6.bean.JobInfoResult;
import com.wfc.test6.bean.JobListResult;
import com.wfc.test6.view.JobInfoView;
import com.wfc.test6.view.JobListView;

import java.util.Map;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wangfengchen on 2016/12/31.
 */

public class JobInfoPresenter extends MvpLceRxPresenter<JobInfoView, JobInfoResult> {

    private Subscription commentsSubs;

    public void getData() {
        if(isViewAttached()) {
            Map<String, String> params = getView().params();
            Observable<JobInfoResult> observable = JobApi.getJobInfo(params);
            subscribe(observable, false);
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
}
