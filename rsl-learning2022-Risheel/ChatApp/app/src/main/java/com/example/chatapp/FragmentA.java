package com.example.chatapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentA extends Fragment {
    private Button button_a;
    private TextView text_a;
    private EditText edit_a;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_a,container,false);
        button_a=v.findViewById(R.id.button_a);
        text_a=v.findViewById(R.id.text_a);
        edit_a=v.findViewById(R.id.edit_a);
        button_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence input=edit_a.getText();
                Bundle bundle = new Bundle();
                bundle.putCharSequence("data", input);
                bundle.putInt("fragment",1);
                FragmentDialog fragmentDialog=new FragmentDialog();
                fragmentDialog.setArguments(bundle);
                fragmentDialog.show(getChildFragmentManager(),"Dialog");
            }
        });
        return v;
    }
    public void updateText(CharSequence text){
        text_a.setText(text);
    }

}
