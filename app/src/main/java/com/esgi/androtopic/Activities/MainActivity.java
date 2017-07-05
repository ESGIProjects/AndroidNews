package com.esgi.androtopic.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.esgi.androtopic.Dialogs.AddNews;
import com.esgi.androtopic.Dialogs.AddTopic;
import com.esgi.androtopic.Fragments.NewsFragment;
import com.esgi.androtopic.Fragments.TopicsFragment;
import com.esgi.androtopic.R;
import com.esgi.androtopic.Tools.RealmInstance;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

public class MainActivity extends FragmentActivity {

    BottomNavigationView bnv;
    NewsFragment nf = new NewsFragment();
    TopicsFragment tf = new TopicsFragment();

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.toolbar_title) TextView title;
    @OnClick(R.id.fab) void action(){
        if(bnv.getMenu().getItem(0).isChecked()){
            AddNews an = new AddNews(this);
            an.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            an.show();
        }
        else{
            AddTopic at = new AddTopic(this);
            at.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            at.show();
        }
    }
    @OnClick(R.id.signout) void signout(){
        AlertDialog.Builder ad = new AlertDialog.Builder(this,R.style.AlertDialogCustom);
        ad.setTitle("Sign out")
                .setMessage("Are you sure you want to sign out ?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Realm realm =  RealmInstance.getRealmInstance(getApplicationContext());
                        realm.beginTransaction();
                        realm.deleteAll();
                        realm.commitTransaction();
                        finishAffinity();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setIcon(R.drawable.splash)
                .show();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    displayFragment(tf,nf);
                    return true;
                case R.id.navigation_notifications:
                    displayFragment(nf,tf);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bnv = (BottomNavigationView) findViewById(R.id.navigation);
        bnv.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        ButterKnife.bind(this);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fragment_container,nf);
        ft.add(R.id.fragment_container,tf);
        ft.show(nf);
        ft.hide(tf);
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder ad = new AlertDialog.Builder(this,R.style.AlertDialogCustom);
        ad.setTitle("Quit application")
                .setMessage("Are you sure you want to quit ?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finishAffinity();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setIcon(R.drawable.splash)
                .show();
    }

    public void displayFragment(android.support.v4.app.Fragment hideFragment, android.support.v4.app.Fragment showFragment){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.hide(hideFragment);
        ft.show(showFragment);
        ft.addToBackStack(null);
        ft.commit();
    }
}
