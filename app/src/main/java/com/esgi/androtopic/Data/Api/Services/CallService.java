package com.esgi.androtopic.Data.Api.Services;

import android.content.Context;
import android.util.Log;

import com.esgi.androtopic.Data.Api.IServiceResultListener;
import com.esgi.androtopic.Data.Api.ServiceResult;
import com.esgi.androtopic.Data.Model.News;
import com.esgi.androtopic.Data.Model.PostAuth;
import com.esgi.androtopic.Data.Model.PostNews;
import com.esgi.androtopic.Data.Model.PostSubscribe;
import com.esgi.androtopic.Data.Model.PostTopic;
import com.esgi.androtopic.Data.Model.Topics;
import com.esgi.androtopic.Data.Model.User;
import com.esgi.androtopic.Tools.ApiCall;
import com.esgi.androtopic.Tools.RealmInstance;

import java.util.List;

import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kevin on 29/06/2017.
 */

public class CallService implements IAuthService, INewsService, ITopicService {

    static CallService cs = null;

    public static CallService getInstance() {
        if (cs == null) {
            cs = new CallService();
        }
        return cs;
    }

    public void login(PostAuth pa, final IServiceResultListener<String> isrl) {
        ApiCall.getRetrofitInstance().login(pa).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                ServiceResult<String> result = new ServiceResult<String>();
                Log.i("RESPONSE : ", response.message());
                result.setResponseCode(response.code());
                if (result.getResponseCode() == 200) {
                    Log.i("TOKEN : ", response.body());
                    result.setSimpleData(response.body());
                    isrl.onResult(result);
                } else {
                    Log.i("ERROR : ", "Retry after !");
                    Log.i("RESPONSE : ", String.valueOf(response.code()));
                    isrl.onResult(result);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                ServiceResult<String> result = new ServiceResult<String>();
                result.setException(t);
                Log.i("FAILURE : ", "No response from server");
                Log.i("CAUSE : ", t.getMessage().toString());
                isrl.onResult(result);
            }
        });
    }

    public void sign(PostSubscribe ps, final IServiceResultListener<Void> isrl) {
        ApiCall.getRetrofitInstance().sign(ps)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        ServiceResult<Void> sr = new ServiceResult<Void>();
                        sr.setResponseCode(response.code());
                        Log.i("RESPONSE : ", response.message());
                        if (sr.getResponseCode() == 200) {
                            Log.i("ERROR : ", "Account already exists !");
                            isrl.onResult(sr);
                        } else if (sr.getResponseCode() == 201) {
                            Log.i("SUCCESS : ", "Account is created ! !");
                            isrl.onResult(sr);
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        ServiceResult<Void> sr = new ServiceResult<Void>();
                        sr.setException(t);
                        Log.i("FAILURE : ", "No response from server");
                        Log.i("CAUSE : ", t.getMessage().toString());
                        isrl.onResult(sr);
                    }
                });
    }

    public void postNews(String token,PostNews pn, final IServiceResultListener<Void> isrl){
        ApiCall.getRetrofitInstance().postNews(token,pn)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        ServiceResult<Void> sr = new ServiceResult<Void>();
                        sr.setResponseCode(response.code());
                        Log.i("RESPONSE : ", response.message());
                        Log.i("SUCCESS : ", "News is created ! !");
                        isrl.onResult(sr);
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        ServiceResult<Void> sr = new ServiceResult<Void>();
                        sr.setException(t);
                        Log.i("FAILURE : ", "No response from server");
                        Log.i("CAUSE : ", t.getMessage().toString());
                        isrl.onResult(sr);
                    }
                });
    }

    public void postNews(int i, IServiceResultListener<Void> isrl){

    }

    public void delNews(int i, IServiceResultListener<Void> isrl){

    }

    public void getNews(String token, final IServiceResultListener<News> isrl){
        ApiCall.getRetrofitInstance().getNews(token)
                .enqueue(new Callback<List<News>>() {
                    @Override
                    public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                        ServiceResult<News> sr = new ServiceResult<News>();
                        sr.setResponseCode(response.code());
                        sr.setData(response.body());
                        Log.i("RESPONSE : ", response.message());
                        Log.i("SUCCESS : ", "NewsList is created ! !");
                        isrl.onResult(sr);
                    }

                    @Override
                    public void onFailure(Call<List<News>> call, Throwable t) {
                        ServiceResult<News> sr = new ServiceResult<News>();
                        sr.setException(t);
                        Log.i("FAILURE : ", "No response from server");
                        Log.i("CAUSE : ", t.getMessage().toString());
                        isrl.onResult(sr);
                    }
                });
    }

    public void getNews(int i, IServiceResultListener<Void> isrl){

    }

    public void putNews(PostNews pn ,int i, IServiceResultListener<Void> isrl){

    }

    public void postTopic(String token, PostTopic pt, final IServiceResultListener<Void> isrl){
        ApiCall.getRetrofitInstance().postTopic(token,pt)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        ServiceResult<Void> sr = new ServiceResult<Void>();
                        sr.setResponseCode(response.code());
                        Log.i("RESPONSE : ", response.message());
                        Log.i("SUCCESS : ", "Topic is created ! !");
                        isrl.onResult(sr);
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        ServiceResult<Void> sr = new ServiceResult<Void>();
                        sr.setException(t);
                        Log.i("FAILURE : ", "No response from server");
                        Log.i("CAUSE : ", t.getMessage().toString());
                        isrl.onResult(sr);
                    }
                });
    }

    public void delTopic(int i, IServiceResultListener<Void> isrl){

    }

    public void getTopics(String token, final IServiceResultListener<Topics> isrl){
        ApiCall.getRetrofitInstance().getTopics(token)
                .enqueue(new Callback<List<Topics>>() {
                    @Override
                    public void onResponse(Call<List<Topics>> call, Response<List<Topics>> response) {
                        ServiceResult<Topics> sr = new ServiceResult<Topics>();
                        sr.setResponseCode(response.code());
                        sr.setData(response.body());
                        Log.i("RESPONSE : ", response.message());
                        Log.i("SUCCESS : ", "TopicsList is created ! !");
                        isrl.onResult(sr);
                    }

                    @Override
                    public void onFailure(Call<List<Topics>> call, Throwable t) {
                        ServiceResult<Topics> sr = new ServiceResult<Topics>();
                        sr.setException(t);
                        Log.i("FAILURE : ", "No response from server");
                        Log.i("CAUSE : ", t.getMessage().toString());
                        isrl.onResult(sr);
                    }
                });
    }

    public void getTopic(int i, IServiceResultListener<Void> isrl){

    }

    public void putTopic(PostTopic pt, IServiceResultListener<Void> isrl){

    }

    public static String getToken(Context context){
        RealmResults<User> result = RealmInstance.getRealmInstance(context).where(User.class).findAll();
        return "Bearer " + result.get(0).getToken();
    }
}
