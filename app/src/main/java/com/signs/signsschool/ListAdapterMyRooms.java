package com.signs.signsschool;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.signs.signsschool.models.Rooms;

import java.util.List;

public class ListAdapterMyRooms extends ArrayAdapter<Rooms> {

    private final Context activityContext;
    private final List<Rooms> list;
    public static final String TAG = "ListView";

    public ListAdapterMyRooms(Context context, List<Rooms> list){
        super(context, R.layout.myroomscell, list);
        this.activityContext = context;
        this.list = list;
    }


    @Override
    public View getView(final int position, View view, ViewGroup viewGroup){

        final ListAdapterMyRooms.ViewHolder viewHolder;

        if (view == null) {
            view = LayoutInflater.from(activityContext).inflate(R.layout.myroomscell, null);
            viewHolder = new ListAdapterMyRooms.ViewHolder();

            viewHolder.room = (TextView) view.findViewById(R.id.roomMyRooms);
            viewHolder.info = (TextView) view.findViewById(R.id.infoMyRooms);

            viewHolder.room.setText(list.get(position).getRoom());
            viewHolder.info.setText(list.get(position).getInfo());

            view.setTag(viewHolder);
        } else {
            viewHolder = (ListAdapterMyRooms.ViewHolder) view.getTag();
        }

        return view;
    }

    private static class ViewHolder {

        TextView room;
        TextView info;

    }
}
