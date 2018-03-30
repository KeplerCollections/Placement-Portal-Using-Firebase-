package com.kepler.studentportal.modules.forgot_password.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.kepler.projectsupportlib.MVPFragment;
import com.kepler.studentportal.R;
import com.kepler.studentportal.VPLogiv;

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
        return null;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
      b_send_otp.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              otpSent();
          }
      });
    }

    @Override
    protected int getViewResource() {
        return R.layout.fragment_otp;
    }

    @Override
    public void showFailureError(int message) {

    }

    @Override
    public void otpSent() {
        fragmentCommunicator.replaceFragment(VerifyOtp.getInstance(),null,false);
    }
}
