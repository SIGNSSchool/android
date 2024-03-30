package com.signs.signsschool;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.signs.signsschool.models.ModelhoursEdit;

import java.util.List;

public class ListAdapterhoursEdit extends ArrayAdapter<ModelhoursEdit> {


    private final Context activityContext;
    private final List<ModelhoursEdit> list;
    public static final String TAG = "ListView";


    public ListAdapterhoursEdit(Context context, List<ModelhoursEdit> list){
        super(context, R.layout.hourseditrow, list);
        this.activityContext = context;
        this.list = list;
    }


    @Override
    public View getView(final int position, View view, ViewGroup viewGroup){

        final ListAdapterhoursEdit.ViewHolder viewHolder;

        if (view == null) {
            view = LayoutInflater.from(activityContext).inflate(R.layout.hourseditrow, null);
            viewHolder = new ListAdapterhoursEdit.ViewHolder();

            viewHolder.teacher = view.findViewById(R.id.teacherHoursEdit);
            viewHolder.date = view.findViewById(R.id.dateHoursEdit);
            viewHolder.hour = view.findViewById(R.id.hourHoursEdit);
            viewHolder.grade = view.findViewById(R.id.gradeHoursEdit);
            viewHolder.course = view.findViewById(R.id.courseHoursEdit);

            viewHolder.hour.setText(list.get(position).getHour());
            viewHolder.date.setText(list.get(position).getDate());
            viewHolder.teacher.setText(list.get(position).getTeacher());
            viewHolder.grade.setText(list.get(position).getGrade());
            viewHolder.course.setText(list.get(position).getCourse());

            view.setTag(viewHolder);
        } else {
            viewHolder = (ListAdapterhoursEdit.ViewHolder) view.getTag();
        }

        return view;
    }

    private static class ViewHolder {

        TextView teacher;
        TextView hour;
        TextView date;
        TextView grade;
        TextView course;

    }

}
