package com.wfc.test6.base;

import android.view.View;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceFragment;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

/**
 * Created by wangfengchen on 2016/12/31.
 */

public abstract class BaseLceFragment<CV extends View, M, V extends MvpLceView<M>,
        P extends MvpPresenter<V>>
        extends MvpLceFragment<CV, M, V, P> {

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return null;
    }

    @Override
    public void setData(M data) {

    }

    @Override
    public void loadData(boolean pullToRefresh) {

    }
}
