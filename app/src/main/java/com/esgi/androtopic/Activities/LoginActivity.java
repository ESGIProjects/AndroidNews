package com.esgi.androtopic.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.esgi.androtopic.Data.Api.IServiceResultListener;
import com.esgi.androtopic.Data.Api.ServiceResult;
import com.esgi.androtopic.Data.Api.Services.CallService;
import com.esgi.androtopic.Data.Model.PostAuth;
import com.esgi.androtopic.Data.Model.User;
import com.esgi.androtopic.R;
import com.esgi.androtopic.Tools.ApiCall;
import com.esgi.androtopic.Tools.CheckRules;
import com.esgi.androtopic.Tools.InternetDetection;
import com.esgi.androtopic.Tools.RealmInstance;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

public class LoginActivity extends AppCompatActivity {

    ProgressDialog pd;
    User user;
    Realm realm;
    String _id ,firstname, lastname, mail, password, token;

    @BindView(R.id.email) EditText email;
    @BindView(R.id.password) EditText pwd;
    @OnClick(R.id.login) void login(){
        if (InternetDetection.isAvailable(getApplicationContext())) {
            if(CheckRules.isEmailValid(email.getText().toString()) && CheckRules.isPasswordValid(pwd.getText().toString())){
                pd = new ProgressDialog(this,R.style.AppCompatAlertDialogStyle);
                pd.setMessage("Wait...");
                pd.show();
                final PostAuth pa = new PostAuth(email.getText().toString(),pwd.getText().toString());
                CallService.getInstance().login(pa, new IServiceResultListener<String>() {
                    @Override
                    public void onResult(ServiceResult<String> sr) {
                        pd.dismiss();
                        if(sr.getResponseCode() == 200){
                            Toast.makeText(getApplicationContext(),"Success !",Toast.LENGTH_SHORT).show();
                            mail = pa.getEmail();
                            password = pa.getPassword();
                            token = sr.getSimpleData();
                            nextQuery();
                            Intent i = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(i);
                            overridePendingTransition(R.animator.slide_from_right, R.animator.slide_to_left);
                        }
                        else if(sr.getResponseCode() != 0){
                            Toast.makeText(getApplicationContext(),"Retry after !",Toast.LENGTH_SHORT).show();
                            clearFields();
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"No response from server !",Toast.LENGTH_SHORT).show();
                            clearFields();
                        }
                    }
                });
                pd.dismiss();
            }
            else{
                Toast.makeText(getApplicationContext(), "Error !", Toast.LENGTH_SHORT).show();
                clearFields();
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

    public void nextQuery() {
        pd = new ProgressDialog(this, R.style.AppCompatAlertDialogStyle);
        pd.setMessage("Update database...");
        pd.show();
        CallService.getInstance().getUser("Bearer " + token, new IServiceResultListener<User>() {
            @Override
            public void onResult(ServiceResult<User> sr) {
                if (sr.getResponseCode() == 200) {
                    Toast.makeText(getApplicationContext(), "Success !", Toast.LENGTH_SHORT).show();
                    _id = sr.getUser().getId();
                    firstname = sr.getUser().getFirstname();
                    lastname = sr.getUser().getLastname();
                    realmQuery();
                    pd.dismiss();
                }
            }
        });
    }

    public void realmQuery(){

        realm = RealmInstance.getRealmInstance(getApplicationContext());

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                user = realm.createObject(User.class);
                user.setId(_id);
                user.setFirstname(firstname);
                user.setLastname(lastname);
                user.setEmail(mail);
                user.setPassword(password);
                user.setToken(token);
            }
        });
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }

    public void clearFields(){
        CheckRules.clearField(email);
        CheckRules.clearField(pwd);
    }
}