package com.kepler.studentportal.modules.login;

import com.kepler.projectsupportlib.BasePresenterImpl;
import com.kepler.projectsupportlib.Logger;
import com.kepler.studentportal.R;
import com.kepler.studentportal.VPLogiv;
import com.kepler.studentportal.api.ApiClient;
import com.kepler.studentportal.api.ApiResponse;
import com.kepler.studentportal.api.BaseResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kepler on 2/4/18.
 */

public class LoginImpe extends BasePresenterImpl<VPLogiv.LoginView> implements VPLogiv.LoginPresenter, Callback<BaseResponse>{

    @Override
    public void login(String username, String password) {
        if (view == null)
            return;
        view.showProgress(R.string.authenticating);
        ApiClient.getClientService().login(username, password).enqueue(this);
    }

    @Override
    public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
        Logger.print(String.valueOf(response));
        if (view == null) {
            return;
        }
        view.dismiss();
        try {
            view.loggedIn(response.body());
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
}
