package com.kepler.studentportal.modules.signup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.kepler.projectsupportlib.BaseActivity;
import com.kepler.studentportal.R;
import com.kepler.studentportal.support.Constants;

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

public class AddEducation extends BaseActivity {

    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.et_board)
    EditText et_board;
    @BindView(R.id.et_school)
    EditText et_school;
    @BindView(R.id.et_stream)
    EditText et_stream;
    @BindView(R.id.et_pcm)
    EditText et_pcm;
    @BindView(R.id.et_pcb)
    EditText et_pcb;
    @BindView(R.id.et_grade)
    EditText et_grade;
    @BindView(R.id.et_avg)
    EditText et_avg;
    //    @BindView(R.id.pursuing)
//    CheckBox pursuing;
    @BindView(R.id.b_add)
    Button b_add;
    JSONObject jsonObject = new JSONObject();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enableBackButton();
        if (getIntent().getExtras() == null) {
            onBackPressed();
        } else {
            switch (getIntent().getExtras().getInt(Constants.KEY, 0)) {
                case R.id.add_highschool:
                    setTitle(R.string.add_highschool);
                    setDisabled(et_stream);
                    setDisabled(et_pcb);
                    setDisabled(et_pcm);
//                    pursuing.setVisibility(View.GONE);
                    break;
                case R.id.add_intermediate:
                    setTitle(R.string.add_intermediate);
//                    pursuing.setVisibility(View.GONE);
                    break;
                case R.id.add_graduation:
                    setTitle(R.string.add_graduation);
                    setDisabled(et_pcb);
                    setDisabled(et_pcm);
                    break;
                case R.id.add_post_graduation:
                    setTitle(R.string.add_post_graduation);
                    setDisabled(et_pcb);
                    setDisabled(et_pcm);
                    break;
                default:
                    onBackPressed();
            }
        }
//        pursuing.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    spinner.setVisibility(View.GONE);
//                } else {
//                    spinner.setVisibility(View.VISIBLE);
//                }
//            }
//        });
        b_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (!pursuing.isChecked() && spinner.getSelectedItemPosition() == 0) {
                if (spinner.getSelectedItemPosition() == 0) {
                    showToast(R.string.select_passing_year);
                    spinner.requestFocus();
                    return;
                }
                if (et_board.getText().toString().trim().isEmpty()) {
                    et_board.setError(getString(R.string.err_field_is_empty));
                    et_board.requestFocus();
                    return;
                }
                if (et_school.getText().toString().trim().isEmpty()) {
                    et_school.setError(getString(R.string.err_field_is_empty));
                    et_school.requestFocus();
                    return;
                }
                if (et_stream.getText().toString().trim().isEmpty()) {
                    et_stream.setError(getString(R.string.err_field_is_empty));
                    et_stream.requestFocus();
                    return;
                }
                if (et_pcm.getText().toString().trim().isEmpty() && et_pcb.getText().toString().trim().isEmpty()) {
                    showToast(R.string.pls_enter_pcm_pcb);
                    et_pcm.requestFocus();
                    return;
                }
                if (et_grade.getText().toString().trim().isEmpty()) {
                    et_grade.setError(getString(R.string.err_field_is_empty));
                    et_grade.requestFocus();
                    return;
                }
//                if (et_avg.getText().toString().trim().isEmpty()) {
//                    et_avg.setError(getString(R.string.err_field_is_empty));
//                    et_avg.requestFocus();
//                    return;
//                }
                try {
                    jsonObject.put(PASSING_YEAR, spinner.getSelectedItem());
//                    jsonObject.put(PASSING_YEAR, (pursuing.isChecked()) ? getString(R.string.pursuing) : spinner.getSelectedItem());
                    jsonObject.put(BOARD_UNIVERSITY, et_board.getText().toString().trim());
                    jsonObject.put(SCHOOL_COLLEGE, et_school.getText().toString().trim());
                    jsonObject.put(STREAM, et_stream.getText().toString().trim());
                    jsonObject.put(PCM_MARKS, et_pcm.getText().toString().trim());
                    jsonObject.put(PCB_MARKS, et_pcb.getText().toString().trim());
                    jsonObject.put(CLASS_DIVISION_GRADE, et_grade.getText().toString().trim());
                    jsonObject.put(AVG_MARKS, et_avg.getText().toString().trim());
                    Intent bundle = new Intent();
                    bundle.putExtra(DATA, jsonObject.toString().substring(0, jsonObject.toString().length()));
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
        return R.layout.activity_add_education;
    }

    public void setDisabled(EditText disabled) {
        disabled.setText(R.string.NA);
        disabled.setFocusable(false);
        disabled.setFocusableInTouchMode(false);
    }
}
