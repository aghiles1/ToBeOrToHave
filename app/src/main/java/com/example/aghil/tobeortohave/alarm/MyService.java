package com.example.aghil.tobeortohave.alarm;

/**
 * Created by aghil on 04/06/2017.
 */



import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyService extends Service
{
    Alarm alarm = new Alarm();
    public void onCreate()
    {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        alarm.setAlarm(this);
        return START_STICKY;
    }

    @Override
    public void onStart(Intent intent, int startId)
    {
        alarm.setAlarm(this);
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }
}