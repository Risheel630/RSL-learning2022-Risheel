package com.example.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button time,date,battery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        time=findViewById(R.id.time);
        date=findViewById(R.id.date);
        battery=findViewById(R.id.battery);
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowTimeFragment timeFragment=new ShowTimeFragment();
                FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment,timeFragment,"Time");
                transaction.addToBackStack("Time");
                FragmentManager fragmentManager=getSupportFragmentManager();
               if((getSupportFragmentManager().getBackStackEntryCount() - 1)>0) {
                    String fragmentTag = getSupportFragmentManager().getBackStackEntryAt(getSupportFragmentManager().getBackStackEntryCount() - 1).getName();
                    Toast.makeText(MainActivity.this, fragmentTag + " last fragment",
                            Toast.LENGTH_LONG).show();
                }
                transaction.commit();
            }
        });
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowDateFragment dateFragment=new ShowDateFragment();
                FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment,dateFragment,"Date");
                transaction.addToBackStack("Date");
                FragmentManager fragmentManager=getSupportFragmentManager();
                if((getSupportFragmentManager().getBackStackEntryCount() - 1)>0) {
                    String fragmentTag = getSupportFragmentManager().getBackStackEntryAt(getSupportFragmentManager().getBackStackEntryCount() - 1).getName();
                    Toast.makeText(MainActivity.this, fragmentTag + " last fragment",
                            Toast.LENGTH_LONG).show();
                }
                transaction.commit();
            }
        });
        battery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBatteryFragment batteryFragment=new ShowBatteryFragment();
                FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment,batteryFragment,"Battery");
                FragmentManager fragmentManager=getSupportFragmentManager();
                transaction.addToBackStack("Battery");
                if((getSupportFragmentManager().getBackStackEntryCount() - 1)>0) {
                    String fragmentTag = getSupportFragmentManager().getBackStackEntryAt(getSupportFragmentManager().getBackStackEntryCount() - 1).getName();
                    Toast.makeText(MainActivity.this, fragmentTag + " last fragment",
                            Toast.LENGTH_LONG).show();
                }
                transaction.commit();
            }
        });

    }
}