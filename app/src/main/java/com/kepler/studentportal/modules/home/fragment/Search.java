package com.kepler.studentportal.modules.home.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.kepler.projectsupportlib.Logger;
import com.kepler.projectsupportlib.MVPFragment;
import com.kepler.studentportal.R;
import com.kepler.studentportal.VPLogiv;
import com.kepler.studentportal.api.ApiResponse;
import com.kepler.studentportal.dao.JobDetails;
import com.kepler.studentportal.dao.Program;
import com.kepler.studentportal.dao.ProgramCategoty;
import com.kepler.studentportal.modules.Job.JobActivity;
import com.kepler.studentportal.support.Constants;

import java.util.ArrayList;

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
    private ArrayAdapter adapter;

    public static Search getInstance() {
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
        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Logger.e("onItemSelected:" + position);
                setParentAdapter(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Logger.e("onNothingSelected:");

            }
        });
        skill.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Logger.e("onItemSelected:" + position);
                setAdapter();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Logger.e("onNothingSelected:");

            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (category.getSelectedItemPosition() == 0) {
                    showAlert(getString(R.string.err_select_category));
                    return;
                }
                presenter.search(String.valueOf(((Program) qualification.getSelectedItem()).getId()));
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
        fragmentCommunicator.showDialog(null,getString(message), null, Logger.DIALOG_ERROR);

    }


    @Override
    public void updateView(ApiResponse response) throws Exception {
        if (response.isStatus()) {
            Bundle bundle = new Bundle();
            bundle.putString(Constants.TITLE, getResources().getString(R.string.search));
            bundle.putParcelableArrayList(Constants.DATA, (ArrayList<JobDetails>) response.getData());
            startActivity(JobActivity.class, bundle);
        } else {
            fragmentCommunicator.showDialog(null,response.getMessage(), null, Logger.DIALOG_ALERT);
        }
    }

    @Override
    public Context getAppContext() {
        return getActivity().getApplicationContext();
    }

    public void setParentAdapter(int position) {
        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, presenter.getProgramCategory(--position));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        skill.setAdapter(adapter);
        if (position == -1)
            setAdapter();
    }

    public void setAdapter() {
        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, presenter.getProgram((skill.getSelectedItem() == null) ? 0 : ((ProgramCategoty) skill.getSelectedItem()).getId()));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        qualification.setAdapter(adapter);

    }


}
