package com.esgi.androtopic.Data.Model;

import io.realm.RealmObject;

/**
 * Created by kevin on 28/06/2017.
 */

public class Posts extends RealmObject{

    private String _id;

    private String author;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String get_id(){ return _id;}

    public void set_id(String _id){ this._id = _id; }

    private String title;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private String content;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private String date;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    private String topic;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Posts(String _id, String title, String content, String date, String topic){
        this._id = _id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.topic = topic;
    }

    public Posts(){

    }



}
