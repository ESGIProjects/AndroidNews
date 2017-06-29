package com.esgi.androtopic.Data.Api;

/**
 * Created by kevin on 28/06/2017.
 */

public interface IServiceResultListener<T> {
    void onResult(ServiceResult<T> sr);
}
