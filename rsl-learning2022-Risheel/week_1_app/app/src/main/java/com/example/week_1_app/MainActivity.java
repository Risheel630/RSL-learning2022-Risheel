package com.example.week_1_app;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logoutIntent();
        viewIntent();
        viewGroupIntent();
    }
    private void logoutIntent()
    {
        final Button logout=findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginActivity.isLogin=false;
                Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
    public void onBackPressed() {
        finishAffinity();
    }
    private void viewIntent()
    {
        final Button logout=findViewById(R.id.view_button);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(MainActivity.this,ViewActivity.class);
                startActivity(intent);
            }
        });
    }
    private void viewGroupIntent()
    {
        final Button logout=findViewById(R.id.view_group);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,ViewGroupActivity.class);
                startActivity(intent);
            }
        });
    }
}
