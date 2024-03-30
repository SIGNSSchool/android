package com.signs.signsschool.assignments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.signs.signsschool.R;

import java.util.List;

public class ListAdapterLearning extends ArrayAdapter<ModelLearning> {


    private final Context activityContext;
    private final List<ModelLearning> list;

    public ListAdapterLearning(Context context, List<ModelLearning> list){
        super(context, R.layout.learningrow, list);
        this.activityContext = context;
        this.list = list;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup){

        final ListAdapterLearning.ViewHolder viewHolder;

        if (view == null) {
            view = LayoutInflater.from(activityContext).inflate(R.layout.learningrow, null);
            viewHolder = new ListAdapterLearning.ViewHolder();

            viewHolder.subject = (TextView) view.findViewById(R.id.subjectLearning);
            viewHolder.image = (ImageView) view.findViewById(R.id.imageLearning);

            viewHolder.subject.setText(list.get(position).getSubject());
           // viewHolder.image.setImageDrawable(Drawable.createFromPath(list.get(position).getImage()));

            view.setTag(viewHolder);
        } else {
            viewHolder = (ListAdapterLearning.ViewHolder) view.getTag();
        }

        return view;
    }

    private static class ViewHolder {
        TextView subject;
        ImageView image;
    }
}
