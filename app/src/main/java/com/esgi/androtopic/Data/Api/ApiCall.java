package com.esgi.androtopic.Data.Api;

import com.esgi.androtopic.Tools.RetrofitInstance;

/**
 * Created by kevin on 24/06/2017.
 */

public class ApiCall {

    public static final String URL = "https://esgi-2017.herokuapp.com/";

    public static ApiInterface getRetrofitInstance(){
        return RetrofitInstance.getInstance(URL).create(ApiInterface.class);
    }
}
