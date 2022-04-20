package com.example.view1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textFunction();
        editFunction();
        imageFunction();
    }
    protected void textFunction()
    {
        final Button textButton=findViewById(R.id.text_view);
        textButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,TextActivity.class);
                startActivity(intent);
            }
        });
    }
    protected void editFunction()
    {
        final Button textButton=findViewById(R.id.edit_view);
        textButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,EditActivity.class);
                startActivity(intent);
            }
        });
    }
    protected void imageFunction()
    {
        final Button textButton=findViewById(R.id.image_view);
        textButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,ImageActivity.class);
                startActivity(intent);
            }
        });
    }

}