package com.esgi.androtopic.Tools;

import com.esgi.androtopic.Data.Model.News;
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

}
