package com.kepler.studentportal.push;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.kepler.projectsupportlib.Logger;
import com.kepler.studentportal.R;
import com.kepler.studentportal.modules.MainActivity;
import com.kepler.studentportal.support.Constants;

import java.util.Map;

/**
 * Created by kepler on 20/3/18.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    public static final String PUSH_NOTIFICATION = "";
    private NotificationUtils notificationUtils;

    private NotificationUtils getNotificationUtils(Context context) {
        if (notificationUtils == null)
            notificationUtils = new NotificationUtils(context);
        return notificationUtils;
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Logger.e("From: " + remoteMessage.getFrom());
        if (remoteMessage == null)
            return;
        if (remoteMessage.getData().size() > 0) {
            Logger.e("Data Payload: " + remoteMessage.getData().toString());
            try {
                handleNotification(remoteMessage.getData());
            } catch (Exception e) {
                Logger.print(e);
            }
        } else if (remoteMessage.getNotification() != null) {
            Logger.e("Notification Body: " + remoteMessage.getNotification().getBody());
            handleNotification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
        }

    }

    private void handleNotification(String title, String message) {
//        if (!NotificationUtils.isAppIsInBackground(getApplicationContext())) {
        if (!NotificationUtils.isAppIsInBackground(getApplicationContext())) {
            Intent pushNotification = new Intent(PUSH_NOTIFICATION);
            pushNotification.putExtra("message", message);
            pushNotification.putExtra("title", title);
            LocalBroadcastManager.getInstance(this).sendBroadcast(pushNotification);
        } else {
            Intent resultIntent = new Intent(getApplicationContext(), MainActivity.class);
            resultIntent.putExtra(Constants.MESSAGE, message);
            resultIntent.putExtra(Constants.TITLE, title);
            showNotificationMessage(title, message, resultIntent, "");
        }
    }

    private void handleNotification(Map<String, String> json) {
        try {
            String imageUrl = opt(json, Constants.IMAGE, "");
            String title = opt(json, Constants.TITLE, getString(R.string.app_name));
            String message = opt(json, Constants.MESSAGE, Constants.MESSAGE);
            Logger.print("title: " + title);
            Logger.print("message: " + message);
//            Logger.print("imageUrl: " + imageUrl);
            Intent resultIntent = new Intent(getApplicationContext(), MainActivity.class);
            resultIntent.putExtra("message", message);
            resultIntent.putExtra("title", title);
            if (!NotificationUtils.isAppIsInBackground(getApplicationContext())) {
                Intent pushNotification = new Intent(PUSH_NOTIFICATION);
                pushNotification.putExtra("message", message);
                pushNotification.putExtra("title", title);
                LocalBroadcastManager.getInstance(this).sendBroadcast(pushNotification);
            } else {
                showNotificationMessage(title, message, resultIntent, imageUrl);
            }
        } catch (Exception e) {
            Logger.print(e);
        }
    }

    private String opt(Map<String, String> json, String key, String defaultV) {
        if (json.containsKey(key))
            return json.get(key);
        return defaultV;
    }

    /**
     * Showing notification with text and image
     */
    private void showNotificationMessage(String title, String message, Intent intent, String imageUrl) {
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        getNotificationUtils(getApplicationContext()).showNotificationMessage(title, message, intent, imageUrl);
    }
}
