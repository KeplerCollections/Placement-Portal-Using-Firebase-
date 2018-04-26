package com.kepler.studentportal.modules.forgot_password.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.kepler.projectsupportlib.Logger;
import com.kepler.projectsupportlib.MVPFragment;
import com.kepler.studentportal.R;
import com.kepler.studentportal.VPLogiv;
import com.kepler.studentportal.api.ApiClient;
import com.kepler.studentportal.api.BaseResponse;

import butterknife.BindView;

/**
 * Created by kepler on 28/3/18.
 */

public class Otp extends MVPFragment<VPLogiv.FpOtpSendPresenter> implements VPLogiv.FpOtpSendView {
    @BindView(R.id.et_username)
    EditText et_username;
    @BindView(R.id.b_send_otp)
    Button b_send_otp;


    public static Otp getInstance() {
        return new Otp();
    }

    @Override
    protected String getFragmentTitle() {
        return getResources().getString(R.string.forgot_password);
    }

    @Override
    protected VPLogiv.FpOtpSendPresenter createPresenter() {
        return new OtpImpe();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        b_send_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.sendOtp(et_username.getText().toString(), "123456");
            }
        });
    }

    @Override
    protected int getViewResource() {
        return R.layout.fragment_otp;
    }

    @Override
    public void showProgress(int message) {
        fragmentCommunicator.showProgressBar(message);
    }

    @Override
    public void dismiss() {
        fragmentCommunicator.dismissProgressBar();
    }


    @Override
    public void showFailureError(int message) {
        fragmentCommunicator.showDialog(null,getString(message),null, Logger.DIALOG_ERROR);

    }

    @Override
    public void otpSent(BaseResponse response) throws Exception {
        if (response.isStatus()) {
            showToast(R.string.otp_sent);
            Bundle bundle=new Bundle();
            bundle.putString(ApiClient.OTP,"123456");
            bundle.putString(ApiClient.USERNAME,et_username.getText().toString());
            fragmentCommunicator.replaceFragment(VerifyOtp.getInstance(), bundle, false);
        } else {
            fragmentCommunicator.showDialog(null,response.getMessage(),null, Logger.DIALOG_ALERT);
        }
    }
}
