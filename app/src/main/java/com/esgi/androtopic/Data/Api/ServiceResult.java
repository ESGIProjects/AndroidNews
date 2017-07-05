package com.esgi.androtopic.Data.Api;

import java.util.List;

/**
 * Created by kevin on 28/06/2017.
 */

public class ServiceResult<T> {
    List<T> data;
    String simpleData;
    int responseCode;
    Throwable exception;

    public String getSimpleData() {
        return simpleData;
    }

    public void setSimpleData(String simpleData) {
        this.simpleData = simpleData;
    }

    public List<T> getData(){
        return data;
    }

    public void setData(List<T> data){
        this.data = data;
    }

    public int getResponseCode(){
        return responseCode;
    }

    public void setResponseCode(int responseCode){
        this.responseCode = responseCode;
    }

    public Throwable getException(){
        return exception;
    }

    public void setException(Throwable e){
        this.exception = e;
    }
}
