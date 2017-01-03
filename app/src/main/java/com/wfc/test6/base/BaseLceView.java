package com.wfc.test6.base;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

import java.util.Map;

/**
 * Created by wangfengchen on 2017/1/3.
 */

public interface BaseLceView<M> extends MvpLceView<M> {

    Map<String, String> params();
}
