package com.wfc.test6.api;

import com.wfc.test6.bean.JobListResult;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by wangfengchen on 2016/12/31.
 */

public class JobApi extends BaseApi {

    //定义接口
    private interface JobService {
        //GET注解不可用@FormUrlEncoded，要用@Query注解引入请求参数


        @GET("api/job/getJobs.htm")
        Observable<JobListResult> getJobList(@QueryMap Map<String, String> map);


        //POST方法没有缓存，适用于更新数据
//        @FormUrlEncoded
//        @POST("")
//        Observable<JobResult> insertJob(@Field("userName") String userName);
    }

    private static final JobService service = getRetrofit().create(JobService.class);

    public static Observable<JobListResult> getJobList(Map<String, String> map){
        return service.getJobList(map);
    }

}
