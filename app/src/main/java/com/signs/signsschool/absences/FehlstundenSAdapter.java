package com.signs.signsschool.absences;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.signs.signsschool.R;
import com.signs.signsschool.absences.models.ModelFehlstundenS;

import java.util.List;

public class FehlstundenSAdapter extends ArrayAdapter<ModelFehlstundenS> implements Filterable {

    private final Context activityContext;
    private final List<ModelFehlstundenS> list;

    public FehlstundenSAdapter(Context context, List<ModelFehlstundenS> list) {
        super(context, R.layout.fehlstundensrow, list);
        this.activityContext = context;
        this.list = list;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {

        final ViewHolder viewHolder;

        if (view == null) {
            view = LayoutInflater.from(activityContext).inflate(R.layout.fehlstundensrow, null);
            viewHolder = new FehlstundenSAdapter.ViewHolder();

            viewHolder.reason = view.findViewById(R.id.dayFehlstundenS);
            viewHolder.createdAt = view.findViewById(R.id.dateFehlstundenS);
            viewHolder.isExcused = view.findViewById(R.id.hourFehlstundenS);

            viewHolder.reason.setText(list.get(position).getReason());
            viewHolder.createdAt.setText(list.get(position).getCreatedAt().substring(0,10));
            viewHolder.isExcused.setText(list.get(position).getIsExcused() ? "Entschuldigt" : "Nicht entschuldigt");

            view.setTag(viewHolder);
        } else {
            viewHolder = (FehlstundenSAdapter.ViewHolder) view.getTag();
        }

        return view;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public ModelFehlstundenS getItem(int position) {
        return list.get(position);
    }

    private static class ViewHolder {

        TextView reason;
        TextView createdAt;
        TextView isExcused;
    }
}