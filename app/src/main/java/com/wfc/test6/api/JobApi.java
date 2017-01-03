package com.wfc.test6.api;

import com.wfc.test6.bean.CommentsResult;
import com.wfc.test6.bean.JobInfoResult;
import com.wfc.test6.bean.JobListResult;
import com.wfc.test6.bean.Result;

import java.util.Map;

import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
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

        @GET("api/job/getInfo.htm")
        Observable<JobInfoResult> getJobInfo(@QueryMap Map<String, String> map);

        @GET("api/enterprise/getComments.htm")
        Observable<CommentsResult> getComments(@QueryMap Map<String, String> map);

        @POST("api/job/update.htm")
        @FormUrlEncoded
        Observable<Result> postJob(@FieldMap Map<String, String> map);


        //POST方法没有缓存，适用于更新数据
//        @FormUrlEncoded
//        @POST("")
//        Observable<JobResult> insertJob(@Field("userName") String userName);
    }

    private static final JobService service = getRetrofit().create(JobService.class);

    public static Observable<JobListResult> getJobList(Map<String, String> map){
        return service.getJobList(map);
    }

    public static Observable<JobInfoResult> getJobInfo(Map<String, String> map){
        return service.getJobInfo(map);
    }

    public static Observable<CommentsResult> getComments(Map<String, String> map){
        return service.getComments(map);
    }

    public static Observable<Result> postJob(Map<String, String> map) {
        return service.postJob(map);
    }
}
