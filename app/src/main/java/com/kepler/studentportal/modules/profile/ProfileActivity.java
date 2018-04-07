package com.kepler.studentportal.modules.profile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.kepler.projectsupportlib.MVPActivity;
import com.kepler.studentportal.R;
import com.kepler.studentportal.VPLogiv;
import com.kepler.studentportal.api.ApiResponse;
import com.kepler.studentportal.dao.User;
import com.kepler.studentportal.modules.signup.AddEducation;
import com.kepler.studentportal.modules.signup.AddExperience;
import com.kepler.studentportal.support.PrefManager;

import org.json.JSONObject;

import butterknife.BindView;

import static com.kepler.studentportal.modules.signup.SignUpActivity.ADD_EXPERIENCE;
import static com.kepler.studentportal.modules.signup.SignUpActivity.GRADUATION;
import static com.kepler.studentportal.modules.signup.SignUpActivity.HIGH_SCHOOL;
import static com.kepler.studentportal.modules.signup.SignUpActivity.INTERMEDIATE;
import static com.kepler.studentportal.modules.signup.SignUpActivity.POST_GRADUATION;
import static com.kepler.studentportal.support.Constants.BOARD_UNIVERSITY;
import static com.kepler.studentportal.support.Constants.CONTACT;
import static com.kepler.studentportal.support.Constants.DATA;
import static com.kepler.studentportal.support.Constants.EMAIL;
import static com.kepler.studentportal.support.Constants.KEY;
import static com.kepler.studentportal.support.Constants.PASSING_YEAR;
import static com.kepler.studentportal.support.Constants.SCHOOL_COLLEGE;

public class ProfileActivity extends MVPActivity<VPLogiv.ProfileViewPresenter> implements VPLogiv.ProfileView, View.OnClickListener {

