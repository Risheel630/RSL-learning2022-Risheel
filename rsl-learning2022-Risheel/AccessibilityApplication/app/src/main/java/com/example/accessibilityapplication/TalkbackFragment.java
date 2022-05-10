package com.example.accessibilityapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TalkbackFragment extends Fragment {
    private int count = 0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_talkback, container, false);
        View container_view = rootView.findViewById(R.id.container_3);
        final TextView counterDescriptionView = container_view.findViewById(R.id.counter_description);
        container_view.findViewById(R.id.counter).setOnClickListener(new View.OnClickListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View view) {
                counterDescriptionView.setText(String.format("Replay %d times", ++count));
            }
        });
        return rootView;
    }
}
