package com.signs.signsschool;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.signs.signsschool.models.Upcoming;

import java.util.List;

public class ListAdapterUpcoming extends ArrayAdapter<Upcoming> {

    private final Context activityContext;
    private final List<Upcoming> list;
    public static final String TAG = "ListView";

    public ListAdapterUpcoming(Context context, List<Upcoming> list){
        super(context, R.layout.upcomingcell, list);
        this.activityContext = context;
        this.list = list;
    }


    @Override
    public View getView(final int position, View view, ViewGroup viewGroup){

        final ListAdapterUpcoming.ViewHolder viewHolder;

        if (view == null) {
            view = LayoutInflater.from(activityContext).inflate(R.layout.upcomingcell, null);
            viewHolder = new ListAdapterUpcoming.ViewHolder();

            viewHolder.subject = (TextView) view.findViewById(R.id.subjectUpcoming);
            viewHolder.days = (TextView) view.findViewById(R.id.daysUpcoming);

            viewHolder.subject.setText(list.get(position).getTitle());
            viewHolder.days.setText(String.valueOf(list.get(position).getDate()));

            view.setTag(viewHolder);
        } else {
            viewHolder = (ListAdapterUpcoming.ViewHolder) view.getTag();
        }

        return view;
    }

    private static class ViewHolder {

        TextView subject;
        TextView days;

    }

}
