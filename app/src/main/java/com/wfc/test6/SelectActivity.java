package com.wfc.test6;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangfengchen on 2016/12/30.
 */

public class SelectActivity extends AppCompatActivity {

    RecyclerView selRecycler;

    SelectAdapter selectAdapter;

    List<SelectModel> selectModelList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        init();
        selRecycler = (RecyclerView) findViewById(R.id.select_recycler);
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        selectAdapter = new SelectAdapter(this, R.layout.item_select, selectModelList);
        //调用以下方法让RecyclerView的第一个条目仅为1列
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                //如果位置是0，那么这个条目将占用SpanCount()这么多的列数，再此也就是3
                //而如果不是0，则说明不是Header，就占用1列即可
                //layoutManager.getSpanCount()
                return 1;
            }
        });
        selRecycler.setLayoutManager(layoutManager);
        selRecycler.setAdapter(selectAdapter);
    }

    void init() {
        for (int i = 0; i < 10; i++) {
            SelectModel sm = new SelectModel();
            sm.name = "n" + i;
            selectModelList.add(sm);
        }
    }

}
