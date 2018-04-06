package com.kepler.studentportal.modules.signup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.kepler.projectsupportlib.BaseActivity;
import com.kepler.studentportal.R;

import org.json.JSONObject;

import butterknife.BindView;

import static com.kepler.studentportal.support.Constants.AVG_MARKS;
import static com.kepler.studentportal.support.Constants.BOARD_UNIVERSITY;
import static com.kepler.studentportal.support.Constants.CLASS_DIVISION_GRADE;
import static com.kepler.studentportal.support.Constants.DATA;
import static com.kepler.studentportal.support.Constants.PASSING_YEAR;
import static com.kepler.studentportal.support.Constants.PCB_MARKS;
import static com.kepler.studentportal.support.Constants.PCM_MARKS;
import static com.kepler.studentportal.support.Constants.SCHOOL_COLLEGE;
import static com.kepler.studentportal.support.Constants.STREAM;

public class AddExperience extends BaseActivity {

    @BindView(R.id.et_experience)
    EditText et_experience;
    @BindView(R.id.et_company)
    EditText et_company;
    @BindView(R.id.et_designation)
    EditText et_designation;
    @BindView(R.id.b_add)
    Button b_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enableBackButton();
        setTitle(R.string.working_experience);
        b_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_experience.getText().toString().trim().isEmpty()) {
                    et_experience.setError(getString(R.string.err_field_is_empty));
                    et_experience.requestFocus();
                    return;
                }
                if (et_company.getText().toString().trim().isEmpty()) {
                    et_company.setError(getString(R.string.err_field_is_empty));
                    et_company.requestFocus();
                    return;
                }
                if (et_designation.getText().toString().trim().isEmpty()) {
                    et_designation.setError(getString(R.string.err_field_is_empty));
                    et_designation.requestFocus();
                    return;
                }
                try {
                    Intent bundle = new Intent();
                    bundle.putExtra(DATA, et_experience.getText().toString().trim()+","+et_company.getText().toString().trim()+","+et_designation.getText().toString().trim());
                    setResult(Activity.RESULT_OK, bundle);
                } catch (Exception e) {

                }
                onBackPressed();
            }
        });
    }

    @Override
    protected ProgressBar getHorizontalProgressBar() {
        return null;
    }

    @Override
    protected int getFragmentContainerId() {
        return 0;
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_add_experience;
    }

}
