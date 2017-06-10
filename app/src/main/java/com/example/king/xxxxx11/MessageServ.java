package com.example.king.xxxxx11;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by king on 6/4/2017.
 */

public class MessageServ extends FirebaseMessagingService {
    @Override
    public void onMessageReceived (RemoteMessage remoteMessage){
        Intent intent = new Intent(this , Splash.class);
        if (remoteMessage.getData().size()>0){
            String message = remoteMessage.getData().get("message");
            Bundle bundle = new Bundle();
            bundle.putString("message", message);
            intent.putExtras(bundle);
        }
        if (remoteMessage.getData().size()>0){
            String kuliah = remoteMessage.getData().get("kuliah");
            Bundle bundle = new Bundle();
            bundle.putString("kuliah",kuliah);
            intent.putExtras(bundle);
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder ncb = new NotificationCompat.Builder(this);
        ncb.setContentTitle("NEXT AGENDA");
        ncb.setContentText(remoteMessage.getNotification().getBody());
        ncb.setAutoCancel(true);
        ncb.setSmallIcon(R.mipmap.ic_launcher);
        ncb.setContentIntent(pi);
        ncb.setSound(Uri.parse("android.resource://"+getPackageName()+"/"+ R.raw.optimus_prime));
        ncb.setVibrate(new long[] { 8000, 9000,});

        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(0, ncb.build());
    }
}
