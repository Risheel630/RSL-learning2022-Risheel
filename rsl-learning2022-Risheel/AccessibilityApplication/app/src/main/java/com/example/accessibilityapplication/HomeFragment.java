package com.example.accessibilityapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomeFragment extends Fragment {
    public interface HomeFragmentClickListener {
        void onButtonClicked(int id);
    }

    private HomeFragmentClickListener clickListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        clickListener = (HomeFragmentClickListener) getContext();
        view.findViewById(R.id.talkback_fragment_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onButtonClicked(R.id.talkback_fragment_view);
            }
        });

        view.findViewById(R.id.accessibility_scanner_fragment_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onButtonClicked(R.id.accessibility_scanner_fragment_view);
            }
        });
    }
}
