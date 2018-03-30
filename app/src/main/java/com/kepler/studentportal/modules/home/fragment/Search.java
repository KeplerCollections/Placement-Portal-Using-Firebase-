package com.kepler.studentportal.modules.home.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.kepler.projectsupportlib.MVPFragment;
import com.kepler.studentportal.R;
import com.kepler.studentportal.VPLogiv;
import com.kepler.studentportal.modules.Job.JobActivity;
import com.kepler.studentportal.support.Constants;

import butterknife.BindView;

/**
 * Created by kepler on 28/3/18.
 */

public class Search extends MVPFragment<VPLogiv.SearchViewPresenter> implements VPLogiv.SearchView {

    @BindView(R.id.search)
    Button search;
    public static Search getInstance(){
        return new Search();
    }
    @Override
    protected String getFragmentTitle() {
        return getResources().getString(R.string.search);
    }

    @Override
    protected VPLogiv.SearchViewPresenter createPresenter() {
        return null;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateView();
            }
        });
    }

    @Override
    protected int getViewResource() {
        return R.layout.fragment_search;
    }

    @Override
    public void showFailureError(int message) {

    }

    @Override
    public void updateView() {
        Bundle bundle=new Bundle();
        bundle.putString(Constants.TITLE,getResources().getString(R.string.search));
        startActivity(JobActivity.class,bundle);
    }
}
