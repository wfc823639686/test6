package com.wfc.test6.base;

import android.support.annotation.NonNull;
import android.view.View;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceActivity;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by wangfengchen on 2017/1/3.
 */

public abstract class BaseLceActivity<CV extends View, M, V extends MvpLceView<M>, P extends MvpPresenter<V>>
        extends MvpLceActivity<CV, M, V, P> {

    protected Map<String, String> params = new LinkedHashMap<>();

}
