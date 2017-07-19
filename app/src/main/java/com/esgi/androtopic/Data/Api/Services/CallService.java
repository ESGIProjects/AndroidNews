package com.esgi.androtopic.Data.Api.Services;

import android.content.Context;
import android.util.Log;

import com.esgi.androtopic.Data.Api.IServiceResultListener;
import com.esgi.androtopic.Data.Api.ServiceResult;
import com.esgi.androtopic.Data.Model.Comments;
import com.esgi.androtopic.Data.Model.News;
import com.esgi.androtopic.Data.Model.PostAuth;
import com.esgi.androtopic.Data.Model.PostComment;
import com.esgi.androtopic.Data.Model.PostNews;
import com.esgi.androtopic.Data.Model.PostSubscribe;
import com.esgi.androtopic.Data.Model.PostTopic;
import com.esgi.androtopic.Data.Model.Posts;
import com.esgi.androtopic.Data.Model.Topics;
import com.esgi.androtopic.Data.Model.User;
import com.esgi.androtopic.Tools.ApiCall;
import com.esgi.androtopic.Tools.RealmInstance;

import org.w3c.dom.Comment;

import java.util.List;

import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kevin on 29/06/2017.
 */

public class CallService implements IAuthService, INewsService, ITopicService, IUserService, ICommentService {

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

