package com.kepler.studentportal.modules.home;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.LocalBroadcastManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ProgressBar;

import com.kepler.projectsupportlib.BaseActivity;
import com.kepler.studentportal.R;
import com.kepler.studentportal.modules.Job.Jobs;
import com.kepler.studentportal.modules.forgot_password.fragment.ChangePassword;
import com.kepler.studentportal.modules.home.fragment.Company;
import com.kepler.studentportal.modules.home.fragment.ContactUs;
import com.kepler.studentportal.modules.home.fragment.Resullt;
import com.kepler.studentportal.modules.home.fragment.Search;
import com.kepler.studentportal.modules.login.LoginActivity;
import com.kepler.studentportal.modules.profile.ProfileActivity;
import com.kepler.studentportal.push.PushNotificationService;
import com.kepler.studentportal.support.PrefManager;

import butterknife.BindView;

import static com.kepler.studentportal.api.ApiClient.USERNAME;
import static com.kepler.studentportal.push.MyFirebaseMessagingService.PUSH_NOTIFICATION;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;
    private boolean doubleBackToExitPressedOnce = false;
    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            String title = intent.getStringExtra("title");
            String message = intent.getStringExtra("message");
            showConfirmDialog(title, message, R.string.see_job, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(HomeActivity.class);
                }
            }, R.string.cancel, null);
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter(PUSH_NOTIFICATION));
    }

    @Override
    protected void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        replaceFragment(Jobs.getInstance(), null, false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        replaceFragment(Jobs.getInstance(), null, false);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_recents:
                                replaceFragment(Jobs.getInstance(), null, false);
                                break;
                            case R.id.action_companies:
                                replaceFragment(Company.getInstance(), null, false);
                                break;
                            case R.id.action_search:
                                replaceFragment(Search.getInstance(), null, false);
                                break;
                            case R.id.action_contact:
                                replaceFragment(ContactUs.getInstance(), null, false);
                                break;
                            case R.id.action_download_result:
                                replaceFragment(Resullt.getInstance(), null, false);
                                break;

                        }
                        return true;
                    }
                });
    }

    @Override
    protected ProgressBar getHorizontalProgressBar() {
        return null;
    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.content_frame;
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_home;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home, menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        showToast(R.string.double_back_to_exit);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                showConfirmDialog(null, getResources().getString(R.string.logout_message), R.string.sure, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (PrefManager.getPrefrences(getApplicationContext()).logout()) {
                            startActivity(LoginActivity.class);
                            finish();
                        } else {
                            showAlertDialog("Failed to logout", null);
                        }
                    }
                }, R.string.cancel, null);
                return true;
            case R.id.action_profile:
                startActivity(ProfileActivity.class);
                break;
            case R.id.action_change_password:
                Bundle bundle = new Bundle();
                bundle.putString(USERNAME, PrefManager.getPrefrences(getApplicationContext()).getUsername());
                replaceFragment(ChangePassword.getInstance(), bundle, false);

                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}