    private static final int UPDATE = 111;
    @BindView(R.id.ll_education)
    LinearLayout ll_education;
    @BindView(R.id.ll_experience)
    LinearLayout ll_experience;

    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_email)
    TextView tv_email;
    @BindView(R.id.tv_contact)
    TextView tv_contact;
    @BindView(R.id.change_number)
    TextView change_number;
    @BindView(R.id.change_email)
    TextView change_email;

    @BindView(R.id.add_experience)
    TextView add_experience;
    @BindView(R.id.add_post_graduation)
    TextView add_post_graduation;
    private User user;
    private JSONObject qualification;
    private Bundle bundle;

    @Override
    protected VPLogiv.ProfileViewPresenter createPresenter() {
        return new ProfileImpe();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enableBackButton();
        setPageTitle(R.string.profile);
        change_number.setOnClickListener(this);
        change_email.setOnClickListener(this);
        add_experience.setOnClickListener(this);
        add_post_graduation.setOnClickListener(this);
        presenter.getUser(PrefManager.getPrefrences(getApplicationContext()).getUsername());
    }

    @Override
    public void showFailureError(int message) {
        showErrorDialog(getString(message), null);
    }

    @Override
    public void showProgress(int message) {
        showProgressDialog(message);
    }

    @Override
    public void dismiss() {
        dismissProgressBar();
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
        return R.layout.activity_profile;
    }

    @Override
    public void updateView(ApiResponse response) throws Exception {
        if (response.isStatus()) {
            user = (User) response.getData().get(0);
            tv_name.setText(user.getFirst_name() + " " + user.getLast_name());
            tv_email.setText(user.getEmail_id());
            tv_contact.setText(user.getContact());
            qualification=new JSONObject(user.getQualification());
            addEducation(qualification);
            addWorkingExp();
            if (response.getCode() == 300) {
                PrefManager.getPrefrences(getApplicationContext()).loggedIn(user.getEmail_id());
                showToast(R.string.updated);
            }
        } else {
            showAlertDialog(response.getMessage(), null);
        }
    }


    private void addWorkingExp() throws Exception {
        if (user.getExperience().equals("0")) {
            add_experience.setVisibility(View.VISIBLE);
        } else {
            add_experience.setVisibility(View.GONE);
            ll_experience.removeAllViews();
            addView(getString(R.string.working_experience), getExpDes(), ll_experience);
        }
    }

    private void addEducation(JSONObject jsonObject) {
        try {
            ll_education.removeAllViews();
            addView(getString(R.string.highschool), getEducationDes(jsonObject.getJSONObject(String.valueOf(HIGH_SCHOOL))), ll_education);
            addView(getString(R.string.intermediate), getEducationDes(jsonObject.getJSONObject(String.valueOf(INTERMEDIATE))), ll_education);
            addView(getString(R.string.graduation), getEducationDes(jsonObject.getJSONObject(String.valueOf(GRADUATION))), ll_education);
            if (jsonObject.has(String.valueOf(POST_GRADUATION))) {
                add_post_graduation.setVisibility(View.GONE);
                addView(getString(R.string.post_graduation), getEducationDes(jsonObject.getJSONObject(String.valueOf(POST_GRADUATION))), ll_education);
            } else {
                add_post_graduation.setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void addView(String sTitle, String des, LinearLayout achor) throws Exception {
        View view = getLayoutInflater().inflate(R.layout.education_list_view, null);
        TextView title = view.findViewById(R.id.title);
        TextView t_des = view.findViewById(R.id.des);
        title.setText(sTitle);
        t_des.setText(des);
        achor.addView(view);
    }

    public String getEducationDes(JSONObject des) throws Exception {
        return getString(R.string.school) + " : " + des.getString(SCHOOL_COLLEGE)
                + "\n" + getString(R.string.board) + " : " + des.getString(BOARD_UNIVERSITY)
                + "\n" + getString(R.string.passing_year) + " : " + des.getString(PASSING_YEAR)
                ;
    }

    public String getExpDes() {
        return getString(R.string.working_experience) + " : " + user.getExperience() + " Years"
                + "\n" + getString(R.string.last_company) + " : " + user.getLast_company()
                + "\n" + getString(R.string.last_designation) + " : " + user.getLast_designation()
                ;
    }

    @Override
    public void onClick(View v) {
        bundle = new Bundle();
        switch (v.getId()) {
            case R.id.change_number:
                if(user==null){
                    showAlertDialog(getString(R.string.unable_to_update),null);
                    return;
                }
                bundle.putParcelable(DATA,user);
                startActivity(UpdateDetails.class, bundle, UPDATE);
                break;
            case R.id.change_email:
                if(user==null){
                    showAlertDialog(getString(R.string.unable_to_update),null);
                    return;
                }
                bundle.putParcelable(DATA,user);
                startActivity(UpdateDetails.class, bundle, UPDATE);
                break;
            case R.id.add_post_graduation:
                if(user==null){
                    showAlertDialog(getString(R.string.unable_to_update),null);
                    return;
                }
                bundle.putInt(KEY, v.getId());
                startActivity(AddEducation.class, bundle, POST_GRADUATION);
                break;
            case R.id.add_experience:
                if(user==null){
                    showAlertDialog(getString(R.string.unable_to_update),null);
                    return;
                }
                startActivity(AddExperience.class, null, ADD_EXPERIENCE);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            try {
                switch (requestCode) {
                    case UPDATE:
                        user.setEmail_id(data.getStringExtra(EMAIL));
                        user.setContact(data.getStringExtra(CONTACT));
                        presenter.updateUser(user);
                        break;
                    case POST_GRADUATION:
                        qualification.put(String.valueOf(POST_GRADUATION), new JSONObject(data.getStringExtra(DATA)));
                        user.setQualification(String.valueOf(qualification));
                        presenter.updateUser(user);
                        break;
                    case ADD_EXPERIENCE:
                        String[] experience = data.getStringExtra(DATA).split(",");
                        user.setExperience(experience[0]);
                        user.setLast_company(experience[1]);
                        user.setLast_designation(experience[2]);
                        presenter.updateUser(user);
                        break;
                }
            } catch (Exception e) {

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
