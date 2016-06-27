package com.example.benjamin.ex4._____FifthActivity;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.benjamin.ex4.R;

import java.util.GregorianCalendar;

/**
 * Created by benjamin on 22/06/16.
 */
public class MessagesUpdateReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        if(MessageService.stopService == false) {
            sendNotification(context);
            setAlarm(context);
        }
    }
    public void sendNotification(Context context)
    {

        NotificationManager notif=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new Notification();
        Intent notificationIntent = new Intent(context, FifthActivityMessaging.class);

        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 1, notificationIntent, 0);

        Notification.Builder builder = new Notification.Builder(context);
        builder.setAutoCancel(true).setTicker(context.getString(R.string.new_message))
                .setContentTitle(context.getString(R.string.notification))
                .setContentText(context.getString(R.string.click)).setSmallIcon(R.drawable.incredibles)
                .setContentIntent(pendingIntent).setOngoing(true);

        notification = builder.getNotification();
        notif.notify(11, notification);
    }
    public void setAlarm(Context context)
    {
        Long time = new GregorianCalendar().getTimeInMillis() + 5 * 1000;
        Intent intentAlarm = new Intent(context, MessagesUpdateReceiver.class);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, time, PendingIntent.getBroadcast(context, 1, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));
    }
}