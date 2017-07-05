package com.esgi.androtopic.Data.Model;

/**
 * Created by kevin on 28/06/2017.
 */

public class PostNews {

    private String title;
    private String content;
    private String date;

    public PostNews(String title, String content, String date){
        this.title = title;
        this.content = content;
        this.date = date;
    }

    public PostNews(String title, String content){
        this.title = title;
        this.content = content;
    }

}
