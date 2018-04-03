package com.kepler.studentportal.modules.home.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.kepler.projectsupportlib.Logger;
import com.kepler.projectsupportlib.MVPFragment;
import com.kepler.studentportal.R;
import com.kepler.studentportal.VPLogiv;
import com.kepler.studentportal.api.ApiResponse;
import com.kepler.studentportal.dao.User;
import com.kepler.studentportal.support.PrefManager;

import butterknife.BindView;

/**
 * Created by kepler on 29/3/18.
 */

public class Profile extends MVPFragment<VPLogiv.ProfileViewPresenter> implements VPLogiv.ProfileView {
    @BindView(R.id.et_username)
    EditText et_username;

    @BindView(R.id.et_number)
    EditText et_number;

    @BindView(R.id.b_udate)
    Button b_udate;
    private User user;

    public static Profile getInstance() {
        return new Profile();
    }

    @Override
    protected String getFragmentTitle() {
        return getString(R.string.update);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.getUser(PrefManager.getPrefrences(getContext()).getUsername());
        b_udate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_username.getText().toString().trim().isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(et_username.getText().toString()).matches()) {
                    et_username.setError(getString(R.string.err_field_is_not_valid));
                    et_username.requestFocus();
                    return;
                }
                if (et_number.getText().toString().trim().isEmpty() || et_number.getText().toString().length() < 10) {
                    et_number.setError(getString(R.string.err_field_is_not_valid));
                    et_number.requestFocus();
                    return;
                }
                user.setContact(et_number.getText().toString().trim());
                user.setEmail_id(et_username.getText().toString().trim());
                presenter.updateUser(user);

            }
        });
    }

    @Override
    protected int getViewResource() {
        return R.layout.fragment_profile;
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
    protected VPLogiv.ProfileViewPresenter createPresenter() {
        return new ProfileImpe();
    }

    @Override
    public void updateView(ApiResponse response) throws Exception {
        if (response.isStatus()) {
            if(response.getCode()==300){
                user=(User) response.getData().get(0);
                et_username.setText(user.getEmail_id());
                et_number.setText(user.getContact());
                PrefManager.getPrefrences(getActivity()).loggedIn(user.getEmail_id());
                showToast(R.string.updated);
            }else{
                user=(User) response.getData().get(0);
                et_username.setText(user.getEmail_id());
                et_number.setText(user.getContact());
                b_udate.setEnabled(true);
            }
        } else {
            fragmentCommunicator.showDialog(response.getMessage(), null, Logger.DIALOG_ALERT);
        }
    }
}
