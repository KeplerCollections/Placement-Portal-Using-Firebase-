package com.kepler.studentportal.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kepler.studentportal.OnItemClickListener;
import com.kepler.studentportal.R;
import com.kepler.studentportal.dao.JobDetails;

import java.util.List;

/**
 * Created by kepler on 28/3/18.
 */

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.JobHolder> {
    private final OnItemClickListener<JobDetails> onItemClickListener;
    Context context;
    private List<JobDetails> detailsList;

    public JobAdapter(List<JobDetails> detailsList, Context context, OnItemClickListener<JobDetails> onItemClickListener) {
        this.detailsList = detailsList;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public JobHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflate the layout file
        View categoryView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_job, parent, false);
        JobHolder cvh = new JobHolder(categoryView);
        return cvh;
    }

    @Override
    public void onBindViewHolder(JobHolder holder, final int position) {
//        holder.imageView.setImageResource(R.drawable.ic_action_favorite);
//        holder.bind(detailsList.get(position));
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class JobHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txt_job_name, txt_job_website, txt_job_email_id, txt_job_exp;

        public JobHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            txt_job_name = view.findViewById(R.id.txt_job_name);
            txt_job_website = view.findViewById(R.id.txt_job_website);
            txt_job_email_id = view.findViewById(R.id.txt_job_email_id);
            txt_job_exp = view.findViewById(R.id.txt_job_exp);
        }

        public void bind(JobDetails jobDetails) {
        }

        @Override
        public void onClick(View v) {
//            onItemClickListener.OnItemClickListener(detailsList.get(getAdapterPosition()));
        }
    }
}
