package com.wfc.test6.view;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;
import com.wfc.test4.bean.JobListResult;

import java.util.Map;

/**
 * Created by wangfengchen on 2016/12/31.
 */

public interface JobListView extends MvpLceView<JobListResult> {

    Map<String, String> params();
}
