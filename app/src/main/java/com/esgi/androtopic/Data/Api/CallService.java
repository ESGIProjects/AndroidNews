package com.esgi.androtopic.Data.Api;

import com.esgi.androtopic.Data.Model.PostAuth;
import com.esgi.androtopic.Data.Model.PostComment;
import com.esgi.androtopic.Data.Model.PostSubscribe;
import com.esgi.androtopic.Tools.RetrofitInstance;

/**
 * Created by kevin on 29/06/2017.
 */

public class CallService implements IAuthService, ICommentService{

    public void login(PostAuth pa, IServiceResultListener<String> isrl){

    }

    public void sign(PostSubscribe ps, IServiceResultListener<Void> isrl){

    }

    public void postComments(PostComment pc, IServiceResultListener<Void> isrl){

    }

    public void delComments(int i, IServiceResultListener<Void> isrl){

    }

    public void getComments(IServiceResultListener<Void> isrl){

    }

    public void getComment(int i, IServiceResultListener<Void> isrl){

    }

    public void putComment(PostComment pc ,int i, IServiceResultListener<Void> isrl){

    }
}
