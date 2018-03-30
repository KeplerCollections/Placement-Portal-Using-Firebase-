package com.kepler.studentportal.modules.forgot_password;

import android.os.Bundle;
import android.widget.ProgressBar;

import com.kepler.projectsupportlib.BaseActivity;
import com.kepler.studentportal.R;
import com.kepler.studentportal.modules.forgot_password.fragment.Otp;

public class ForgotPassword extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enableBackButton();
        replaceFragment(Otp.getInstance(), null, false);

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
        return R.layout.activity_forgot_password;
    }
}
