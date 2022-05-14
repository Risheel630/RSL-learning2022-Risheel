package com.example.quizapp;


import android.os.HandlerThread;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.os.Handler;
import android.os.SystemClock;

public class TimerLiveData extends ViewModel {
    private final MutableLiveData<Long> timeData = new MutableLiveData<>(0L);
    private final Handler handler;
    private long startTime;
    private HandlerThread handlerThread;

    public TimerLiveData() {
        super();
        handlerThread = new HandlerThread("Timer Thread");
        handlerThread.start();
        handler = new Handler(handlerThread.getLooper());
    }
    public void terminate(){
        handler.removeCallbacks(tickTime);
    }
    public TimerLiveData(Handler handler) {
        this.handler = handler;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        handler.getLooper().quit();
    }

    public MutableLiveData<Long> getTimeData() {
        return timeData;
    }

    public void startTimer() {
        startTime = SystemClock.elapsedRealtime();
        handler.post(tickTime);
    }
    private Runnable tickTime = new Runnable() {
        @Override
        public void run() {
            timeData.postValue(SystemClock.elapsedRealtime() - startTime);
            handler.postDelayed(tickTime, 100L);
        }
    };
}