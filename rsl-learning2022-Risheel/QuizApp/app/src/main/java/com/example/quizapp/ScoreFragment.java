package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class ScoreFragment extends AppCompatActivity {
    private Button reStart;
    private TextView time, score;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_activity);
        time = findViewById(R.id.time_log);
        score=findViewById(R.id.score);
        time.setText(QuestionActivity.currentTime);
        reStart = findViewById(R.id.restart);
        int marks=0;
        for(int i=0;i<QuestionListAdapter.questionList.size();i++)
        {
            if(QuestionListAdapter.questionList.get(i).getUserSelectedOption()==QuestionListAdapter.questionList.get(i).getCorrectOption()){
                marks++;
            }
        }
        String scoreString=(marks+"/"+"10");
        score.setText(scoreString);
        reStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ScoreFragment.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
