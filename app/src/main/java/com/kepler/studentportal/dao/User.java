package com.kepler.studentportal.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

/**
 * Created by kepler on 28/3/18.
 */

public class User implements Parcelable{


    @Expose
    private String id;
    @Expose
    private String first_name;
    @Expose
    private String last_name;
    @Expose
    private String contact;
    @Expose
    private String password;
    @Expose
    private String email_id;
    @Expose
    private String gender;
    @Expose
    private String experience;
    @Expose
    private String last_company;
    @Expose
    private String last_designation;
    @Expose
    private String qualification;
    @Expose
    private String passing_year;
    @Expose
    private String date;
    @Expose
    private String status;

    public User(String first_name, String last_name, String contact, String password, String email_id, String gender, String experience, String last_company, String last_designation, String qualification,String passing_year) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.contact = contact;
        this.password = password;
        this.email_id = email_id;
        this.gender = gender;
        this.experience = experience;
        this.last_company = last_company;
        this.last_designation = last_designation;
        this.qualification = qualification;
        this.passing_year = passing_year;
    }

    protected User(Parcel in) {
//        id = in.readString();
//        first_name = in.readString();
//        last_name = in.readString();
        contact = in.readString();
//        password = in.readString();
        email_id = in.readString();
//        gender = in.readString();
//        experience = in.readString();
//        last_company = in.readString();
//        last_designation = in.readString();
//        qualification = in.readString();
//        date = in.readString();
//        status = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getContact() {
        return contact;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail_id() {
        return email_id;
    }

    public String getGender() {
        return gender;
    }

    public String getExperience() {
        return experience;
    }

    public String getLast_company() {
        return last_company;
    }

    public String getLast_designation() {
        return last_designation;
    }

    public String getQualification() {
        return qualification;
    }

    public String getDate() {
        return date;
    }

    public String getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public void setLast_company(String last_company) {
        this.last_company = last_company;
    }

    public void setLast_designation(String last_designation) {
        this.last_designation = last_designation;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeString(id);
//        dest.writeString(first_name);
//        dest.writeString(last_name);
        dest.writeString(contact);
//        dest.writeString(password);
        dest.writeString(email_id);
//        dest.writeString(gender);
//        dest.writeString(experience);
//        dest.writeString(last_company);
//        dest.writeString(last_designation);
//        dest.writeString(qualification);
//        dest.writeString(date);
//        dest.writeString(status);
    }
}
