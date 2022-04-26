package com.example.chatapp;

import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class FragmentDialog extends DialogFragment {
    Button send;
    Button cancel;
    TextView message;
    SendData listener;
    public interface SendData{
        void sendMessage(CharSequence input,int fragment);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_dialog,container,false);
        message=v.findViewById(R.id.message_box);
        send=v.findViewById(R.id.send);
        cancel=v.findViewById(R.id.cancel);
        Bundle bundle=getArguments();
       CharSequence data= bundle.getCharSequence("data");
       int fragment=bundle.getInt("fragment");
       message.setText(data);
       send.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               listener.sendMessage(data,fragment);
               getDialog().dismiss();
           }
       });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });

        return v;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context  instanceof SendData)
        {
            listener=(SendData) context;
        }
        else
        {
            throw new RuntimeException(context.toString()+"implement sendData");
        }
    }
}
