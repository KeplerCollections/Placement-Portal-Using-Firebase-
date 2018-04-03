package com.kepler.studentportal.modules.home.fragment;

import com.kepler.projectsupportlib.BaseFragment;
import com.kepler.projectsupportlib.Logger;
import com.kepler.projectsupportlib.MVPFragment;
import com.kepler.studentportal.R;
import com.kepler.studentportal.VPLogiv;
import com.kepler.studentportal.api.ApiResponse;

/**
 * Created by kepler on 29/3/18.
 */

public class Profile extends MVPFragment<VPLogiv.ProfileViewPresenter> implements VPLogiv.ProfileView{
    @Override
    protected String getFragmentTitle() {
        return getString(R.string.profile);
    }

    public static Profile getInstance(){
        return new Profile();
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
        fragmentCommunicator.showDialog(message,null, Logger.DIALOG_ERROR);

    }

    @Override
    protected VPLogiv.ProfileViewPresenter createPresenter() {
        return new ProfileImpe();
    }

    @Override
    public void updateView(ApiResponse response) throws Exception {
        if(response.isStatus()){

        }else {
            fragmentCommunicator.showDialog(response.getMessage(),null, Logger.DIALOG_ALERT);
        }
    }
}
