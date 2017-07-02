package com.esgi.androtopic.Data.Api.Services;

import com.esgi.androtopic.Data.Api.IServiceResultListener;
import com.esgi.androtopic.Data.Model.PostTopic;

/**
 * Created by kevin on 28/06/2017.
 */

public interface ITopicService {

    void postTopic(String token, PostTopic pt, IServiceResultListener<Void> isrl);

    void delTopic(int i, IServiceResultListener<Void> isrl);

    void getTopics(IServiceResultListener<Void> isrl);

    void getTopic(int i, IServiceResultListener<Void> isrl);

    void putTopic(PostTopic pt, IServiceResultListener<Void> isrl);

}
