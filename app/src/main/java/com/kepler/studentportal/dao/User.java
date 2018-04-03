package com.kepler.studentportal.dao;

import com.google.gson.annotations.Expose;

/**
 * Created by kepler on 28/3/18.
 */

public class User {
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
    private String date;
    @Expose
    private String status;


    public User(String first_name, String last_name, String contact, String password, String email_id, String gender, String experience, String last_company, String last_designation, String qualification) {
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
    }

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
}
