package com.kepler.studentportal.modules.signup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.kepler.projectsupportlib.MVPActivity;
import com.kepler.studentportal.R;
import com.kepler.studentportal.VPLogiv;
import com.kepler.studentportal.api.BaseResponse;
import com.kepler.studentportal.dao.User;
import com.kepler.studentportal.modules.home.HomeActivity;
import com.kepler.studentportal.modules.login.LoginActivity;
import com.kepler.studentportal.support.PrefManager;

import org.json.JSONObject;

import java.util.HashMap;

import butterknife.BindView;

import static com.kepler.studentportal.support.Constants.DATA;
import static com.kepler.studentportal.support.Constants.KEY;
import static com.kepler.studentportal.support.Constants.TITLE;

public class SignUpActivity extends MVPActivity<VPLogiv.SignUpPresenter> implements VPLogiv.SignUpView, View.OnClickListener {

    private static final int HIGH_SCHOOL = 10;
    private static final int INTERMEDIATE = 12;
    private static final int GRADUATION = 14;
    private static final int POST_GRADUATION = 16;
    @BindView(R.id.b_register)
    Button b_register;
    @BindView(R.id.et_first_name)
    EditText et_first_name;
    @BindView(R.id.et_last_name)
    EditText et_last_name;
    @BindView(R.id.et_email)
    EditText et_email;
    @BindView(R.id.et_password)
    EditText et_password;
    @BindView(R.id.et_contact)
    EditText et_contact;
    @BindView(R.id.gender)
    RadioGroup gender;
    @BindView(R.id.add_highschool)
    TextView add_highschool;
    @BindView(R.id.add_intermediate)
    TextView add_intermediate;
    @BindView(R.id.add_graduation)
    TextView add_graduation;
    @BindView(R.id.add_post_graduation)
    TextView add_post_graduation;
    @BindView(R.id.ll_education)
    LinearLayout ll_education;
    @BindView(R.id.et_experience)
    EditText et_experience;
    @BindView(R.id.et_company)
    EditText et_company;
    @BindView(R.id.et_designation)
    EditText et_designation;
    @BindView(R.id.terms_and_condition)
    CheckBox terms_and_condition;
    private HashMap<String, View> integerViewHashMap = new HashMap<>();
    private JSONObject qualification=new JSONObject();
    private Bundle bundle;

