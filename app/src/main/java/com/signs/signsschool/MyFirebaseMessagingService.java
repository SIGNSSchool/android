package com.signs.signsschool;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.e("newToken", s);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("token", s);
        editor.commit();

    }

    public String getToken(Context context) {
        return context.getSharedPreferences("_", MODE_PRIVATE).getString("token", "empty");
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);


        if (message.getData().size() > 0) {
            getMessage(message.getData().get("title"), message.getData().get("body"));

        } else {
            System.out.println("Message has no payload");
        }

        if (message.getNotification() != null) {

        }

    }


    public void getMessage(String title, String message) {
        System.out.println("Trying to show Notification with title"+title);
        Intent intent = new Intent(this, Home.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 100 /* Request code */, intent,
                PendingIntent.FLAG_IMMUTABLE);


        Uri defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        String channelId = "Default";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.playstore)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSound)
                .setContentIntent(pendingIntent);


        NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId, "default", NotificationManager.IMPORTANCE_HIGH);
            manager.createNotificationChannel(channel);
        }

        manager.notify(100, builder.build());
    }
}
