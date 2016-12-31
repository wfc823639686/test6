package com.wfc.test6;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.Arrays;
import java.util.List;

/**
 * Created by wangfengchen on 2016/12/28.
 */

public class TestStickyNavActivity extends AppCompatActivity {

    private RecyclerView mRecycler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_test_sticky_nav);
        mRecycler = (RecyclerView) findViewById(R.id.id_stickynavlayout_content);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecycler.setAdapter(new MyListAdapter(this, R.layout.item_test,
                Arrays.asList(
                        "http://ssb-img.shangshaban.com/Image_20160816190201.png",
                        "http://ssb-img.shangshaban.com/Image_20160816190201.png",
                        "http://ssb-img.shangshaban.com/Image_20160816190201.png",
                        "http://ssb-img.shangshaban.com/Image_20160816190201.png",
                        "http://ssb-img.shangshaban.com/Image_20160816190201.png",
                        "http://ssb-img.shangshaban.com/Image_20160816190201.png",
                        "http://ssb-img.shangshaban.com/Image_20160816190201.png",
                        "http://ssb-img.shangshaban.com/Image_20160816190201.png",
                        "http://ssb-img.shangshaban.com/Image_20160816190201.png"
                )));
    }

    class MyListAdapter extends CommonAdapter<String> {

        public MyListAdapter(Context context, int layoutId, List<String> datas) {
            super(context, layoutId, datas);
        }

        @Override
        protected void convert(ViewHolder holder, String s, int position) {
            holder.setText(R.id.text, s);
            ImageView iv = holder.getView(R.id.img);
            Glide.with(getBaseContext())
                    .load(s)
                    .placeholder(R.mipmap.ic_launcher_round)
                    .error(R.mipmap.ic_launcher)
                    .into(iv);
        }
    }
}
