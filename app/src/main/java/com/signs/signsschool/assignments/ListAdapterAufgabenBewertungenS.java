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

public class ListAdapterAufgabenBewertungenS extends ArrayAdapter<ModelAufgabenBewertungenS> implements Filterable {

    private final Context activityContext;
    private final List<ModelAufgabenBewertungenS> list;

    public ListAdapterAufgabenBewertungenS(Context context, List<ModelAufgabenBewertungenS> list) {
        super(context, R.layout.aufgabenbewertungensrow, list);
        this.activityContext = context;
        this.list = list;
    }


    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {

        final ViewHolder viewHolder;

        if (view == null) {
            view = LayoutInflater.from(activityContext).inflate(R.layout.aufgabenbewertungensrow, null);
            viewHolder = new ListAdapterAufgabenBewertungenS.ViewHolder();

            viewHolder.course = view.findViewById(R.id.titleAufgabenBewertungenS);
            viewHolder.createdAt = view.findViewById(R.id.dateAufgabenBewertungenS);

            viewHolder.course.setText(list.get(position).getCourse());
            viewHolder.createdAt.setText(list.get(position).getCreatedAt());

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        return view;
    }

    private static class ViewHolder {
        TextView course;
        TextView createdAt;
    }
}
