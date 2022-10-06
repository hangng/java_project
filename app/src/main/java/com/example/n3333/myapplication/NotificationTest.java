package com.example.n3333.myapplication;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class NotificationTest extends AppCompatActivity {

    Button b1;
    private NotificationManagerCompat notificationManager;
    public static final String CHANNEL_1_ID = "channel1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_test);
        notificationManager = NotificationManagerCompat.from(this);
        b1 = findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(getApplicationContext(), CHANNEL_1_ID)
                                .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE)
                                .setPriority(NotificationCompat.PRIORITY_HIGH)
                                .setWhen(System.currentTimeMillis()).setSmallIcon(android.R.drawable.ic_dialog_map)
                                .setTicker("")
                                .setContentTitle("Title")
                                .setContentText("ContextText")
                                .setContentInfo("Info")
                                .setVisibility(Notification.VISIBILITY_PUBLIC)
                                .setAutoCancel(true);


//                Notification notification = new NotificationCompat.Builder(NotificationTest.this, App.CHANNEL_2_ID)
//                        .setSmallIcon(R.drawable.happy)
//                        .setContentTitle("Testting")
//                        .setContentText("Helloworld")
//                        .build();
//
//                notificationManager.notify(1, notification);

/*// Tapping the Notification will open up MainActivity
                Intent i = new Intent(NotificationTest.this, MainActivity.class);

// an action to use later
// defined as an app constant:
// public static final String MESSAGE_CONSTANT = "com.example.myapp.notification";
//                i.setAction(MainActivity.MESSAGE_CONSTANT);
// you can use extras as well
                i.putExtra("some_extra", "testValue");

                i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                PendingIntent notificationIntent = PendingIntent.getActivity(NotificationTest.this, 999, i, PendingIntent.FLAG_UPDATE_CURRENT);
                NotificationCompat.Builder builder = new NotificationCompat.Builder(NotificationTest.this.getApplicationContext());
                builder.setContentIntent(notificationIntent);
                builder.setAutoCancel(true);
                builder.setLargeIcon(BitmapFactory.decodeResource(NotificationTest.this.getResources(), R.drawable.mad));
                builder.setSmallIcon(android.R.drawable.ic_dialog_map);
                builder.setContentText("Test Message Text");
                builder.setTicker("Test Ticker Text");
                builder.setContentTitle("Test Message Title");

// set high priority for Heads Up Notification
                builder.setPriority(NotificationCompat.PRIORITY_HIGH);
                builder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
*/
// It won't show "Heads Up" unless it plays a sound
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ) mBuilder.setVibrate(new long[0]);
                NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                mNotificationManager.notify(999, mBuilder.build());
            }
        });
    }

    private void addNotification() {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.happy)
                        .setContentTitle("Notifications Example")
                        .setPriority(NotificationManagerCompat.IMPORTANCE_HIGH)
                        .setContentText("This is a test notification");

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }
}
