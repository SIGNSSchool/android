package com.signs.signsschool.news;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.signs.signsschool.HomeService;
import com.signs.signsschool.R;

import java.io.Serializable;
import java.util.ArrayList;

public class NewsHomeAdapter extends RecyclerView.Adapter<NewsHomeAdapter.ViewHolder> {

    private final Context activityContext;
    HomeService homeService;
    private final ArrayList<ModelNews> arrayList;

    public NewsHomeAdapter(Context context, ArrayList<ModelNews> list){
        this.activityContext = context;
        this.arrayList = list;
    }

    @NonNull
    @Override
    public NewsHomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewsHomeAdapter.ViewHolder(LayoutInflater.from(activityContext).inflate(R.layout.newshomerow, parent, false));
    }

    @Override
    public void onAttachedToRecyclerView(
            RecyclerView recyclerView)
    {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onBindViewHolder(final NewsHomeAdapter.ViewHolder holder, final int position) {

        ModelNews recyclerData = arrayList.get(position);

        holder.TitleTV.setText(recyclerData.getTitle());
        holder.itemView.setOnClickListener(holder);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(activityContext, NewsDetail.class);
                intent.putExtra("titleNews", recyclerData.getTitle());
                intent.putExtra("descriptionNews", recyclerData.getDescription());
               // intent.putExtra("dateNews", recyclerData.getDate());
                //intent.putExtra("message_idNews", recyclerData.getId());

                Bundle args = new Bundle();
                args.putSerializable("ARRAYLIST", (Serializable) recyclerData.getOptions());
                intent.putExtra("optionsNews", args);

                activityContext.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView TitleTV;
        RecyclerView recyclerView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            recyclerView = itemView.findViewById(R.id.recyclerViewNewsHome);
            TitleTV = itemView.findViewById(R.id.newsTVHome);
        }
        @Override
        public void onClick(View view) {

        }

    }
}

