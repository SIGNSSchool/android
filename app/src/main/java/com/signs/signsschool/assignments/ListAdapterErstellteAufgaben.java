package com.signs.signsschool.assignments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.TextView;

import com.signs.signsschool.R;

import java.util.List;

public class ListAdapterErstellteAufgaben extends ArrayAdapter<ModelErstellteAufgaben> implements Filterable {
    private final Context activityContext;
    private final List<ModelErstellteAufgaben> list;

    public ListAdapterErstellteAufgaben(Context context, List<ModelErstellteAufgaben> list){
        super(context, R.layout.erstellteaufgabenrow, list);
        this.activityContext = context;
        this.list = list;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup){

        final ListAdapterErstellteAufgaben.ViewHolder viewHolder;

        if (view == null) {
            view = LayoutInflater.from(activityContext).inflate(R.layout.erstellteaufgabenrow, null);
            viewHolder = new ListAdapterErstellteAufgaben.ViewHolder();

            viewHolder.name = (TextView) view.findViewById(R.id.nameErstellteAufgaben);
            viewHolder.subject = (TextView) view.findViewById(R.id.dayErstellteAufgaben);
            viewHolder.date = (TextView) view.findViewById(R.id.dateErstellteAufgaben);

            viewHolder.name.setText(list.get(position).getName());
            viewHolder.subject.setText(list.get(position).getSubject());
            viewHolder.date.setText(list.get(position).getDate());

            view.setTag(viewHolder);
        } else {
            viewHolder = (ListAdapterErstellteAufgaben.ViewHolder) view.getTag();
        }

        return view;
    }

    private static class ViewHolder {
        TextView name;
        TextView subject;
        TextView date;
    }
}
