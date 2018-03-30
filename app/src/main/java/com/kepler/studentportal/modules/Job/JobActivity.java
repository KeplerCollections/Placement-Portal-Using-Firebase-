package com.kepler.studentportal.modules.Job;

import android.os.Bundle;
import android.widget.ProgressBar;

import com.kepler.projectsupportlib.BaseActivity;
import com.kepler.studentportal.R;

public class JobActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enableBackButton();
        replaceFragment(Jobs.getInstance(),getIntent().getExtras(),false);
    }

    @Override
    protected ProgressBar getHorizontalProgressBar() {
        return null;
    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.content_frame;
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_job;
    }
}
