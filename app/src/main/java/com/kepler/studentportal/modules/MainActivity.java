package com.kepler.studentportal.modules;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import com.kepler.projectsupportlib.BaseActivity;
import com.kepler.studentportal.R;
import com.kepler.studentportal.modules.home.HomeActivity;
import com.kepler.studentportal.modules.login.LoginActivity;
import com.kepler.studentportal.support.PrefManager;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (PrefManager.getPrefrences(getApplicationContext()).isInSession()) {
                    startActivity(HomeActivity.class);
                } else {
                    startActivity(LoginActivity.class);
                }
                finish();
            }
        }, 2000);
    }

    @Override
    protected ProgressBar getHorizontalProgressBar() {
        return null;
    }

    @Override
    protected int getFragmentContainerId() {
        return 0;
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }
}
