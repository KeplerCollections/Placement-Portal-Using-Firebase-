package com.kepler.studentportal.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

/**
 * Created by kepler on 28/3/18.
 */

public class JobDetails implements Parcelable {
    @Expose
    private String job_id;
    @Expose
    private String company_id;
    @Expose
    private String job_profile;
    @Expose
    private String job_apply_url;
    @Expose
    private String job_min_exp_req;
    @Expose
    private String job_max_exp_req;
    @Expose
    private String job_category;
    @Expose
    private String job_skill;
    @Expose
    private String job_qualification;
    @Expose
    private String interview_vanue;
    @Expose
    private String date;

    protected JobDetails(Parcel in) {
        job_id = in.readString();
        company_id = in.readString();
        job_profile = in.readString();
        job_apply_url = in.readString();
        job_min_exp_req = in.readString();
        job_max_exp_req = in.readString();
        job_category = in.readString();
        job_skill = in.readString();
        job_qualification = in.readString();
        interview_vanue = in.readString();
        date = in.readString();
    }

    public static final Creator<JobDetails> CREATOR = new Creator<JobDetails>() {
        @Override
        public JobDetails createFromParcel(Parcel in) {
            return new JobDetails(in);
        }

        @Override
        public JobDetails[] newArray(int size) {
            return new JobDetails[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(job_id);
        dest.writeString(company_id);
        dest.writeString(job_profile);
        dest.writeString(job_apply_url);
        dest.writeString(job_min_exp_req);
        dest.writeString(job_max_exp_req);
        dest.writeString(job_category);
        dest.writeString(job_skill);
        dest.writeString(job_qualification);
        dest.writeString(interview_vanue);
        dest.writeString(date);
    }

    public String getJob_id() {
        return job_id;
    }

    public String getCompany_id() {
        return company_id;
    }

    public String getJob_profile() {
        return job_profile;
    }

    public String getJob_apply_url() {
        return job_apply_url;
    }

    public String getJob_min_exp_req() {
        return job_min_exp_req;
    }

    public String getJob_max_exp_req() {
        return job_max_exp_req;
    }

    public String getJob_category() {
        return job_category;
    }

    public String getJob_skill() {
        return job_skill;
    }

    public String getJob_qualification() {
        return job_qualification;
    }

    public String getInterview_vanue() {
        return interview_vanue;
    }

    public String getDate() {
        return date;
    }
}
