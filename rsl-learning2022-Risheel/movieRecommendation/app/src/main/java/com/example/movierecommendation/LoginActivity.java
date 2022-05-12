package com.example.movierecommendation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText loginEmail , loginPassword;
    Button registerButton , loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginEmail = findViewById(R.id.etLoginEmail);
        loginPassword = findViewById(R.id.etLoginPassword);
        registerButton = findViewById(R.id.registerButton);
        loginButton = findViewById(R.id.loginBtn);
        databaseHelper db = new databaseHelper(this);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              String tempMail =   loginEmail.getText().toString().trim();
              String tempPass = loginPassword.getText().toString().trim();
              if(!tempMail.isEmpty() && !tempPass.isEmpty()){
                   if(db.checkUser(new UserModel(tempMail,tempPass))){
                       if (db.checkUserAndPassword(new UserModel(tempMail,tempPass))){
                           Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                           startActivity(intent);
                           SharedPreferences sharedPreferences=getSharedPreferences("User Data",MODE_PRIVATE);
                           SharedPreferences.Editor editor=sharedPreferences.edit();
                           editor.putString("email",tempMail);
                           editor.putString("password",tempPass);
                           editor.commit();
                       }
                       else
                       {
                           Toast.makeText(LoginActivity.this, "Enter Correct Password", Toast.LENGTH_SHORT).show();
                       }
                   }
                   else{
                       Toast.makeText(LoginActivity.this, "User Not Found", Toast.LENGTH_SHORT).show();
                   }

              }
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences data = getSharedPreferences("User Data", MODE_PRIVATE);
        String email=data.getString("email","");
        String password=data.getString("password","");
        if(!email.equals("") && !password.equals("")){
            loginEmail.setText(email);
            loginPassword.setText(password);
        }
    }
}