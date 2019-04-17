package com.example.chen.gank.data.api;

import com.example.chen.gank.data.bean.Day;
import com.example.chen.gank.data.bean.GankDailyResult;
import com.example.chen.gank.data.bean.GankFilterResult;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Coder : chenshuaiyu
 * Time : 2019/4/14 9:43
 */
public interface Api {

    @GET("today")
    Observable<GankDailyResult> getDaily();

    @GET("day/history")
    Observable<Day> getDayHistory();


    @GET("day/{year}/{month}/{day}")
    Observable<GankDailyResult> getDay(
            @Path("year") String year,
            @Path("month") String month,
            @Path("day") String day
    );

    @GET("data/{filterType}/{count}/{page}")
    Observable<GankFilterResult> gankFilter(
            @Path("filterType") String filterType,
            @Path("count") int count,
            @Path("page") int page
    );

}
