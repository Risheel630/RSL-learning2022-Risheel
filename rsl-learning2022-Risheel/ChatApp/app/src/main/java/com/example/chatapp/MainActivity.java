package com.example.chatapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements FragmentDialog.SendData{
    private FragmentA fragmentA;
    private FragmentB fragmentB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentA=new FragmentA();
        fragmentB=new FragmentB();
        FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();
        fragmentTransaction
                .replace(R.id.container_a,fragmentA)
                .replace(R.id.container_b,fragmentB)
                .commit();
    }

    public void sendMessage(CharSequence input,int fragment) {
        if (fragment == 1) {
            fragmentB.updateText(input);
        } else {
            fragmentA.updateText(input);
        }
    }

}