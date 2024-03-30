package com.signs.signsschool.signs;

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

import java.util.ArrayList;
import java.util.List;


public class ListAdapterSigns extends ArrayAdapter<SignsModel> implements Filterable {

    private final Context activityContext;
    private List<SignsModel> arrayList;
    private List<SignsModel> filteredList;

    public ListAdapterSigns(Context context, ArrayList<SignsModel> list) {
        super(context, R.layout.custom_row_view, list);
        this.activityContext = context;
        this.arrayList = list;
        this.filteredList = list;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                filteredList = (ArrayList<SignsModel>) results.values;
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                ArrayList<SignsModel> FilteredArrList = new ArrayList<SignsModel>();

                if (arrayList == null) {
                    arrayList = new ArrayList<SignsModel>(arrayList);
                }

                if (constraint == null || constraint.length() == 0) {

                    results.count = arrayList.size();
                    results.values = arrayList;
                } else {
                    constraint = constraint.toString().toLowerCase();
                    for (int i = 0; i < arrayList.size(); i++) {
                        String needle = arrayList.get(i).getSender().getFirstName();

                        if (needle.toLowerCase().startsWith(constraint.toString())) {
                            Log.i("name", arrayList.get(i).getRecipient().getFirstName());
                            FilteredArrList.add(new SignsModel(arrayList.get(i).subject, arrayList.get(i).submitBy, arrayList.get(i).createdAt, arrayList.get(i).message, arrayList.get(i).schoolId, arrayList.get(i).recipientId, arrayList.get(i).recipient,arrayList.get(i).senderId, arrayList.get(i).sender, arrayList.get(i).courseId, arrayList.get(i).pk, arrayList.get(i).questions, arrayList.get(i).limitedQuestion));
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
            view = LayoutInflater.from(activityContext).inflate(R.layout.custom_row_view, null);
            viewHolder = new ViewHolder();

            viewHolder.name = view.findViewById(R.id.nameSIGNSEingang);
            viewHolder.subject = view.findViewById(R.id.subjectSIGNSEingang);
            viewHolder.date = view.findViewById(R.id.dateSIGNSEingang);

            String senderName = arrayList.get(position).getSender().getFirstName() + " "+ arrayList.get(position).getSender().getLastName();

            viewHolder.name.setText(senderName);
            viewHolder.subject.setText(arrayList.get(position).getSubject());
            viewHolder.date.setText(arrayList.get(position).getCreatedAt().substring(0, 10));

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        return view;
    }

    @Nullable
    @Override
    public SignsModel getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    private static class ViewHolder {
        TextView name;
        TextView subject;
        TextView date;
    }
}