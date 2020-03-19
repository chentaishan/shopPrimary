package com.xts.shop.net;

import com.xts.shop.bean.CataLogItemBean;
import com.xts.shop.bean.CatalogBean;
import com.xts.shop.bean.TopicBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    String sBaseUrl = "https://cdwan.cn/api/";

    //专题数据请求接口
    @GET("topic/list")
    Flowable<TopicBean> getTopic(@Query("page") int page, @Query("size") int size);

//    https://cdwan.cn/api/catalog/index
//    https://cdwan.cn/api/catalog/current?id=1005001
    //专题数据请求接口
    @GET("catalog/index")
    Flowable<CatalogBean> getLeftDataList();

    @GET("catalog/current?id=1005001")
    Flowable<CataLogItemBean> getRightDataList();




}
