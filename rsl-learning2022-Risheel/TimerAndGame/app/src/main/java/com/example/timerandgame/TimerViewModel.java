package com.example.timerandgame;

import android.os.HandlerThread;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.os.Handler;
import android.os.SystemClock;

public class TimerViewModel extends ViewModel {
    private final MutableLiveData<Long> timeData=new MutableLiveData<>(0L);
    private final Handler handler;
    private long startTime;
    public TimerViewModel(){
        super();
        HandlerThread handlerThread=new HandlerThread("Timer Thread");
        handlerThread.start();
        handler=new Handler(handlerThread.getLooper());
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        handler.getLooper().quit();
    }
    public MutableLiveData<Long> getTimeData(){
        return timeData;
    }
    public void startTimer(){
        startTime= SystemClock.elapsedRealtime();
        handler.post(tickTime);
    }
    public void stopTimer(){
        handler.removeCallbacks(tickTime);
    }
    private Runnable tickTime=new Runnable() {
        @Override
        public void run() {
            timeData.postValue(SystemClock.elapsedRealtime()-startTime);
            handler.postDelayed(tickTime,100L);
        }
    };
}
