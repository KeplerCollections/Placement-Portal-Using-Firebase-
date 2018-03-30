package com.kepler.studentportal;

import com.kepler.projectsupportlib.MVP;
import com.kepler.studentportal.dao.User;

/**
 * Created by kepler on 28/3/18.
 */

public class VPLogiv {

    interface Base extends MVP.BaseView {
        void showFailureError(int message);
    }

    interface BaseMore extends Base {
        void showProgress(int message);

        void dismiss();
    }


    /********* Logic for login activity ************/
    public interface LoginView extends Base {
        void loggedIn();
    }

    public interface LoginPresenter extends MVP.BasePresenter<LoginView> {
        void login(String username,String password);
    }

    /********* Logic for SiginUp activity ************/
    public interface SignUpView extends Base {
        void registered();
    }

    public interface SignUpPresenter extends MVP.BasePresenter<SignUpView> {
        void register(User user);
    }

    /********* Logic for send otp************/
    public interface FpOtpSendView extends Base {
        void otpSent();
    }

    public interface FpOtpSendPresenter extends MVP.BasePresenter<FpOtpSendView> {
        void sendOtp(String number,String otp);
    }

    /********* Logic for verify otp************/
    public interface FpOtpVerifyView extends Base {
        void otpVerified();
    }

    public interface FpOtpVerifyPresenter extends MVP.BasePresenter<FpOtpVerifyView> {
        void verifyOtp(String number,String otp);
    }

    /********* Logic for change password************/
    public interface ChangePasswordView extends Base {
        void passwordChanged();
    }

    public interface ChangePasswordPresenter extends MVP.BasePresenter<ChangePasswordView> {
        void changePassword(String number,String password);
    }

    /********* Logic for company************/
    public interface CompanyView extends Base {
        void updateView();
    }

    public interface CompanyViewPresenter extends MVP.BasePresenter<CompanyView> {
        void loadCompanies();
    }

    /********* Logic for jobs************/
    public interface JobView extends Base {
        void updateView();
    }

    public interface JobViewPresenter extends MVP.BasePresenter<JobView> {
        void loadCompanies();
    }

    /********* Logic for search************/
    public interface SearchView extends Base {
        void updateView();
    }

    public interface SearchViewPresenter extends MVP.BasePresenter<SearchView> {
        void search(String category,String skill,String qualification);
    }


}
