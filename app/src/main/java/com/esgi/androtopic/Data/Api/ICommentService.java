package com.esgi.androtopic.Data.Api;

import com.esgi.androtopic.Data.Model.PostComment;

/**
 * Created by kevin on 28/06/2017.
 */

public interface ICommentService {

    public void postComments(PostComment pc, IServiceResultListener<Void> isrl);

    public void delComments(int i, IServiceResultListener<Void> isrl);

    public void getComments(IServiceResultListener<Void> isrl);

    public void getComment(int i, IServiceResultListener<Void> isrl);

    public void putComment(PostComment pc ,int i, IServiceResultListener<Void> isrl);
}
