package com.example.quizapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class AnsweredActivity extends AppCompatActivity {
    RadioButton[] option = new RadioButton[4];
    RadioGroup radioGroup;
    TextView question;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_view);
        question = findViewById(R.id.question);
        option[0] = findViewById(R.id.radio0);
        option[1] = findViewById(R.id.radio1);
        option[2] = findViewById(R.id.radio2);
        option[3] = findViewById(R.id.radio3);
        radioGroup = findViewById(R.id.radioGroup1);
        Bundle bundle = this.getIntent().getExtras();
        int pos = bundle.getInt("pos");
        QuestionModel questionModel = QuestionListAdapter.questionList.get(pos);
        question.setText(questionModel.getQuestion());
        for (int i = 0; i < 4; i++) {
            option[i].setText(questionModel.getOptions()[i]);
        }
        if (questionModel.getUserSelectedOption() != -1) {

            if (questionModel.getUserSelectedOption() == 0) {
                radioGroup.check(R.id.radio0);
            } else if (questionModel.getUserSelectedOption() == 1) {
                radioGroup.check(R.id.radio1);
            } else if (questionModel.getUserSelectedOption() == 2) {
                radioGroup.check(R.id.radio2);
            } else {
                radioGroup.check(R.id.radio3);
            }
        }
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (R.id.radio0 == i)
                    QuestionListAdapter.questionList.get(pos).setUserSelectedOption(0);
                else if (R.id.radio1 == i)
                    QuestionListAdapter.questionList.get(pos).setUserSelectedOption(1);
                else if (R.id.radio2 == i)
                    QuestionListAdapter.questionList.get(pos).setUserSelectedOption(2);
                else
                    QuestionListAdapter.questionList.get(pos).setUserSelectedOption(3);
            }
        });


    }
    

}
