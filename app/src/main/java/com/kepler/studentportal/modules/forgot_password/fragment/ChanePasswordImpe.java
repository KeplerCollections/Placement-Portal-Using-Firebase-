package com.kepler.studentportal.modules.forgot_password.fragment;

import com.kepler.projectsupportlib.BasePresenterImpl;
import com.kepler.projectsupportlib.Logger;
import com.kepler.studentportal.R;
import com.kepler.studentportal.VPLogiv;
import com.kepler.studentportal.api.ApiClient;
import com.kepler.studentportal.api.BaseResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kepler on 2/4/18.
 */

public class ChanePasswordImpe extends BasePresenterImpl<VPLogiv.ChangePasswordView> implements VPLogiv.ChangePasswordPresenter, Callback<BaseResponse>{



    @Override
    public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
        Logger.print(String.valueOf(response));
        if (view == null) {
            return;
        }
        view.dismiss();
        try {
            view.passwordChanged(response.body());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onFailure(Call<BaseResponse> call, Throwable t) {
        Logger.print(t);
        if (view == null) {
            return;
        }
        view.dismiss();
        view.showFailureError(R.string.error);
    }

    @Override
    public void changePassword(String number, String password) {
        if (view == null)
            return;
        view.showProgress(R.string.loading);
        ApiClient.getClientService().changePassword(number, password).enqueue(this);

    }
}
