package com.signs.signsschool.absences;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.signs.signsschool.R;
import com.signs.signsschool.absences.models.ModelAttendance;

import java.util.List;

public class AttendanceAdapter extends ArrayAdapter<ModelAttendance> {
    private final Context activityContext;
    private final List<ModelAttendance> list;

    public AttendanceAdapter(Context context, List<ModelAttendance> list){
        super(context, R.layout.attendancerow, list);
        this.activityContext = context;
        this.list = list;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup){

        final AttendanceAdapter.ViewHolder viewHolder;

        if (view == null) {
            view = LayoutInflater.from(activityContext).inflate(R.layout.attendancerow, null);
            viewHolder = new AttendanceAdapter.ViewHolder();

            viewHolder.name = (TextView) view.findViewById(R.id.nameAttendance);

            viewHolder.name.setText(list.get(position).getCoursname());

            view.setTag(viewHolder);
        } else {
            viewHolder = (AttendanceAdapter.ViewHolder) view.getTag();
        }

        return view;
    }

    private static class ViewHolder {

        TextView name;
    }
}
