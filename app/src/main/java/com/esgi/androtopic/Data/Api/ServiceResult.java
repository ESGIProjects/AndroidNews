package com.esgi.androtopic.Data.Api;

/**
 * Created by kevin on 28/06/2017.
 */

public class ServiceResult<T> {
    T data;
    int responseCode;
    Throwable exception;

    public T getData(){
        return data;
    }

    public void setData(T data){
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
