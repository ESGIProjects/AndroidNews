package com.esgi.androtopic.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.esgi.androtopic.Data.Model.User;
import com.esgi.androtopic.R;
import com.esgi.androtopic.Tools.RealmInstance;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by kevin on 22/06/2017.
 */

public class SplashScreenActivity extends Activity {

    private static int TIMER = 2500;
    private Realm realm;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                realm = RealmInstance.getRealmInstance(getApplicationContext());
                RealmResults<User> result = realm.where(User.class)
                        .findAll();
                if(result.isEmpty()){
                    Intent i = new Intent(SplashScreenActivity.this, LoginActivity.class);
                    startActivity(i);
                    overridePendingTransition(R.animator.slide_from_right, R.animator.slide_to_left);
                    finish();
                }
                else{
                    Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(i);
                    overridePendingTransition(R.animator.slide_from_right, R.animator.slide_to_left);
                    finish();
                }
            }
        }, TIMER);
    }
}
