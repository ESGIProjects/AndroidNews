package com.esgi.androtopic.Data.Api.Services;

import com.esgi.androtopic.Data.Api.IServiceResultListener;
import com.esgi.androtopic.Data.Model.PostNews;

/**
 * Created by kevin on 28/06/2017.
 */

public interface INewsService {

    void postNews(PostNews pn, IServiceResultListener<Void> isrl);

    void postNews(int i, IServiceResultListener<Void> isrl);

    void delNews(int i, IServiceResultListener<Void> isrl);

    void getNews(IServiceResultListener<Void> isrl);

    void getNews(int i, IServiceResultListener<Void> isrl);

    void putNews(PostNews pn ,int i, IServiceResultListener<Void> isrl);

}
