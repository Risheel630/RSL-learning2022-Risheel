package com.example.timerandgame;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static android.content.Context.MODE_PRIVATE;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.util.List;
import java.util.Random;

/**
 * Adapter class to show list of {@link CountryModel}.
 */
public class CountryListAdapter extends Adapter<CountryListAdapter.CountryListHolder> {

    private List<CountryModel> countryModels;
    static Context context;
    public CountryListAdapter(List<CountryModel> countryModels,Context context) {
        this.countryModels = countryModels;
        this.context=context;
    }

    @NonNull
    @Override
    public CountryListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1, parent, false);
        return new CountryListHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryListHolder holder, int position) {
        holder.bindView(countryModels.get(position));

    }

    @Override
    public int getItemCount() {
        return countryModels.size();
    }

    static class CountryListHolder extends ViewHolder {
        public CountryListHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Random rnd = new Random();
                    int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                    MainActivity.linearLayout.setBackgroundColor(color);
                    SharedPreferences sharedPreferences = context.getSharedPreferences("User Data", MODE_PRIVATE);
                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
                    myEdit.putString("color", String.valueOf(color));
                    myEdit.commit();
                }
            });
        }

        public void bindView(CountryModel model) {
            ((TextView) itemView).setText(model.getCountryName());
        }
    }
}
