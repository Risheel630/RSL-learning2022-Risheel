package com.example.wifiindicator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.LevelListDrawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private Button add,remove,turnOff;
    private LevelListDrawable levelListDrawable;
    private static int level=5;
    private static boolean wifiStatus=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add=findViewById(R.id.add);
        remove=findViewById(R.id.remove);
        turnOff=findViewById(R.id.off);
        imageView=(ImageView) findViewById(R.id.wifi);
        if(savedInstanceState!=null)
        {
            level=savedInstanceState.getInt("level");
            wifiStatus=savedInstanceState.getBoolean("status");
            imageView.setImageLevel(level);
        }
        else
        {
            imageView.setImageLevel(5);
        }

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(level==5)
                {
                    Toast.makeText(MainActivity.this,"Please turn on wifi",Toast.LENGTH_SHORT).show();
                }
                else if(level==4)
                {
                    Toast.makeText(MainActivity.this,"This is max level",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    level++;
                    imageView.setImageLevel(level);
                }
            }
        });
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(level==5)
                {
                    Toast.makeText(MainActivity.this,"Please turn on wifi",Toast.LENGTH_SHORT).show();
                }
                else if(level==0)
                {
                    Toast.makeText(MainActivity.this,"This is min level",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    level--;
                    imageView.setImageLevel(level);
                }
            }
        });
        turnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(wifiStatus)
                {
                    imageView.setImageLevel(5);
                    level=5;
                }
                else{
                    imageView.setImageLevel(0);
                    level=0;
                }
                wifiStatus=!wifiStatus;
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("level",level);
        outState.putBoolean("status",wifiStatus);
    }
}