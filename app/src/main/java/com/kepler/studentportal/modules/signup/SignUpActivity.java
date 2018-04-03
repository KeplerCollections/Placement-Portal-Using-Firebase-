package com.kepler.studentportal.modules.signup;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.kepler.projectsupportlib.MVPActivity;
import com.kepler.studentportal.R;
import com.kepler.studentportal.VPLogiv;
import com.kepler.studentportal.api.ApiResponse;
import com.kepler.studentportal.api.BaseResponse;
import com.kepler.studentportal.modules.login.LoginActivity;

import butterknife.BindView;

public class SignUpActivity extends MVPActivity<VPLogiv.SignUpPresenter> implements VPLogiv.SignUpView {

    @BindView(R.id.b_register)
    Button b_register;
    @Override
    protected VPLogiv.SignUpPresenter createPresenter() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enableBackButton();
        setTitle(R.string.sign_up);
        b_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(LoginActivity.class);
                finish();
            }
        });
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
        return R.layout.activity_sign_up;
    }

    @Override
    public void showProgress(int message) {

    }

    @Override
    public void dismiss() {

    }

    @Override
    public void showFailureError(int message) {

    }

    @Override
    public void registered(BaseResponse response) throws Exception {

    }
}
