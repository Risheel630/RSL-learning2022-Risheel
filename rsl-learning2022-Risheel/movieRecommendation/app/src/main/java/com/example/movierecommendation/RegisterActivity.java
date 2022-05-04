package com.example.movierecommendation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    EditText registerName, registerEmail, registerPassword;
    Button registerButton, loginButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        databaseHelper myDb = new databaseHelper(this);
        registerName = findViewById(R.id.etRegisterName);
        registerEmail = findViewById(R.id.etRegisterEmail);
        registerPassword = findViewById(R.id.etRegisterPassword);
        registerButton = findViewById(R.id.registerBtn);
        loginButton = findViewById(R.id.login);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!registerName.getText().toString().isEmpty() && !registerEmail.getText().toString().isEmpty()
                        && !registerPassword.getText().toString().isEmpty()) {

                    UserModel user = new UserModel(registerName.getText().toString(), registerEmail.getText().toString(),
                            registerPassword.getText().toString());
                    if (myDb.addUser(user)) {
                        Toast.makeText(RegisterActivity.this, "User Created Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(RegisterActivity.this, "Error Creating User", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
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
}
