package com.wfc.test6.api;

import com.wfc.test6.bean.EnterpriseResult;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by wangfengchen on 2016/12/31.
 */

public class UserApi extends BaseApi {

    //定义接口
    private interface UserService {
        //GET注解不可用@FormUrlEncoded，要用@Query注解引入请求参数


        @GET("api/enterprise/getInfo.htm")
        Observable<EnterpriseResult> getEnterpriseInfo(@Query("id") int id);


        //POST方法没有缓存，适用于更新数据
//        @FormUrlEncoded
//        @POST("")
//        Observable<JobResult> insertJob(@Field("userName") String userName);
    }

    protected static final UserService service = getRetrofit().create(UserService.class);

    public static Observable<EnterpriseResult> getEnterpriseInfo(int userId){
        return service.getEnterpriseInfo(userId);
    }

}
