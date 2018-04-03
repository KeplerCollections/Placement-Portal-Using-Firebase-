package com.kepler.studentportal.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kepler on 2/4/18.
 */

public class ApiClient {
    public static final String ACTION="action";
    public static final String COMPANY_ID="company_id";
    public static final String CATEGORY="category";
    public static final String SKILL="skill";
    public static final String QUALIFICATION="qualification";
    public static final String USERNAME="username";
    public static final String USER_ID="userId";
    public static final String PASSWORD="password";
    public static final String OTP="otp";
    private static ApiService service;

    private ApiClient(){

    }
    public static ApiService getClientService(){
        if(service==null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://www.databricktechnologies.com/www/ami/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            service = retrofit.create(ApiService.class);
        }
        return service;
    }
}
