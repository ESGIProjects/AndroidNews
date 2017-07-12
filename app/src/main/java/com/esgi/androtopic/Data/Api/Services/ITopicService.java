package com.esgi.androtopic.Data.Api.Services;

import com.esgi.androtopic.Data.Api.IServiceResultListener;
import com.esgi.androtopic.Data.Model.PostTopic;
import com.esgi.androtopic.Data.Model.Topics;

/**
 * Created by kevin on 28/06/2017.
 */

public interface ITopicService {

    void postTopic(String token, PostTopic pt, IServiceResultListener<Void> isrl);

    void delTopic(String token, String id, IServiceResultListener<Void> isrl);

    void getTopics(String token, IServiceResultListener<Topics> isrl);

    void getTopic(int i, IServiceResultListener<Void> isrl);

    void putTopic(String token,PostTopic pn ,String id, IServiceResultListener<Void> isrl);

}
