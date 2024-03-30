package com.signs.signsschool.absences;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.signs.signsschool.R;
import com.signs.signsschool.absences.models.Absence;

import java.util.List;

public class AbsencesAdapter extends ArrayAdapter<Absence> {
    private final Context activityContext;
    private final List<Absence> list;

    public AbsencesAdapter(Context context, List<Absence> list){
        super(context, R.layout.fehlstundenleinsichtrow, list);
        this.activityContext = context;
        this.list = list;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup){
        final AbsencesAdapter.ViewHolder viewHolder;

        if (view == null) {
            view = LayoutInflater.from(activityContext).inflate(R.layout.fehlstundenleinsichtrow, null);
            viewHolder = new AbsencesAdapter.ViewHolder();

            viewHolder.name = (TextView) view.findViewById(R.id.nameFehlstundenLEinsicht);
            viewHolder.date = (TextView) view.findViewById(R.id.dateFehlstundenLEinsicht);
            viewHolder.status = (TextView) view.findViewById(R.id.statusFehlstundenLEinsicht);
            viewHolder.reason = (TextView) view.findViewById(R.id.reasonFehlstundenLEinsicht);

            String name = list.get(position).getUser().getFirstName() + " " + list.get(position).getUser().getLastName();
            String status = list.get(position).getExcused() ? "Entschuldigt" : "Nicht entschuldigt";

            viewHolder.name.setText(name);
            viewHolder.date.setText(list.get(position).getCreatedAt());
            viewHolder.status.setText(status);
            viewHolder.reason.setText(list.get(position).getReason());

            view.setTag(viewHolder);
        } else {
            viewHolder = (AbsencesAdapter.ViewHolder) view.getTag();
        }
        return view;
    }

    private static class ViewHolder {
        TextView name;
        TextView date;
        TextView status;
        TextView reason;
    }
}
