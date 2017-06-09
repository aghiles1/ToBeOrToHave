package com.example.aghil.tobeortohave.alarm;

/**
 * Created by aghil on 04/06/2017.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AutoStart extends BroadcastReceiver
{
    Alarm alarm = new Alarm();

    @Override
    public void onReceive(Context context, Intent intent)
    {
          alarm.setAlarm(context);
    }
}