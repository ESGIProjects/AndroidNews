package com.esgi.androtopic.Data.Model;

import io.realm.RealmObject;

/**
 * Created by Jason Pierna on 19/07/2017.
 */

public class Comments extends RealmObject {

    private String _id, author, title, content, news;

    public Comments(String _id, String author, String title, String content, String news){
        this._id = _id;
        this.author = author;
        this.title = title;
        this.content = content;
        this.news = news;
    }

    public Comments(){

    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }
}
