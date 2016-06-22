package com.example.benjamin.ex4;

/**
 * Created by benjamin on 21/06/16.
 */
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import java.util.GregorianCalendar;
import java.util.Random;

public class MessageService extends Service {
    public static Context context;
    public static boolean stopService = false;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        stopService = false;
        setAlarm();
        return START_STICKY;
    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onDestroy()
    {
        stopService = true;
    }
    public void setAlarm()
    {
        Long time = new GregorianCalendar().getTimeInMillis()+5*1000;
        Intent intentAlarm = new Intent(this, MessagesUpdateReceiver.class);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, time,
                PendingIntent.getBroadcast(this, 1, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));
        Toast.makeText(this, "Alarm Scheduled for Tommrrow", Toast.LENGTH_LONG).show();
    }
}