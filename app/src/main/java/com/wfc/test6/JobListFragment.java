package com.wfc.test6;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wfc.test4.adapter.JobListAdapter;
import com.wfc.test4.base.BaseLceFragment;
import com.wfc.test4.bean.JobListResult;
import com.wfc.test4.bean.JobModel;
import com.wfc.test4.presenter.JobListPresenter;
import com.wfc.test4.ui.recyclerview.LoadMoreWrapper;
import com.wfc.test4.view.JobListView;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangfengchen on 2016/12/31.
 */

public class JobListFragment
        extends BaseLceFragment<SwipeRefreshLayout, JobListResult, JobListView, JobListPresenter>
        implements JobListView, SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "JobListFragment";
    private JobListAdapter jobListAdapter;
    private List<JobModel> list = new ArrayList<>();
    private HeaderAndFooterWrapper mHeaderAndFooterWrapper;
    private LoadMoreWrapper mLoadMoreWrapper;
    private RecyclerView mRecyclerView;
    private Map<String, String> params = new LinkedHashMap<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_job_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        contentView.setOnRefreshListener(this);
        jobListAdapter = new JobListAdapter(getActivity(), list);
        mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(jobListAdapter);
        mLoadMoreWrapper = new LoadMoreWrapper(getContext(), mHeaderAndFooterWrapper);
        mLoadMoreWrapper.setOnLoadListener(new LoadMoreWrapper.OnLoadListener() {
            @Override
            public void onRetry() {

            }

            @Override
            public void onLoadMore() {
                Log.e(TAG, "onLoadMore");
                getPresenter().getData();
            }
        });
        mRecyclerView = (RecyclerView) view.findViewById(R.id.job_list_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mLoadMoreWrapper);
        mRecyclerView.setHasFixedSize(true);
        getPresenter().getData();
        contentView.setRefreshing(true);
    }

    @NonNull
    @Override
    public JobListPresenter createPresenter() {
        return new JobListPresenter();
    }

    @Override
    public void setData(JobListResult data) {
        Log.e(TAG, "setData " + data.results.size());
        if (contentView.isRefreshing()) {
            list.clear();
            contentView.setRefreshing(false);
        }
        list.addAll(data.results);
        mLoadMoreWrapper.notifyDataSetChanged();
    }

    @Override
    public void showError(Throwable e, boolean pullToRefresh) {
        super.showError(e, pullToRefresh);
        Log.e(TAG, "error");
        contentView.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        getPresenter().getData();
    }

    @Override
    public Map<String, String> params() {
        String last = "";
        if(!contentView.isRefreshing()) {
            last = list!=null && !list.isEmpty() ? ""+list.get(list.size() - 1).id : "";
        }
        params.put("last", last);
        return params;
    }
}
