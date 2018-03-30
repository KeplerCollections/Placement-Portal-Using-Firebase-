package com.kepler.studentportal.modules.forgot_password.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.kepler.projectsupportlib.MVPFragment;
import com.kepler.studentportal.R;
import com.kepler.studentportal.VPLogiv;
import com.kepler.studentportal.modules.login.LoginActivity;

import butterknife.BindView;

/**
 * Created by kepler on 28/3/18.
 */

public class ChangePassword extends MVPFragment<VPLogiv.ChangePasswordPresenter> implements VPLogiv.ChangePasswordView{
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
        return null;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        b_change_password.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              passwordChanged();
          }
      });
    }

    @Override
    protected int getViewResource() {
        return R.layout.fragment_change_password;
    }

    @Override
    public void showFailureError(int message) {

    }

    @Override
    public void passwordChanged() {
        startActivity(LoginActivity.class);
        getActivity().finish();
    }
}
