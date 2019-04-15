package com.example.chen.gank.data.api;

import com.example.chen.gank.data.bean.GankDailyResult;
import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Coder : chenshuaiyu
 * Time : 2019/4/14 9:43
 */
public interface Api {

    @GET("today")
    Observable<GankDailyResult> getDaily();


}
