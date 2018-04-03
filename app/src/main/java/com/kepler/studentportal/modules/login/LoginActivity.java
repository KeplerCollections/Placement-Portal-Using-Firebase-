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
import com.kepler.studentportal.api.BaseResponse;
import com.kepler.studentportal.modules.forgot_password.ForgotPassword;
import com.kepler.studentportal.modules.home.HomeActivity;
import com.kepler.studentportal.modules.signup.SignUpActivity;
import com.kepler.studentportal.support.PrefManager;

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
        return new LoginImpe();
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
    public void showProgress(int message) {
        showProgressBar(message);
    }

    @Override
    public void dismiss() {
        dismissProgressBar();
    }

    @Override
    public void showFailureError(int message) {
        showErrorDialog(getString(message), null);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.b_login:
                presenter.login(et_username.getText().toString(), et_password.getText().toString());
                break;
            case R.id.b_signup:
                startActivity(SignUpActivity.class);
                break;
            case R.id.tv_forgot_password:
                startActivity(ForgotPassword.class);
                break;
        }
    }


    @Override
    public void loggedIn(BaseResponse response) throws Exception {
        if (response.isStatus()) {
            showToast(R.string.logged_in);
            PrefManager.getPrefrences(getApplicationContext()).loggedIn(response.getMessage());
            startActivity(HomeActivity.class);
            finish();
        } else {
            showAlertDialog(response.getMessage(), null);
        }
    }
}
