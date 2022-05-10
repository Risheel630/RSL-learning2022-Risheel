package com.example.accessibilityapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements HomeFragment.HomeFragmentClickListener {
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.my_nav_host_fragment, new HomeFragment());
        fragmentTransaction.commit();
    }

    @Override
    public void onButtonClicked(int id) {
        Fragment fragment = new Fragment();
        switch (id) {
            case R.id.talkback_fragment_view:
                fragment = new TalkbackFragment();
                break;
            case R.id.accessibility_scanner_fragment_view:
                fragment = new AccessibilityScannerFragment();
                break;
            default:
                break;
        }

        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.my_nav_host_fragment, fragment);
        fragmentTransaction.commit();
    }
}
