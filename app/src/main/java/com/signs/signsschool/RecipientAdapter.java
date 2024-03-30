package com.signs.signsschool;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.signs.signsschool.models.User;

import java.util.List;

public class RecipientAdapter extends ArrayAdapter<User> {

    private final Context activityContext;
    private final List<User> list;

    public RecipientAdapter(Context context, List<User> list){
        super(context, R.layout.recipientrow, list);
        this.activityContext = context;
        this.list = list;
    }

    @Nullable
    @Override
    public User getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup){

        final RecipientAdapter.ViewHolder viewHolder;

        if (view == null) {
            view = LayoutInflater.from(activityContext).inflate(R.layout.recipientrow, null);
            viewHolder = new RecipientAdapter.ViewHolder();

            viewHolder.name = (TextView) view.findViewById(R.id.nameRecipient);
            viewHolder.grade = (TextView) view.findViewById(R.id.gradeRecipient);

            String name = list.get(position).getFirstName() + " " + list.get(position).getLastName();

            viewHolder.name.setText(name);
           // viewHolder.grade.setText(list.get(position).getGrade());

            view.setTag(viewHolder);
        } else {
            viewHolder = (RecipientAdapter.ViewHolder) view.getTag();
        }

        return view;
    }

    private static class ViewHolder {
        TextView name, grade;
    }
}
