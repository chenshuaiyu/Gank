package com.example.chen.gank.data.api;

import com.example.chen.gank.data.bean.Day;
import com.example.chen.gank.data.bean.GankDailyResult;
import com.example.chen.gank.data.bean.GankFilterResult;
import com.example.chen.gank.data.bean.SearchBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author : chenshuaiyu
 * @date : 2019/4/14 9:43
 */
public interface Api {

    /**
     * 获取最新干货
     *
     * @return
     */
    @GET("today")
    Observable<GankDailyResult> getDaily();

    /**
     * 获取 Gank 更新日
     *
     * @return
     */
    @GET("day/history")
    Observable<Day> getDayHistory();

    /**
     * 获取某日干货
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    @GET("day/{year}/{month}/{day}")
    Observable<GankDailyResult> getDay(
            @Path("year") String year,
            @Path("month") String month,
            @Path("day") String day
    );

    /**
     * 获取 Gank 各类别数据
     *
     * @param filterType
     * @param count
     * @param page
     * @return
     */
    @GET("data/{filterType}/{count}/{page}")
    Observable<GankFilterResult> gankFilter(
            @Path("filterType") String filterType,
            @Path("count") int count,
            @Path("page") int page
    );

    /**
     * 搜索干货
     *
     * @param keywords
     * @param category
     * @param count
     * @param page
     * @return
     */
    @GET("search/query/{keywords}/category/{category}/count/{count}/page/{page}")
    Observable<SearchBean> search(
            @Path("keywords") String keywords,
            @Path("category") String category,
            @Path("count") int count,
            @Path("page") int page
    );
}
