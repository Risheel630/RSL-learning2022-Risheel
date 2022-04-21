package com.example.week_1_app;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.*;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    static boolean isLogin=false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setLoginIntent();
    }
    public void onBackPressed() {
        finishAffinity();
    }
    private void setLoginIntent()
    {
        final Button login=findViewById(R.id.login);
        final EditText userName=findViewById(R.id.user_name);
        final EditText Password=findViewById(R.id.password);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               final String name=userName.getText().toString();
               final String password=Password.getText().toString();
                if(TextUtils.isEmpty(name) || TextUtils.isEmpty(password)){
                    Toast.makeText(LoginActivity.this,"Enter name or password", Toast.LENGTH_SHORT).show();
                }
                else if(!name.equals("admin") || !password.equals("admin123"))
                {
                    Toast.makeText(LoginActivity.this,"Enter correct credential", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    isLogin=true;
                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

}
