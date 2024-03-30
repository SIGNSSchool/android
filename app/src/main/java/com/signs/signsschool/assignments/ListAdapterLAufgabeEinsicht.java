package com.signs.signsschool.assignments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.signs.signsschool.R;

import java.util.List;

public class ListAdapterLAufgabeEinsicht extends ArrayAdapter<ModelLAufgabeEinsicht> {

    private final Context activityContext;
    private final List<ModelLAufgabeEinsicht> list;

    public ListAdapterLAufgabeEinsicht(Context context, List<ModelLAufgabeEinsicht> list){
        super(context, R.layout.laufgabeeinsichtrow, list);
        this.activityContext = context;
        this.list = list;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup){

        final ListAdapterLAufgabeEinsicht.ViewHolder viewHolder;

        if (view == null) {
            view = LayoutInflater.from(activityContext).inflate(R.layout.laufgabeeinsichtrow, null);
            viewHolder = new ListAdapterLAufgabeEinsicht.ViewHolder();

            viewHolder.name = (TextView) view.findViewById(R.id.nameLAufgabeEinsicht);
            viewHolder.date = (TextView) view.findViewById(R.id.dateLAufgabeEinsicht);
            viewHolder.title = (TextView) view.findViewById(R.id.titleLAufgabeEinsicht);

            viewHolder.name.setText(list.get(position).getName());
            viewHolder.date.setText(list.get(position).getDate());
            viewHolder.title.setText(list.get(position).getTitle());

            view.setTag(viewHolder);
        } else {
            viewHolder = (ListAdapterLAufgabeEinsicht.ViewHolder) view.getTag();
        }

        return view;
    }

    private static class ViewHolder {
        TextView name;
        TextView date;
        TextView title;
    }
}
