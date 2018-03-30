package com.kepler.studentportal.modules.home.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.kepler.OnItemClickListener;
import com.kepler.projectsupportlib.MVPFragment;
import com.kepler.studentportal.R;
import com.kepler.studentportal.VPLogiv;
import com.kepler.studentportal.adapter.CompanyAdapter;
import com.kepler.studentportal.dao.CompanyDetails;
import com.kepler.studentportal.modules.Job.JobActivity;
import com.kepler.studentportal.support.Constants;

import butterknife.BindView;

/**
 * Created by kepler on 28/3/18.
 */

public class Company extends MVPFragment<VPLogiv.CompanyViewPresenter> implements VPLogiv.CompanyView {
    @BindView(R.id.rv_list)
    RecyclerView rv_list;


    public static Company getInstance(){
        return new Company();
    }
    @Override
    protected String getFragmentTitle() {
        return getResources().getString(R.string.companies);
    }

    @Override
    protected VPLogiv.CompanyViewPresenter createPresenter() {
        return null;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv_list.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        CompanyAdapter companyAdapter  = new CompanyAdapter(null, getActivity(), new OnItemClickListener<CompanyDetails>() {
            @Override
            public void OnItemClickListener(CompanyDetails value) {
                Bundle bundle=new Bundle();
                bundle.putString(Constants.TITLE,"TCS Jobs");
                startActivity(JobActivity.class,bundle);
            }
        });
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rv_list.setLayoutManager(horizontalLayoutManager);
        rv_list.setAdapter(companyAdapter);
    }

    @Override
    protected int getViewResource() {
        return R.layout.fragment_rv;
    }

    @Override
    public void showFailureError(int message) {

    }

    @Override
    public void updateView() {

    }
}
