package com.esgi.androtopic.Data.Model;

import io.realm.RealmObject;

/**
 * Created by kevin on 18/07/2017.
 */

public class TopicNewsRealm extends RealmObject {

    private String _id;
    private String author;
    private String title;
    private String content;
    private String date;
    private String tag;

    public TopicNewsRealm(String _id, String author, String title, String content, String date, String tag){
        this._id = _id;
        this.author = author;
        this.title = title;
        this.content = content;
        this.date = date;
        this.tag = tag;
    }

    public TopicNewsRealm(){

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTag() { return this.tag; }

    public void setTag(String tag) { this.tag = tag; }
}
