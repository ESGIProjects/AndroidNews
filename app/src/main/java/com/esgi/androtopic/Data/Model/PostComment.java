package com.esgi.androtopic.Data.Model;

/**
 * Created by kevin on 28/06/2017.
 */

public class PostComment {

    private String title;
    private String content;
    private String news;
    private String date;

    public PostComment(String title, String content, String news, String date){
        this.title = title;
        this.content = content;
        this.news = news;
        this.date = date;
    }
}
