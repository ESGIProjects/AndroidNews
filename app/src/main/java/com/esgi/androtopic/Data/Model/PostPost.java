package com.esgi.androtopic.Data.Model;

import io.realm.RealmObject;

/**
 * Created by kevin on 28/06/2017.
 */

public class PostPost extends RealmObject{

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

    public PostPost(String title, String content, String date, String topic){
        this.title = title;
        this.content = content;
        this.date = date;
        this.topic = topic;
    }

    public PostPost(){

    }



}
