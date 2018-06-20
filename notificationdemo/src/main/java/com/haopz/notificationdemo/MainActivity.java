package com.haopz.notificationdemo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity {

    private Button notification1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notification1 = findViewById(R.id.notification1);
        notification1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NotificationActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 100, intent, 0);
                Notification.Builder builder = new Notification.Builder(MainActivity.this);
                builder.setContentIntent(pendingIntent);
                builder.setContentTitle("ContentTitle");
                builder.setContentText("ContentText");
                builder.setSmallIcon(R.mipmap.ic_launcher);
                builder.setAutoCancel(true);
                Notification notification = builder.build();

                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(1000, notification);
            }
        });

        Button notification2 = findViewById(R.id.notification2);
        notification2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.item_remote);
                Intent intent = new Intent(MainActivity.this, NotificationActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 100, intent, 0);
                Notification.Builder builder = new Notification.Builder(MainActivity.this);
                builder.setContentIntent(pendingIntent);
                builder.setContentTitle("ContentTitle");
//                builder.setContentText("ContentText");
//                builder.setContent(remoteViews);
                builder.setSmallIcon(R.mipmap.ic_launcher);
                builder.setAutoCancel(true);
                Notification notification = builder.build();

                notification.bigContentView = remoteViews;
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(10000, notification);
            }
        });
    }
}
