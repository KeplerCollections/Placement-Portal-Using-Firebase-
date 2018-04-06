package com.kepler.studentportal.modules.profile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.kepler.projectsupportlib.BaseActivity;
import com.kepler.studentportal.R;
import com.kepler.studentportal.dao.User;

import butterknife.BindView;

import static com.kepler.studentportal.support.Constants.CONTACT;
import static com.kepler.studentportal.support.Constants.DATA;
import static com.kepler.studentportal.support.Constants.EMAIL;

public class UpdateDetails extends BaseActivity {

    @BindView(R.id.et_contact)
    EditText et_contact;
    @BindView(R.id.et_email)
    EditText et_email;
    @BindView(R.id.b_update)
    Button b_update;
    private Bundle bundle;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getIntent().getExtras();
        if (bundle == null || bundle.getParcelable(DATA)==null || !(bundle.getParcelable(DATA) instanceof User)) {
            onBackPressed();
        } else {
            enableBackButton();
            setTitle(R.string.update);
            user=bundle.getParcelable(DATA);
            et_contact.setText(user.getContact());
            et_email.setText(user.getEmail_id());
            b_update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (et_contact.getText().toString().trim().isEmpty() || et_contact.getText().toString().trim().length()<10) {
                        et_contact.setError(getString(R.string.err_field_is_not_valid));
                        et_contact.requestFocus();
                        return;
                    }
                    if (et_email.getText().toString().trim().isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(et_email.getText().toString()).matches()) {
                        et_email.setError(getString(R.string.err_field_is_not_valid));
                        et_email.requestFocus();
                        return;
                    }
                    try {
                        Intent bundle = new Intent();
                        bundle.putExtra(EMAIL, et_email.getText().toString().trim());
                        bundle.putExtra(CONTACT, et_contact.getText().toString().trim());
                        setResult(Activity.RESULT_OK, bundle);
                    } catch (Exception e) {

                    }
                    onBackPressed();
                }
            });
        }
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
        return R.layout.activity_update_details;
    }

}
