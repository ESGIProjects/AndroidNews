package com.esgi.androtopic.Tools;

import com.esgi.androtopic.Data.Model.Comments;
import com.esgi.androtopic.Data.Model.News;
import com.esgi.androtopic.Data.Model.Posts;
import com.esgi.androtopic.Data.Model.Topics;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by kevin on 19/07/2017.
 */

public class RealmQuery {

    static News news;
    static Topics topics;
    static Posts posts;
    static Comments comments;

    public static void addNewsDatabase(final Realm realm,List<News> list){

        for(final News n : list){
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm bgRealm) {
                    news = realm.createObject(News.class);
                    news.set_id(n.get_id());
                    news.setAuthor(n.getAuthor());
                    news.setTitle(n.getTitle());
                    news.setContent(n.getContent());
                    //tnr.setDate(n.getDate());
                }
            });
        }
    }

    public static void addTopicsDatabase(final Realm realm,List<Topics> list){

        for(final Topics t : list){
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm bgRealm) {
                    topics = realm.createObject(Topics.class);
                    topics.set_id(t.get_id());
                    topics.setAuthor(t.getAuthor());
                    topics.setTitle(t.getTitle());
                    topics.setContent(t.getContent());
                    //tnr.setDate(n.getDate());
                }
            });
        }
    }

    public static void addPostsDatabase(final Realm realm,List<Posts> list){

        for(final Posts p : list){
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm bgRealm) {
                    posts = realm.createObject(Posts.class);
                    posts.set_id(p.get_id());
                    posts.setAuthor(p.getAuthor());
                    posts.setTitle(p.getTitle());
                    posts.setContent(p.getContent());
                    //tnr.setDate(n.getDate());
                }
            });
        }
    }

    public static void addCommentsDatabase(final Realm realm,List<Comments> list){

        for(final Comments c : list){
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm bgRealm) {
                    comments = realm.createObject(Comments.class);
                    comments.set_id(c.get_id());
                    comments.setAuthor(c.getAuthor());
                    comments.setTitle(c.getTitle());
                    comments.setContent(c.getContent());
                    //tnr.setDate(n.getDate());
                }
            });
        }
    }

    public static List<News> getNewsDatabase(RealmResults<News> results){
        List<News> list = new ArrayList<>();
        for(News news : results){
            list.add(news);
        }
        return list;
    }

    public static List<Topics> getTopicsDatabase(RealmResults<Topics> results){
        List<Topics> list = new ArrayList<>();
        for(Topics t : results){
            list.add(t);
        }
        return list;
    }

    public static List<Posts> getPostsDatabase(RealmResults<Posts> results){
        List<Posts> list = new ArrayList<>();
        for(Posts p : results){
            list.add(p);
        }
        return list;
    }

    public static List<Comments> getCommentsDatabase(RealmResults<Comments> results){
        List<Comments> list = new ArrayList<>();
        for(Comments c : results){
            list.add(c);
        }
        return list;
    }

    public static void deleteNews(Realm realm){
        RealmResults<News> results = realm.where(News.class).findAll();
        realm.beginTransaction();
        results.deleteAllFromRealm();
        realm.commitTransaction();
    }

    public static void deleteTopics(Realm realm){
        RealmResults<Topics> results = realm.where(Topics.class).findAll();
        realm.beginTransaction();
        results.deleteAllFromRealm();
        realm.commitTransaction();
    }

    public static void deletePosts(Realm realm){
        RealmResults<Posts> results = realm.where(Posts.class).findAll();
        realm.beginTransaction();
        results.deleteAllFromRealm();
        realm.commitTransaction();
    }

    public static void deleteComments(Realm realm){
        RealmResults<Comments> results = realm.where(Comments.class).findAll();
        realm.beginTransaction();
        results.deleteAllFromRealm();
        realm.commitTransaction();
    }

}
