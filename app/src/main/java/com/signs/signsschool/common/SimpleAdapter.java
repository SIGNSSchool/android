package com.signs.signsschool.common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.signs.signsschool.R;

import java.util.ArrayList;
import java.util.List;

public class SimpleAdapter extends ArrayAdapter<Simple> {
    private final Context activityContext;
    private final List<Simple> list;
    public ArrayList<String> choices = new ArrayList<>();

    public SimpleAdapter(Context context, List<Simple> list) {
        super(context, R.layout.simple_row, list);
        this.activityContext = context;
        this.list = list;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {

        final SimpleAdapter.ViewHolder viewHolder;

        if (view == null) {
            view = LayoutInflater.from(activityContext).inflate(R.layout.simple_row, null);
            viewHolder = new SimpleAdapter.ViewHolder();

            viewHolder.item = view.findViewById(R.id.simple_item);

            viewHolder.item.setText(list.get(position).getItem());

            view.setTag(viewHolder);
        } else {
            viewHolder = (SimpleAdapter.ViewHolder) view.getTag();
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choices.add(list.get(position).item);
            }
        });

        return view;
    }

    private static class ViewHolder {
        TextView item;
    }
}
