package com.example.timerandgame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.format.DateUtils;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;
import com.example.timerandgame.CountryListViewModel.RequestStatus;
public class MainActivity extends AppCompatActivity {

    private AlertDialog failureDialog;
    private ProgressDialog loadingDialog;
    private CountryListViewModel viewModel;
    private TimerViewModel timerViewModel;
    private Button start,stop;
    private TextView time;
    public static LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start=findViewById(R.id.start);
        stop=findViewById(R.id.stop);
        time=findViewById(R.id.time_text);
        linearLayout=(LinearLayout) findViewById(R.id.main_layout);
        ViewModelProvider viewModelProvider = new ViewModelProvider(
                this, (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()));
        viewModel = viewModelProvider.get(CountryListViewModel.class);
        timerViewModel=viewModelProvider.get(TimerViewModel.class);
        timerViewModel.getTimeData().observe(this,this::setTime);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timerViewModel.startTimer();
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timerViewModel.stopTimer();
            }
        });
        setUpLiveData();
    }
    public void setTime(long millis){

        String timeString = DateUtils.formatElapsedTime(millis/1000);
        String millisString=String.format(Locale.ROOT,"%03d",millis%1000);

        SpannableString formatString=new SpannableString(timeString+millisString);
        formatString.setSpan(new RelativeSizeSpan(0.5f),timeString.length(),formatString.length(),0);
        Log.d("OKOKOK", "setTime: "+formatString);
        if(time!=null)
        {
            time.setText(formatString);
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (failureDialog != null) {
            failureDialog.dismiss();
        }
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
    }

    private void setUpLiveData() {
        viewModel.getCountryLiveData().observe(this, new Observer<List<CountryModel>>() {
            @Override
            public void onChanged(List<CountryModel> countryModels) {
                handleCountryList(countryModels);
            }
        });
        viewModel.getRequestStatusLiveData().observe(this, new Observer<RequestStatus>() {
            @Override
            public void onChanged(RequestStatus requestStatus) {
                handleRequestStatus(requestStatus);
            }
        });
    }

    private void handleCountryList(List<CountryModel> countryModels) {
        CountryListAdapter adapter = new CountryListAdapter(countryModels,this);
        RecyclerView countryRecyclerView = findViewById(R.id.gamesRecyclerView);
        countryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        countryRecyclerView.setAdapter(adapter);
    }

    private void handleRequestStatus(RequestStatus requestStatus) {
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
            loadingDialog.setTitle("Fetching games");
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
                .setTitle("Country list request failed")
                .setMessage("Country list fetching is failed, do you want to retry?")
                .setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        viewModel.refetchCountry();
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

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences data = getSharedPreferences("User Data", MODE_PRIVATE);
        String color=data.getString("color","0xFFFFFF");
        linearLayout.setBackgroundColor(Integer.valueOf(color));
    }
}