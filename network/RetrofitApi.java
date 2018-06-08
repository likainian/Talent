package com.irenshi.personneltreasure.network;

import android.graphics.Bitmap;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by mike.li on 2018/4/17.
 * 申请有关接口
 */

interface RetrofitApi {

    //普通Get
    @GET
    Observable<String> requestGet(@Url String url);

    //普通Post
    @POST
    Observable<String> requestPost(@Url String url, @Body RequestBody body);

    //没有body的post
    @POST
    Observable<String> requestPost(@Url String url);

    //普通Get
    @GET
    Observable<Bitmap> requestImage(@Url String url);

}
