package com.signs.signsschool.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.TextView;

import com.signs.signsschool.R;

import java.util.List;

public class CreatedNewsAdapter extends ArrayAdapter<ModelNews> implements Filterable {

    private final Context activityContext;
    private final List<ModelNews> list;

    public CreatedNewsAdapter(Context context, List<ModelNews> list) {
        super(context, R.layout.newserstellterow, list);
        this.activityContext = context;
        this.list = list;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {

        final CreatedNewsAdapter.ViewHolder viewHolder;

        if (view == null) {
            view = LayoutInflater.from(activityContext).inflate(R.layout.newserstellterow, null);
            viewHolder = new CreatedNewsAdapter.ViewHolder();

            viewHolder.title = view.findViewById(R.id.nameNEWSErstellte);
            viewHolder.date = view.findViewById(R.id.dateNEWSErstellte);

            viewHolder.title.setText(list.get(position).getTitle());
            viewHolder.date.setText(list.get(position).getCreatedAt());

            view.setTag(viewHolder);
        } else {
            viewHolder = (CreatedNewsAdapter.ViewHolder) view.getTag();
        }

        return view;
    }

    private static class ViewHolder {
        TextView title;
        TextView date;
    }
}
