package com.example.week_1_app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.*;
import android.view.View;
import android.widget.Button;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        int secondsDelayed = 1500;
        new Handler().postDelayed(new Runnable() {
            public void run() {
                if(LoginActivity.isLogin)
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                else
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish();
            }
        }, secondsDelayed );

    }
}
