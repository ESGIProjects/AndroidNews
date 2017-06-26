package com.esgi.androtopic.Data.Api;

import com.esgi.androtopic.Data.Model.PostAuth;
import com.esgi.androtopic.Data.Model.PostSubscribe;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by kevin on 24/06/2017.
 */

public interface ApiInterface {

    @POST("auth/login")
    Call<String> login(@Body PostAuth pa);

    @POST("auth/subscribe")
    Call<PostSubscribe> sign(@Body PostSubscribe ps);

    //@POST("/comments")

    //@POST("/news")

    //@POST("/posts")

    //@POST("/topics")

}
