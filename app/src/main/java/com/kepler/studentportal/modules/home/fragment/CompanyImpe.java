package com.kepler.studentportal.modules.home.fragment;

import com.kepler.projectsupportlib.BasePresenterImpl;
import com.kepler.projectsupportlib.Logger;
import com.kepler.studentportal.R;
import com.kepler.studentportal.VPLogiv;
import com.kepler.studentportal.api.ApiClient;
import com.kepler.studentportal.api.ApiResponse;
import com.kepler.studentportal.api.BaseResponse;
import com.kepler.studentportal.dao.CompanyDetails;
import com.kepler.studentportal.dao.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kepler on 2/4/18.
 */

public class CompanyImpe extends BasePresenterImpl<VPLogiv.CompanyView> implements VPLogiv.CompanyViewPresenter, Callback<ApiResponse<CompanyDetails>>{

    @Override
    public void loadCompanies() {
        if (view == null)
            return;
        view.showProgress(R.string.loading);
        ApiClient.getClientService().getCompanies().enqueue(this);

    }

    @Override
    public void onResponse(Call<ApiResponse<CompanyDetails>> call, Response<ApiResponse<CompanyDetails>> response) {
        Logger.print(String.valueOf(response));
        if (view == null) {
            return;
        }
        view.dismiss();
        try {
            view.updateView(response.body());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onFailure(Call<ApiResponse<CompanyDetails>> call, Throwable t) {
        Logger.print(t);
        if (view == null) {
            return;
        }
        view.dismiss();
        view.showFailureError(R.string.error);
    }
}
