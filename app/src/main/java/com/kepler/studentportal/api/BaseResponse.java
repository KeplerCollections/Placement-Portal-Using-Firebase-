package com.kepler.studentportal.api;

import com.google.gson.annotations.Expose;

/**
 * Created by kepler on 2/4/18.
 */

public class BaseResponse{
    @Expose
    private int code;
    @Expose
    private boolean status;
    @Expose
    private String message;

    public int getCode() {
        return code;
    }

    public boolean isStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

}
