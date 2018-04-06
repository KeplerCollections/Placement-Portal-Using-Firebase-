package com.kepler.studentportal.push;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.kepler.projectsupportlib.CheckInterNetNetwork;
import com.kepler.projectsupportlib.Logger;
import com.kepler.studentportal.api.ApiClient;
import com.kepler.studentportal.push.dao.PushResponse;
import com.kepler.studentportal.support.PrefManager;

import java.util.Timer;
import java.util.TimerTask;



/**
 * Created by PC on 11/3/2015.
 */
public class PushNotificationService extends Service {

    private Timer timer;
    private TimerTask doAsynchronousTask;
    private String reg_id = null;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        reg_id = PrefManager.getPrefrences(getApplicationContext()).getRegId();
        if (reg_id == null)
            stopSelf();
        startCheckInTimer();
    }


    @Override
    public void onDestroy() {
        if (doAsynchronousTask != null)
            doAsynchronousTask.cancel();
        if (timer != null)
            timer.cancel();
        super.onDestroy();
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {

        if (doAsynchronousTask != null)
            doAsynchronousTask.cancel();
        if (timer != null)
            timer.cancel();
    }

    private synchronized void pushKey() {
//        ApiClient.getClientService().sendRegistrationToServer(reg_id, reg_id, PrefManager.getInstance(getApplicationContext()).getCountryCode()).enqueue(new Callback<PushResponse>() {
//            @Override
//            public void onResponse(Call<PushResponse> call, Response<PushResponse> response) {
//               if (response.isSuccessful() && response.body().getStatus()) {
//                    PrefManager.getInstance(getApplicationContext()).storeREG_IDCheck(true);
//                    stopSelf();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<PushResponse> call, Throwable t) {
//                Logger.print(t);
//            }
//        });
    }

    private void startCheckInTimer() {
        final Handler handler = new Handler();
        timer = new Timer();
        doAsynchronousTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        if (CheckInterNetNetwork.isInternetAvailable(getApplicationContext())) {
                            pushKey();
                        }
                    }
                });
            }
        };
        int update_interval = 100000;
        timer.schedule(doAsynchronousTask, 0, update_interval); // execute in


    }


}