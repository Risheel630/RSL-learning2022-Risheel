package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.List;

public class QuestionListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public  static List<QuestionModel> questionList;

    public QuestionListAdapter(List<QuestionModel> questionList) {
        this.questionList = questionList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new QuestionFeed(LayoutInflater.from(parent.getContext()).inflate(R.layout.question_list_view, parent, false));
    }
     public void dataChange(){
        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        QuestionModel questionModel=questionList.get(position);
        ((QuestionFeed)holder).setData(questionModel);
        int pos= holder.getAbsoluteAdapterPosition();
        ((QuestionFeed)holder).question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(),AnsweredActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("questionObj", questionList.get(pos));
                bundle.putInt("pos",pos);
                intent.putExtras(bundle);
            }
        });
        ((QuestionFeed) holder).bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(questionList.get(pos).isBookmarked()){
                    ((QuestionFeed) holder).bookmark.setImageResource(R.drawable.ic_baseline_bookmark_border_24);
                    questionList.get(pos).setBookmarked(false);
                }
                else
                {
                    ((QuestionFeed) holder).bookmark.setImageResource(R.drawable.ic_baseline_bookmark_24);
                    questionList.get(pos).setBookmarked(true);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }


    static class QuestionFeed extends RecyclerView.ViewHolder {
        private TextView question;
        private ImageView bookmark, answered;

        public QuestionFeed(@NonNull View itemView) {
            super(itemView);
            question = itemView.findViewById(R.id.question_list);
            bookmark = itemView.findViewById(R.id.bookmark);
            answered = itemView.findViewById(R.id.answered);
        }
        void setData(QuestionModel questionModel) {
            question.setText(String.valueOf(questionModel.getQuestion()));
            if (questionModel.isBookmarked()) {
                bookmark.setImageResource(R.drawable.ic_baseline_bookmark_24);
            }
            if (questionModel.getUserSelectedOption() != -1) {
                answered.setImageResource(R.drawable.ic_baseline_check_box_24);
            }
        }
    }
}
