package com.example.aghil.tobeortohave.alarm;

/**
 * Created by aghil on 04/06/2017.
 */
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.widget.Toast;

import com.example.aghil.tobeortohave.ItemListActivity;
import com.example.aghil.tobeortohave.R;
import com.example.aghil.tobeortohave.model.Livraison;

import java.util.List;

public class Alarm extends BroadcastReceiver
{
    private  List<Livraison> list;
    public Alarm(){
        list = ItemListActivity.getLiv();
    }
    @Override
    public void onReceive(Context context, Intent intent)
    {
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "");
        wl.acquire();
           for (int i=0; i < list.size();i++){
                if ((list.get(i).getTemps()-((System.currentTimeMillis()/60000.0)-list.get(i).getCurrentTime()))<0) {
                    Toast.makeText(context, list.get(i).getLiv() + " est arrivé", Toast.LENGTH_LONG).show();
                    android.app.Notification.Builder builder = new android.app.Notification.Builder(context);
                    builder.setAutoCancel(false);
                    builder.setTicker("this is ticker text");
                    builder.setContentTitle("ToBeOrToHave Notification");
                    builder.setContentText(list.get(i).getLiv());
                    builder.setSmallIcon(R.mipmap.ic_launcher);
                    builder.setOngoing(true);
                    builder.build();
                    android.app.Notification myNotication = builder.getNotification();
                    NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                    manager.notify(11, myNotication);
                    list.remove(i);
                    break;
                }
            }

        wl.release();
    }

    public void setAlarm(Context context)
    {
        AlarmManager am =( AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, Alarm.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 1, pi);
    }
    public void cancelAlarm(Context context)
    {
        Intent intent = new Intent(context, Alarm.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
    }

}