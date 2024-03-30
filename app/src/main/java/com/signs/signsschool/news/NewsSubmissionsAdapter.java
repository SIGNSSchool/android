package com.signs.signsschool.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.signs.signsschool.R;

import java.util.List;

public class NewsSubmissionsAdapter extends ArrayAdapter<ModelNewsSubmission> {

    private final Context activityContext;
    private final List<ModelNewsSubmission> list;

    public NewsSubmissionsAdapter(Context context, List<ModelNewsSubmission> list){
        super(context, R.layout.wahleinsichtrow, list);
        this.activityContext = context;
        this.list = list;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup){

        final NewsSubmissionsAdapter.ViewHolder viewHolder;

        if (view == null) {
            view = LayoutInflater.from(activityContext).inflate(R.layout.wahleinsichtrow, null);
            viewHolder = new NewsSubmissionsAdapter.ViewHolder();

            viewHolder.name = (TextView) view.findViewById(R.id.nameWahlEinsicht);
            viewHolder.date = (TextView) view.findViewById(R.id.dateWahlEinsicht);
            viewHolder.title = (TextView) view.findViewById(R.id.titleWahlEinsicht);

            String name = list.get(position).getUser().getFirstName() + " " + list.get(position).getUser().getLastName();

            viewHolder.name.setText(name);
            viewHolder.date.setText(list.get(position).getSubmittedAt());
            viewHolder.title.setText(list.get(position).getTitle());

            view.setTag(viewHolder);
        } else {
            viewHolder = (NewsSubmissionsAdapter.ViewHolder) view.getTag();
        }

        return view;
    }

    private static class ViewHolder {
        TextView name;
        TextView date;
        TextView title;
    }
}
