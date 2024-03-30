package com.signs.signsschool.absences;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.signs.signsschool.R;
import com.signs.signsschool.absences.models.Absence;

import java.util.ArrayList;
import java.util.List;


public class RecentAbsencesAdapter extends ArrayAdapter<Absence> implements Filterable {

    private final Context activityContext;
    public static final String TAG = "ListView";
    private List<Absence> arrayList;
    private List<Absence> filteredList;

    public RecentAbsencesAdapter(Context context, List<Absence> list) {
        super(context, R.layout.aktuellefehlstundenrow, list);
        this.activityContext = context;
        this.arrayList = list;
        this.filteredList = list;
    }



    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {

            @Override
            protected void publishResults(CharSequence constraint,FilterResults results) {

                filteredList = (ArrayList<Absence>) results.values;
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                ArrayList<Absence> FilteredArrList = new ArrayList<>();

                if (arrayList == null) {
                    arrayList = new ArrayList<Absence>(filteredList);
                }

                if (constraint == null || constraint.length() == 0) {

                    results.count = arrayList.size();
                    results.values = arrayList;
                } else {
                    constraint = constraint.toString().toLowerCase();
                    for (int i = 0; i < arrayList.size(); i++) {
                        String data = arrayList.get(i).getUser().getFirstName();

                        if (data.toLowerCase().startsWith(constraint.toString())) {
                            Log.i("name", arrayList.get(i).getUser().getFirstName());
                            FilteredArrList.add(new Absence(arrayList.get(i).getFromDate(), arrayList.get(i).getToDate(), arrayList.get(i).getCreatedAt(), arrayList.get(i).getUpdatedAt(), arrayList.get(i).getUserId(), arrayList.get(i).getGrade(), arrayList.get(i).getReason(), arrayList.get(i).getPk(), arrayList.get(i).getExcused(), arrayList.get(i).getUser()));
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

        final RecentAbsencesAdapter.ViewHolder viewHolder;

        if (view == null) {
            view = LayoutInflater.from(activityContext).inflate(R.layout.aktuellefehlstundenrow, null);
            viewHolder = new RecentAbsencesAdapter.ViewHolder();

            viewHolder.name = view.findViewById(R.id.nameAktuelleFehlstunden);
            viewHolder.from = view.findViewById(R.id.fromAktuelleFehlstunden);
            viewHolder.to = view.findViewById(R.id.toAktuelleFehlstunden);
            viewHolder.test = view.findViewById(R.id.testAktuelleFehlstunden);
            viewHolder.reason = view.findViewById(R.id.reasonAktuelleFehlstunden);
            viewHolder.date = view.findViewById(R.id.dateAktuelleFehlstunden);

            String name = filteredList.get(position).getUser().getFirstName() + " " + filteredList.get(position).getUser().getLastName();

            String isExcused = filteredList.get(position).getExcused() ? "Entschuldigt" : "Nicht Entschuldigt";

            viewHolder.name.setText(name);
            viewHolder.from.setText(filteredList.get(position).getFromDate());
            viewHolder.to.setText(filteredList.get(position).getToDate());
            viewHolder.test.setText(isExcused);
            viewHolder.reason.setText(filteredList.get(position).getReason());
            viewHolder.date.setText(filteredList.get(position).getCreatedAt());

            view.setTag(viewHolder);
        } else {
            viewHolder = (RecentAbsencesAdapter.ViewHolder) view.getTag();
        }

        return view;
    }

    @Nullable
    @Override
    public Absence getItem(int position) {
        return filteredList.get(position);
    }

    @Override
    public int getCount() {
        return filteredList.size();
    }

    private static class ViewHolder {

        TextView name;
        TextView from;
        TextView to;
        TextView test;
        TextView reason;
        TextView date;
    }
}
