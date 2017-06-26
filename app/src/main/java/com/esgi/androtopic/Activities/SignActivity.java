package com.esgi.androtopic.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.esgi.androtopic.Data.Api.ApiCall;
import com.esgi.androtopic.Data.Model.PostSubscribe;
import com.esgi.androtopic.R;
import com.esgi.androtopic.Tools.CheckRules;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kevin on 22/06/2017.
 */

public class SignActivity extends AppCompatActivity {

    ProgressDialog pd;
    @BindView(R.id.email) EditText email;
    @BindView(R.id.password) EditText pwd;
    @BindView(R.id.firstname) EditText fn;
    @BindView(R.id.lastname) EditText ln;

    @OnClick(R.id.create) void create(){
        if(CheckRules.isEmailValid(email.getText().toString()) && CheckRules.isPasswordValid(pwd.getText().toString()) &&
                CheckRules.isFirstnameValid(fn.getText().toString()) && CheckRules.isLastnameValid(ln.getText().toString())){
            signPost(email.getText().toString(),pwd.getText().toString(), fn.getText().toString(),
                    ln.getText().toString());
        }
        else{
            Toast.makeText(getApplicationContext(),"Error !", Toast.LENGTH_SHORT).show();
            email.getText().clear();
            pwd.getText().clear();
            fn.getText().clear();
            ln.getText().clear();
        }
    };

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_sign);
        getSupportActionBar().hide();
        ButterKnife.bind(this);
    }

    public void signPost(String email, String password, String firstname, String lastname){
        pd = new ProgressDialog(this,ProgressDialog.STYLE_SPINNER);
        pd.setMessage("Wait...");
        pd.show();
        PostSubscribe ps = new PostSubscribe(email, password, firstname, lastname);
        ApiCall.getRetrofitInstance().sign(ps)
                .enqueue(new Callback<PostSubscribe>() {
                    @Override
                    public void onResponse(Call<PostSubscribe> call, Response<PostSubscribe> response) {
                        Log.i("RESPONSE : ", response.message());
                        if(response.code() == 200){
                            pd.dismiss();
                            Toast.makeText(getApplicationContext(),"The account already exists !",Toast.LENGTH_SHORT).show();
                            clearFields();
                        }
                        else if(response.code() == 201){
                            pd.dismiss();
                            Toast.makeText(getApplicationContext(),"The account is created !",Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(SignActivity.this, LoginActivity.class);
                            startActivity(i);
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Retry !",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<PostSubscribe> call, Throwable t) {
                        pd.dismiss();
                        Log.i("FAILURE : ", "No response from server");
                        Toast.makeText(getApplicationContext(),"Retry after !",Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(SignActivity.this, LoginActivity.class);
        startActivity(i);
    }

    public void clearFields(){
        email.getText().clear();
        pwd.getText().clear();
        fn.getText().clear();
        ln.getText().clear();
    }
}
