package com.esgi.androtopic.Data.Api;

import com.esgi.androtopic.Data.Model.News;
import com.esgi.androtopic.Data.Model.PostAuth;
import com.esgi.androtopic.Data.Model.PostComment;
import com.esgi.androtopic.Data.Model.PostNews;
import com.esgi.androtopic.Data.Model.PostPost;
import com.esgi.androtopic.Data.Model.PostSubscribe;
import com.esgi.androtopic.Data.Model.PostTopic;
import com.esgi.androtopic.Data.Model.Topics;
import com.esgi.androtopic.Data.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by kevin on 24/06/2017.
 */

public interface ApiInterface {

    // POST ENDPOINTS

    @POST("auth/login")
    Call<String> login(@Body PostAuth pa);

    @POST("auth/subscribe")
    Call<Void> sign(@Body PostSubscribe ps);

    @POST("/comments")
    Call<Void> postComments(@Body PostComment pc);

    @POST("/news")
    Call<Void> postNews(@Header("Authorization") String token, @Body PostNews pn);

    @POST("/news/{id}/moderate")
    Call<Void> postNews(@Path("id") int id);

    @POST("/topics")
    Call<Void> postTopic(@Header("Authorization") String token,@Body PostTopic pt);

    @POST("/post")
    Call<Void> postPost(@Body PostPost pn);

    //DELETE ENDPOINTS

    @DELETE("/comments/{id}")
    Call<Void> delComment(@Path("id") int id);

    @DELETE("/news/{id}")
    Call<Void> delNews(@Header("Authorization") String token, @Path("id") String id);

    @DELETE("/posts/{id}")
    Call<Void> delPost(@Path("id") int id);

    @DELETE("/topics/{id}")
    Call<Void> delTopic(@Header("Authorization") String token, @Path("id") String id);

    //GET ENDPOINTS

    @GET("/comments")
    Call<Void> getComments();

    @GET("/comments/{id}")
    Call<Void> getComment(@Path("id") int id);

    @GET("/news")
    Call<List<News>> getNews(@Header("Authorization") String token);

    @GET("/news/{id}")
    Call<Void> getNews(@Path("id") int id);

    @GET("/posts")
    Call<Void> getPosts();

    @GET("/posts/{id}")
    Call<Void> getPost(@Path("id") int id);

    @GET("/topics")
    Call<List<Topics>> getTopics(@Header("Authorization") String token);

    @GET("/topics/{id}")
    Call<Void> getTopic(@Path("id") int id);

    @GET("/users/me")
    Call<User> getUser(@Header("Authorization") String token);

    // PUT ENDPOINTS

    @PUT("/comments/{id}")
    Call<Void> putComment(@Body PostComment pc ,@Path("id") int id);

    @PUT("/news/{id}")
    Call<Void> putNews(@Header("Authorization") String token,@Body PostNews pn ,@Path("id") String id);

    @PUT("/posts/{id}")
    Call<Void> putPost(@Body PostPost pp ,@Path("id") int id);

    @PUT("/topics/{id}")
    Call<Void> putTopic(@Header("Authorization") String token,@Body PostTopic pn ,@Path("id") String id);

}
