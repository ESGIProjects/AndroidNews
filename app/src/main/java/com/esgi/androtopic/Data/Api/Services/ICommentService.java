package com.esgi.androtopic.Data.Api.Services;

import com.esgi.androtopic.Data.Api.IServiceResultListener;
import com.esgi.androtopic.Data.Model.Comments;
import com.esgi.androtopic.Data.Model.PostComment;

import java.util.List;

/**
 * Created by kevin on 28/06/2017.
 */

public interface ICommentService {

    public void postComments(String token, PostComment pc, IServiceResultListener<Void> isrl);

    public void delComments(String token, int i, IServiceResultListener<Void> isrl);

    public void getComments(String token, IServiceResultListener<List<Comments>> isrl);

    public void getComment(String token, int i, IServiceResultListener<Comments> isrl);

    public void putComment(String token, PostComment pc ,int i, IServiceResultListener<Void> isrl);
}
