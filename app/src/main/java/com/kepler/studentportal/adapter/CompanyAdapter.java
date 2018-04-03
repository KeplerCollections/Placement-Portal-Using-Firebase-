package com.kepler.studentportal.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kepler.studentportal.OnItemClickListener;
import com.kepler.studentportal.R;
import com.kepler.studentportal.dao.CompanyDetails;

import java.util.List;

/**
 * Created by kepler on 28/3/18.
 */

public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.CompanyViewHolder> {
    private final OnItemClickListener<CompanyDetails> onItemClickListener;
    Context context;
    private List<CompanyDetails> detailsList;

    public CompanyAdapter(List<CompanyDetails> detailsList, Context context, OnItemClickListener<CompanyDetails> onItemClickListener) {
        this.detailsList = detailsList;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public CompanyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflate the layout file
        View categoryView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_company, parent, false);
        CompanyViewHolder cvh = new CompanyViewHolder(categoryView);
        return cvh;
    }

    @Override
    public void onBindViewHolder(CompanyViewHolder holder, final int position) {
//        holder.imageView.setImageResource(R.drawable.ic_action_favorite);
//        holder.bind(detailsList.get(position));
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class CompanyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txt_company_name, txt_company_website, txt_company_email_id, txt_company_address;

        public CompanyViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            txt_company_name = view.findViewById(R.id.txt_company_name);
            txt_company_website = view.findViewById(R.id.txt_company_website);
            txt_company_email_id = view.findViewById(R.id.txt_company_email_id);
            txt_company_address = view.findViewById(R.id.txt_company_address);
        }

        public void bind(CompanyDetails companyDetails) {
        }

        @Override
        public void onClick(View v) {
//            onItemClickListener.OnItemClickListener(detailsList.get(getAdapterPosition()));
            onItemClickListener.OnItemClickListener(null);
        }
    }
}
