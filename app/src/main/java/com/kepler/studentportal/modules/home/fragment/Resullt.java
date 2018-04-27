package com.kepler.studentportal.modules.home.fragment;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.kepler.projectsupportlib.Logger;
import com.kepler.projectsupportlib.MVPFragment;
import com.kepler.studentportal.R;
import com.kepler.studentportal.VPLogiv;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;

import butterknife.BindView;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by kepler on 28/3/18.
 */

public class Resullt extends MVPFragment<VPLogiv.ResultViewPresenter> implements VPLogiv.ResultView {
    @BindView(R.id.rv_list)
    ListView rv_list;
    @BindView(R.id.b_reload)
    Button b_reload;
    private ArrayAdapter<File> adapter;


    public static Resullt getInstance() {
        return new Resullt();
    }

    @Override
    protected String getFragmentTitle() {
        return getResources().getString(R.string.result);
    }

    @Override
    protected VPLogiv.ResultViewPresenter createPresenter() {
        return new ResultImpe();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        b_reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.downloadFile();
            }
        });
        rv_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Uri path = Uri.fromFile(adapter.getItem(position));
                Intent pdfOpenintent = new Intent(Intent.ACTION_VIEW);
                pdfOpenintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                pdfOpenintent.setDataAndType(path, "application/vnd.ms-excel");
                try {
                    startActivity(pdfOpenintent);
                } catch (ActivityNotFoundException e) {
                    showFailureError(R.string.app_not_found);

                }
            }
        });
        setAdapter();
    }

    @Override
    protected int getViewResource() {
        return R.layout.fragment_result;
    }

    @Override
    public void showProgress(int message) {
        fragmentCommunicator.showProgressBar(message);
    }

    @Override
    public void dismiss() {
        fragmentCommunicator.dismissProgressBar();
    }


    @Override
    public void showFailureError(int message) {
        fragmentCommunicator.showDialog(null,getString(message), null, Logger.DIALOG_ERROR);

    }


    @Override
    public void updateView(Response<ResponseBody> response) {
        if (response.isSuccessful()) {
            writeResponseBodyToDisk(response.body());
            setAdapter();
        } else {
            fragmentCommunicator.showDialog(null,response.message(), null, Logger.DIALOG_ALERT);
        }
    }

    private void setAdapter() {
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, getListOfFiles());
//                rv_list.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
//                LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
//                rv_list.setLayoutManager(horizontalLayoutManager);
        rv_list.setAdapter(adapter);
    }

    private boolean writeResponseBodyToDisk(ResponseBody body) {
        try {
            // todo change the file location/name according to your needs
            String path = Environment.getExternalStorageDirectory().toString() + "/.ah_am_result";

            File futureStudioIconFile = new File(path + File.separator + Calendar.getInstance().getTime() + ".xlsx");

            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(futureStudioIconFile);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;

                    Logger.d("", "file download: " + fileSizeDownloaded + " of " + fileSize);
                }

                outputStream.flush();

                return true;
            } catch (IOException e) {
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }

    private File[] getListOfFiles() {
        String path = Environment.getExternalStorageDirectory().toString() + "/.ah_am_result";
        File directory = new File(path);
        if (!directory.exists())
            directory.mkdir();
        File[] files = directory.listFiles();
        Logger.e("Files", "Size: " + files.length);
        return files;
    }
}
