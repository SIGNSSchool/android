package com.signs.signsschool.signs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.signs.signsschool.R;

import java.util.ArrayList;

public class ListAdapterSIGNSEinsicht extends ArrayAdapter<ModelSIGNSEinsicht> implements Filterable {

    private final Context activityContext;

    ArrayList<ModelSIGNSEinsicht> arrayList;
    ArrayList<ModelSIGNSEinsicht> filteredList;


    public ListAdapterSIGNSEinsicht(Context context, ArrayList<ModelSIGNSEinsicht> list) {
        super(context, R.layout.activity_signs_einsicht, list);
        this.activityContext = context;
        this.arrayList = list;
        this.filteredList = list;
    }


    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {


            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                filteredList = (ArrayList<ModelSIGNSEinsicht>) results.values;
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                ArrayList<ModelSIGNSEinsicht> FilteredArrList = new ArrayList<ModelSIGNSEinsicht>();

                if (arrayList == null) {
                    arrayList = new ArrayList<>(filteredList);
                }

                if (constraint == null || constraint.length() == 0) {

                    results.count = arrayList.size();
                    results.values = arrayList;
                } else {
                    constraint = constraint.toString().toLowerCase();
                    for (int i = 0; i < arrayList.size(); i++) {
                        String data = arrayList.get(i).getSender().getFirstName();

                        if (data.toLowerCase().startsWith(constraint.toString())) {
                            FilteredArrList.add(new ModelSIGNSEinsicht(arrayList.get(i).getSender(), arrayList.get(i).subject, arrayList.get(i).createdAt, arrayList.get(i).message, arrayList.get(i).getIsConfirmed(), arrayList.get(i).getChoices(), arrayList.get(i).getLimitedChoice()));
                        }
                    }

                    results.count = FilteredArrList.size();
                    results.values = FilteredArrList;
                }
                return results;
            }
        };
        return filter;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {

        final ViewHolder viewHolder;

        if (view == null) {
            view = LayoutInflater.from(activityContext).inflate(R.layout.signseinsichtrow, null);
            viewHolder = new ViewHolder();

            viewHolder.name = view.findViewById(R.id.nameSIGNSEinsicht);
            viewHolder.subject = view.findViewById(R.id.subjectSIGNSEinsicht);
            viewHolder.date = view.findViewById(R.id.dateSIGNSEinsicht);
            viewHolder.status = view.findViewById(R.id.statusSIGNSEinsicht);

            String senderName = filteredList.get(position).getSender().getFirstName() + " " + filteredList.get(position).getSender().getLastName();

            viewHolder.name.setText(senderName);
            viewHolder.subject.setText(filteredList.get(position).getSubject());
            viewHolder.date.setText(filteredList.get(position).getCreatedAt());

            String isConfirmed = filteredList.get(position).getIsConfirmed() ? "Bestätigt" : "Nicht bestätigt";

            viewHolder.status.setText(isConfirmed);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        return view;
    }

    @Override
    public int getCount() {
        return filteredList.size();
    }

    @Nullable
    @Override
    public ModelSIGNSEinsicht getItem(int position) {
        return filteredList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }


    private static class ViewHolder {

        TextView name;
        TextView subject;
        TextView date;
        TextView status;
    }
}
