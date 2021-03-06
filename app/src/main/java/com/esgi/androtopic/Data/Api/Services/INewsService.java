package com.esgi.androtopic.Data.Api.Services;

import com.esgi.androtopic.Data.Api.IServiceResultListener;
import com.esgi.androtopic.Data.Model.News;
import com.esgi.androtopic.Data.Model.PostNews;

/**
 * Created by kevin on 28/06/2017.
 */

public interface INewsService {

    void postNews(String token ,PostNews pn, IServiceResultListener<Void> isrl);

    void postNews(int i, IServiceResultListener<Void> isrl);

    void delNews(String token, String id, IServiceResultListener<Void> isrl);

    void getNews(String token, IServiceResultListener<News> isrl);

    void getNews(int i, IServiceResultListener<Void> isrl);

    void putNews(String token,PostNews pn ,String id, IServiceResultListener<Void> isrl);

}
