package za.ac.cput.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import za.ac.cput.LoginActivity;
import za.ac.cput.R;

public class NotificationUtils {
    public static final String CHANNEL_ID_1 = "HungryStudents";
    public static final String CHANNEL_NAME_1 = "HungryStudents";
    public static final String CHANNEL_DESC_1 = "Hungry Students";

    public static final String CHANNEL_ID_2 = "HungryStudentsService";
    public static final String CHANNEL_NAME_2 = "HungryStudentsService";
    public static final String CHANNEL_DESC_2 = "Hungry Students";


    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void sendNotification(String title, String description, Context context) {
        Intent resultIntent = new Intent(context, LoginActivity.class);
        int num = (int) System.currentTimeMillis();
        PendingIntent pendingIntent1 = PendingIntent.getActivity(context, 1, resultIntent, PendingIntent.FLAG_IMMUTABLE);
        PendingIntent pendingIntent2 = PendingIntent.getActivity(context, 1, resultIntent, PendingIntent.FLAG_IMMUTABLE);

        Notification.Action action1 = new Notification.Action.Builder(R.drawable.logo_small, "Open Hungry Students", pendingIntent1)
                .build();

        Notification.Action action2 = new Notification.Action.Builder(R.drawable.logo_small, "Open Hungry Students", pendingIntent2)
                .build();

        Notification notification1 = new Notification.Builder(context, CHANNEL_ID_1)
                .setContentTitle(title)
                .setContentText(description)
                .setSmallIcon(R.drawable.logo_small)
                .setPriority(Notification.PRIORITY_MAX)
                .setChannelId(CHANNEL_ID_1)
                .setGroup("example_group")
                .setContentIntent(pendingIntent1)
                .setAutoCancel(true)
                .setActions(action1)
                .build();

        Notification notification2 = new Notification.Builder(context, CHANNEL_ID_1)
                .setContentTitle(title)
                .setContentText(description)
                .setSmallIcon(R.drawable.logo_small)
                .setPriority(Notification.PRIORITY_MAX)
                .setChannelId(CHANNEL_ID_1)
                .setGroup("example_group")
                .setContentIntent(pendingIntent2)
                .setAutoCancel(true)
                .setActions(action2)
                .build();


        NotificationManager manager = context.getSystemService(NotificationManager.class);

        manager.notify(num, notification1);
    }
}
