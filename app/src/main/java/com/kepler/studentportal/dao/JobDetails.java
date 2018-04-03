package com.kepler.studentportal.dao;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by kepler on 28/3/18.
 */

public class JobDetails implements Parcelable {
    protected JobDetails(Parcel in) {
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
    }
}
