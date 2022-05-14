package com.example.quizapp;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class QuestionLiveData extends AndroidViewModel implements Response.Listener<String>, Response.ErrorListener {
    private static final String API_URL = "https://raw.githubusercontent.com/tVishal96/sample-english-mcqs/master/db.json";
    private MutableLiveData<List<QuestionModel>> questionLive = new MutableLiveData<>();
    private MutableLiveData<RequestStatus> requestStatusLiveData = new MutableLiveData<>();

    private RequestQueue queue;

    public QuestionLiveData(@NonNull Application application) {
        super(application);
        queue = Volley.newRequestQueue(application);
        requestStatusLiveData.postValue(RequestStatus.IN_PROGRESS);
        fetchQuestion();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        requestStatusLiveData.postValue(RequestStatus.FAILED);
    }

    @Override
    public void onResponse(String response) {
        try {
            List<QuestionModel> countryModels = parseResponse(response);
            questionLive.postValue(countryModels);
            requestStatusLiveData.postValue(RequestStatus.SUCCEEDED);
        } catch (JSONException e) {
            e.printStackTrace();
            requestStatusLiveData.postValue(RequestStatus.FAILED);
        }
    }

    public LiveData<List<QuestionModel>> getQuestionLiveDataList() {
        return questionLive;
    }

    public LiveData<RequestStatus> getRequestStatusLiveData() {
        return requestStatusLiveData;
    }

    private void fetchQuestion() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, API_URL, this, this);
        queue.add(stringRequest);
    }

    public void reFetch() {
        requestStatusLiveData.postValue(RequestStatus.IN_PROGRESS);
        fetchQuestion();
    }

    @NonNull
    private List<QuestionModel> parseResponse(String response) throws JSONException {

        List<QuestionModel> models = new ArrayList<>();
        JSONObject res = new JSONObject(response);
        JSONArray entries = res.optJSONArray("questions");
        if (entries == null) {
            return models;
        }
        for (int i = 0; i < entries.length(); i++) {
            JSONObject obj = (JSONObject) entries.get(i);
            String[] option = new String[4];
            JSONArray options = obj.optJSONArray("options");
            Log.d("OKOK", "onErrorResponse: " + options);
            for (int j = 0; j < (options != null ? options.length() : 0); j++) {
                option[j] = options.getString(j);
            }
            String question = obj.optString("question");
            int correctOption = Integer.parseInt(obj.getString("correct_option"));
            QuestionModel model = new QuestionModel(option, correctOption, question, false);
            models.add(model);
        }

        return models;
    }

    public enum RequestStatus {
        IN_PROGRESS,
        FAILED,
        SUCCEEDED
    }
}
