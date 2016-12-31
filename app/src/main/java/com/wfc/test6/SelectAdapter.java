package com.wfc.test6;

import android.content.Context;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by wangfengchen on 2016/12/30.
 */

public class SelectAdapter extends CommonAdapter<SelectModel> {

    public SelectAdapter(Context context, int layoutId, List<SelectModel> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, SelectModel selectModel, int position) {
        holder.setText(R.id.item_select_name, selectModel.name);

    }
}
