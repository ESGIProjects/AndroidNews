package com.esgi.androtopic.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.esgi.androtopic.Data.Api.ApiCall;
import com.esgi.androtopic.Data.Model.PostAuth;
import com.esgi.androtopic.Data.Model.User;
import com.esgi.androtopic.R;
import com.esgi.androtopic.Tools.CheckRules;
import com.esgi.androtopic.Tools.InternetDetection;
import com.esgi.androtopic.Tools.RealmInstance;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    ProgressDialog pd;
    User user;
    Realm realm;
    @BindView(R.id.email) EditText email;
    @BindView(R.id.password) EditText pwd;
    @OnClick(R.id.login) void login(){
        if (InternetDetection.isAvailable(getApplicationContext())) {
            if(CheckRules.isEmailValid(email.getText().toString()) && CheckRules.isPasswordValid(pwd.getText().toString())){
                loginPost(email.getText().toString(), pwd.getText().toString());
            }
            else{
                Toast.makeText(getApplicationContext(), "Error !", Toast.LENGTH_SHORT).show();
                email.getText().clear();
                pwd.getText().clear();
            }
        } else {
            Toast.makeText(getApplicationContext(),"There is no connection !",Toast.LENGTH_SHORT).show();
        }
    }
    @OnClick(R.id.sign) void sign(){
        Intent i = new Intent(LoginActivity.this, SignActivity.class);
        startActivity(i);
        overridePendingTransition(R.animator.slide_from_right, R.animator.slide_to_left);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        ButterKnife.bind(this);
    }

    public void loginPost(final String email, final String password){
        pd = new ProgressDialog(this,ProgressDialog.STYLE_SPINNER);
        pd.setMessage("Wait...");
        pd.show();
        PostAuth pa = new PostAuth(email,password);
        ApiCall.getRetrofitInstance().login(pa).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                pd.dismiss();
                Log.i("RESPONSE : ", response.message());
                if(response.code() == 200){
                    Log.i("TOKEN : ", response.body());
                    realmQuery(email,password,response.body());
                    Toast.makeText(getApplicationContext(),"Success !",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                    overridePendingTransition(R.animator.slide_from_right, R.animator.slide_to_left);
                }
                else{
                    Toast.makeText(getApplicationContext(),"There is an error, retry !",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                pd.dismiss();
                Log.i("FAILURE : ", "No response from server");
                Log.i("CAUSE : ", t.getMessage().toString());
                Toast.makeText(getApplicationContext(),"Retry after !",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void realmQuery(final String email, final String password, final String token) {
        pd = new ProgressDialog(this, ProgressDialog.STYLE_SPINNER);
        pd.setMessage("Update database...");
        pd.show();
        realm = RealmInstance.getRealmInstance(getApplicationContext());

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                user = realm.createObject(User.class);
                user.setEmail(email);
                user.setPassword(password);
                user.setToken(token);
                pd.dismiss();
            }
        });
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}

