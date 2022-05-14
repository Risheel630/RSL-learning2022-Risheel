package com.example.quizapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.format.DateUtils;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

public class QuestionActivity extends AppCompatActivity {
    private AlertDialog failureDialog;
    private ProgressDialog loadingDialog;
    private QuestionLiveData questionViewModel;
    private TextView time;
    private TimerLiveData timerLiveData;
    private Button submit;
    public static SpannableString currentTime;
    ViewModelProvider viewModelProvider;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_question_list);
        time = findViewById(R.id.time);
        submit = findViewById(R.id.submit);
        viewModelProvider = new ViewModelProvider(
                this, (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()));
        questionViewModel = viewModelProvider.get(QuestionLiveData.class);
        timerLiveData = viewModelProvider.get(TimerLiveData.class);
        timerLiveData.getTimeData().observe(this, this::setTime);
        if (savedInstanceState == null) {
            timerLiveData.startTimer();
        }

        setQuestion();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(QuestionActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Confirmation ");
                builder.setMessage("Are you sure you want to Submit the test?");
                builder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(QuestionActivity.this, ScoreFragment.class);
                                intent.putExtra("time", currentTime);
                                startActivity(intent);
                            }
                        });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    public void setTime(long millis) {

        String timeString = DateUtils.formatElapsedTime(millis / 1000);
        String millisString = String.format(Locale.ROOT, "%03d", millis % 1000);

        SpannableString formatString = new SpannableString(timeString);
        formatString.setSpan(new RelativeSizeSpan(0.2f), timeString.length(), formatString.length(), 0);
        if (time != null) {
            currentTime = formatString;
            time.setText(formatString);
        }
        if (millis >= 600000) {
            timerLiveData.terminate();
            Intent intent = new Intent(QuestionActivity.this, ScoreFragment.class);
            intent.putExtra("time", currentTime);
            startActivity(intent);
        }

    }

    private void setQuestion() {
        questionViewModel.getQuestionLiveDataList().observe(this, new Observer<List<QuestionModel>>() {
            @Override
            public void onChanged(List<QuestionModel> questionModels) {
                handleQuestionList(questionModels);
            }
        });
        questionViewModel.getRequestStatusLiveData().observe(this, new Observer<QuestionLiveData.RequestStatus>() {
            @Override
            public void onChanged(QuestionLiveData.RequestStatus requestStatus) {
                handleRequestStatus(requestStatus);
            }
        });
    }
    private void handleQuestionList(List<QuestionModel> list) {
        QuestionListAdapter adapter = new QuestionListAdapter(list);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setAdapter(adapter);

    }

    private void handleRequestStatus(QuestionLiveData.RequestStatus requestStatus) {
        switch (requestStatus) {
            case IN_PROGRESS:
                showSpinner();
                break;
            case SUCCEEDED:
                hideSpinner();
                break;
            case FAILED:
                showError();
                break;
        }
    }

    private void showSpinner() {
        if (loadingDialog == null) {
            loadingDialog = new ProgressDialog(this);
            loadingDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            loadingDialog.setTitle("Fetching Questions");
            loadingDialog.setMessage("Please wait...");
            loadingDialog.setIndeterminate(true);
            loadingDialog.setCanceledOnTouchOutside(false);
        }
        loadingDialog.show();
    }

    private void hideSpinner() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
    }

    private void showError() {
        hideSpinner();
        if (failureDialog == null) {
            failureDialog = getFailureDialog();
        }
        failureDialog.show();
    }

    private AlertDialog getFailureDialog() {
        return new AlertDialog.Builder(this)
                .setTitle("Question list request failed")
                .setMessage("Question list fetching is failed, do you want to retry?")
                .setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        questionViewModel.reFetch();
                    }
                })
                .setNegativeButton("Close app", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .create();
    }
}
