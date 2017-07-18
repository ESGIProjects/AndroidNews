package com.esgi.androtopic.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.esgi.androtopic.Data.Model.User;
import com.esgi.androtopic.R;
import com.esgi.androtopic.Tools.InternetDetection;
import com.esgi.androtopic.Tools.RealmInstance;

import io.realm.RealmResults;

/**
 * Created by kevin on 22/06/2017.
 */

public class SplashScreenActivity extends Activity {

    private static int TIMER = 2500;
    Intent i;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final SharedPreferences sp = getApplicationContext().getSharedPreferences("settings", Context.MODE_PRIVATE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                RealmResults<User> result = RealmInstance.getRealmInstance(getApplicationContext()).where(User.class)
                        .findAll();
                if(!InternetDetection.isAvailable(getApplicationContext()) && !result.isEmpty()){
                    sp.edit().putBoolean("isOnline",false).apply();
                    i = new Intent(SplashScreenActivity.this, MainActivity.class);
                    Toast.makeText(getApplicationContext(),"There is no internet connection, data are loaded from local database !",Toast.LENGTH_SHORT).show();
                }
                else if(InternetDetection.isAvailable(getApplicationContext()) && !result.isEmpty()){
                    sp.edit().putBoolean("isOnline",true).apply();
                    i = new Intent(SplashScreenActivity.this, MainActivity.class);
                }
                if(result.isEmpty()){
                    i = new Intent(SplashScreenActivity.this, LoginActivity.class);
                }
                startActivity(i);
                overridePendingTransition(R.animator.slide_from_right, R.animator.slide_to_left);
                finish();
            }
        }, TIMER);
    }
}
