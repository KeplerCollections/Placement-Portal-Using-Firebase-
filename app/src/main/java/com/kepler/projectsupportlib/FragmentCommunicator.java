package com.kepler.projectsupportlib;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;


/**
 * Created by special on 21/11/17.
 */

public interface FragmentCommunicator {

    void setFragmentTitle(String title);


    void dismissProgressBar();

    void showProgressBar(int message);

    void showDialog(int message, DialogInterface.OnClickListener onClickListener, int dialogType);

    void showDialog(String message, DialogInterface.OnClickListener onClickListener, int dialogType);

    void dismissHorizontalProgress();

    void replaceFragment(Fragment fragment, Bundle bundle, boolean addTo);

    void addFragment(Fragment fragment, Bundle bundle, boolean addTo);
}
