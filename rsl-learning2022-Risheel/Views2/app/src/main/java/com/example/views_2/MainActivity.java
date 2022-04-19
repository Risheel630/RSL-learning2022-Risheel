package com.example.views_2;

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
        task1Function();
        task2Function();
        task3Function();

    }
    protected void task1Function()
    {
        final Button task_1=findViewById(R.id.task_1);
        task_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Task1Activity.class);
                startActivity(intent);
            }
        });
    }
    protected void task2Function()
    {
        final Button task_1=findViewById(R.id.task_2);
        task_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Task2Activity.class);
                startActivity(intent);
            }
        });
    }
    protected void task3Function()
    {
        final Button task_1=findViewById(R.id.task_3);
        task_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Task3Activity.class);
                startActivity(intent);
            }
        });
    }
}