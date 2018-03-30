package com.kepler.studentportal.modules.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.kepler.projectsupportlib.MVPActivity;
import com.kepler.studentportal.R;
import com.kepler.studentportal.VPLogiv;
import com.kepler.studentportal.modules.forgot_password.ForgotPassword;
import com.kepler.studentportal.modules.home.HomeActivity;
import com.kepler.studentportal.modules.signup.SignUpActivity;

import butterknife.BindView;

public class LoginActivity extends MVPActivity<VPLogiv.LoginPresenter> implements VPLogiv.LoginView, View.OnClickListener {

    @BindView(R.id.et_username)
    EditText et_username;
    @BindView(R.id.et_password)
    EditText et_password;
    @BindView(R.id.b_login)
    Button b_login;
    @BindView(R.id.b_signup)
    Button b_signup;
    @BindView(R.id.tv_forgot_password)
    TextView tv_forgot_password;

    @Override
    protected VPLogiv.LoginPresenter createPresenter() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.login);
        b_login.setOnClickListener(this);
        b_signup.setOnClickListener(this);
        tv_forgot_password.setOnClickListener(this);
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
        return R.layout.activity_login;
    }

    @Override
    public void showFailureError(int message) {

    }

    @Override
    public void loggedIn() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.b_login:
                startActivity(HomeActivity.class);
                finish();
                break;
            case R.id.b_signup:
                startActivity(SignUpActivity.class);
                break;
            case R.id.tv_forgot_password:
                startActivity(ForgotPassword.class);
                break;
        }
    }
}
