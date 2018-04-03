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
import com.kepler.studentportal.api.BaseResponse;
import com.kepler.studentportal.modules.login.LoginActivity;
import com.kepler.studentportal.support.PrefManager;

import butterknife.BindView;

/**
 * Created by kepler on 28/3/18.
 */

public class ChangePassword extends MVPFragment<VPLogiv.ChangePasswordPresenter> implements VPLogiv.ChangePasswordView {
    @BindView(R.id.et_password)
    EditText et_password;
    @BindView(R.id.et_confirm_password)
    EditText et_confirm_password;
    @BindView(R.id.b_change_password)
    Button b_change_password;


    public static ChangePassword getInstance() {
        return new ChangePassword();
    }

    @Override
    protected String getFragmentTitle() {
        return getResources().getString(R.string.change_password);
    }

    @Override
    protected VPLogiv.ChangePasswordPresenter createPresenter() {
        return new ChanePasswordImpe();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        b_change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_password.getText().toString().isEmpty()) {
                    fragmentCommunicator.showDialog(R.string.err_field_is_empty, null, Logger.DIALOG_ERROR);
                    return;
                }
                if (!et_password.getText().toString().equals(et_confirm_password.getText().toString())) {
                    fragmentCommunicator.showDialog(R.string.err_password_not_matched, null, Logger.DIALOG_ERROR);
                    return;
                }
                presenter.changePassword(PrefManager.getPrefrences(getActivity()).getUsername(), et_password.getText().toString());

            }
        });
    }

    @Override
    protected int getViewResource() {
        return R.layout.fragment_change_password;
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
        fragmentCommunicator.showDialog(message, null, Logger.DIALOG_ERROR);

    }

    @Override
    public void passwordChanged(BaseResponse response) throws Exception {
        if (response.isStatus()) {
            startActivity(LoginActivity.class);
            getActivity().finish();
        } else {
            fragmentCommunicator.showDialog(response.getMessage(), null, Logger.DIALOG_ALERT);
        }
    }
}
