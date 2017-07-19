package com.esgi.androtopic.Data.Api.Services;

import com.esgi.androtopic.Data.Api.IServiceResultListener;
import com.esgi.androtopic.Data.Model.Posts;

/**
 * Created by kevin on 28/06/2017.
 */

public interface IPostService {

    void postPost(String token, Posts p, IServiceResultListener<Void> isrl);

    void delPost(int i, IServiceResultListener<Void> isrl);

    void getPosts(String token, IServiceResultListener<Void> isrl);

    void getPost(int i, IServiceResultListener<Void> isrl);

    void putPost(Posts p ,int i, IServiceResultListener<Void> isrl);

}
