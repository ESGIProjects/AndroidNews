package com.esgi.androtopic.Data.Api;

import com.esgi.androtopic.Data.Model.PostAuth;
import com.esgi.androtopic.Data.Model.PostComment;
import com.esgi.androtopic.Data.Model.PostNews;
import com.esgi.androtopic.Data.Model.PostPost;
import com.esgi.androtopic.Data.Model.PostSubscribe;
import com.esgi.androtopic.Data.Model.PostTopic;

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
    Call<Void> sign(@Body PostSubscribe ps);

    @POST("/comments")
    Call<Void> postComments(@Body PostComment pc);

    @POST("/news")
    Call<Void> postNews(@Body PostNews pn);

    @POST("/topic")
    Call<Void> postTopic(@Body PostTopic pt);

    @POST("/post")
    Call<Void> postPost(@Body PostPost pn);



}
