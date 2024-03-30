package com.signs.signsschool;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.signs.signsschool.models.Course;

import java.util.List;

public class CoursesAdapter extends ArrayAdapter<Course>  {


    private final Context activityContext;
    private final List<Course> list;

    public CoursesAdapter(Context context, List<Course> list){
        super(context, R.layout.kurserow, list);
        this.activityContext = context;
        this.list = list;
    }


    @Override
    public View getView(final int position, View view, ViewGroup viewGroup){

        final CoursesAdapter.ViewHolder viewHolder;

        if (view == null) {
            view = LayoutInflater.from(activityContext).inflate(R.layout.kurserow, null);
            viewHolder = new CoursesAdapter.ViewHolder();

            viewHolder.course = (TextView) view.findViewById(R.id.nameKurse);
            viewHolder.teacher = (TextView) view.findViewById(R.id.teacherKurse);
            viewHolder.grade = (TextView) view.findViewById(R.id.courseIDKurse);

            String name = list.get(position).getTeacher().getFirstName() + " " + list.get(position).getTeacher().getLastName();
            String grade = list.get(position).getGrade() + ". Jahrgang";

            viewHolder.course.setText(list.get(position).getCourse());
            viewHolder.teacher.setText(name);
            viewHolder.grade.setText(grade);

            view.setTag(viewHolder);
        } else {
            viewHolder = (CoursesAdapter.ViewHolder) view.getTag();
        }

        return view;
    }

    private static class ViewHolder {
        TextView course;
        TextView teacher;
        TextView grade;
    }
}
