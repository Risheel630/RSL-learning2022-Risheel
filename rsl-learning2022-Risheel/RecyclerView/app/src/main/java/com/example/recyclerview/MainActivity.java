package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Adapter.OnItemClickListener {
    private RecyclerView mRecyclerView;
    List<Type> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list=new ArrayList();
        TypeAFeed typeAFeed1=new TypeAFeed("Brian Chan",420,149);
        list.add(new Type(0,typeAFeed1));
        TypeBFeed typeBFeed1=new TypeBFeed("Carlos Toxtli",4340,1249,"United States,Mexico,Canada");
        list.add(new Type(1,typeBFeed1));
        TypeBFeed typeBFeed2=new TypeBFeed("Bo-Yi Wu",1230,2249,"appleboy");
        list.add(new Type(1,typeBFeed2));
        TypeAFeed typeAFeed3=new TypeAFeed("Mike Penz",340,2129);
        list.add(new Type(0,typeAFeed3));
        TypeBFeed typeBFeed3=new TypeBFeed("Andrey Sitni",406,2349,"Vienna, Austria");
        list.add(new Type(1,typeBFeed3));
        TypeBFeed typeBFeed4=new TypeBFeed("Corey Donohoe",43450,449,"United States");
        list.add(new Type(1,typeBFeed4));
        TypeAFeed typeAFeed5=new TypeAFeed("Katrina Owen",4450,4449);
        list.add(new Type(0,typeAFeed5));
        TypeBFeed typeBFeed5=new TypeBFeed("Brandon Keepers",1200,4559,"Winchester, VA");
        list.add(new Type(1,typeBFeed5));
        TypeBFeed typeBFeed6=new TypeBFeed("Brandon Keepers",1200,4559,"Winchester, VA");
        list.add(new Type(1,typeBFeed6));
        TypeAFeed typeAFeed2=new TypeAFeed("Rich Harris",4340,4329);
        list.add(new Type(0,typeAFeed2));
        TypeAFeed typeAFeed4=new TypeAFeed("Yichun Zhang",340,429);
        list.add(new Type(0,typeAFeed4));
        mRecyclerView=findViewById(R.id.recyclerview);
        mRecyclerView.setAdapter(new Adapter(list,this));

    }

    @Override
    public void onItemClicked(int position) {
        final Type type=list.get(position);
        TypeFeed typeFeed=type.getObject();
        Toast.makeText(MainActivity.this,typeFeed.getName()+" "+"follower: "+typeFeed.getFollowers()+" "+"contribution: "+typeFeed.getContributions(),Toast.LENGTH_SHORT).show();
    }
}