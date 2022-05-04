package com.example.movierecommendation;



import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//import com.bumptech.glide.Glide;

import com.bumptech.glide.Glide;

import java.util.List;


public class MovieRecyclerViewAdaptor extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    List<MovieItem> movieItemLists;
    OnItemClickListener onItemClickListener;
    Context context;
    int position;

//    private RecyclerViewClickInterface recyclerViewClickInterface;

    public MovieRecyclerViewAdaptor(List<MovieItem> movieItemLists , Context context ){
        this.movieItemLists = movieItemLists;
        this.context = context;
        //this.recyclerViewClickInterface = recyclerViewClickInterface;

    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view;
        view = layoutInflater.inflate(R.layout.movie_list_item , parent , false);
        return new viewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        viewHolder viewHolder =(viewHolder) holder;
        viewHolder.movieName.setText("NAME: "+movieItemLists.get(position).getMovieName());
        Log.d("MOM", movieItemLists.get(position).getMovieName());
        ;
        viewHolder.movieCategory.setText("Category: "+movieItemLists.get(position).getMovieCategory());
        SharedPreferences data = context.getSharedPreferences("User Data", MODE_PRIVATE);
        SharedPreferences.Editor editor=data.edit();
        viewHolder.likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewHolder.likeButton.setColorFilter(R.color.black);
                viewHolder.dislikeButton.setColorFilter(R.color.white);
                MovieItem movie=movieItemLists.get(holder.getAdapterPosition());
                movie.setLike(1);
                editor.putBoolean(movieItemLists.get(holder
                .getAdapterPosition()).getMovieCategory(),true);
                editor.commit();

            }

        });
        viewHolder.dislikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewHolder.dislikeButton.setColorFilter(R.color.black);
                viewHolder.likeButton.setColorFilter(R.color.white);
                MovieItem movie=movieItemLists.get(holder.getAdapterPosition());
                movie.setLike(2);
                editor.remove(movieItemLists.get(holder
                        .getAdapterPosition()).getMovieCategory());

            }

        });
        Glide.with(context).load(movieItemLists.get(position).getMovieThumbUrl()).into(viewHolder.thumbImageView);
        position=holder.getAdapterPosition();

    }

    @Override
    public int getItemCount() {
        return movieItemLists.size();
    }



    class viewHolder extends RecyclerView.ViewHolder{

        TextView movieName;
        TextView movieCategory;
        ImageView thumbImageView , likeButton , dislikeButton;


        public viewHolder(@NonNull View itemView) {
            super(itemView);

            movieName = itemView.findViewById(R.id.tvMovieName);
            movieCategory = itemView.findViewById(R.id.tvMovieCategory);
            thumbImageView = itemView.findViewById(R.id.thumImageview);
            likeButton = itemView.findViewById(R.id.likeImg);
            dislikeButton= itemView.findViewById(R.id.dislikeImg);

//            likeButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    recyclerViewClickInterface.onItemLiked(getAdapterPosition());
//                }
//            });
//            dislikeButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    recyclerViewClickInterface.onItemDisliked(getAdapterPosition());
//                }
//            });


        }
    }
    interface OnItemClickListener {
        void onItemClicked(int position);
    }


}