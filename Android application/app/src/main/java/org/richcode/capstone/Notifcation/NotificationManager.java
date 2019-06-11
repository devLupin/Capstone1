package org.richcode.capstone.Notifcation;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.service.autofill.UserData;
import android.support.annotation.RequiresApi;
import android.support.annotation.StringDef;
import android.support.v4.app.NotificationManagerCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONObject;
import org.richcode.capstone.MainActivity;
import org.richcode.capstone.R;

import java.io.OutputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.net.HttpURLConnection;
import java.net.URL;

public class NotificationManager {



    private static String GROUP_NAME = "undefined";

    public static void setGroupName(String name){
        GROUP_NAME = name;
    }

    public static void createChannel(Context context, String name, String description) {
        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){
            NotificationChannelGroup group1 = new NotificationChannelGroup(GROUP_NAME, GROUP_NAME);
            getManager(context).createNotificationChannelGroup(group1);

            NotificationChannel channelMessage = new NotificationChannel(Channel.NAME, name, android.app.NotificationManager.IMPORTANCE_DEFAULT);
            channelMessage.setDescription(description);
            channelMessage.setGroup(GROUP_NAME);
            channelMessage.setLightColor(Color.parseColor("#42a5f5"));
            channelMessage.enableVibration(true);
            channelMessage.setVibrationPattern(new long[]{0, 0});
            getManager(context).createNotificationChannel(channelMessage);
        }
    }

    private static android.app.NotificationManager getManager(Context context) {
        return (android.app.NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public static void showNormalNotification(Context context, int id, String title, String content) {
        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){
            Notification.Builder builder = new Notification.Builder(context, Channel.NAME)
                    .setContentTitle(title)
                    .setContentText(content)
                    .setSmallIcon(getSmallIcon())
                    .setAutoCancel(true);
            getManager(context).notify(id, builder.build());
        }
        else{
            Notification.Builder builder = new Notification.Builder(context)
                    .setContentTitle(title)
                    .setContentText(content)
                    .setSmallIcon(getSmallIcon())
                    .setAutoCancel(true);
            getManager(context).notify(id, builder.build());
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public static void showInboxStyleNotification(Context context, int id, String title, String content, String[] boxText) {
        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){
            Notification.Builder builder = new Notification.Builder(context, Channel.NAME)
                    .setContentTitle(title)
                    .setContentText(content)
                    .setSmallIcon(getSmallIcon())
                    .setAutoCancel(true)
                    .setOngoing(true);
            Notification.InboxStyle inboxStyle = new Notification.InboxStyle();
            inboxStyle.setBigContentTitle(title);
            inboxStyle.setSummaryText(content);




            for(String str : boxText) {
                inboxStyle.addLine(str);
            }

            builder.setStyle(inboxStyle);

            getManager(context).notify(id, builder.build());
        }
        else{
            Notification.Builder builder = new Notification.Builder(context)
                    .setContentTitle(title)
                    .setContentText(content)
                    .setSmallIcon(getSmallIcon())
                    .setAutoCancel(true)
                    .setOngoing(true);
            Notification.InboxStyle inboxStyle = new Notification.InboxStyle();
            inboxStyle.setBigContentTitle(title);
            inboxStyle.setSummaryText(content);

            for (String str : boxText) {
                inboxStyle.addLine(str);
            }

            builder.setStyle(inboxStyle);

            getManager(context).notify(id, builder.build());
        }
    }

    private static final String FCM_MESSAGE_URL = "https://fcm.googleapis.com/fcm/send";
    private static final String SERVER_KEY = "AAAAU0JX3nQ:APA91bGPJ59UGAt2VcNUbNTbw2jtVen3W8v6X2g3Y1QPHkitcdjZhAhCnYmvN1t0mEn6SdHtgq02a94aeLxDjPxxxs3_XzefRdOvrdvhvUu8YMDdoHNHDvtzzx8G3UynMA93tt_RQr2T";
    public static void makeFcmNoti(final String token, final String message) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject root = new JSONObject();
                    JSONObject notification = new JSONObject();
                    notification.put("body", message);
                    notification.put("title", "공지사항");
                    root.put("data", notification);
                    root.put("to", "/topics/"+token);
                    /////

                    notification.put("icon", "alarm"); // <-- 추가  (R.drawable_ic_message)
                    root.put("click_action", "OPEN_ACTIVITY"); // click_action 추가!

                    ////

                    URL Url = new URL(FCM_MESSAGE_URL);
                    HttpURLConnection conn = (HttpURLConnection) Url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);
                    conn.addRequestProperty("Authorization", "key=" + SERVER_KEY);
                    conn.setRequestProperty("Accept", "application/json");
                    conn.setRequestProperty("Content-type", "application/json");
                    OutputStream os = conn.getOutputStream();
                    os.write(root.toString().getBytes("utf-8"));
                    os.flush();
                    conn.getResponseCode();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    public static void deleteNotification(Context context, int id){
        NotificationManagerCompat.from(context).cancel(id);
    }

    private static int getSmallIcon() {
        return R.mipmap.ic_launcher;
    }

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({
            Channel.NAME
    })
    public @interface Channel {
        String NAME = "CHANNEL";
    }

}