package com.example.recyclerview;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<Type> list;
    OnItemClickListener onItemClickListener;

    public Adapter(List<Type> list, OnItemClickListener onItemClickListener) {
        this.list = list;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==0)
        {
            Log.d("BIG", "onCreateViewHolder: ");
            return new TypeA(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_type_a,parent,false),onItemClickListener);
        }
       else {

           return new TypeB(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_type_b,parent,false),onItemClickListener);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position)==0)
        {
            TypeAFeed typeAFeed=(TypeAFeed) list.get(position).getObject();
            ((TypeA) holder).setTypeADate(typeAFeed);
        }
        else
        {
            TypeBFeed typeBFeed=(TypeBFeed) list.get(position).getObject();
            ((TypeB) holder).setTypeBDate(typeBFeed);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getFeedType();
    }


    static class TypeA extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView name,followers,contribute;
        OnItemClickListener onItemClickListener;
        @Override
        public void onClick(View view) {
            onItemClickListener.onItemClicked(getBindingAdapterPosition());
        }

        public TypeA(@NonNull View itemView,OnItemClickListener onItemClickListener ) {
            super(itemView);
            name=itemView.findViewById(R.id.name_a);
            followers=itemView.findViewById(R.id.followers_a);
            contribute=itemView.findViewById(R.id.contribution_a);
            this.onItemClickListener=onItemClickListener;
            itemView.setOnClickListener(this);
        }
        void setTypeADate(TypeAFeed typeA)
        {
            name.setText(typeA.getName());
            followers.setText(String.valueOf(typeA.getFollowers()));
            contribute.setText(String.valueOf(typeA.getContributions()));
        }
    }
    static class TypeB extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView name,followers,contribute,location;
        OnItemClickListener onItemClickListener;
        public TypeB(@NonNull View itemView,OnItemClickListener onItemClickListener) {
            super(itemView);
            name=itemView.findViewById(R.id.name_b);
            followers=itemView.findViewById(R.id.followers_b);
            contribute=itemView.findViewById(R.id.contribution_b);
            location=itemView.findViewById(R.id.location_b);
            this.onItemClickListener=onItemClickListener;
            itemView.setOnClickListener(this);
        }
        void setTypeBDate(TypeBFeed typeB)
        {
            name.setText(typeB.getName());
            followers.setText(String.valueOf(typeB.getFollowers()));
            contribute.setText(String.valueOf(typeB.getContributions()));
            location.setText(typeB.getLocation());
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onItemClicked(getBindingAdapterPosition());
        }
    }
    interface OnItemClickListener {
        void onItemClicked(int position);
    }
}
