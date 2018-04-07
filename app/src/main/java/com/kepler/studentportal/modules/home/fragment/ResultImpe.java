package com.kepler.studentportal.modules.home.fragment;

import com.kepler.projectsupportlib.BasePresenterImpl;
import com.kepler.projectsupportlib.Logger;
import com.kepler.studentportal.R;
import com.kepler.studentportal.VPLogiv;
import com.kepler.studentportal.api.ApiClient;
import com.kepler.studentportal.api.ApiResponse;
import com.kepler.studentportal.api.BaseResponse;
import com.kepler.studentportal.dao.CompanyDetails;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kepler on 2/4/18.
 */

public class ResultImpe extends BasePresenterImpl<VPLogiv.ResultView> implements VPLogiv.ResultViewPresenter, Callback<ResponseBody>{


    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        Logger.print(String.valueOf(response));
        if (view == null) {
            return;
        }
        view.dismiss();
        try {
            view.updateView(response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        Logger.print(t);
        if (view == null) {
            return;
        }
        view.dismiss();
        view.showFailureError(R.string.error);
    }

    @Override
    public void downloadFile() {
        if (view == null)
            return;
        view.showProgress(R.string.downloading);
        ApiClient.getClientService().downloadResult().enqueue(this);

    }
}
