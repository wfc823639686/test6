package com.wfc.test6.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wfc.test6.R;
import com.wfc.test6.bean.JobModel;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by wangfengchen on 2016/12/31.
 */

public class JobListAdapter extends CommonAdapter<JobModel> {

    private Context mContext;

    private static final String TAG = "JobListAdapter";

    public JobListAdapter(Context context, List<JobModel> datas) {
        super(context, R.layout.item_job, datas);
        mContext = context;
    }

    @Override
    protected void convert(ViewHolder holder, JobModel job, int position) {
        holder.setText(R.id.item_job_name, job.jobName);
        holder.setText(R.id.item_job_full_name, job.enterprise.name);
        ImageView headIv = holder.getView(R.id.item_job_head);
        Glide.with(mContext)
                .load(job.enterprise.head)
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher)
                .into(headIv);
    }
}
