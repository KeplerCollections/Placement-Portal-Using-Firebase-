package com.kepler.studentportal.modules.home.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import com.kepler.projectsupportlib.Logger;
import com.kepler.projectsupportlib.MVPFragment;
import com.kepler.studentportal.R;
import com.kepler.studentportal.VPLogiv;
import com.kepler.studentportal.api.ApiResponse;
import com.kepler.studentportal.dao.JobDetails;
import com.kepler.studentportal.modules.Job.JobActivity;
import com.kepler.studentportal.support.Constants;

import butterknife.BindView;

/**
 * Created by kepler on 28/3/18.
 */

public class Search extends MVPFragment<VPLogiv.SearchViewPresenter> implements VPLogiv.SearchView {

    @BindView(R.id.search)
    Button search;
    @BindView(R.id.category)
    Spinner category;
    @BindView(R.id.skill)
    Spinner skill;
    @BindView(R.id.qualification)
    Spinner qualification;
    public static Search getInstance(){
        return new Search();
    }
    @Override
    protected String getFragmentTitle() {
        return getResources().getString(R.string.search);
    }

    @Override
    protected VPLogiv.SearchViewPresenter createPresenter() {
        return new SearchImpe();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.search((String) category.getSelectedItem(),(String) skill.getSelectedItem(),(String) qualification.getSelectedItem());
            }
        });
    }

    @Override
    protected int getViewResource() {
        return R.layout.fragment_search;
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
    public void updateView(ApiResponse response) throws Exception {
        if(response.isStatus()){
            Bundle bundle=new Bundle();
            bundle.putString(Constants.TITLE,getResources().getString(R.string.search));
            bundle.putParcelable(Constants.DATA,(JobDetails)response.getData());
            startActivity(JobActivity.class,bundle);
        }else {
            fragmentCommunicator.showDialog(response.getMessage(),null, Logger.DIALOG_ALERT);
        }
    }
}
