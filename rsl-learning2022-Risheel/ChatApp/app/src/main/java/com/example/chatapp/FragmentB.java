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

public class FragmentB extends Fragment {
    private Button button_b;
    private TextView text_b;
    private EditText edit_b;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_b,container,false);
        button_b=v.findViewById(R.id.button_b);
        text_b=v.findViewById(R.id.text_b);
        edit_b=v.findViewById(R.id.edit_b);
        button_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence input=edit_b.getText();
                Bundle bundle = new Bundle();
                bundle.putCharSequence("data", input);
                bundle.putInt("fragment",2);
                FragmentDialog fragmentDialog=new FragmentDialog();
                fragmentDialog.setArguments(bundle);
                fragmentDialog.show(getChildFragmentManager(),"Dialog");
            }
        });
        return v;
    }
    public void updateText(CharSequence text){
        text_b.setText(text);
    }

}
