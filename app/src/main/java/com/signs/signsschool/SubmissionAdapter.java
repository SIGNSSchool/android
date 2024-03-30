package com.signs.signsschool;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.signs.signsschool.signs.Submission;

import java.util.List;

public class SubmissionAdapter extends ArrayAdapter<Submission> {

    private final Context activityContext;
    private final List<Submission> list;

    public SubmissionAdapter(Context context, List<Submission> list){
        super(context, R.layout.laufgabebewertungenrow, list);
        this.activityContext = context;
        this.list = list;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup){

        final SubmissionAdapter.ViewHolder viewHolder;

        if (view == null) {
            view = LayoutInflater.from(activityContext).inflate(R.layout.laufgabebewertungenrow, null);
            viewHolder = new SubmissionAdapter.ViewHolder();

            viewHolder.name = (TextView) view.findViewById(R.id.nameLAufgabeBewertungen);
            viewHolder.createdAt = (TextView) view.findViewById(R.id.dateLAufgabeBewertungen);

            String name = list.get(position).getUser().getFirstName() + " "+ list.get(position).getUser().getLastName();

            viewHolder.name.setText(name);
            viewHolder.createdAt.setText(list.get(position).getCreatedAt().substring(0,10));

            view.setTag(viewHolder);
        } else {
            viewHolder = (SubmissionAdapter.ViewHolder) view.getTag();
        }

        return view;
    }

    private static class ViewHolder {

        TextView name;
        TextView createdAt;
    }


}
