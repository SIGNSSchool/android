package com.signs.signsschool.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.signs.signsschool.R;

import java.util.List;

public class NewsAdapter extends ArrayAdapter<ModelNews> {

    private final Context activityContext;
    private final List<ModelNews> list;

    public NewsAdapter(Context context, List<ModelNews> list){
        super(context, R.layout.newsrow, list);
        this.activityContext = context;
        this.list = list;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup){

        final NewsAdapter.ViewHolder viewHolder;

        if (view == null) {
            view = LayoutInflater.from(activityContext).inflate(R.layout.newsrow, null);
            viewHolder = new NewsAdapter.ViewHolder();

            viewHolder.title = (TextView) view.findViewById(R.id.titleNews);
            viewHolder.date = (TextView) view.findViewById(R.id.dateNews);
            viewHolder.description = (TextView) view.findViewById(R.id.descriptionNews);

            viewHolder.title.setText(list.get(position).getTitle());
            viewHolder.date.setText(list.get(position).getCreatedAt());
            viewHolder.description.setText(list.get(position).getDescription());

            view.setTag(viewHolder);
        } else {
            viewHolder = (NewsAdapter.ViewHolder) view.getTag();
        }

        return view;
    }

    static class ViewHolder {
        TextView title;
        TextView date;
        TextView description;
    }
}
