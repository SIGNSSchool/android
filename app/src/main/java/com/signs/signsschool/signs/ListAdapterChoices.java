package com.signs.signsschool.signs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.signs.signsschool.R;
import com.signs.signsschool.models.Choice;

import java.util.List;

public class ListAdapterChoices extends ArrayAdapter<Choice> {


    private final Context activityContext;
    private final List<Choice> list;

    public ListAdapterChoices(Context context, List<Choice> list) {
        super(context, R.layout.kurserow, list);
        this.activityContext = context;
        this.list = list;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {

        final ViewHolder viewHolder;

        if (view == null) {
            view = LayoutInflater.from(activityContext).inflate(R.layout.kurserow, null);
            viewHolder = new ViewHolder();

            viewHolder.question = view.findViewById(R.id.nameKurse);
            viewHolder.answer = view.findViewById(R.id.subjectKurse);

            viewHolder.question.setText(list.get(position).getQuestion());
            viewHolder.answer.setText(list.get(position).getAnswer());

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        return view;
    }

    private static class ViewHolder {

        TextView question;
        TextView answer;
    }

}
