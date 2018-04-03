package com.kepler.projectsupportlib;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.kepler.studentportal.R;

import java.util.ArrayDeque;
import java.util.Queue;

import butterknife.ButterKnife;

import static com.kepler.projectsupportlib.Logger.DIALOG_ALERT;
import static com.kepler.projectsupportlib.Logger.DIALOG_ERROR;

public abstract class BaseActivity extends AppCompatActivity implements FragmentCommunicator {

    //in your Activity
    Queue<DeferredFragmentTransaction> deferredFragmentTransactions = new ArrayDeque<>();
    private ProgressDialog dialog;
    private SharedPreferences srdPrf;
    private boolean isRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        ButterKnife.bind(this);
    }



    protected void hideToolbar() {
        if (getSupportActionBar() == null)
            return;
        getSupportActionBar().hide();
    }

    protected SharedPreferences getDefaultSharedPreferences() {
        if (srdPrf == null)
//            srdPrf = PreferenceManager.getDefaultSharedPreferences(this);
            srdPrf = getSharedPreferences(getResources().getString(R.string.app_name), Context.MODE_PRIVATE);
        return srdPrf;
    }

    @Override
    protected void onResume() {
        super.onResume();
        isRunning = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        isRunning = false;
    }

    @Override
    public void dismissHorizontalProgress() {
        if (getHorizontalProgressBar() != null && getHorizontalProgressBar().getVisibility() == View.VISIBLE)
            getHorizontalProgressBar().setVisibility(View.GONE);
    }

    protected void enableBackButton() {
        if (getSupportActionBar() == null)
            return;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    protected void setPageTitle(int title) {
        if (getSupportActionBar() == null)
            return;
        getSupportActionBar().setTitle(title);
    }

    protected void startActivity(@NonNull Class<? extends BaseActivity> aClass) {
        Intent intent = new Intent(this, aClass);
        startActivity(intent);
    }
    protected void startActivity(@NonNull Class<? extends BaseActivity> aClass, Bundle bundle,int request_code) {
        Intent intent = new Intent(this, aClass);
        if (bundle != null)
            intent.putExtras(bundle);
        startActivityForResult(intent,request_code);
    }

    protected void startActivity(@NonNull Class<? extends BaseActivity> aClass, int flags) {
        Intent intent = new Intent(this, aClass);
        intent.setFlags(flags);
        startActivity(intent);
    }


    protected void startActivity(@NonNull Class<? extends BaseActivity> aClass, Bundle bundle) {
        Intent intent = new Intent(this, aClass);
        if (bundle != null)
            intent.putExtras(bundle);
        startActivity(intent);
    }


    protected void showToast(int message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    protected void showProgressDialog(int msg) {
        if (dialog == null) {
            dialog = new ProgressDialog(this);
            dialog.setCancelable(false);
        }
        dialog.setMessage(getResources().getString(msg));
        dialog.show();
    }

    private void dismiss() {
        if (dialog != null && dialog.isShowing())
            dialog.dismiss();
    }

    private void replaceFragment(@NonNull Fragment fragment, int contentFrameId, final boolean addToStack) {
        if (!isRunning) {
            DeferredFragmentTransaction deferredFragmentTransaction = new DeferredFragmentTransaction() {
                @Override
                public void commit() {
                    replaceFragmentInternal(getContentFrameId(), getReplacingFragment(), addToStack);
                }
            };

            deferredFragmentTransaction.setContentFrameId(contentFrameId);
            deferredFragmentTransaction.setReplacingFragment(fragment);

            deferredFragmentTransactions.add(deferredFragmentTransaction);
        } else {
            replaceFragmentInternal(contentFrameId, fragment, addToStack);
        }
    }

    private void addFragment(@NonNull Fragment fragment, int contentFrameId, final boolean addToStack) {

        if (!isRunning) {
            DeferredFragmentTransaction deferredFragmentTransaction = new DeferredFragmentTransaction() {
                @Override
                public void commit() {
                    addFragmentInternal(getContentFrameId(), getReplacingFragment(), addToStack);
                }
            };
//            R.id.fragment_container
            deferredFragmentTransaction.setContentFrameId(contentFrameId);
            deferredFragmentTransaction.setReplacingFragment(fragment);

            deferredFragmentTransactions.add(deferredFragmentTransaction);
        } else {
            addFragmentInternal(contentFrameId, fragment, addToStack);
        }
    }

    private void addFragmentInternal(int contentFrameId, Fragment fragment, final boolean addToStack) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(contentFrameId, fragment);
        if (addToStack)
            transaction.addToBackStack(null);
        transaction.commit();
    }


    private void replaceFragmentInternal(int contentFrameId, Fragment replacingFragment, boolean addToStack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(contentFrameId, replacingFragment);
        if (addToStack)
            transaction.addToBackStack(null);
        transaction.commit();
    }

    protected void showConfirmationDialog(String message, DialogInterface.OnClickListener listener) {
        showSimpleAlert(R.string.confirm, message, R.string.sure, listener, R.string.cancel, null, false);
    }

    protected void showAlertDialog(String message, DialogInterface.OnClickListener listener) {
        showSimpleAlert(R.string.alert, message, R.string.okay, listener, 0, null, false);
    }

    protected void showErrorDialog(String message, DialogInterface.OnClickListener listener) {
        showSimpleAlert(R.string.error, message, R.string.okay, listener, 0, null, false);
    }

    private void showSimpleAlert(int title, String message, int positiveBtnTextId, DialogInterface.OnClickListener positiveBtnListener, int negativeBtnTextId, DialogInterface.OnClickListener negativeBtnListener, boolean isCancelable) {
        if (negativeBtnTextId == 0) {
            new AlertDialog.Builder(this).setTitle(title).setMessage(message).setPositiveButton(positiveBtnTextId, positiveBtnListener).setCancelable(isCancelable).create().show();
        } else {
            new AlertDialog.Builder(this).setTitle(title).setMessage(message).setPositiveButton(positiveBtnTextId, positiveBtnListener).setNegativeButton(negativeBtnTextId, negativeBtnListener).setCancelable(isCancelable).create().show();
        }
    }

    @Override
    protected void onDestroy() {
        dialog = null;
        Logger.print("onDestroy");
        super.onDestroy();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        while (!deferredFragmentTransactions.isEmpty()) {
            deferredFragmentTransactions.remove().commit();
        }
    }

    @Override
    public void onBackPressed() {
        int count = getFragmentManager().getBackStackEntryCount();
        if (count == 0) {
            super.onBackPressed();
            //additional code
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public void setFragmentTitle(String title) {
        setTitle(title);
    }

    @Override
    public void showProgressBar(int message) {
        showProgressDialog(message);
    }

    @Override
    public void dismissProgressBar() {
        dismiss();
    }

    @Override
    public void showDialog(int message, DialogInterface.OnClickListener onClickListener, int dialogType) {
        switch (dialogType){
            case DIALOG_ERROR:
                showErrorDialog(getResources().getString(message), onClickListener);
                break;
            case DIALOG_ALERT:
                showAlertDialog(getResources().getString(message), onClickListener);
                break;
        }
    }
    @Override
    public void showDialog(String message, DialogInterface.OnClickListener onClickListener, int dialogType) {
        switch (dialogType){
            case DIALOG_ERROR:
                showErrorDialog(message, onClickListener);
                break;
            case DIALOG_ALERT:
                showAlertDialog(message, onClickListener);
                break;
        }
    }

    //    @Override
//    public void replaceFragment(Fragment fragment, boolean addTo) {
//        replaceFragment(fragment, getFragmentContainerId(), addTo);
//
//    }
//
//    @Override
//    public void addFragment(Fragment fragment, boolean addTo) {
//        addFragment(fragment, getFragmentContainerId(), addTo);
//    }

    @Override
    public void replaceFragment(Fragment fragment, Bundle bundle, boolean addTo) {
        if (bundle != null)
            fragment.setArguments(bundle);
        replaceFragment(fragment, getFragmentContainerId(), addTo);
    }

    @Override
    public void addFragment(Fragment fragment, Bundle bundle, boolean addTo) {
        if (bundle != null)
            fragment.setArguments(bundle);
        addFragment(fragment, getFragmentContainerId(), addTo);
    }

    protected boolean isInternetAvailable() {
        return CheckInterNetNetwork.isInternetAvailable(getApplicationContext());
    }

    protected abstract ProgressBar getHorizontalProgressBar();

    protected abstract int getFragmentContainerId();

    protected abstract int getContentView();
}
