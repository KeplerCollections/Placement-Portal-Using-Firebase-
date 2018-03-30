package com.kepler.studentportal.modules.home.fragment;

import com.kepler.projectsupportlib.BaseFragment;
import com.kepler.studentportal.R;

/**
 * Created by kepler on 29/3/18.
 */

public class Profile extends BaseFragment {
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
}
