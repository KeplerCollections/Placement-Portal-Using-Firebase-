package com.kepler.studentportal.modules.home.fragment;

import com.kepler.projectsupportlib.BaseFragment;
import com.kepler.studentportal.R;

/**
 * Created by kepler on 29/3/18.
 */

public class ContactUs extends BaseFragment {
    @Override
    protected String getFragmentTitle() {
        return getString(R.string.contact_us);
    }

    public static ContactUs getInstance(){
        return new ContactUs();
    }

    @Override
    protected int getViewResource() {
        return R.layout.fragment_contact_us;
    }
}
