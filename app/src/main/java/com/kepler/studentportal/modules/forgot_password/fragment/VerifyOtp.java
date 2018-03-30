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

public class VerifyOtp extends MVPFragment<VPLogiv.FpOtpVerifyPresenter> implements VPLogiv.FpOtpVerifyView{
    @BindView(R.id.et_otp)
    EditText et_otp;
    @BindView(R.id.b_verify_otp)
    Button b_verify_otp;


    public static VerifyOtp getInstance() {
        return new VerifyOtp();
    }

    @Override
    protected String getFragmentTitle() {
        return getResources().getString(R.string.verify_otp);
    }

    @Override
    protected VPLogiv.FpOtpVerifyPresenter createPresenter() {
        return null;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        b_verify_otp.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              otpVerified();
          }
      });
    }

    @Override
    protected int getViewResource() {
        return R.layout.fragment_verify_otp;
    }

    @Override
    public void showFailureError(int message) {

    }

    @Override
    public void otpVerified() {
        fragmentCommunicator.replaceFragment(ChangePassword.getInstance(),null,false);

    }
}
