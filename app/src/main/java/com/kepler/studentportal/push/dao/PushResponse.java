package com.kepler.studentportal.push.dao;

import com.google.gson.annotations.Expose;

/**
 * Created by kepler on 21/3/18.
 */

public class PushResponse {
    @Expose
    private Boolean status;
    @Expose
    private Integer keycode;

    public Boolean getStatus() {
        return status;
    }

    public Integer getKeycode() {
        return keycode;
    }
}
