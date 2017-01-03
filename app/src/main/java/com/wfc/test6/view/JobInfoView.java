package com.wfc.test6.view;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;
import com.wfc.test6.base.BaseLceView;
import com.wfc.test6.bean.CommentsResult;
import com.wfc.test6.bean.JobInfoResult;
import com.wfc.test6.bean.JobListResult;

import java.util.Map;

/**
 * Created by wangfengchen on 2016/12/31.
 */

public interface JobInfoView extends BaseLceView<JobInfoResult> {

    Map<String, String> commentsParams();

    void setCommentsData(CommentsResult result);

}
