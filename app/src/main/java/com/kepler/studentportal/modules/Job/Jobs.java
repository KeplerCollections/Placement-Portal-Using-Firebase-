package com.kepler.studentportal.modules.Job;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.kepler.projectsupportlib.Logger;
import com.kepler.projectsupportlib.MVPFragment;
import com.kepler.studentportal.OnItemClickListener;
import com.kepler.studentportal.R;
import com.kepler.studentportal.VPLogiv;
import com.kepler.studentportal.adapter.JobAdapter;
import com.kepler.studentportal.api.ApiResponse;
import com.kepler.studentportal.dao.JobDetails;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.kepler.studentportal.api.ApiClient.COMPANY_ID;
import static com.kepler.studentportal.support.Constants.DATA;
import static com.kepler.studentportal.support.Constants.TITLE;

/**
 * Created by kepler on 28/3/18.
 */

public class Jobs extends MVPFragment<VPLogiv.JobViewPresenter> implements VPLogiv.JobView {
    @BindView(R.id.rv_list)
    RecyclerView rv_list;
    private Bundle bundle;

    public static Jobs getInstance() {
        return new Jobs();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getArguments();
    }

    @Override
    protected String getFragmentTitle() {
        return (bundle == null) ? getResources().getString(R.string.recent_jobs) : bundle.getString(TITLE, getResources().getString(R.string.recent_jobs));
    }

    @Override
    protected VPLogiv.JobViewPresenter createPresenter() {
        return new JobImpe();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (bundle == null || bundle.getParcelableArrayList(DATA) == null) {
            presenter.getJobs((bundle==null) ? null : bundle.getString(COMPANY_ID, null));
        } else {
           initAdapter(bundle.<JobDetails>getParcelableArrayList(DATA));
        }
    }

    private void initAdapter(List<JobDetails> list) {
        rv_list.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        JobAdapter jobAdapter = new JobAdapter(list, getActivity(), new OnItemClickListener<JobDetails>() {
            @Override
            public void OnItemClickListener(JobDetails value) {

            }
        });
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rv_list.setLayoutManager(horizontalLayoutManager);
        rv_list.setAdapter(jobAdapter);
    }

    @Override
    protected int getViewResource() {
        return R.layout.fragment_rv;
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
    public void updateView(ApiResponse response) {
        if(response.isStatus()){
            initAdapter(response.getData());
        }else{
            fragmentCommunicator.showDialog(response.getMessage(),null, Logger.DIALOG_ALERT);
        }
    }
}
