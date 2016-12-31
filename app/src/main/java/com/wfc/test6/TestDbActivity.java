package com.wfc.test6;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.wfc.test6.bean.Enterprise;
import com.wfc.test6.bean.User;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import okhttp3.Call;

/**
 * Created by wangfengchen on 2016/12/30.
 */

public class TestDbActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "TestDbActivity";
    private UserManager userManager;
    private EnterpriseManager enterpriseManager;
    Button insertBtn, queryBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userManager = new UserManager();
        enterpriseManager = new EnterpriseManager();
//        insertBtn = (Button) findViewById(R.id.main_insert_btn);
//        queryBtn = (Button) findViewById(R.id.main_query_btn);
        insertBtn.setOnClickListener(this);
        queryBtn.setOnClickListener(this);
        getData();

    }

    void getData() {
        OkHttpUtils.get()
                .url("http://apitest.shangshaban.com/api/enterprise/getInfo.htm")
                .addParams("id", "3")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "e " + e);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            Enterprise e = gson.fromJson(jsonObject.getJSONObject("detail").toString(), Enterprise.class);
                            Log.d(TAG, "e " + e.getFullName());
                            enterpriseManager.insertOrReplace(e);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    void insert() {
        User user = new User();
        user.setName("wfc");
        user.setPhone("18612011897");
        Object r = userManager.insertOrReplace(user);
        Log.e(TAG, "r " + r);
    }

    void query() {
//        List<User> list = userManager.loadAll();
//        print(list);
//        list = userManager.queryRaw("where phone=?","18612011897");
//        List<User> list = userManager.getQueryBuilder()
//                .where(UserDao.Properties.Name.like("w%"))
//                .list();
        List<Enterprise> list = enterpriseManager.loadAll();
        print(list);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.main_insert_btn:
//                insert();
//                break;
//            case R.id.main_query_btn:
//                query();
//                break;
        }
    }

    void print(List<Enterprise> list) {
        for (Enterprise u : list) {
            Log.e(TAG, "f " + u.getFullName());
            Log.e(TAG, "i " + u.getId());
            Log.e(TAG, "s " + u.getShortName());
        }
    }
}
