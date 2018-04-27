package com.kepler.studentportal.dao;

import com.google.gson.annotations.Expose;

/**
 * Created by kepler on 28/3/18.
 */

public class CompanyDetails {
    @Expose
    private String company_id;
    @Expose
    private String company_name;
    @Expose
    private String company_website;
    @Expose
    private String company_emailid;
    @Expose
    private String company_address;

    public String getCompany_id() {
        return company_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public String getCompany_website() {
        return company_website;
    }

    public String getCompany_emailid() {
        return company_emailid;
    }

    public String getCompany_address() {
        return company_address;
    }
}
