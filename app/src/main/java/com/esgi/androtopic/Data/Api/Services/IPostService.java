package com.esgi.androtopic.Data.Api.Services;

import com.esgi.androtopic.Data.Api.IServiceResultListener;
import com.esgi.androtopic.Data.Model.PostPost;

/**
 * Created by kevin on 28/06/2017.
 */

public interface IPostService {

    void postPost(String token, PostPost pp, IServiceResultListener<Void> isrl);

    void delPost(int i, IServiceResultListener<Void> isrl);

    void getPosts(IServiceResultListener<Void> isrl);

    void getPost(int i, IServiceResultListener<Void> isrl);

    void putPost(PostPost pp ,int i, IServiceResultListener<Void> isrl);

}
