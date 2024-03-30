package com.signs.signsschool.assignments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.signs.signsschool.R;

import java.util.List;

public class ListAdapterFach extends ArrayAdapter<ModelFach> {
    private final Context activityContext;
    private final List<ModelFach> list;

    public ListAdapterFach(Context context, List<ModelFach> list){
        super(context, R.layout.fachrow, list);
        this.activityContext = context;
        this.list = list;
    }


    @Override
    public View getView(final int position, View view, ViewGroup viewGroup){

        final ListAdapterFach.ViewHolder viewHolder;

        if (view == null) {
            view = LayoutInflater.from(activityContext).inflate(R.layout.fachrow, null);
            viewHolder = new ListAdapterFach.ViewHolder();

            viewHolder.day = view.findViewById(R.id.dayFach);
            viewHolder.date = view.findViewById(R.id.dateFach);

            viewHolder.day.setText(list.get(position).getDay());
            viewHolder.date.setText(list.get(position).getDate());

            view.setTag(viewHolder);
        } else {
            viewHolder = (ListAdapterFach.ViewHolder) view.getTag();
        }

        return view;
    }

    private static class ViewHolder {
        TextView day;
        TextView date;
    }
}