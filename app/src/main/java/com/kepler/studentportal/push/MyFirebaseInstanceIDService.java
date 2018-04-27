package com.kepler.studentportal.push;

import android.content.Intent;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessaging;
import com.kepler.projectsupportlib.Logger;
import com.kepler.studentportal.support.PrefManager;

/**
 * Created by kepler on 20/3/18.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        // Saving reg id to shared preferences
        FirebaseMessaging.getInstance().subscribeToTopic("job");
        PrefManager.getPrefrences(getApplicationContext()).storeRegIdInPref(refreshedToken);
        // sending reg id to your server
        sendRegistrationToServer(refreshedToken);

    }

    private void sendRegistrationToServer(final String token) {
        // sending gcm token to server
        Logger.e("sendRegistrationToServer: " + token);
        Intent intent = new Intent(getApplicationContext(), PushNotificationService.class);
        startService(intent);
    }


}
