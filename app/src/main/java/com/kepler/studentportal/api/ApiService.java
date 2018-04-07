package com.kepler.studentportal.api;

import com.kepler.studentportal.dao.CompanyDetails;
import com.kepler.studentportal.dao.JobDetails;
import com.kepler.studentportal.dao.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import static com.kepler.studentportal.api.ApiClient.CATEGORY;
import static com.kepler.studentportal.api.ApiClient.COMPANY_ID;
import static com.kepler.studentportal.api.ApiClient.OTP;
import static com.kepler.studentportal.api.ApiClient.PASSWORD;
import static com.kepler.studentportal.api.ApiClient.QUALIFICATION;
import static com.kepler.studentportal.api.ApiClient.SKILL;
import static com.kepler.studentportal.api.ApiClient.USERNAME;
import static com.kepler.studentportal.support.Constants.PASSING_YEAR;

/**
 * Created by kepler on 2/4/18.
 */

public interface ApiService {

    @FormUrlEncoded
    @POST("controller.php?action=login")
    Call<BaseResponse> login(@Field(USERNAME) String user_name, @Field(PASSWORD) String passwordn, @Field(PASSING_YEAR) String passing_year);

    @POST("controller.php?action=register")
    Call<BaseResponse> register(@Body User user);

    @FormUrlEncoded
    @POST("controller.php?action=forgot_password")
    Call<BaseResponse> sendOTP(@Field(USERNAME) String user_name, @Field(OTP) String otp);

    @GET("controller.php?action=getCompanies")
    Call<ApiResponse<CompanyDetails>> getCompanies();

    @GET("controller.php?action=getJobs")
    Call<ApiResponse<JobDetails>> getJobs(@Query(COMPANY_ID) String company_id);

    @GET("controller.php?action=getUser")
    Call<ApiResponse<User>> getUser(@Query(USERNAME) String username);

    @POST("controller.php?action=updateUser")
    Call<ApiResponse<User>> updateUser(@Body User user);

    @FormUrlEncoded
    @POST("controller.php?action=changePassword")
    Call<BaseResponse> changePassword(@Field(USERNAME) String user_name, @Field(PASSWORD) String password);

    @GET("controller.php?action=search")
    Call<ApiResponse<JobDetails>> search(@Query(CATEGORY) String category,@Query(SKILL) String skill,@Query(QUALIFICATION) String qualification);

    @GET("Results WAT (24.08.2017).xlsx")
    Call<ResponseBody> downloadResult();

}
