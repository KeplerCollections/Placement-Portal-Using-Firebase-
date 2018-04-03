package com.kepler.studentportal;

import com.kepler.projectsupportlib.MVP;
import com.kepler.studentportal.api.ApiResponse;
import com.kepler.studentportal.api.BaseResponse;
import com.kepler.studentportal.dao.User;

/**
 * Created by kepler on 28/3/18.
 */

public class VPLogiv {

    interface Base extends MVP.BaseView {
        void showFailureError(int message);
    }

    interface APIBase extends Base {
        void showProgress(int message);
        void dismiss();
    }


    /********* Logic for login activity ************/
    public interface LoginView extends APIBase {
        void loggedIn(BaseResponse response) throws Exception;
    }

    public interface LoginPresenter extends MVP.BasePresenter<LoginView> {
        void login(String username,String password);
    }

    /********* Logic for SiginUp activity ************/
    public interface SignUpView extends APIBase {
        void registered(BaseResponse response) throws Exception;
    }

    public interface SignUpPresenter extends MVP.BasePresenter<SignUpView> {
        void register(User user);
    }

    /********* Logic for send otp************/
    public interface FpOtpSendView extends APIBase {
        void otpSent(BaseResponse response) throws Exception;
    }

    public interface FpOtpSendPresenter extends MVP.BasePresenter<FpOtpSendView> {
        void sendOtp(String number,String otp);
    }

//    /********* Logic for verify otp************/
//    public interface FpOtpVerifyView extends APIBase {
//        void otpVerified(ApiResponse response) throws Exception;
//    }
//
//    public interface FpOtpVerifyPresenter extends MVP.BasePresenter<FpOtpVerifyView> {
//        void verifyOtp(String number,String otp);
//    }

    /********* Logic for change password************/
    public interface ChangePasswordView extends APIBase {
        void passwordChanged(BaseResponse response) throws Exception;
    }

    public interface ChangePasswordPresenter extends MVP.BasePresenter<ChangePasswordView> {
        void changePassword(String number,String password);
    }

    /********* Logic for company************/
    public interface CompanyView extends APIBase {
        void updateView(ApiResponse response) throws Exception;
    }

    public interface CompanyViewPresenter extends MVP.BasePresenter<CompanyView> {
        void loadCompanies();
    }

    /********* Logic for jobs************/
    public interface JobView extends APIBase {
        void updateView(ApiResponse response) throws Exception;
    }

    public interface JobViewPresenter extends MVP.BasePresenter<JobView> {
        void getJobs(String company_id);
    }

    /********* Logic for search************/
    public interface SearchView extends APIBase {
        void updateView(ApiResponse response) throws Exception;
    }

    public interface SearchViewPresenter extends MVP.BasePresenter<SearchView> {
        void search(String category,String skill,String qualification);
    }

    /********* Logic for profile************/
    public interface ProfileView extends APIBase {
        void updateView(ApiResponse response) throws Exception;
    }

    public interface ProfileViewPresenter extends MVP.BasePresenter<ProfileView> {
        void getUser(String username);
        void updateUser(User user);
    }


}
