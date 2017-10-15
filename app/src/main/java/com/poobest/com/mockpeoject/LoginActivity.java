package com.poobest.com.mockpeoject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.poobest.com.mockpeoject.dashboard.DashBoardActivity;

/**
 * Created by RUNGNUENG on 15/10/2560.
 */

public class LoginActivity extends AppCompatActivity{

    LoginButton login_facebook;
    Button skip_login;
    CallbackManager callbackManager;
    TextView txtStatus;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initInstance();
        loginWithFB();

    }

    private void initInstance() {

        callbackManager = CallbackManager.Factory.create();
        login_facebook =  findViewById(R.id.login_facebook);
        skip_login =  findViewById(R.id.skip_login);
        txtStatus =  findViewById(R.id.txtStatus);
    }

    private void loginWithFB(){

        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(getBaseContext(),"success",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, DashBoardActivity.class));
                finish();
            }

            @Override
            public void onCancel() {
                txtStatus.setText("Login Canceled");
            }

            @Override
            public void onError(FacebookException e) {
                txtStatus.setText("Login Error");
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void SkipLogin(View view){
        Intent intent = new Intent(this,DashBoardActivity.class);
        startActivity(intent);
        finish();
    }
}