    @Override
    protected VPLogiv.SignUpPresenter createPresenter() {
        return new SignUpImpe();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enableBackButton();
        bundle=getIntent().getExtras();
        setTitle((bundle==null)? R.string.sign_up : bundle.getInt(TITLE,R.string.sign_up));
        add_highschool.setOnClickListener(this);
        add_intermediate.setOnClickListener(this);
        add_graduation.setOnClickListener(this);
        add_post_graduation.setOnClickListener(this);
        b_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_first_name.getText().toString().trim().isEmpty()) {
                    et_first_name.setError(getString(R.string.err_field_is_empty));
                    et_first_name.requestFocus();
                    return;
                }
                if (et_last_name.getText().toString().trim().isEmpty()) {
                    et_last_name.setError(getString(R.string.err_field_is_empty));
                    et_last_name.requestFocus();
                    return;
                }
                if (et_email.getText().toString().trim().isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(et_email.getText().toString()).matches()) {
                    et_email.setError(getString(R.string.err_field_is_not_valid));
                    et_email.requestFocus();
                    return;
                }
                if (et_contact.getText().toString().trim().isEmpty() || et_contact.getText().toString().length() < 10) {
                    et_contact.setError(getString(R.string.err_field_is_not_valid));
                    et_contact.requestFocus();
                    return;
                }
                if (et_password.getText().toString().trim().isEmpty() || et_password.getText().toString().length() < 6) {
                    et_password.setError(getString(R.string.err_field_is_not_valid));
                    et_password.requestFocus();
                    return;
                }
                if (add_highschool.getVisibility() == View.VISIBLE) {
                    showToast(R.string.add_highschool);
                    add_highschool.requestFocus();
                    return;
                }
                if (add_intermediate.getVisibility() == View.VISIBLE) {
                    showToast(R.string.add_intermediate);
                    add_intermediate.requestFocus();
                    return;
                }
                if (add_graduation.getVisibility() == View.VISIBLE) {
                    showToast(R.string.add_graduation);
                    add_graduation.requestFocus();
                    return;
                }
                if (add_post_graduation.getVisibility() == View.VISIBLE) {
                    showToast(R.string.add_post_graduation);
                    add_post_graduation.requestFocus();
                    return;
                }
                if (!et_experience.getText().toString().trim().isEmpty()) {
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
                }
                if (!terms_and_condition.isChecked()) {
                    terms_and_condition.setError(getString(R.string.err_field_is_empty));
                    terms_and_condition.requestFocus();
                    return;
                }
                presenter.register(new User(et_first_name.getText().toString().trim(),
                        et_last_name.getText().toString().trim(),
                        et_contact.getText().toString().trim(),
                        et_password.getText().toString().trim(),
                        et_email.getText().toString().trim(),
                        findViewById(gender.getCheckedRadioButtonId()).getTag().toString(),
                        et_experience.getText().toString().trim(),
                        et_company.getText().toString().trim(),
                        et_designation.getText().toString().trim(),
                        qualification.toString()
                        ));
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
        return R.layout.activity_sign_up;
    }


    @Override
    public void showProgress(int message) {
        showProgressBar(message);
    }

    @Override
    public void dismiss() {
        dismissProgressBar();
    }

    @Override
    public void showFailureError(int message) {
        showErrorDialog(getString(message), null);
    }

    @Override
    public void onClick(View v) {
        Bundle bundle=new Bundle();
        bundle.putInt(KEY,v.getId());
        switch (v.getId()) {
            case R.id.add_highschool:
                startActivity(AddEducation.class,bundle,HIGH_SCHOOL);
                break;
            case R.id.add_intermediate:
                startActivity(AddEducation.class,bundle,INTERMEDIATE);
                break;
            case R.id.add_graduation:
                startActivity(AddEducation.class,bundle,GRADUATION);
                break;
            case R.id.add_post_graduation:
                startActivity(AddEducation.class,bundle,POST_GRADUATION);
                break;
        }
    }

    private void addView(TextView button, int sTitle) {
        if (!integerViewHashMap.containsKey(button.getTag())) {
            View view = getLayoutInflater().inflate(R.layout.education_list, null);
            TextView title = view.findViewById(R.id.title);
            ImageButton delete = view.findViewById(R.id.delete);
            title.setText(sTitle);
            delete.setTag(button.getTag());
            delete.setOnClickListener(new MyClickListener());
            integerViewHashMap.put((String) button.getTag(), view);
            ll_education.addView(integerViewHashMap.get(button.getTag()));
        }else {
            integerViewHashMap.get(button.getTag()).setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK){
            try {
                switch (requestCode){
                    case HIGH_SCHOOL:
                        qualification.put(String.valueOf(HIGH_SCHOOL),data.getStringExtra(DATA));
                        addView(add_highschool,R.string.highschool);
                        add_highschool.setVisibility(View.GONE);
                        break;
                    case INTERMEDIATE:
                        qualification.put(String.valueOf(INTERMEDIATE),data.getStringExtra(DATA));
                        addView(add_intermediate,R.string.intermediate);
                        add_intermediate.setVisibility(View.GONE);
                        break;
                    case GRADUATION:
                        qualification.put(String.valueOf(GRADUATION),data.getStringExtra(DATA));
                        addView(add_graduation,R.string.graduation);
                        add_graduation.setVisibility(View.GONE);
                        break;
                    case POST_GRADUATION:
                        qualification.put(String.valueOf(POST_GRADUATION),data.getStringExtra(DATA));
                        addView(add_post_graduation,R.string.post_graduation);
                        add_post_graduation.setVisibility(View.GONE);
                        break;
                }
            }catch (Exception e){

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void registered(BaseResponse response) throws Exception {
        if (response.isStatus()) {
            showToast(R.string.registered);
            startActivity(LoginActivity.class);
            finish();
        } else {
            showAlertDialog(response.getMessage(), null);
        }
    }

    private class MyClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch ((String) v.getTag()) {
                case "10":
                    integerViewHashMap.get(v.getTag()).setVisibility(View.GONE);
                    add_highschool.setVisibility(View.VISIBLE);
                    break;
                case "12":
                    integerViewHashMap.get(v.getTag()).setVisibility(View.GONE);
                    ll_education.removeView(v);
                    add_intermediate.setVisibility(View.VISIBLE);
                    break;
                case "14":
                    integerViewHashMap.get(v.getTag()).setVisibility(View.GONE);
                    ll_education.removeView(v);
                    add_graduation.setVisibility(View.VISIBLE);
                    break;
                case "16":
                    integerViewHashMap.get(v.getTag()).setVisibility(View.GONE);
                    ll_education.removeView(v);
                    add_post_graduation.setVisibility(View.VISIBLE);
                    break;
            }
        }
    }
}
