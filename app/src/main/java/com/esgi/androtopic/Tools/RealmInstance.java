package com.esgi.androtopic.Tools;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by kevin on 25/06/2017.
 */

public class RealmInstance {

    static Realm realm = null;

    static RealmConfiguration rc = null;

    public static Realm getRealmInstance(Context context){
        if(realm == null){
            realm.init(context);
            realm = Realm.getDefaultInstance();
            rc = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
            realm.setDefaultConfiguration(rc);
        }
        return realm;
    }



}
