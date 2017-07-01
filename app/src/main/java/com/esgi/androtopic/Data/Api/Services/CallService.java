package com.esgi.androtopic.Data.Api.Services;

import android.util.Log;

import com.esgi.androtopic.Data.Api.IServiceResultListener;
import com.esgi.androtopic.Data.Api.ServiceResult;
import com.esgi.androtopic.Data.Model.PostAuth;
import com.esgi.androtopic.Data.Model.PostComment;
import com.esgi.androtopic.Data.Model.PostSubscribe;
import com.esgi.androtopic.Tools.ApiCall;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kevin on 29/06/2017.
 */

public class CallService implements IAuthService, ICommentService {

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
                    result.setData(response.body());
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

    public void postComments(PostComment pc, IServiceResultListener<Void> isrl) {

    }

    public void delComments(int i, IServiceResultListener<Void> isrl) {

    }

    public void getComments(IServiceResultListener<Void> isrl) {

    }

    public void getComment(int i, IServiceResultListener<Void> isrl) {

    }

    public void putComment(PostComment pc, int i, IServiceResultListener<Void> isrl) {

    }
}
