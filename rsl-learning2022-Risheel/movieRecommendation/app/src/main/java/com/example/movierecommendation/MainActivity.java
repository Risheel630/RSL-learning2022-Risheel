package com.example.movierecommendation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageView toggleSwitchButton, logoutButton;
    List<MovieItem> list;
    List<MovieItem> recommendedList;
    RecyclerView recyclerView;
    static boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toggleSwitchButton = findViewById(R.id.imListSwitch);
        logoutButton = findViewById(R.id.imLogout);
        recyclerView = findViewById(R.id.movieRecyclerView);
        list = new ArrayList<>();
        recommendedList = new ArrayList<>();
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest1 = new JsonObjectRequest(Request.Method.GET, "https://api.rss2json.com/v1/api.json?rss_url=https%3A%2F%2Fwww.filmfestivals.com%2Fchannel%2Ffilm%2Fanimation%2Ffeed",
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("items");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject movie = jsonArray.getJSONObject(i);
                        String name = movie.getString("title");
                        JSONArray categories = movie.getJSONArray("categories");
                        String categorie = categories.getString(0);
                        String thumbnail = movie.getString("thumbnail");
                        list.add(new MovieItem(name, categorie, thumbnail, 0));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        JsonObjectRequest jsonObjectRequest2 = new JsonObjectRequest(Request.Method.GET, "https://api.rss2json.com/v1/api.json?rss_url=https%3A%2F%2Fwww.filmfestivals.com%2Ftaxonomy%2Fterm%2F30%252B31%252B32%252B33%252B34%252B35%252B36%252B6774%252B590757%2F0%2Ffeed",
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("items");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject movie = jsonArray.getJSONObject(i);
                        String name = movie.getString("title");
                        JSONArray categories = movie.getJSONArray("categories");
                        String categorie = categories.getString(0);
                        String thumbnail = movie.getString("thumbnail");
                        list.add(new MovieItem(name, categorie, thumbnail, 0));
                    }
                    recyclerView.setAdapter(new MovieRecyclerViewAdaptor(list, MainActivity.this));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(jsonObjectRequest1);
        requestQueue.add(jsonObjectRequest2);
        toggleSwitchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag) {
                    Log.d("MOM", String.valueOf(recommendedList.size()));
                    recyclerView.setAdapter(new MovieRecyclerViewAdaptor(recommendedList, MainActivity.this));
                }
                else recyclerView.setAdapter(new MovieRecyclerViewAdaptor(list, MainActivity.this));
                flag = !flag;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences data = getSharedPreferences("User Data", MODE_PRIVATE);
        for (int i = 0; i < list.size(); i++) {
            Boolean category = data.getBoolean(list.get(i).getMovieCategory(), false);
            if (category) {
                recommendedList.add(list.get(i));
            }
        }
    }
}