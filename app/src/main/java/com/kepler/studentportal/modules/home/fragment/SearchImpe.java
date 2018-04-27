package com.kepler.studentportal.modules.home.fragment;

import android.database.Cursor;

import com.kepler.projectsupportlib.BasePresenterImpl;
import com.kepler.projectsupportlib.Logger;
import com.kepler.studentportal.R;
import com.kepler.studentportal.VPLogiv;
import com.kepler.studentportal.api.ApiClient;
import com.kepler.studentportal.api.ApiResponse;
import com.kepler.studentportal.dao.JobDetails;
import com.kepler.studentportal.dao.Program;
import com.kepler.studentportal.dao.ProgramCategoty;
import com.kepler.studentportal.db.DatabaseManger;
import com.kepler.studentportal.support.Constants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kepler on 2/4/18.
 */

public class SearchImpe extends BasePresenterImpl<VPLogiv.SearchView> implements VPLogiv.SearchViewPresenter, Callback<ApiResponse<JobDetails>> {

    List<ProgramCategoty> programCategoties = new ArrayList<>();
    List<Program> programs = new ArrayList<>();
    private Cursor cursor;

    @Override
    public void onResponse(Call<ApiResponse<JobDetails>> call, Response<ApiResponse<JobDetails>> response) {
        Logger.print(String.valueOf(response));
        if (view == null) {
            return;
        }
        view.dismiss();
        try {
            view.updateView(response.body());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onFailure(Call<ApiResponse<JobDetails>> call, Throwable t) {
        Logger.print(t);
        if (view == null) {
            return;
        }
        view.dismiss();
        view.showFailureError(R.string.error);
    }

    @Override
    public void search(String qualification) {
        if (view == null)
            return;
        view.showProgress(R.string.loading);
        ApiClient.getClientService().search(qualification).enqueue(this);
    }

    @Override
    public List<ProgramCategoty> getProgramCategory(int is_technical) {
        programCategoties.clear();
        if(view==null)
            return programCategoties;
        try {
            cursor = DatabaseManger.getInstance(view.getAppContext()).get(DatabaseManger.TABLE_PROGRAM_CATEGORY, " where " + Constants.IS_TECHNICAL + "=?", new String[]{String.valueOf(is_technical)});
            if (cursor.moveToFirst()) {
                do {
                    programCategoties.add(new ProgramCategoty(cursor.getInt(cursor.getColumnIndex(Constants.ID)),
                            cursor.getString(cursor.getColumnIndex(Constants.PROGRAM_CATEGORY)),
                            cursor.getInt(cursor.getColumnIndex(Constants.IS_TECHNICAL))));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {


        } finally {
            if(cursor!=null)
            cursor.close();
        }
        return programCategoties;
    }

    @Override
    public List<Program> getProgram(int progran_category_id) {
        programs.clear();
        if(view==null)
            return programs;
        try {
            cursor = DatabaseManger.getInstance(view.getAppContext()).get(DatabaseManger.TABLE_PROGRAM, " where " + Constants.PROGRAM_CATEGORY_ID + "=?", new String[]{String.valueOf(progran_category_id)});
            if (cursor.moveToFirst()) {
                do {
                    programs.add(new Program(cursor.getInt(cursor.getColumnIndex(Constants.ID)),
                            cursor.getString(cursor.getColumnIndex(Constants.PROGRAM)),
                            cursor.getInt(cursor.getColumnIndex(Constants.PROGRAM_CATEGORY_ID))));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {


        } finally {
            if(cursor!=null)
                cursor.close();
        }
        return programs;
    }
}
