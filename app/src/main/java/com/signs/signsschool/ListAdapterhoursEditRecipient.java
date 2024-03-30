package com.signs.signsschool;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.signs.signsschool.models.ModelhoursEditRecipient;

import java.util.List;

public class ListAdapterhoursEditRecipient extends ArrayAdapter<ModelhoursEditRecipient> {

    private final Context activityContext;
    private final List<ModelhoursEditRecipient> list;
    public static final String TAG = "ListView";

    public ListAdapterhoursEditRecipient(Context context, List<ModelhoursEditRecipient> list){
        super(context, R.layout.hourseditrecipientrow, list);
        this.activityContext = context;
        this.list = list;
    }


    @Override
    public View getView(final int position, View view, ViewGroup viewGroup){

        final ListAdapterhoursEditRecipient.ViewHolder viewHolder;

        if (view == null) {
            view = LayoutInflater.from(activityContext).inflate(R.layout.hourseditrecipientrow, null);
            viewHolder = new ListAdapterhoursEditRecipient.ViewHolder();

            viewHolder.name = (TextView) view.findViewById(R.id.namehoursRecipient);
            viewHolder.id = (TextView) view.findViewById(R.id.idhoursRecipient);


            viewHolder.name.setText(list.get(position).getName());
            viewHolder.id.setText(String.valueOf(list.get(position).getId()));

            view.setTag(viewHolder);
        } else {
            viewHolder = (ListAdapterhoursEditRecipient.ViewHolder) view.getTag();
        }

        return view;
    }

    private static class ViewHolder {

        TextView name, id;
    }
}
