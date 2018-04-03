package com.kepler.studentportal.api;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kepler on 2/4/18.
 */

public class ApiResponse<T> extends BaseResponse {

    private List<T> data =new ArrayList<>();

    public List<T> getData() {
        return data;
    }
}
