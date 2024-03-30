package com.signs.signsschool;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.signs.signsschool.common.Simple;

import java.util.List;

public class ListAdapterKursStudents extends ArrayAdapter<Simple> {

    private final Context activityContext;
    private final List<Simple> list;

    public ListAdapterKursStudents(Context context, List<Simple> list){
        super(context, R.layout.kursstudentsrow, list);
        this.activityContext = context;
        this.list = list;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup){

        final ListAdapterKursStudents.ViewHolder viewHolder;

        if (view == null) {
            view = LayoutInflater.from(activityContext).inflate(R.layout.kursstudentsrow, null);
            viewHolder = new ListAdapterKursStudents.ViewHolder();

            viewHolder.name = (TextView) view.findViewById(R.id.nameKursStudents);

            viewHolder.name.setText(list.get(position).getItem());

            view.setTag(viewHolder);
        } else {
            viewHolder = (ListAdapterKursStudents.ViewHolder) view.getTag();
        }

        return view;
    }

    private static class ViewHolder {
        TextView name;
    }
}
