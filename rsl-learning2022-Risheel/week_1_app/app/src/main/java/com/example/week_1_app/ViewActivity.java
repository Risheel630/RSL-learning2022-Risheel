package com.example.week_1_app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.*;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewActivity extends AppCompatActivity {
    static int rotation=0;
    static boolean imageFlag=true;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        textTransition();
        imageTransition();
    }
    private void textTransition()
    {
        final TextView text=findViewById(R.id.text_view);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s=Integer.toString(rotation);
                if(rotation==0)
                {
                    text.setTextColor(getResources().getColor(R.color.red));
                }
                else if(rotation==1)
                {
                    text.setTextColor(getResources().getColor(R.color.green));
                }
                else
                {
                    text.setTextColor(getResources().getColor(R.color.blue));
                }
                rotation++;
                rotation=(rotation%3);
            }
        });
    }
    private void imageTransition()
    {
        final ImageView image=findViewById(R.id.like);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imageFlag)
                    image.setBackgroundColor(getResources().getColor(R.color.blue));
                else
                    image.setBackgroundColor(getResources().getColor(R.color.grey));
                imageFlag=!imageFlag;
            }
        });
    }
}
