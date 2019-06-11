package org.richcode.capstone;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.richcode.capstone.Notifcation.NotificationManager;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        NotificationManager.setGroupName(getString(R.string.app_name));
        NotificationManager.createChannel(getApplicationContext(), "Notice Notification", "Notice Notification");
        NotificationManager.showNormalNotification(getApplicationContext(),
                1,
                remoteMessage.getData().get("title"),
                remoteMessage.getData().get("body"));
        //Log.d("SSSS", remoteMessage.getData().toString());
    }

}