    public void postNews(String token, PostNews pn, final IServiceResultListener<Void> isrl) {
        ApiCall.getRetrofitInstance().postNews(token, pn)
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

    public void postNews(int i, IServiceResultListener<Void> isrl) {

    }

    public void delNews(String token, String id, final IServiceResultListener<Void> isrl) {
        ApiCall.getRetrofitInstance().delNews(token, id)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        ServiceResult<Void> sr = new ServiceResult<>();
                        sr.setResponseCode(response.code());
                        Log.i("RESPONSE : ", response.message());
                        isrl.onResult(sr);
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        ServiceResult<Void> sr = new ServiceResult<>();
                        sr.setException(t);
                        Log.i("FAILURE : ", "No response from server");
                        Log.i("CAUSE : ", t.getMessage().toString());
                        isrl.onResult(sr);
                    }
                });
    }

    public void getNews(String token, final IServiceResultListener<News> isrl) {
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

    public void getNews(int i, IServiceResultListener<Void> isrl) {

    }

    public void putNews(String token, PostNews pn, String id, final IServiceResultListener<Void> isrl) {
        ApiCall.getRetrofitInstance().putNews(token, pn, id)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        ServiceResult<Void> sr = new ServiceResult<>();
                        sr.setResponseCode(response.code());
                        Log.i("RESPONSE : ", response.message());
                        isrl.onResult(sr);
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        ServiceResult<Void> sr = new ServiceResult<>();
                        sr.setException(t);
                        Log.i("FAILURE : ", "No response from server");
                        Log.i("CAUSE : ", t.getMessage().toString());
                        isrl.onResult(sr);
                    }
                });
    }

    public void postTopic(String token, PostTopic pt, final IServiceResultListener<Void> isrl) {
        ApiCall.getRetrofitInstance().postTopic(token, pt)
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

    public void delTopic(String token, String id, final IServiceResultListener<Void> isrl) {
        ApiCall.getRetrofitInstance().delTopic(token, id)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        ServiceResult<Void> sr = new ServiceResult<>();
                        sr.setResponseCode(response.code());
                        Log.i("RESPONSE : ", response.message());
                        isrl.onResult(sr);
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        ServiceResult<Void> sr = new ServiceResult<>();
                        sr.setException(t);
                        Log.i("FAILURE : ", "No response from server");
                        Log.i("CAUSE : ", t.getMessage().toString());
                        isrl.onResult(sr);
                    }
                });
    }

    public void getTopics(String token, final IServiceResultListener<Topics> isrl) {
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

    public void getTopic(int i, IServiceResultListener<Void> isrl) {

    }

    public void putTopic(String token, PostTopic pn, String id, final IServiceResultListener<Void> isrl) {
        ApiCall.getRetrofitInstance().putTopic(token, pn, id)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        ServiceResult<Void> sr = new ServiceResult<>();
                        sr.setResponseCode(response.code());
                        Log.i("RESPONSE : ", response.message());
                        isrl.onResult(sr);
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        ServiceResult<Void> sr = new ServiceResult<>();
                        sr.setException(t);
                        Log.i("FAILURE : ", "No response from server");
                        Log.i("CAUSE : ", t.getMessage().toString());
                        isrl.onResult(sr);
                    }
                });
    }


    public void getUser(String token, final IServiceResultListener<User> isrl) {
        ApiCall.getRetrofitInstance().getUser(token).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                ServiceResult<User> sr = new ServiceResult<User>();
                sr.setResponseCode(response.code());
                sr.setUser(response.body());
                Log.i("RESPONSE : ", response.message());
                isrl.onResult(sr);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                ServiceResult<User> sr = new ServiceResult<User>();
                sr.setException(t);
                Log.i("FAILURE : ", "No response from server");
                Log.i("CAUSE : ", t.getMessage().toString());
                isrl.onResult(sr);
            }
        });
    }

    public static String getToken(Context context) {
        RealmResults<User> result = RealmInstance.getRealmInstance(context).where(User.class).findAll();
        return "Bearer " + result.get(0).getToken();
    }

    public static String getID(Context context) {
        RealmResults<User> result = RealmInstance.getRealmInstance(context).where(User.class).findAll();
        return result.get(0).getId();
    }


    @Override
    public void postComments(String token, PostComment pc, final IServiceResultListener<Void> isrl) {
        ApiCall.getRetrofitInstance().postComments(token, pc)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        ServiceResult<Void> sr = new ServiceResult<>();
                        sr.setResponseCode(response.code());
                        Log.i("RESPONSE : ", response.message());
                        isrl.onResult(sr);
                    }

                    public void onFailure(Call<Void> call, Throwable t) {
                        ServiceResult<Void> sr = new ServiceResult<>();
                        sr.setException(t);
                        Log.i("FAILURE : ", "No response from server");
                        Log.i("CAUSE : ", t.getMessage().toString());
                        isrl.onResult(sr);
                    }
                });
    }

    public void getPosts(String token, final IServiceResultListener<Posts> isrl) {
        ApiCall.getRetrofitInstance().getPosts(token)
                .enqueue(new Callback<List<Posts>>() {
                    @Override
                    public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                        ServiceResult<Posts> sr = new ServiceResult<Posts>();
                        sr.setResponseCode(response.code());
                        sr.setData(response.body());
                        Log.i("RESPONSE : ", response.message());
                        Log.i("SUCCESS : ", "TopicsList is created ! !");
                        isrl.onResult(sr);
                    }

                    @Override
                    public void onFailure(Call<List<Posts>> call, Throwable t) {
                        ServiceResult<Posts> sr = new ServiceResult<Posts>();
                        sr.setException(t);
                        Log.i("FAILURE : ", "No response from server");
                        Log.i("CAUSE : ", t.getMessage().toString());
                        isrl.onResult(sr);
                    }
                });
    }

    @Override
    public void delComments(String token, int i, final IServiceResultListener<Void> isrl) {
        ApiCall.getRetrofitInstance().delComment(token, i)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        ServiceResult<Void> sr = new ServiceResult<>();
                        sr.setResponseCode(response.code());
                        Log.i("RESPONSE : ", response.message());
                        isrl.onResult(sr);
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        ServiceResult<Void> sr = new ServiceResult<>();
                        sr.setException(t);
                        Log.i("FAILURE : ", "No response from server");
                        Log.i("CAUSE : ", t.getMessage().toString());
                        isrl.onResult(sr);
                    }
                });
    }

    @Override
    public void getComments(String token, final IServiceResultListener<List<Comments>> isrl) {
        ApiCall.getRetrofitInstance().getComments(token)
                .enqueue(new Callback<List<Comments>>() {
                    @Override
                    public void onResponse(Call<List<Comments>> call, Response<List<Comments>> response) {
                        ServiceResult<List<Comments>> sr = new ServiceResult<List<Comments>>();
                        sr.setResponseCode(response.code());
                        Log.i("RESPONSE : ", response.message());
                        isrl.onResult(sr);
                    }

                    @Override
                    public void onFailure(Call<List<Comments>> call, Throwable t) {
                        ServiceResult<List<Comments>> sr = new ServiceResult<List<Comments>>();
                        sr.setException(t);
                        Log.i("FAILURE : ", "No response from server");
                        Log.i("CAUSE : ", t.getMessage().toString());
                        isrl.onResult(sr);
                    }
                });
    }

    @Override
    public void getComment(String token, int i, final IServiceResultListener<Comments> isrl) {
        ApiCall.getRetrofitInstance().getComment(token, i)
                .enqueue(new Callback<Comments>() {
                    @Override
                    public void onResponse(Call<Comments> call, Response<Comments> response) {
                        ServiceResult<Comments> sr = new ServiceResult<Comments>();
                        sr.setResponseCode(response.code());
                        Log.i("RESPONSE : ", response.message());
                        isrl.onResult(sr);
                    }

                    @Override
                    public void onFailure(Call<Comments> call, Throwable t) {
                        ServiceResult<Comments> sr = new ServiceResult<>();
                        sr.setException(t);
                        Log.i("FAILURE : ", "No response from server");
                        Log.i("CAUSE : ", t.getMessage().toString());
                        isrl.onResult(sr);
                    }
                });
    }

    @Override
    public void putComment(String token, PostComment pc, int i, final IServiceResultListener<Void> isrl) {
        ApiCall.getRetrofitInstance().putComment(token, pc, i)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        ServiceResult<Void> sr = new ServiceResult<>();
                        sr.setResponseCode(response.code());
                        Log.i("RESPONSE : ", response.message());
                        isrl.onResult(sr);
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        ServiceResult<Void> sr = new ServiceResult<>();
                        sr.setException(t);
                        Log.i("FAILURE : ", "No response from server");
                        Log.i("CAUSE : ", t.getMessage().toString());
                        isrl.onResult(sr);

                    }
                });
    }
}
