package com.esgi.androtopic.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.esgi.androtopic.Data.Api.IServiceResultListener;
import com.esgi.androtopic.Data.Api.ServiceResult;
import com.esgi.androtopic.Data.Api.Services.CallService;
import com.esgi.androtopic.Data.Model.PostSubscribe;
import com.esgi.androtopic.R;
import com.esgi.androtopic.Tools.CheckRules;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kevin on 22/06/2017.
 */

public class SignActivity extends AppCompatActivity {

    ProgressDialog pd;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.password)
    EditText pwd;
    @BindView(R.id.firstname)
    EditText fn;
    @BindView(R.id.lastname)
    EditText ln;

    @OnClick(R.id.create)
    void create() {
        if (CheckRules.isEmailValid(email.getText().toString()) && CheckRules.isPasswordValid(pwd.getText().toString()) &&
                CheckRules.isFirstnameValid(fn.getText().toString()) && CheckRules.isLastnameValid(ln.getText().toString())) {
            pd = new ProgressDialog(this, ProgressDialog.STYLE_SPINNER);
            pd.setMessage("Wait...");
            pd.show();
            PostSubscribe ps = new PostSubscribe(email.getText().toString(), pwd.getText().toString(),
                    fn.getText().toString(), ln.getText().toString());
            CallService.getInstance().sign(ps, new IServiceResultListener<Void>() {
                @Override
                public void onResult(ServiceResult<Void> sr) {
                    pd.dismiss();
                    if (sr.getResponseCode() == 200) {
                        clearFields();
                        Toast.makeText(getApplicationContext(), "The account already exists !", Toast.LENGTH_SHORT).show();
                    }
                    else if (sr.getResponseCode() == 201) {
                        Toast.makeText(getApplicationContext(), "The account is created !", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(SignActivity.this, LoginActivity.class);
                        startActivity(i);
                        overridePendingTransition(R.animator.slide_from_left, R.animator.slide_to_right);
                    }
                    else if (sr.getResponseCode() != 0){
                        Toast.makeText(getApplicationContext(), "Retry after !", Toast.LENGTH_SHORT).show();
                        clearFields();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "No response from server !", Toast.LENGTH_SHORT).show();
                        clearFields();
                    }
                }
            });
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_sign);
        getSupportActionBar().hide();
        ButterKnife.bind(this);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(SignActivity.this, LoginActivity.class);
        startActivity(i);
        overridePendingTransition(R.animator.slide_from_left, R.animator.slide_to_right);
    }

    public void clearFields() {
        email.getText().clear();
        pwd.getText().clear();
        fn.getText().clear();
        ln.getText().clear();
    }
}
